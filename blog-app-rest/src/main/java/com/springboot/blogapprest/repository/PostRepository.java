package com.springboot.blogapprest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blogapprest.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
