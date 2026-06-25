package com.cqust.styletransfer.dao;

import lombok.Data;

import java.io.Serializable;

@Data
public class FileInfo implements Serializable {

    private Integer id;//主键

    private String  title;//标题

    private String uploadDate;//上传时间

    private String imageName;//图片名称


}
