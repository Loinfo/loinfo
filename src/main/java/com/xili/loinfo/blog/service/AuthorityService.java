package com.xili.loinfo.blog.service;

import com.xili.loinfo.blog.domain.Authority;

/**
 * Authority 服务接口
 */
public interface AuthorityService {

    /**
     * 根据id获取Authority
     * @param id
     * @return
     */
    Authority getAuthorityById(Long id);
}
