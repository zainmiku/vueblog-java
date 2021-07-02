package com.fanhoufang.service.impl;

import com.fanhoufang.entity.Blog;
import com.fanhoufang.mapper.BlogMapper;
import com.fanhoufang.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
