package com.tyilack.assist.util;

/**
 *
 * @author wulongtao
 * @date 2018/4/25
 */
public class FileInfo {
    private String fileName;
    private String relativePath;
    private String fileAbsPath;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileAbsPath() {
        return fileAbsPath;
    }

    public void setFileAbsPath(String fileAbsPath) {
        this.fileAbsPath = fileAbsPath;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "fileName='" + fileName + '\'' +
                ", fileAbsPath='" + fileAbsPath + '\'' +
                ", relativePath='" + relativePath + '\'' +
                '}';
    }
}
