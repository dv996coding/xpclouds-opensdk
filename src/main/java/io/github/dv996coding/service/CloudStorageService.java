package io.github.dv996coding.service;

import io.github.dv996coding.config.OssAutoConfiguration;
import io.github.dv996coding.config.OssProperties;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author 984199774@qq.com
 */
public abstract class CloudStorageService {
    /**
     * 云存储配置信息
     */
    @Resource
    protected OssAutoConfiguration client;

    public OssProperties getOssProperties() {
        return this.client.getOssProperties();
    }

    /**
     * 文件路径
     *
     * @param prefix    前缀
     * @param subPrefix 子前缀
     * @param suffix    后缀
     * @return 返回上传路径
     */
    public String getPath(String prefix, String subPrefix, String suffix) {
        // 生成uuid
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // 文件路径
        String path = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/" + uuid;
        if (StringUtils.isNotBlank(prefix)) {
            if (StringUtils.isNotBlank(subPrefix)) {
                path = subPrefix + "/" + path;
            }
            path = prefix + "/" + path;
        }
        return path + suffix;
    }

    /**
     * 文件上传
     *
     * @param data 文件字节数组
     * @param path 文件路径，包含文件名
     * @return 返回http地址
     */
    public abstract String upload(byte[] data, String path);

    /**
     * 文件上传
     *
     * @param data   文件字节数组
     * @param suffix 后缀
     * @return 返回http地址
     */
    public abstract String uploadSuffix(byte[] data, String suffix);

    /**
     * 文件上传
     *
     * @param inputStream 字节流
     * @param path        文件路径，包含文件名
     * @return 返回http地址
     */
    public abstract String upload(InputStream inputStream, String path);

    /**
     * 删除文件
     *
     * @param objName - 对象名称
     */
    public abstract void deleteObject(String objName);
}
