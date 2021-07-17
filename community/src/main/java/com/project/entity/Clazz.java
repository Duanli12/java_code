package com.project.entity;

import java.io.Serializable;

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
@TableName("clazz")
public class Clazz extends BaseEntity implements Serializable {
    @TableId
    private String keyname;
    
    private String id;

    private double name;

}