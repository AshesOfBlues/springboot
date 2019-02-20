package com.alex.blog.test;

import com.alex.blog.blogstart.Application;
import com.alex.blog.domain.es.EsBlog;
import com.alex.blog.repository.es.EsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class EsBlogTest {
    @Autowired
    private EsRepository esRepository;

    @Before
    public void initRepository() {
        //  esRepository.deleteAll();
        esRepository.deleteAll();
        esRepository.save(new EsBlog("你好吗", "是这个", "你猜"));
        esRepository.save(new EsBlog("你不好", "你好", "你猜"));
        esRepository.save(new EsBlog("你猜", "你猜", "你猜"));
        esRepository.save(new EsBlog("1", "1", "1"));
        esRepository.save(new EsBlog("3", "1", "1"));

    }

    @Test
    public void testFindDistinctByTitleContainingOrContentContainingOrSummaryContaining() {
        Pageable pageable = PageRequest.of(0, 20);

        String content = "1";

        Page<EsBlog> page = esRepository.findEsBlogsByContentContaining(content, pageable);

        List<EsBlog> list = page.getContent();
     //   EsBlog esBlog = list.get(0);
       // System.out.println(list.size() + " " + esBlog);

        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }

        System.out.println("------------------");
//
        for(EsBlog esBlog : list){
            System.out.println(esBlog);;
        }

//        for (EsBlog esBlog : page.getContent()) {
//            System.out.println(page.getContent());
//            esBlog.toString();
//        }

    }
}
