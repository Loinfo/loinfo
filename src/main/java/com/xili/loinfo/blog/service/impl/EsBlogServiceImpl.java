package com.xili.loinfo.blog.service.impl;

import com.xili.loinfo.blog.domain.EsBlog;
import com.xili.loinfo.blog.domain.User;
import com.xili.loinfo.blog.repository.EsBlogRepository;
import com.xili.loinfo.blog.service.EsBlogService;
import com.xili.loinfo.blog.service.UserService;
import com.xili.loinfo.blog.vo.TagVO;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.search.aggregations.AggregationBuilders.terms;

/**
 * @author xili
 * @since 2020/1/12 17:39
 **/
@Service
public class EsBlogServiceImpl implements EsBlogService {

    @Autowired
    private EsBlogRepository esBlogRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private UserService userService;

    private static final Pageable TOP_5_PAGEABLE = PageRequest.of(0, 5);
    private static final String EMPTY_KEYWORD = "";

    @Override
    public void removeEsBlog(String id) {
        esBlogRepository.deleteById(id);
    }

    @Override
    public EsBlog updateEsBlog(EsBlog esBlog) {
        return esBlogRepository.save(esBlog);
    }

    @Override
    public EsBlog getEsBlogByBlogId(Long blogId) {
        return esBlogRepository.findByBlogId(blogId);
    }

    @Override
    public Page<EsBlog> listNewestEsBlogs(String keyWord, Pageable pageable) {
        Page<EsBlog> pages = null;
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        if (pageable.getSort() == null) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        }
        pages = esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContaining(keyWord, keyWord, keyWord, keyWord, pageable);
        return pages;
    }

    @Override
    public Page<EsBlog> listHotestEsBlogs(String keyWord, Pageable pageable) {
        Sort sort = new Sort(Sort.Direction.DESC,"readSize","commentSize","voteSize","createTime");
        if (pageable.getSort() == null) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        }
        return esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContaining(keyWord, keyWord, keyWord, keyWord, pageable);
    }

    @Override
    public Page<EsBlog> listEsBlogs(Pageable pageable) {
        return esBlogRepository.findAll(pageable);
    }

    @Override
    public List<EsBlog> listTop5NewestEsBlogs() {
        Page<EsBlog> page = this.listHotestEsBlogs(EMPTY_KEYWORD, TOP_5_PAGEABLE);
        return page.getContent();
    }

    @Override
    public List<EsBlog> listTop5HotestEsBlogs() {
        Page<EsBlog> page = this.listHotestEsBlogs(EMPTY_KEYWORD, TOP_5_PAGEABLE);
        return page.getContent();
    }

    @Override
    public List<TagVO> listTop30Tags() {
        //TODO 着重了解下
        List<TagVO> list = new ArrayList<>();
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withIndices("blog").withTypes("blog")
                .addAggregation(terms("tags").field("tags").size(30)).build();

        Aggregations aggregations = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<Aggregations>() {
            @Override
            public Aggregations extract(SearchResponse searchResponse) {
                return searchResponse.getAggregations();
            }
        });

        StringTerms modelTerms = (StringTerms) aggregations.asMap().get("tags");
        Iterator<StringTerms.Bucket> modelBucketIt = modelTerms.getBuckets().iterator();
        while (modelBucketIt.hasNext()) {
            StringTerms.Bucket actionTypeBucket = modelBucketIt.next();
            list.add(new TagVO(actionTypeBucket.getKey().toString(), actionTypeBucket.getDocCount()));
        }
        return list;
    }

    @Override
    public List<User> listTop12Users() {
        List<String> usernamelist = new ArrayList<>();
        // given
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withSearchType(SearchType.QUERY_THEN_FETCH)
                .withIndices("blog").withTypes("blog")
                .addAggregation(terms("users").field("username").size(12))
                .build();
        // when
        Aggregations aggregations = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<Aggregations>() {
            @Override
            public Aggregations extract(SearchResponse response) {
                return response.getAggregations();
            }
        });

        StringTerms modelTerms =  (StringTerms)aggregations.asMap().get("users");
        //TODO 可能存在问题
        Iterator<StringTerms.Bucket> modelBucketIt = modelTerms.getBuckets().iterator();
        while (modelBucketIt.hasNext()) {
            Terms.Bucket actiontypeBucket = modelBucketIt.next();
            String username = actiontypeBucket.getKey().toString();
            usernamelist.add(username);
        }
        List<User> list = userService.listUsersByUsernames(usernamelist);

        return list;
    }
}
