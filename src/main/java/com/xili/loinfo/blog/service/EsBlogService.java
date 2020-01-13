package com.xili.loinfo.blog.service;

import com.xili.loinfo.blog.domain.Blog;
import com.xili.loinfo.blog.domain.EsBlog;
import com.xili.loinfo.blog.domain.User;
import com.xili.loinfo.blog.vo.TagVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Es blog服务接口
 * @author xili
 * @since 2020/1/12 17:12
 **/
public interface EsBlogService {

    /**
     * 删除blog
     * @param id
     */
    void removeEsBlog(String id);

    /**
     * 更新 Esblog
     * @param esBlog
     * @return
     */
    EsBlog updateEsBlog(EsBlog esBlog);

    /**
     * 根据id获取Blog
     * @param blogId
     * @return
     */
    EsBlog getEsBlogByBlogId(Long blogId);

    /**
     * 最新博客列表，分页
     * @param keyWord
     * @param pageable
     * @return
     */
    Page<EsBlog> listNewestEsBlogs(String keyWord, Pageable pageable);

    /**
     * 最热博客列表， 分页
     * @param keyWord
     * @param pageable
     * @return
     */
    Page<EsBlog> listHotestEsBlogs(String keyWord, Pageable pageable);

    /**
     * 博客列表，分页
     * @param pageable
     * @return
     */
    Page<EsBlog> listEsBlogs(Pageable pageable);

    /**
     * 最新前5
     * @return
     */
    List<EsBlog> listTop5NewestEsBlogs();

    /**
     * 最热前5
     * @return
     */
    List<EsBlog> listTop5HotestEsBlogs();

    /**
     * 最热前 30 标签
     * @return
     */
    List<TagVO> listTop30Tags();

    /**
     * 最热前12用户
     * @return
     */
    List<User> listTop12Users();
}
