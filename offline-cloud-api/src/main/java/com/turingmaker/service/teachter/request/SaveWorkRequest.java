package com.turingmaker.service.teachter.request;

import java.io.Serializable;

/**
 * 保存作品的实体类
 * @author  tzj
 */
public class SaveWorkRequest implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = -6660013153269400376L;

	private String author;

    private Long userId;
    /**
     * 作品名称
     *
     */
     private String  workName;

    /**
     * 	作品描述
     */
   private String  description;

    /**
     * 	程序d代码
      */
    private String  programCode;

    /**
     * 作品id
     */
    private  Long id;


    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "SaveWorkRequest{" +
                "author='" + author + '\'' +
                ", userId=" + userId +
                ", workName='" + workName + '\'' +
                ", description='" + description + '\'' +
                ", programCode='" + programCode + '\'' +
                ", id=" + id +
                '}';
    }
}
