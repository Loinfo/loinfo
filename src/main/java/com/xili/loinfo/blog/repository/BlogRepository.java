package com.xili.loinfo.blog.repository;

import com.xili.loinfo.blog.domain.Blog;
import com.xili.loinfo.blog.domain.Catalog;
import com.xili.loinfo.blog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xili
 * @since 2020/1/12 16:57
 **/
public interface BlogRepository extends JpaRepository<Blog, Long> {

    /**
     * 根据用户
     * @param user
     * @param title
     * @param pageable
     * @return
     */
    Page<Blog> findByUserAndTitleLike(User user, String title, Pageable pageable);

    /**
     * 根据用户名分页查询用户列表
     * @param title
     * @param user
     * @param tags
     * @param user2
     * @param pageable
     * @return
     */
    Page<Blog> findByTitleLikeAndUserOrTagsLikeAndUserOrderByCreateTimeDesc(String title,User user,String tags,User user2,Pageable pageable);

    /**
     * 根据用户名分页查询用户列表
     * @param catalog
     * @param pageable
     * @return
     */
    Page<Blog> findByCatalog(Catalog catalog, Pageable pageable);
}
