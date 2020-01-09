package com.xili.loinfo.blog.repository;

import com.xili.loinfo.blog.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Authority仓库
 * @author xili
 * @since 2020/1/7 18:53
 **/
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
