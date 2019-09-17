package com.xili.loinfo.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: liangxin
 * @Date: 2019/8/23
 * @Description:
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
