package com.xili.loinfo.blog.service;

import com.xili.loinfo.blog.domain.Blog;
import com.xili.loinfo.blog.domain.Catalog;
import com.xili.loinfo.blog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Blog服務接口
 * @author xili
 * @since 2020/1/12 16:06
 **/
public interface BlogService {

    /**
     * 保存blog
     * @param blog
     * @return
     */
    Blog saveBlog(Blog blog);

    /**
     * 删除blog
     * @param id
     */
    void removeBlog(Long id);

    /**
     * 根据idh获取blog
     * @param id
     * @return
     */
    Blog getBlogById(Long id);

    /**
     * 根据用户名进行分页模糊查询（最新）
     * @param user
     * @param title
     * @param pageable
     * @return
     */
    Page<Blog> listBlogsByTitleVote(User user, String title, Pageable pageable);

    /**
     * 根据用户名进行分页模糊查询（最热）
     * @param user
     * @param title
     * @param pageable
     * @return
     */
    Page<Blog> listBlogsByTitleVoteAndSort(User user, String title, Pageable pageable);

    /**
     * 根据分类进行查询
     * @param catalog
     * @param pageable
     * @return
     */
    Page<Blog> listBlogsByCatalog(Catalog catalog, Pageable pageable);

    /**
     * 阅读量递增
     * @param id
     */
    void readingIncrease(Long id);

    /**
     * 发表评论
     * @param blogId
     * @param commentContent
     * @return
     */
    Blog createComment(Long blogId, String commentContent);

    /**
     * 删除评论
     * @param blogId
     * @param commentId
     */
    void removeComment(Long blogId, Long commentId);

    /**
     * 删除点赞
     * @param blogId
     * @return
     */
    Blog createVote(Long blogId);

    /**
     * 取消点赞
     * @param blogId
     * @param voteId
     */
    void removeVote(Long blogId, Long voteId);
}
