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
    public IPage list(Integer pageNum, Integer pageSize) {
        Page page = new Page(pageNum, pageSize);
        IPage iPage = page(page, new QueryWrapper<Blog>().orderByDesc("created"));
        return iPage;
    }

    @Override
    public Blog getInfo(Long id) {
        Blog blog = getById(id);
        Assert.notNull(blog, "该博客不存在");
        return blog;
    }

    @Override
    public void edit(Blog blog) {
        Blog temp = null;
        if (blog.getId() != null) {
            temp = getById(blog.getId());
            Assert.isTrue(temp.getUserId().equals(ShiroUtil.getProfile().getId()), "没有权限编辑");
        } else {
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }
        BeanUtil.copyProperties(blog, temp, "id", "userId", "created", "status");
        saveOrUpdate(temp);
return;
    }

    @Override
    public void delete(Long id) {
        boolean removeFlag = removeById(id);
        if(removeFlag){
            return ;
        }else{
            throw new RuntimeException("博客不存在");
        }
    }
}
