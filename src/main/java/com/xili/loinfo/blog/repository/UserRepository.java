package com.xili.loinfo.blog.repository;

import com.xili.loinfo.blog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Auther: liangxin
 * @Date: 2019/8/23
 * @Description:
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据用户姓名分页查询用户列表
     * @param name
     * @param pageable
     * @return
     */
    Page<User> findByNameLike(String name, Pageable pageable);

    /**
     * 根据用户账户查询用户
     * @param username
     * @return
     */
    User findByUsername(String username);
}
