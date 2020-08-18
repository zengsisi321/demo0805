package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表
 * @author 99200
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`user`")
public class User {

    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     *用户头像
     */
    @TableField(value = "user_portrait")
    private String userPortrait;

    /**
     * 用户昵称
     */
    @TableField(value = "user_account")
    private String userAccount;

    /**
     * 用户手机号
     */
    @TableField(value = "user_phone")
    private String userPhone;

    /**
     * 用户密码
     */
    @TableField(value = "user_password")
    private String userPassword;

    /**
     * 用户邮箱
     */
    @TableField(value = "user_mail")
    private String userMail;

    /**
     * 注册时间
     */
    @TableField(value = "user_time")
    private Date userTime;

    /**
     * 是否冻结 0否 1是
     */
    @TableField(value="user_delete")
    private Integer userDelete;


    /**
     * token
     */
    @TableField(exist = false)
    private String token;
}