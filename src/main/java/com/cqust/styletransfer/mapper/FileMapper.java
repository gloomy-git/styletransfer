package com.cqust.styletransfer.mapper;

//创建一个FileMapper，提供将文件信息添加到数据库，以及查询数据库中所有文件信息方法

import com.cqust.styletransfer.dao.FileInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileMapper {

    /**
     * 添加操作
     * @param title
     * @param uploadDate
     * @param imageName
     * @return
     */
    @Insert("INSERT INTO image(title,upload_date,image_name)VALUES(#{title},#{uploadDate},#{imageName})")
    public int insert(@Param("title")String title,@Param("uploadDate")String uploadDate,@Param("imageName")String imageName);

    /**
     * 查询操作
     * @return
     */
    @Select("SELECT id,title,upload_date,image_name from image")
    public List<FileInfo> findAllfile();
}
