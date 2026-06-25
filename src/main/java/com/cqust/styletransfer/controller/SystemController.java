package com.cqust.styletransfer.controller;

import com.cqust.styletransfer.dao.FileInfo;
import com.cqust.styletransfer.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SystemController {

    @Resource
    private FileService fileService;

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("register")
    public String register(){
        return "register";
    }

    @GetMapping("demo")
    public String demo(){
        return "demo";
    }

    @GetMapping("show")
    public String show(){
        return "show";
    }

    @GetMapping("getAllFile")
    public String getAllFile(HttpServletRequest request){
        List<FileInfo> allFile = fileService.getAllFile();
        for (FileInfo fileInfo : allFile) {
            System.out.println(fileInfo.getImageName());
        }
        request.setAttribute("fileList", allFile);
        return "getAllFile";
    }

    @GetMapping("uploadFile")
    public String uploadFile(){
        return "uploadFile";
    }

    @GetMapping("know")
    public String konw(){
        return "know";
    }
}