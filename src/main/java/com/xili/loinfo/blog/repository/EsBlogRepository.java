package com.xili.loinfo.blog.repository;

import com.xili.loinfo.blog.domain.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author xili
 * @since 2020/1/12 17:40
 **/
public interface EsBlogRepository extends ElasticsearchRepository<EsBlog, String> {

    /**
     * 模糊查询（去重）
     * @param title
     * @param Summary
     * @param content
     * @param tags
     * @param pageable
     * @return
     */
    Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContaining(String title, String Summary, String content, String tags, Pageable pageable);

    EsBlog findByBlogId(Long blogId);
}
