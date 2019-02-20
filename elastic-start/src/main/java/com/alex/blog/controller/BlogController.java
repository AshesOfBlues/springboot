package com.alex.blog.controller;

import com.alex.blog.domain.es.EsBlog;
import com.alex.blog.repository.es.EsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private EsRepository repository;

    @RequestMapping
    public List<EsBlog> list(@RequestParam("content") String content,
                             @RequestParam(value = "index", defaultValue = "0") Integer index,
                             @RequestParam(value = "pageSize" , defaultValue = "10") Integer pageSize){
        Pageable pageable = PageRequest.of(index, pageSize);
        Page<EsBlog> page = repository.findEsBlogsByContentContaining(content, pageable);

        return page.getContent();
    }
}
