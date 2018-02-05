package net.yiyutao.filesystem;

import net.yiyutao.common.ApplicationContextProvider;
import net.yiyutao.filesystem.api.IFileService;
import net.yiyutao.filesystem.service.QiNiuServiceImpl;

/**
 * @author: masterYI
 * @date: 2017/11/29
 * @time: 10:44
 * Description:云服务商，文件上传目标，如：文件服务器，七牛云CDN，阿里云CDN
 */
public enum ServiceProviderEnum {

    Local(ApplicationContextProvider.getBean(QiNiuServiceImpl.class)),
    QiNiu(ApplicationContextProvider.getBean(QiNiuServiceImpl.class));

    private IFileService fileService;

    ServiceProviderEnum(IFileService fileService) {
        this.fileService = fileService;
    }

    public IFileService getUploadService() {
        return fileService;
    }
}
