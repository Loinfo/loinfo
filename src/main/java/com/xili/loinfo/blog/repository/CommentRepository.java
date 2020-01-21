package com.xili.loinfo.blog.repository;

import com.xili.loinfo.blog.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xili
 * @since 2020/1/22 0:05
 **/
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
