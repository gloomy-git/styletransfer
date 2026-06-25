package com.cqust.styletransfer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.UUID;

@Controller
public class ImageController {

    @RequestMapping("/index")
    public String index(){
        return "index.html";
    }

    @RequestMapping("/demo")
    public String demo(){
        return "demo";
    }

    @RequestMapping("/getImage")
    @ResponseBody
    public String getImage(
            @RequestParam(value = "image") MultipartFile image,
            @RequestParam(value = "style")MultipartFile style) throws Exception {

        if(image.isEmpty() || style.isEmpty()){
            System.out.println("No image uploaded");
            return "index.html";
        }

        String imageName = UUID.randomUUID() + ".jpg";
        String styleName = UUID.randomUUID() + ".jpg";

        String filePath = "C:\\Deeplearning\\learn\\style_trans\\image\\";

        String imagePath = filePath + imageName;
        String stylePath = filePath + styleName;

        File destImage = new File(imagePath);
        File destStyle = new File(stylePath);

        image.transferTo(destImage);
        style.transferTo(destStyle);

        String aiPath = getAIImagePath(imagePath, stylePath);

        return aiPath;
    }

    public String getAIImagePath(String imagePath, String stylePath) throws Exception {

        Process proc;
        String[] args = new String[]{"F:\\Deeplearning\\Aanconda\\envs\\tf\\python","C:\\Deeplearning\\learn\\style_trans\\main.py",imagePath,stylePath};

        proc = Runtime.getRuntime().exec(args);
        new Thread(new SyncPipe(proc.getErrorStream(),System.err)).start();
        new Thread(new SyncPipe(proc.getInputStream(), System.out)).start();

        System.out.println(imagePath);
        System.out.println(stylePath);

        PrintWriter stdin = new PrintWriter(proc.getOutputStream());
        stdin.println("python " + "C:\\Deeplearning\\learn\\style_trans\\main.py " + imagePath + " " + stylePath);
        stdin.close();

        BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line = null;
        String content = in.readLine();
        while ((line = in.readLine()) != null){
            content = line;
            char s = content.trim().charAt(0);
            if(s == 65279){
                if(content.length() > 1){
                    content = content.substring(1);
                }
            }
        }
        in.close();
        proc.waitFor();

        System.out.println("Generated image: " + content);
        return content;
    }
}