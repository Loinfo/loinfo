package com.xili.loinfo.blog.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 菜单值对象
 * @author xili
 * @since 2020/1/8 18:53
 **/
@Data
@ToString
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String url;

    public Menu(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
