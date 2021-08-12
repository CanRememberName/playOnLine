package com.dhkj.playonline.service;

import com.dhkj.playonline.pojo.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    //新增一个用户
    int insertAUser(User user);

    //查找一个用户
    User selectUserByUsername(String username);

    //修改密码的时候通过邮箱和用户名查找用户
    User selectUserByMailAndUsername(String mail, String username);

    //更改用户的账号密码
    int updatePassword(String username, String password);
}
