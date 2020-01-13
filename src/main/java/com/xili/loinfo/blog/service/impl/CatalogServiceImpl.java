package com.xili.loinfo.blog.service.impl;

import com.xili.loinfo.blog.domain.Catalog;
import com.xili.loinfo.blog.domain.User;
import com.xili.loinfo.blog.repository.CatalogRepository;
import com.xili.loinfo.blog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author xili
 * @since 2020/1/13 0:14
 **/
@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogRepository catalogRepository;

    @Override
    public Catalog saveCatalog(Catalog catalog) {
        List<Catalog> list = catalogRepository.findByUserAndName(catalog.getUser(), catalog.getName());
        if (!CollectionUtils.isEmpty(list)) {
            throw new IllegalArgumentException("该分类已经存在了");
        }
        return catalogRepository.save(catalog);
    }

    @Override
    public void removeCatalog(Long id) {
        catalogRepository.deleteById(id);
    }

    @Override
    public Catalog getCatalogById(Long id) {
        return catalogRepository.findById(id).get();
    }

    @Override
    public List<Catalog> listCatalogs(User user) {
        return catalogRepository.findByUser(user);
    }
}
