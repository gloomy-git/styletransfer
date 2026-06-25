package com.cqust.styletransfer.controller;

import com.cqust.styletransfer.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class FileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @Resource
    private FileService fileService;


    /**
     * 文件上传
     */
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("fileImage")MultipartFile fileImage,
                             @RequestParam("title")String title,HttpServletRequest request)  {

       if(fileImage.isEmpty()){
           LOGGER.error("上传失败，请选择文件");
           return "redirect:/getAllFile";
       }
        try {
            String  result = fileService.uploadFile(fileImage,title);
            return "redirect:/getAllFile";
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("文件上传失败");
            return "redirect:/getAllFile";
        }

    }

    /**
     * 查询所有
     * @param request
     * @return
     */
//    @RequestMapping("/getAllFile")
//    public String getAllFile(HttpServletRequest request){
//        LOGGER.info("进入 getAllFile！！！！");
//        List<FileInfo> allFile = fileService.getAllFile();
//        request.setAttribute("fileList", allFile);
//        LOGGER.debug(allFile.toString());
//        return "fileDownload";
//    }
}
