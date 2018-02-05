package net.yiyutao.conf;

import net.yiyutao.utils.LoggerUtils;
import net.yiyutao.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * @author: masterYI
 * @date: 2017/12/11
 * @time: 14:08
 * Description:初始化配置
 */
@Configuration
public class InitConfig {

    @Value("${localFileDirectory}")
    private String directory;

    @PostConstruct
    public void initFile() {
        if (StringUtils.isBlank(directory)) {
            return;
        }
        try {
            File file = new File(directory);
            if (!file.exists()) {
                boolean boo = file.mkdir();
                if (!boo) {
                    LoggerUtils.error("初始化", "初始化空文件夹{" + directory + "}失败，", this.getClass());
                }
            }
        } catch (Exception e) {
            LoggerUtils.error("初始化文件", e.getStackTrace(), this.getClass());
        }

    }


}
