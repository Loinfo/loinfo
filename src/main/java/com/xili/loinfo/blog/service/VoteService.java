package com.xili.loinfo.blog.service;

import com.xili.loinfo.blog.domain.Vote;

/**
 * 点赞服务接口
 * @author xili
 * @since 2020/1/22 0:12
 **/
public interface VoteService {

    /**
     * 根据id获取 Vote
     * @param id
     * @return
     */
    Vote getVoteById(Long id);

    /**
     * 删除Vote
     * @param id
     * @return
     */
    void removeVote(Long id);
}
