package com.fanhoufang.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fanhoufang.common.lang.Result;
import com.fanhoufang.entity.Blog;
import com.fanhoufang.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author fanhoufang
 * @since 2021-07-02
 */
@Slf4j
@RestController
@Api(tags = "博客相关接口")
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    BlogService blogService;

    @ApiOperation(value = "博客列表查询接口", httpMethod = "GET")
    @GetMapping("/list")
    public IPage list(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        log.info("博客列表查询接口入参--------------{}" + "pageNum=" + pageNum + "pageSize=" + pageSize);
        return blogService.list(pageNum, pageSize);
    }

    @ApiOperation(value = "博客id查询接口", httpMethod = "GET")
    @GetMapping("/{id}")
    public Blog getInfo(@PathVariable(name = "id") Long id) {
        log.info("博客根据id查询接口入参--------------{}" + "id=" + id);
        return blogService.getInfo(id);
    }

    @RequiresAuthentication
    @ApiOperation(value = "博客编辑接口", httpMethod = "PUT")
    @PutMapping("/edit")
    public void edit(@Validated @RequestBody Blog blog) {
        log.info("博客编辑接口入参--------------{}" + "blog=" + blog);
        blogService.edit(blog);
    }

    @ApiOperation(value = "博客根据id删除接口", httpMethod = "DELETE")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        log.info("博客根据id删除接口入参--------------{}" + "id=" + id);
        blogService.delete(id);
    }

}
