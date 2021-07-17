package com.project.controller;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.common.aop.annotation.DataScope;
import com.project.common.utils.DataResult;
import com.project.entity.User;
import com.project.mapper.TUserMapper;
import com.project.service.TUserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * 消息管理
 *
 * @author 
 * @version V1.0
 * @date 
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    @Resource
    private TUserMapper noticeMapper;
    @Resource
    private TUserService noticeService;

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public DataResult add(@RequestBody User sysContent) {
    	QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
    	String phone = sysContent.getPhone();
		queryWrapper.eq("phone",phone);
    	User selectOne = noticeMapper.selectOne(queryWrapper);
    	if(selectOne != null) {
    		return DataResult.fail("当前账号已被注册");
    	}
    	
    	sysContent.setId(UUID.randomUUID().toString());
    	sysContent.setMoney(0.0);
    	noticeMapper.insert(sysContent);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids) {
    	noticeService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("/update")
    public DataResult update(@RequestBody User sysContent) {
    	noticeMapper.updateById(sysContent);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("/listByPage")
    @DataScope
    public DataResult findListByPage(@RequestBody User sysContent) {
        Page page = new Page(sysContent.getPage(), sysContent.getLimit());
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        if (!StringUtils.isEmpty(sysContent.getUsername())) {
            queryWrapper.like(User::getUsername, sysContent.getUsername());
        }
        IPage<User> iPage = noticeService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
}
