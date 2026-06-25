package com.cqust.styletransfer.controller;

import com.cqust.styletransfer.dao.User;
import com.cqust.styletransfer.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

//控制层：接受数据，返回
@RequestMapping("user")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    /*
    * 注册账号
    * */
   @PostMapping("create")
    public Map<String,Object>createAccount(User user){
       return userService.createAccount(user);
   }

    /**
     * 登录账号
     * @param user
     * @return
     */
   @PostMapping("login")
    public Map<String,Object>loginAccount(User user, HttpSession session){

       //登录时绑定session
       session.setAttribute("user",user.getEmail());

       return userService.loginAccount(user);
   }

    /**
     * 激活账号
     * @param confirmCode
     * @return
     */
   @RequestMapping("activation")
    public Map<String,Object>actionAccount(String confirmCode){
       return userService.activationAccount(confirmCode);
   }

    @RequestMapping("/getusername")
    @ResponseBody
    public String getUsername(HttpServletRequest request, HttpServletResponse response)throws Exception{
        //先从session中获取到之前存在session中的用户信息，然后通过ObjectMapper输出返回一个json数据给html页面，由页面去解析这个json数据
        String name =(String)request.getSession().getAttribute("user");
        //1.第一种方法，objectMapper.writeValue自动将数据格式转换为Json然后自动将Jason转发到浏览器，使用ajax进行局部刷新获取name，格式为json
            if(name!=null){
                ObjectMapper objectMapper=new ObjectMapper();
                objectMapper.writeValue(response.getOutputStream(),name);
            }

        //2.第二种方法，直接使用@ResponseBody+字符串，将字符串直接转发到浏览器，使用ajax进行局部刷新获取name，格式为text
        return name;
    }

}
