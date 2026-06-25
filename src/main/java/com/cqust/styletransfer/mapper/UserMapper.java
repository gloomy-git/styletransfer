package com.cqust.styletransfer.mapper;

import com.cqust.styletransfer.dao.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

//数据层：做数据的同步
@Repository
public interface UserMapper {

    /*
    * 新增账号
    * */
    @Insert("INSERT into table_users(email,password,salt,confirm_code,activation_time,is_valid)"+
            " VALUES (#{email},#{password},#{salt},#{confirmCode},#{activationTime},#{isValid})")
    int insertUser(User user);


    /*
    * 根据确认码查询用户
    * */
    @Select("SELECT email,activation_time from table_users where confirm_code=#{confirmCode} AND is_valid=0")
    User selectUserByConfirmCode(@Param("confirmCode") String confirmCode);


   /*
   *根据确认码查询用户并修改状态值为1(可用)
   */
    @Update("UPDATE table_users SET is_valid = 1 WHERE confirm_code=#{confirmCode}")
    int updateUserByConfirmCode(@Param("confirmCode") String confirmCode);


    /*
    * 根据邮箱查询用户
    * */
    @Select("SELECT email,password,salt FROM table_users WHERE email=#{email} AND is_valid=1")
    List<User> selectUserByEmail(@Param("email") String email);

}
