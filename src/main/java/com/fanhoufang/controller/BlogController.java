package com.fanhoufang.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanhoufang.common.lang.Result;
import com.fanhoufang.entity.Blog;
import com.fanhoufang.service.BlogService;
import com.fanhoufang.util.ShiroUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fanhoufang
 * @since 2021-07-02
 */
@Slf4j
@RestController
@Api(tags = "博客集相关接口")
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    BlogService blogService;
    @ApiOperation(value = "博客列表查询接口",httpMethod = "GET")
    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize){
        log.info("博客列表查询接口入参--------------{}"+"pageNum="+pageNum+"pageSize="+pageSize);
        Page page = new Page(pageNum,pageSize);
        IPage iPage = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.success(iPage);
    }

    @ApiOperation(value = "博客id查询接口",httpMethod = "GET")
    @GetMapping("/{id}")
    public Result list(@PathVariable(name = "id") Long id){
        log.info("博客根据id查询接口入参--------------{}"+"id="+id);
        Blog blog = blogService.getById(id);
        Assert.notNull(blog,"该博客不存在");
        return Result.success(blog);
    }

    @RequiresAuthentication
    @ApiOperation(value = "博客编辑接口",httpMethod = "PUT")
    @PutMapping ("/edit")
    public Result list(@Validated @RequestBody Blog blog){
        log.info("博客编辑接口入参--------------{}"+"blog="+blog);
            Blog temp = null;
            if(blog.getId() != null) {
                temp = blogService.getById(blog.getId());
                Assert.isTrue(temp.getUserId() == ShiroUtil.getProfile().getId(), "没有权限编辑");
            } else {
                temp = new Blog();
                temp.setUserId(ShiroUtil.getProfile().getId());
                temp.setCreated(LocalDateTime.now());
                temp.setStatus(0);
            }
            BeanUtil.copyProperties(blog, temp, "id", "userId", "created", "status");
            blogService.saveOrUpdate(temp);
            return Result.success(200,"操作成功",null);
    }

}
