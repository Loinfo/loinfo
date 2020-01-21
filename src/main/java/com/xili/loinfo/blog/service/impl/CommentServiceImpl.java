package com.xili.loinfo.blog.service.impl;

import com.xili.loinfo.blog.domain.Comment;
import com.xili.loinfo.blog.repository.CommentRepository;
import com.xili.loinfo.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author xili
 * @since 2020/1/22 0:04
 **/
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void removeComment(Long id) {
        commentRepository.deleteById(id);
    }
}
