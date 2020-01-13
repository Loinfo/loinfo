package com.xili.loinfo.blog.service;

import com.xili.loinfo.blog.domain.Catalog;
import com.xili.loinfo.blog.domain.User;

import java.util.List;

/**
 * @author xili
 * @since 2020/1/13 0:07
 **/
public interface CatalogService {

    /**
     * 保存 Catalog
     * @param catalog
     * @return
     */
    Catalog saveCatalog(Catalog catalog);

    /**
     * 删除 Catalog
     * @param id
     */
    void removeCatalog(Long id);

    /**
     * 根据id获取 Catalog
     * @param id
     * @return
     */
    Catalog getCatalogById(Long id);

    /**
     * 获取Catalog列表
     * @param user
     * @return
     */
    List<Catalog> listCatalogs(User user);
}
