package com.cool.controller;

import com.alibaba.fastjson.JSON;
import com.cool.entity.Blog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author water33
 */
@RestController
public class BlogController {

    @GetMapping("/blog")
    public Blog getBlog(){
        Blog blog = new Blog();
        blog.setAuthor("lucy");
        blog.setContent("这是一篇关于elasticsearch的短文,不好看不收钱");
        blog.setCreateTime(LocalDateTime.now());
        blog.setTitle("测试elasticsearch标题");

        System.out.println(JSON.toJSONString(blog));
        return blog;
    }

    @PutMapping("/blog/insert")
    public Blog insertBlog(@RequestBody Blog blog){
        System.out.println(blog);
        return blog;
    }

}
