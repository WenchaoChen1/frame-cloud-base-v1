package com.gstdev.cloud.plugin.storage.core.service;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.gstdev.cloud.plugin.storage.core.model.StoreConfig;
//import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;


/**
 * 文件服务抽象类 description
 *
 */
public abstract class AbstractFileService {

    protected StoreConfig config;

    /**
     * 初始化文件服务配置
     *
     * @param config 存储配置
     * @return AbstractFileService
     */
    public AbstractFileService init(StoreConfig config) {
        this.config = config;
        return this;
    }

    /**
     * 构建文件下载的response
     */
    protected void buildResponse(HttpServletResponse response, byte[] data, String fileName) throws IOException {
        response.reset();
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.setContentType("multipart/form-data");
        response.addHeader("Content-Length", "" + data.length);
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setDateHeader("Expires", (System.currentTimeMillis() + 1000));
//        IOUtils.write(data, response.getOutputStream());
    }

    /**
     * 获取实际桶名称
     *
     * @param bucketName 桶名
     * @return 实际桶名
     */
    protected String getRealBucketName(String bucketName) {
        return StringUtils.isNotBlank(config.getBucketPrefix())
                ? String.format("%s-%s", config.getBucketPrefix(), bucketName)
                : bucketName;
    }

    /**
     * 根据文件url获取文件实际的ObjectKey
     *
     * @param url url
     * @return 文件Key
     */
//    protected String getFileKey(String bucketName, String url) {
//        String prefixUrl = getObjectPrefixUrl(bucketName);
//        try {
//            // 若url使用路径传参，则已经经过了decode操作
//            return URLDecoder.decode(url, BaseConstants.DEFAULT_CHARSET).substring(prefixUrl.length());
//        } catch (Exception e) {
//            return url.substring(prefixUrl.length());
//        }
//    }
}
