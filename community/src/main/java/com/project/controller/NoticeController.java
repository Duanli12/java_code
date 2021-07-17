package com.project.controller;

import java.util.Date;
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
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.common.aop.annotation.DataScope;
import com.project.common.utils.DataResult;
import com.project.entity.Notice;
import com.project.mapper.TNoticeMapper;
import com.project.service.TNoticeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * 消息管理
 *
 * @author 
 * @version V1.0
 * @date 
 */
@Api(tags = "消息管理")
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Resource
    private TNoticeMapper noticeMapper;
    @Resource
    private TNoticeService noticeService;

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public DataResult add(@RequestBody Notice sysContent) {
    	sysContent.setId(UUID.randomUUID().toString());
    	sysContent.setCreatetime(new Date());
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
    public DataResult update(@RequestBody Notice sysContent) {
    	noticeMapper.updateById(sysContent);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("/listByPage")
    @DataScope
    public DataResult findListByPage(@RequestBody Notice sysContent) {
        Page page = new Page(sysContent.getPage(), sysContent.getLimit());
        LambdaQueryWrapper<Notice> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!StringUtils.isEmpty(sysContent.getTitle())) {
            queryWrapper.like(Notice::getTitle, sysContent.getTitle());
        }
        if (!StringUtils.isEmpty(sysContent.getContent())) {
            queryWrapper.like(Notice::getContent, sysContent.getContent());
        }
        IPage<Notice> iPage = noticeService.page(page, queryWrapper);
        
        return DataResult.success(iPage);
    }
    
}
