package com.xili.loinfo.blog.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * 权限
 * @since
 * @author xili
 */
@Entity
public class Authority implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(nullable = false)
    @Setter
    private String name;

    @Override
    public String getAuthority() {
        return null;
    }
}
