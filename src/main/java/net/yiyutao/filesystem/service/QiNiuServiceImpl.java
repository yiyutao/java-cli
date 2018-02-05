package net.yiyutao.filesystem.service;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import net.yiyutao.filesystem.api.IFileService;
import net.yiyutao.utils.LoggerUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author: masterYI
 * @date: 2017/11/29
 * @time: 10:46
 * Description:
 */
@Service
public class QiNiuServiceImpl implements IFileService {

    @Value("${accessKey}")
    private String accessKey;

    @Value("${secretKey}")
    private String secretKey;

    @Value("${bucket}")
    private String bucket;

    @Value("${fileUrl}")
    private String fileUrl;

    /**
     * 七牛云简单上传
     *
     * @param localFilePath 本地文件路径
     * @param filename      上传到七牛云的文件名
     * @return 七牛云文件访问路径
     */
    @Override
    public String upload(String localFilePath, String filename) {

        String url;

        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        Response response;
        try {
            response = uploadManager.put(localFilePath, filename, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            url = fileUrl + "/" + filename;
        } catch (QiniuException e) {
            LoggerUtils.error("七牛云上文文件", e.getMessage(), QiNiuServiceImpl.class);
            url = "";
        }
        return url;
    }

}
