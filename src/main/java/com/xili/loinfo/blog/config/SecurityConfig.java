package com.xili.loinfo.blog.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.authentication.AuthenticationManagerBeanDefinitionParser;
//
///**
// * @Auther: liangxin
// * @Date: 2019/9/10
// * @Description: 安全配置类
// */
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/css/**", "/js/**", "/fonts", "/index")
//                .permitAll()
//                .antMatchers("/users/**").hasRole("ADMIN")
//                .and()
//                .formLogin()
//                .loginPage("/users")
//                .failureUrl("/login-error")
//                ;
//    }
//
//    /**
//     * 认证信息管理
//     * @param auth
//     * @throws Exception
//     */
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("root").password("root").roles("ADMIN");
//
//    }
//}
