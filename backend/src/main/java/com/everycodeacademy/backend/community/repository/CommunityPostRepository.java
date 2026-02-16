package com.everycodeacademy.backend.community.repository;

import com.everycodeacademy.backend.community.entity.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityPostRepository extends JpaRepository<CommunityPost, Long> {
}
