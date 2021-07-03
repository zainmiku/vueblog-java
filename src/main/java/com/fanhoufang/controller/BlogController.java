package com.fanhoufang.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanhoufang.common.lang.Result;
import com.fanhoufang.entity.Blog;
import com.fanhoufang.service.BlogService;
import com.fanhoufang.util.ShiroUtil;
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
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    BlogService blogService;
    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize){
        Page page = new Page(pageNum,pageSize);
        IPage iPage = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.success(iPage);
    }
    @GetMapping("/{id}")
    public Result list(@PathVariable(name = "id") Long id){
        Blog blog = blogService.getById(id);
        Assert.notNull(blog,"该博客不存在");
        return Result.success(blog);
    }
    @RequiresAuthentication
    @PutMapping ("/edit")
    public Result list(@Validated @RequestBody Blog blog){

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
