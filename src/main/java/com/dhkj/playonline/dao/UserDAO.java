package com.dhkj.playonline.dao;

import com.dhkj.playonline.pojo.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface UserDAO {

    //注册一个用户
    int insertAUser(User user);

    //查找一个用户
    User selectUserByUsername(String username);

    //修改密码的时候通过邮箱和用户名查找用户
    User selectUserByMailAndUsername(String mail, String username);

    //更改用户的账号密码
    int updatePassword(String username, String password);
}
