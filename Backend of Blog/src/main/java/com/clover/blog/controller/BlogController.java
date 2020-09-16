package com.clover.blog.controller;

import com.clover.blog.entity.Blog;
import com.clover.blog.entity.Result;
import com.clover.blog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;

@Controller
@RequestMapping("blog")
@CrossOrigin
@Slf4j
public class BlogController {
    @Value("${user.blog-path}")
    private String blogPath;

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("getBlog")
    public void getBlog(String blogId, HttpServletResponse response) throws Exception {
        Blog blog = blogService.findBlogById(blogId);
        if (blog == null) return;
        blogService.addView(blogId);
        response.setHeader("Content-Disposition", "attachment;filename=" + blog.getBlogFile());
        response.setContentType("application/octet-stream;charset=UTF-8");
        OutputStream outputStream = response.getOutputStream();
        File file = new File(blogPath, blog.getUserId() +"/"+ blog.getBlogFile());
        if (!file.exists()) return ;
        InputStream inputStream = new FileInputStream(file);
        try {
            byte[] buf = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, bytesRead);
            }
        } finally {
            inputStream.close();
            outputStream.close();
        }
    }

    @PostMapping("getBlogList")
    @ResponseBody
    public Result<Blog[]> getBlogList(String id) {
        try {
            Blog[] blogs = blogService.findBlogByUser(id);
            return Result.success("返回博客成功",blogs);
        } catch (Exception e){
            return Result.fail(e.getMessage());
        }
    }

    @GetMapping("changeLikes")
    @ResponseBody
    public Result<String> changeLikes(String blogId, boolean add) {
        blogService.changeLike(blogId, add);
        return Result.success();
    }

}
