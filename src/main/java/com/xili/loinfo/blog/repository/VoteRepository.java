package com.xili.loinfo.blog.repository;

import com.xili.loinfo.blog.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xili
 * @since 2020/1/22 0:13
 **/
public interface VoteRepository extends JpaRepository<Vote, Long> {
}
