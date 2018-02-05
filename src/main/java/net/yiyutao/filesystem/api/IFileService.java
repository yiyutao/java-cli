package net.yiyutao.filesystem.api;

import org.springframework.stereotype.Service;

/**
 * @author: masterYI
 * @date: 2017/11/29
 * @time: 16:09
 * Description:
 */
@Service
public interface IFileService {

    /**
     * 上传文件
     *
     * @param localFilePath
     * @param filename
     * @return
     */
    String upload(String localFilePath, String filename);
}
