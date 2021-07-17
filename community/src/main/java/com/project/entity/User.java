package com.project.entity;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.apache.poi.hpsf.Decimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户
 *
 * @author 
 * @version V1.0
 * @date 
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("user")
public class User extends BaseEntity implements Serializable {
    @TableId
    private String id;

    @NotBlank(message = "账号不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    private String phone;

    private Integer age;

    private String sex;
    private String img;
    private String idcard;

    
    private Double money;

}