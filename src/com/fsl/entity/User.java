package com.fsl.entity;

public class User {
    private Integer id;
    private String  userName;
    private String  passWord;
    private Integer fileId;
    
    private File    file;
    
    
    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassWord() {
        return passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public Integer getFileId() {
        return fileId;
    }
    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }
    
    @Override
    public String toString() {
        return "User [id=" + id + ", userName=" + userName + ", passWord=" + passWord + ", fileId=" + fileId + ", file=" + file + "]";
    }

}
