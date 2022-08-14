package io.github.dv996coding.util;

import com.aliyuncs.utils.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * 基于 apache httpClient4.5 的HTTP工具类
 *
 * @author 984199774@qq.com
 */
public final class HttpClientUtil {
    private static final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);
    /**
     * 全局连接池对象
     */
    private static final PoolingHttpClientConnectionManager CONNECTION_MANAGER = new PoolingHttpClientConnectionManager();

    static {
        // 设置最大连接数
        CONNECTION_MANAGER.setMaxTotal(200);
        // 设置每个连接的路由数
        CONNECTION_MANAGER.setDefaultMaxPerRoute(20);
    }

    /**
     * 获取Http客户端连接对象
     *
     * @param timeOut 超时时间 ms
     * @return Http客户端连接对象
     */
    public static CloseableHttpClient getHttpClient(Integer timeOut) {
        RequestConfig requestConfig = RequestConfig.custom()
                // 获取连接超时时间
                .setConnectionRequestTimeout(timeOut)
                // 请求超时时间
                .setConnectTimeout(timeOut)
                // 响应超时时间
                .setSocketTimeout(timeOut)
                .build();
        HttpRequestRetryHandler retryHandler = (exception, executionCount, context) -> {
            if (executionCount >= Constant.MAX_RETRY_TIME) {
                // 如果已经重试了3次，就放弃
                return false;
            }
            if (exception instanceof NoHttpResponseException) {
                // 如果服务器丢掉了连接，那么就重试
                return true;
            }
            if (exception instanceof SSLHandshakeException) {
                // 不要重试SSL握手异常
                return false;
            }
            if (exception instanceof InterruptedIOException) {
                // 超时
                return true;
            }
            if (exception instanceof UnknownHostException) {
                return false;
            }
            if (exception instanceof ConnectTimeoutException) {
                return false;
            }
            if ((exception instanceof SSLException)) {
                return false;
            }
            HttpClientContext clientContext = HttpClientContext.adapt(context);
            HttpRequest request = clientContext.getRequest();
            // 如果请求是幂等的，就再次尝试
            return !(request instanceof HttpEntityEnclosingRequest);
        };
        return HttpClients.custom()
                // 把请求相关的超时信息设置到连接客户端
                .setDefaultRequestConfig(requestConfig)
                // 把请求重试设置到连接客户端
                .setRetryHandler(retryHandler)
                .setConnectionManager(CONNECTION_MANAGER)
                .build();
    }

    public static String doPostJson(String url, String json) {
        return doPost(url, json, null);
    }

    public static String doPost(String url, String json, Map<String, String> headers) {
        CloseableHttpClient httpClient = getHttpClient(10000);
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            if (headers != null && headers.size() > 0) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
            }
            if (!StringUtils.isEmpty(json)) {
                StringEntity entity = new StringEntity(json, "UTF-8");
                entity.setContentEncoding("UTF-8");
                httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
                httpPost.setEntity(entity);
            }

            response = httpClient.execute(httpPost);
            if (response != null) {
                Integer status = response.getStatusLine().getStatusCode();
                if (status >= Constant.SUCCESS && status < Constant.STATUS) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else if (status.equals(Constant.LIMIT_REQUEST)) {
                    return "{\"code\":429,\"msg\":\"too manny requests\"}";
                } else if (status.equals(Constant.PARAM_ERROR)) {
                    return "{\"code\":400,\"msg\":\"Parameter request error\"}";
                } else {
                    log.warn("Unexpected response status: {}", status);
                }
            }
            return null;
        } catch (Exception e) {
            log.error("HttpPost Request Exception: {0}", e);
            try {
                httpPost.abort();
            } catch (Exception e1) {
                log.error("HttpPost Abort Exception: {0}", e1);
            }
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    log.error("HttpPost Response Close IO Exception: {0}", e);
                }
            }
        }
        return null;
    }

    @Deprecated
    public static String doPostJson(String url, String json, Map<String, String> headers) {
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(5000).setSocketTimeout(10000).build();
            // 为httpPost实例设置配置
            httpPost.setConfig(requestConfig);

            // 设置请求头
            httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
            if (headers != null && headers.size() > 0) {
                for (String key : headers.keySet()) {
                    httpPost.addHeader(key, headers.get(key));
                }
            }

            // 解决中文乱码问题
            StringEntity stringEntity = new StringEntity(json, "UTF-8");
            stringEntity.setContentEncoding("UTF-8");
            httpPost.setEntity(stringEntity);

            ResponseHandler<String> responseHandler = response -> {
                Integer status = response.getStatusLine().getStatusCode();
                if (status >= Constant.SUCCESS && status < Constant.STATUS) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else if (status.equals(Constant.LIMIT_REQUEST)) {
                    return "{\"code\":429,\"msg\":\"too manny requests\"}";
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
            return httpClient.execute(httpPost, responseHandler);
        } catch (Exception e) {
            System.out.println("请求异常");
            e.printStackTrace();
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    System.out.println("http 关闭请求异常");
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
