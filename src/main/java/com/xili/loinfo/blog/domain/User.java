package com.xili.loinfo.blog.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @auther: liangxin
 * @date: 2019/8/23
 * @Description: User实体
 */
@ToString
@Entity
public class User implements UserDetails, Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    @Getter
    @Setter
    private Long id;

    //名字
    @NotEmpty(message = "姓名不能为空")
    @Size(min = 2, max = 20)
    @Column(nullable = false, length = 20)
    @Getter
    @Setter
    private String name;

    //年龄
    @Column
    private Integer age;

    //email
    @NotEmpty(message = "邮箱不能为空")
    @Size(max = 50)
    @Email(message = "邮箱格式不对")
    @Column(nullable = false, length = 50, unique = true)
    @Getter
    @Setter
    private String email;

    //用户名,用户登陆唯一标识
    @NotEmpty(message = "账号不能为空")
    @Size(min = 3, max = 20)
    @Column(nullable = false, length = 20, unique = true)
    @Getter
    @Setter
    private String username;

    //密码
    @NotEmpty(message = "密码不能为空")
    @Size(max = 100)
    @Column(length = 100)
    @Getter
    @Setter
    private String password;

    //头像
    @Column(length = 200)
    @Getter
    @Setter
    private String avatar;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    @Setter
    private List<Authority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //  需将 List<Authority> 转成 List<SimpleGrantedAuthority>，否则前端拿不到角色列表名称
        List<SimpleGrantedAuthority> simpleAuthorities = new ArrayList<>();
        for (GrantedAuthority authority : this.authorities) {
            simpleAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }
        return simpleAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    /**
     * 将密码加密
     * @param password
     */
    public void setEncodePassword(String password) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode(password);
        this.password = encodePassword;
    }
}
