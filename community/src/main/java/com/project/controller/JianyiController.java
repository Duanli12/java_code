package com.project.controller;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.common.aop.annotation.DataScope;
import com.project.common.utils.DataResult;
import com.project.entity.Suggest;
import com.project.entity.User;
import com.project.mapper.TCourseMapper;
import com.project.mapper.TUserMapper;
import com.project.service.TCourseService;

import io.swagger.annotations.ApiOperation;


/**
 * 消息管理
 *
 * @author 
 * @version V1.0
 * @date 
 */
@RestController
@RequestMapping("/jianyi")
public class JianyiController {
    @Resource
    private TCourseMapper noticeMapper;
    @Resource
    private TCourseService noticeService;
    @Autowired
    TUserMapper tUserMapper;

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public DataResult add(@RequestBody Suggest sysContent) {
    	sysContent.setId(UUID.randomUUID().toString());
    	noticeMapper.insert(sysContent);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("/listByPage")
    @DataScope
    public DataResult findListByPage(@RequestBody Suggest sysContent) {
        Page page = new Page(sysContent.getPage(), sysContent.getLimit());
        LambdaQueryWrapper<Suggest> queryWrapper = Wrappers.lambdaQuery();
        IPage<Suggest> iPage = noticeService.page(page, queryWrapper);
        List<Suggest> records = iPage.getRecords();
        if(records!=null && !records.isEmpty()) {
        	for (Suggest tSign : records) {
        		
        		User u1 = tUserMapper.selectById(tSign.getUserid());
        		tSign.setUsername(u1.getUsername());
			}
        }
        return DataResult.success(iPage);
    }
    
}
