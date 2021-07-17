package com.project.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.poi.hpsf.Decimal;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 *
 * @author 
 * @version V1.0
 * @date 
 */
@Data
@TableName("demand")
public class FieldEntity extends BaseEntity  implements Serializable {
    @TableId
    private String id;

    private String userid;

    private String title;

    private String address;
    
    private String content;
    private String fenlei;
    private double money;
    
    private double timenum;
    
    
    @TableField(fill = FieldFill.INSERT)
    private Date createtime;
    private String jzsj;
    private Integer status;
    
    @TableField(exist = false)
    private String undertaker;
    @TableField(exist = false)
    private String fabutaker;
    @TableField(exist = false)
    private String fabuliuyan;
    @TableField(exist = false)
    private String jieshouliuyan;
    @TableField(exist = false)
    private String signid;

}