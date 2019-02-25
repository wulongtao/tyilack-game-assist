package com.tyilack.assist.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author 小小黑
 */
@Slf4j
public class UploadFile implements Serializable {
    /**
     * 子目录名称
     */
    private static final String SUB_DIRECTORY = "uploads";
    private MultipartFile file;
    private String filePath;

    public UploadFile() {
    }

    public UploadFile(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public FileInfo save(String parentPath) {
        return save(parentPath, "");
    }

    public FileInfo save(String parentPath, String filePrefix) {
        String datePath = File.separator + DateTimeUtil.getDateNow() + File.separator;
        //拼接子目录
        StringBuilder sb = new StringBuilder();
        sb.append(parentPath);
        sb.append(datePath);

        String path = sb.toString();
        log.info("Upload file path = " + path);

        String filename = file.getOriginalFilename();

        String randomName = filePrefix + UUID.randomUUID().toString().replaceAll("-", "_");
        filename = randomName  +  filename.substring(filename.lastIndexOf('.'));

        File savedFile = new File(path, filename);
        if (!savedFile.getParentFile().exists()) {
            savedFile.getParentFile().mkdirs();
        }

        this.filePath = path + File.separator + filename;
        log.info("dest path = " + this.filePath);

        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileAbsPath(this.filePath);
        fileInfo.setRelativePath(datePath + filename);
        fileInfo.setFileName(randomName);

        try {
            file.transferTo(new File(this.filePath));
        } catch (IOException e) {
            this.filePath = null;
            return null;
        }

        log.info("Upload Success");
        return fileInfo;
    }

    public FileInfo save(boolean autoCreateName) {
        //获取跟目录
        File path = new File(SpringUtil.getWebappRootPath());
        if(!path.exists()) {
            path = new File("");
        }

        //目录拼上日期
        String extPath = "uploads" + File.separator + DateTimeUtil.getDateNow() + File.separator;
        File upload = new File(path.getAbsolutePath(),extPath);
        if(!upload.exists()) {
            upload.mkdirs();
        }
        log.info("上传目录路径： {}", upload.getAbsolutePath());

        String filename = file.getOriginalFilename();
        if (autoCreateName) {
            filename = UUID.randomUUID().toString() +  filename.substring(filename.lastIndexOf('.'));
        }


        this.filePath = upload.getAbsolutePath() + File.separator + filename;
        String relativePath = extPath + filename;
        log.info("上传路径 ：{} ", this.filePath);


        FileInfo fileInfo;

        try {
            file.transferTo(new File(this.filePath));

            fileInfo = new FileInfo();
            fileInfo.setFileAbsPath(this.filePath);
            fileInfo.setRelativePath(relativePath);
            fileInfo.setFileName(filename);
        } catch (IOException e) {
            log.error("上传文件失败", e);
            this.filePath = null;
            return null;
        }

        log.info("上传文件成功");
        return fileInfo;
    }
}
