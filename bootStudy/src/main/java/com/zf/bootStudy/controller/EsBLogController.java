package com.zf.bootStudy.controller;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import com.zf.bootStudy.entity.Blog;
import com.zf.bootStudy.entity.EsBlog;
import com.zf.bootStudy.service.BlogRepository;
import com.zf.bootStudy.service.EsBlogService;
 
/**
 * @Description: blog控制类
 * @Author oyc
 * @Date 2020/5/10 10:35 下午
 */
@RestController
@RequestMapping("blog")
public class EsBLogController {
 
    @Resource
    private BlogRepository blogRepository;
 
    @Resource
    private EsBlogService searchService;
 
    @GetMapping("init")
    private String initBlog() {
        List<Blog> blogs = blogRepository.findAll();
        List<EsBlog> esBlogs = new ArrayList<>();
        blogs.forEach(blog -> {
                    esBlogs.add(new EsBlog(blog.getId(), blog.getTitle(), blog.getContent()));
                }
        );
        searchService.save(esBlogs);
        return "init Success";
    }
 
    /**
     * @param blog 博客文档
     * @return
     */
    @PostMapping("save")
    public void save(EsBlog blog) {
        searchService.save(blog);
    }
 
    /**
     * @param id 文档id
     * @return
     */
    @GetMapping("getById")
    public Object getById(int id) {
        return searchService.getById(id);
    }
 
    /**
     * @param key 关键字
     * @return
     */
    @GetMapping("search")
    public Page<EsBlog> getByKey(HttpServletRequest request, String key) {
        Pageable pageable = getPageByRequest(request);
        return searchService.getByKey(key, pageable);
    }
 
    /**
     * @param key 关键字
     * @return
     */
    @GetMapping("keyWord")
    public Page<EsBlog> getByKeyWord(HttpServletRequest request, String key) {
        Pageable pageable = getPageByRequest(request);
        return searchService.getByKeyWord(key, pageable);
    }
 
    private Pageable getPageByRequest(HttpServletRequest request) {
        int page = StringUtils.isEmpty(request.getParameter("page")) ? 1 : Integer.parseInt(request.getParameter("page"));
        int size = StringUtils.isEmpty(request.getParameter("size")) ? 10 : Integer.parseInt(request.getParameter("size"));
        Pageable pageable = PageRequest.of(page - 1, size);
        return pageable;
    }

}
