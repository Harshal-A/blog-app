package com.springboot.blogapprest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blogapprest.entity.Comment;
import com.springboot.blogapprest.payload.CommentDto;

public interface Commentrepository extends JpaRepository<Comment, Long> {

	List<Comment> findByPostId(long postId);
	
}
