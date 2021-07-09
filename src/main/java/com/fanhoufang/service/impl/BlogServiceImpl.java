package com.fanhoufang.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fanhoufang.common.lang.Result;
import com.fanhoufang.entity.Blog;
import com.fanhoufang.mapper.BlogMapper;
import com.fanhoufang.service.BlogService;
import com.fanhoufang.util.ShiroUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fanhoufang
 * @since 2021-07-02
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Override
    public Result list(Integer pageNum, Integer pageSize) {
        Page page = new Page(pageNum, pageSize);
        IPage iPage = page(page, new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.success(iPage);
    }

    @Override
    public Result getInfo(Long id) {
        Blog blog = getById(id);
        Assert.notNull(blog, "该博客不存在");
        return Result.success(blog);
    }

    @Override
    public Result edit(Blog blog) {
        Blog temp = null;
        if (blog.getId() != null) {
            temp = getById(blog.getId());
            Assert.isTrue(temp.getUserId() != ShiroUtil.getProfile().getId(), "没有权限编辑");
        } else {
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }
        BeanUtil.copyProperties(blog, temp, "id", "userId", "created", "status");
        saveOrUpdate(temp);
        return Result.success(200, "操作成功", null);
    }

    @Override
    public Result delete(Long id) {
        boolean removeFlag = removeById(id);
        if(removeFlag){
            return Result.success(200,"删除成功",null);
        }else{
            return Result.fail("博客不存在");
        }
    }
}
