package com.clover.blog.service;

import com.clover.blog.dao.BlogDao;
import com.clover.blog.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class BlogServiceImp implements BlogService{

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    private final BlogDao blogDao;

    public BlogServiceImp(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    public boolean findBlogId(String blogId) {
        return blogId!=null && !blogId.equals("") && isInteger(blogId) && blogDao.findBlogById(blogId) != null;
    }

    @Override
    public Blog findBlogById(String blogId) {
        return findBlogId(blogId) ? blogDao.findBlogById(blogId) : null;
    }

    @Override
    public void addBlog(Blog blog) {
        blogDao.insertBlog(blog);
    }

    @Override
    public Blog[] findBlogByUser(String userId) {
        return blogDao.findBlogByUser(userId);
    }

    @Override
    public int addView(String blogId) {
        return findBlogId(blogId) ? blogDao.updateBlogView(blogId) : 0;
    }

    @Override
    public int changeBlogVisible(String blogId, boolean visible) {
        return findBlogId(blogId) ? blogDao.updateBlogVisible(blogId, visible) : 0;
    }

    @Override
    public int changeLike(String blogId, boolean add) {
        return add ? blogDao.addBlogLike(blogId) : blogDao.decBlogLike(blogId);
    }
}
