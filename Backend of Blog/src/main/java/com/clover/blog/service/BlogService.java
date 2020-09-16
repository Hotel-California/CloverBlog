package com.clover.blog.service;

import com.clover.blog.entity.Blog;

public interface BlogService {
    Blog findBlogById(String blogId);
    void addBlog(Blog blog);
    Blog[] findBlogByUser(String userId);
    int addView(String blogId);
    int changeLike(String blogId, boolean add);
    int changeBlogVisible(String blogId, boolean visible);
}
