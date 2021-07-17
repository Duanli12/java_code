package com.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.entity.Clazz;
import com.project.mapper.TClazzMapper;
import com.project.service.TClazzService;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author 
 * @version V1.0
 * @date 
 */
@Service("clazzService")
@Slf4j
public class TClazzServiceImpl extends ServiceImpl<TClazzMapper, Clazz> implements TClazzService {

	@Autowired
	TClazzMapper clazzMapper;

	public JSONArray getType() {
		List<Clazz> list = clazzMapper.selectList(Wrappers.<Clazz>lambdaQuery());
		return JSONArray.parseArray(JSON.toJSONString(list));
	}
    
}
