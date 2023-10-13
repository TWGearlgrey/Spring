package kr.co.sboard.repository;

import kr.co.sboard.entity.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {
    public Page<ArticleEntity> findByParentAndCate(int parent, String cate, Pageable pageable);
}