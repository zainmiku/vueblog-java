package com.fanhoufang.service;

import com.fanhoufang.common.lang.Result;
import com.fanhoufang.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fanhoufang
 * @since 2021-07-02
 */
public interface BlogService extends IService<Blog> {
    public Result list(Integer pageNum,Integer pageSize);

    public Result getInfo(Long id);

    public Result edit(Blog blog);

    public Result delete(Long id);
}
