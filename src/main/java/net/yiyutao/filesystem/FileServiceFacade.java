package net.yiyutao.filesystem;

import net.yiyutao.filesystem.api.IFileService;
import net.yiyutao.filesystem.api.IFileServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: masterYI
 * @date: 2017/11/29
 * @time: 10:40
 * Description:
 */
@Service
public class FileServiceFacade implements IFileServiceFacade {

    @Autowired
    private IFileService fileService;

    @Override
    public String upload(String localFilePath, String fileName, ServiceProviderEnum serviceProviderEnum) {
        this.fileService = serviceProviderEnum.getUploadService();
        String fileUrl = fileService.upload(localFilePath, fileName);
        return fileUrl;
    }

}
