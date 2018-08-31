package com.leon.security.dto;

/**
 * @Auther: chongwang
 * @Date: 2018/5/17 23:04
 */
public class FileInfo {

    private String path;

    public FileInfo(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
