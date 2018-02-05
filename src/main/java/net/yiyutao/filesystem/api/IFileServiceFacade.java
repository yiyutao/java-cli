package net.yiyutao.filesystem.api;

import net.yiyutao.filesystem.ServiceProviderEnum;
import org.springframework.stereotype.Service;

/**
 * @author: masterYI
 * @date: 2017/11/29
 * @time: 10:42
 * Description:上传文件
 */
@Service
public interface IFileServiceFacade {


    /**
     * 上传文件
     *
     * @param localFilePath       本地文件路径
     * @param fileName            文件名称
     * @param serviceProviderEnum 上传文件目标
     * @return 文件访问路径
     */
    String upload(String localFilePath, String fileName, ServiceProviderEnum serviceProviderEnum);
}
