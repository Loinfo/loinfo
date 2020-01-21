package com.xili.loinfo.blog.service;

import com.xili.loinfo.blog.domain.Comment;

/**
 * 评论服务接口
 * @author xili
 * @since 2020/1/22 0:03
 **/
public interface CommentService {

    /**
     * 根据id获取 评论对象
     * @param id
     * @return
     */
    Comment getCommentById(Long id);

    /**
     * 删除评论
     * @param id
     */
    void removeComment(Long id);
}
