package com.xili.loinfo.blog.vo;

import com.xili.loinfo.blog.domain.Catalog;
import lombok.Data;
import lombok.ToString;

/**
 * @author xili
 * @since 2020/1/14 0:03
 **/
@Data
@ToString
public class CatalogVO {

    private static final long serialVersionUID = 1L;

    private String username;

    private Catalog catalog;

    public CatalogVO() {
    }
}
