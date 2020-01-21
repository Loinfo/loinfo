package com.xili.loinfo.blog.service.impl;

import com.xili.loinfo.blog.domain.Vote;
import com.xili.loinfo.blog.repository.VoteRepository;
import com.xili.loinfo.blog.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author xili
 * @since 2020/1/22 0:13
 **/
@Service
public class VoteServiceImpl implements VoteService{

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public Vote getVoteById(Long id) {
        return voteRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void removeVote(Long id) {
        voteRepository.deleteById(id);
    }
}
