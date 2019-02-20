package com.alex.blog.repository.es;

import com.alex.blog.domain.es.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

public interface EsRepository extends ElasticsearchRepository<EsBlog, String> {
    Page<EsBlog> findEsBlogsByContentContaining(String content, Pageable pageable);
}