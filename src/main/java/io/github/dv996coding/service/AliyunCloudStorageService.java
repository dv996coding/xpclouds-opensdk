package io.github.dv996coding.service;

import com.aliyun.oss.OSSClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author 984199774@qq.com
 */
@ConditionalOnClass({OSSClient.class})
@Component
public class AliyunCloudStorageService extends CloudStorageService{

    @Override
    public String upload(byte[] data, String path) {
        return upload(new ByteArrayInputStream(data), path);
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return upload(data, getPath(getOssProperties().getPrefix(), getOssProperties().getSubPrefix(), suffix));
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            client.ossClientFactoryBean().getObject().putObject(getOssProperties().getBucketName(), path, inputStream);
        } catch (Exception e){
           e.printStackTrace();
        }
        return getOssProperties().getBindDomain() + "/" + path;
    }

    @Override
    public void deleteObject(String objName) {
        try {
            client.ossClientFactoryBean().getObject().deleteObject(getOssProperties().getBucketName(), objName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
