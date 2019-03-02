package com.helper.util;

import com.helper.ConstantBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

/**
 * 文件工具
 * 可以存储文件到本地
 * 前提是必须有ConstantBean这个Bean存在
 * 因为这个Bean中存在文件路径
 * @author SouthLight
 */
@Component
@ConditionalOnBean(ConstantBean.class)
public class FileUtil {

    static ConstantBean constantConfig;
    @Autowired
    public  void setConstantConfig(ConstantBean constantConfig) {
        FileUtil.constantConfig = constantConfig;
    }

    private final static Logger log = LoggerFactory.getLogger(FileUtil.class);
    public static boolean saveFileToLocal(MultipartFile file, String folderName) {
        String path = constantConfig.static_file_path + folderName + "/";
        //获取文件名
        String fileName = file.getOriginalFilename();
        path += fileName;
        try {
            file.transferTo(new File(path));
        } catch (IOException e) {
            log.error("FileUtil saving file error!",e);
            return false;
        }
        return true;
    }

    public static void createFolder(String path) throws IOException{
        File file = new File(path);
        // 如果文件或者文件夹不存在，则创建
        if (!file.exists()){
            file.mkdir();
        }
    }
}
