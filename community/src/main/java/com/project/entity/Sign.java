package com.project.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 
 * @version V1.0
 * @date 
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sign")
public class Sign extends BaseEntity implements Serializable {
    @TableId
    private String id;

    private String courseid;

    private String userid;

   /* private String dianhua;*/

    private String username;
    private Integer age;
    private String sex;
    private String address;
    private String phone;
    private String fabuliuyan;
    private String jieshouliuyan;

    @TableField(fill = FieldFill.INSERT)
    private Date createtime;
    
    private String num;
    
    private Integer status;
    
    @TableField(exist = false)
    private String xuqiuName;
    @TableField(exist = false)
    private String xuqiuContent;
    @TableField(exist = false)
    private String fabuUserName;
    @TableField(exist = false)
    private String jieshouUserName;
    @TableField(exist = false)
    private double money;


}