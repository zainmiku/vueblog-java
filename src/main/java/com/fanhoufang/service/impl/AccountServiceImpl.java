package com.fanhoufang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fanhoufang.entity.User;
import com.fanhoufang.mapper.UserMapper;
import com.fanhoufang.service.AccountService;
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
public class AccountServiceImpl extends ServiceImpl<UserMapper, User> implements AccountService {

}
