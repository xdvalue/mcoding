package com.mcoding.base.ui.bean.attachment;

import java.io.Serializable;
import java.util.Date;

public class Attachment implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final String SEPARATOR = "/";
	
	public static final int FILE_SAVE_DES_LOCAL = 1;
	public static final int FILE_SAVE_DES_CDN = 2;
	public static final int FILE_SAVE_DES_REMOTE = 3;

	private Integer id;

    private String filePath;

    private String fileName;

    private String fileRename;

    private String fileSuffix;

    private Double fileSize;

    private Date createTime;

    private String fileUrl;

    private Integer fileSaveDes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFileRename() {
        return fileRename;
    }

    public void setFileRename(String fileRename) {
        this.fileRename = fileRename == null ? null : fileRename.trim();
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix == null ? null : fileSuffix.trim();
    }

    public Double getFileSize() {
        return fileSize;
    }

    public void setFileSize(Double fileSize) {
        this.fileSize = fileSize;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    public Integer getFileSaveDes() {
        return fileSaveDes;
    }

    public void setFileSaveDes(Integer fileSaveDes) {
        this.fileSaveDes = fileSaveDes;
    }
}