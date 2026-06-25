package com.cqust.styletransfer.service;

import com.cqust.styletransfer.dao.FileInfo;
import com.cqust.styletransfer.mapper.FileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class FileService {
    private  static final Logger LOGGER= LoggerFactory.getLogger(FileService.class);

    @Resource
    FileMapper fileMapper;

    /**
     * 文件上传
     * @param image
     * @param title
     * @return
     * @throws Exception
     */
    @Transactional
    public String uploadFile(MultipartFile image,String title)throws Exception{

        File imagePath;//图片存放地址
        String path = System.getProperty("user.dir");//获取项目相对路径
        imagePath = new File(path+"/src//main/resources/static/file");

        //判断文件夹是否存在
        if(!imagePath.exists()){
            //不存在，创建
            imagePath.mkdirs();
        }
        //获取文件名称
        String imageName=image.getOriginalFilename();
        //创建文件存放地址
        File resultPath= new File(imagePath+"/"+imageName);

        if(resultPath.exists()){
            LOGGER.warn("图片已经存在！");
            return "false";
        }
        //创建上传时间
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        image.transferTo(resultPath);
       //插入数据库
        fileMapper.insert(title,simpleDateFormat.format(new Date()),imageName);
        //输出信息
        System.out.println("imageResultPath:"+resultPath.getCanonicalPath());

        return  "true";
    }

    /**
     * 查询
     * @return
     */
    @Transactional
    public List<FileInfo>getAllFile(){
        return fileMapper.findAllfile();
    }

}
