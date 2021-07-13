package com.zf.bootStudy.service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.zf.bootStudy.entity.Blog;

/**
 * @ClassName BlogRepository
 * @Description TODO
 * @Author oyc
 * @Date 2020/12/4 22:32
 * @Version
 */
public interface BlogRepository extends JpaRepository<Blog, Integer>, JpaSpecificationExecutor {
}