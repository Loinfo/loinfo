package com.xili.loinfo.test;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @Auther: liangxin
 * @Date: 2019/8/23
 * @Description: User实体
 */
@Data
@ToString
@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private Long id;

    //用户名
    @Column
    private String username;

    //名字
    @Column
    private String name;

    //年龄
    @Column
    private Integer age;

    //email
    @Column
    private String email;

}
