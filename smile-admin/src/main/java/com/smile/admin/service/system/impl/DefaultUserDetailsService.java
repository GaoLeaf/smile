package com.smile.admin.service.system.impl;

import com.smile.admin.bean.domain.User;
import com.smile.admin.bean.domain.UserExample;
import com.smile.admin.bean.dto.SecurityUser;
import com.smile.admin.mapper.system.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gaowenjin
 * @date 2021/1/6
 * @description:
 */
@Primary
@Service("userDetailsService")
public class DefaultUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserExample example = new UserExample();

        example.createCriteria().andUsernameEqualTo(username);

        List<User> users = userMapper.selectByExample(example);

        if (users == null || users.isEmpty()) {
            throw new UsernameNotFoundException(username); // 用户不存在
        }

        //TODO 获取用户角色

        return new SecurityUser(users.get(0));
    }

}
