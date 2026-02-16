package com.everycodeacademy.backend.content.repository;

import com.everycodeacademy.backend.content.entity.ContentArticle;
import com.everycodeacademy.backend.content.entity.ContentCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentArticleRepository extends JpaRepository<ContentArticle, Long> {
    List<ContentArticle> findByCategoryOrderByCreatedAtDesc(ContentCategory category);
}
