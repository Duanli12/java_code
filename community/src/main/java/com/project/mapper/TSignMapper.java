package com.project.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.entity.Sign;

/**
 *
 * @author 
 * @version V1.0
 * @date 
 */
public interface TSignMapper extends BaseMapper<Sign> {
	
	@Update("update sign set status=3 where courseid=#{courseid}")
	int updateStatus(String courseid);
	
	@Select("select * from sign where courseid=#{courseid} and (status=2 or status=4) ")
	Sign getOneByTwo(String courseid);
	
}