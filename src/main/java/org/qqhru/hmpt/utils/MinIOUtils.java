package org.qqhru.hmpt.utils;

import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import io.minio.messages.SseConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@Component
public class MinIOUtils {

    private static final Logger log = LoggerFactory.getLogger(MinIOUtils.class);

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    private String secretKey;

    @Value("${minio.bucketName}")
    private String bucketName;

    private MinioClient minioClient;

    public MinioClient getMinioClient() {
        if (minioClient == null) {
            minioClient = MinioClient.builder()
                    .endpoint(endpoint)
                    .credentials(accessKey, secretKey)
                    .build();
        }
        return minioClient;
    }

    /**
     * 判断 bucket是否存在
     *
     * @param bucketName 桶名
     * @return
     */
    public boolean bucketExists(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return getMinioClient().bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    /**
     * 创建bucket
     *
     * @param bucketName 桶名
     */
    public void makeBucket(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        boolean isExist = bucketExists(bucketName);
        if (!isExist) {
            getMinioClient().makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    /**
     * 获取全部bucket
     */
    public List<Bucket> listBuckets() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return getMinioClient().listBuckets();
    }


    /**
     * 获取bucket的加密配置信息
     *
     * @param bucketName 桶名
     * @return ServerSideEncryptionConfiguration
     */
    public SseConfiguration getBucketEncryption(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return getMinioClient().getBucketEncryption(GetBucketEncryptionArgs.builder().bucket(bucketName).build());
    }


    /**
     * 删除bucket
     *
     * @param bucketName 桶名
     */
    public void removeBucket(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        boolean isExist = bucketExists(bucketName);
        if (isExist) {
            getMinioClient().removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
        }
    }

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    public String upload(MultipartFile file) {
        String fileUrl = null;
        try {
            makeBucket(bucketName);
            InputStream inputStream = file.getInputStream();
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(newFileName)
                    .stream(inputStream, file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build();
            getMinioClient().putObject(putObjectArgs);
            fileUrl = endpoint + "/" + bucketName + "/" + newFileName;
            log.info("文件上传成功：{}", fileUrl);
        } catch (Exception e) {
            log.error("文件上传失败", e);
        }
        return fileUrl;
    }
}