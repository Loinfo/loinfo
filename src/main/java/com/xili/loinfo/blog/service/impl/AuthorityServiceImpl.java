package com.xili.loinfo.blog.service.impl;

import com.xili.loinfo.blog.domain.Authority;
import com.xili.loinfo.blog.repository.AuthorityRepository;
import com.xili.loinfo.blog.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xili
 * @since 2020/1/7 18:52
 **/
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Authority getAuthorityById(Long id) {
        return authorityRepository.findById(id).get();
    }
}
