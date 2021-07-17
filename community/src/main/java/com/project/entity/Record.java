package com.project.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 
 * @version V1.0
 * @date 
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("reord")
public class Record extends BaseEntity implements Serializable {
    @TableId
    private String id;
    private String userid;
    private String reuserid;
    private String username;
    private String reusername;
    private String fuhao;
    private Integer type;

    private double money;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date createtime;
}