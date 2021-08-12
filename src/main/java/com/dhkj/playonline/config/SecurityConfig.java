package com.dhkj.playonline.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //首页所有人可以访问,其他页面需要登录
        http.authorizeRequests().antMatchers("/static/**","/","/toLogin","/toRegister","/register","/toChange","/change").permitAll()
                .antMatchers("/**").hasRole("vip1");
        http.formLogin().loginPage("/toLogin?error=false").loginProcessingUrl("/login")
                .failureUrl("/toLogin?error=true");
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("dabao").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1")
//                .and()
//                .withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1");
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username,password,enable from users WHERE username=?")
                .authoritiesByUsernameQuery("select username,roles from users where username=?")
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
