package com.project.controller;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.common.aop.annotation.DataScope;
import com.project.common.utils.DataResult;
import com.project.entity.Clazz;
import com.project.entity.FieldEntity;
import com.project.entity.Sign;
import com.project.entity.User;
import com.project.mapper.TClazzMapper;
import com.project.mapper.TFieldMapper;
import com.project.mapper.TSignMapper;
import com.project.mapper.TUserMapper;
import com.project.service.TClazzService;
import com.project.service.TSignService;

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
@RequestMapping("/clazz")
public class ClazzController {
    @Resource
    private TSignMapper noticeMapper;
    @Resource
    private TSignService noticeService;
    @Autowired
    TFieldMapper tFieldMapper;
    @Autowired
    TUserMapper tUserMapper;
    @Autowired
    TClazzService clazzService;
    @Autowired
    TClazzMapper clazzMapper;

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public DataResult add(@RequestBody Sign sysContent) {
    	sysContent.setId(UUID.randomUUID().toString());
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
    public DataResult update(@RequestBody Sign sysContent) {
    	Sign sg = noticeMapper.selectById(sysContent.getId());
    	
    	if(sysContent.getStatus() == 2) {
    		FieldEntity field =tFieldMapper.selectById(sg.getCourseid());
    		field.setStatus(3);
			tFieldMapper.updateById(field );
			
			noticeMapper.updateStatus(sg.getCourseid());
    	}
    	
    	noticeMapper.updateById(sysContent);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("/listByPage")
    @DataScope
    public DataResult findListByPage(@RequestBody Sign sysContent) {
        Page page = new Page(sysContent.getPage(), sysContent.getLimit());
        LambdaQueryWrapper<Sign> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!StringUtils.isEmpty(sysContent.getUsername())) {
            queryWrapper.like(Sign::getUsername, sysContent.getUsername());
        }
        if (!StringUtils.isEmpty(sysContent.getPhone())) {
            queryWrapper.like(Sign::getPhone, sysContent.getPhone());
        }
        IPage<Sign> iPage = noticeService.page(page, queryWrapper);
        List<Sign> records = iPage.getRecords();
        if(records!=null && !records.isEmpty()) {
        	for (Sign tSign : records) {
        		FieldEntity selectById = tFieldMapper.selectById(tSign.getCourseid());
        		tSign.setXuqiuName(selectById.getTitle());
        		
        		User u1 = tUserMapper.selectById(selectById.getUserid());
        		tSign.setFabuUserName(u1.getUsername());
        		
        		User u2 = tUserMapper.selectById(tSign.getUserid());
        		tSign.setJieshouUserName(u2.getUsername());
			}
        }
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询分页数据")
    @PostMapping("/listBy")
    @DataScope
    public DataResult listBy(@RequestBody Clazz sysContent) {
        Page page = new Page(sysContent.getPage(), sysContent.getLimit());
        LambdaQueryWrapper<Clazz> queryWrapper = Wrappers.lambdaQuery();
        IPage<Clazz> iPage = clazzService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "新增")
    @PostMapping("/addClazz")
    public DataResult addClazz(@RequestBody Clazz sysContent) {
    	sysContent.setKeyname(UUID.randomUUID().toString());
    	clazzMapper.insert(sysContent);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/deleteClazz")
    public DataResult deleteClazz(@RequestBody @ApiParam(value = "id集合") List<String> ids) {
    	clazzService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("/updateClazz")
    public DataResult updateClazz(@RequestBody Clazz sysContent) {
    	clazzMapper.updateById(sysContent);
        return DataResult.success();
    }
}
