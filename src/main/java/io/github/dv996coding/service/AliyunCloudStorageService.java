package io.github.dv996coding.service;

import com.aliyun.oss.OSSClient;
import io.github.dv996coding.properties.OssProperties;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author 984199774@qq.com
 */
public class AliyunCloudStorageService extends CloudStorageService {
    /**
     * 云存储配置信息
     */
    private OssProperties properties;
    private OSSClient ossClient;

    public AliyunCloudStorageService(OssProperties properties, OSSClient ossClient) {
        this.properties = properties;
        this.ossClient = ossClient;
    }


    @Override
    public String upload(byte[] data, String path) {
        return upload(new ByteArrayInputStream(data), path);
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return upload(data, getPath(properties.getPrefix(), properties.getSubPrefix(), suffix));
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            ossClient.putObject(properties.getBucketName(), path, inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties.getBindDomain() + "/" + path;
    }

    @Override
    public void deleteObject(String objName) {
        try {
            ossClient.deleteObject(properties.getBucketName(), objName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
