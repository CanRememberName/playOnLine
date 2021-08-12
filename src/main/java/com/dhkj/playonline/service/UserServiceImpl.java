package com.dhkj.playonline.service;


import com.dhkj.playonline.dao.UserDAO;
import com.dhkj.playonline.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public int insertAUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = user.getPassword();
        user.setPassword(encoder.encode(password));
        return userDAO.insertAUser(user);
    }

    @Override
    public User selectUserByUsername(String username) {
        return userDAO.selectUserByUsername(username);
    }

    @Override
    public User selectUserByMailAndUsername(String mail, String username) {
        return userDAO.selectUserByMailAndUsername(mail, username);
    }

    @Override
    public int updatePassword(String username, String password) {
        String word = new BCryptPasswordEncoder().encode(password);
        return userDAO.updatePassword(username,word);
    }


}
