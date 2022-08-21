package com.fanhoufang.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fanhoufang.common.lang.Result;
import com.fanhoufang.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fanhoufang
 * @since 2021-07-02
 */
public interface BlogService extends IService<Blog> {
    IPage list(Integer pageNum, Integer pageSize);

    Blog getInfo(Long id);

    void edit(Blog blog);

    public void delete(Long id);
}
