package com.springboot.blogapprest.service;

import java.util.List;

import com.springboot.blogapprest.payload.CommentDto;

public interface CommentService {

	CommentDto createComment(long postId,CommentDto commentDto);
	
	List<CommentDto> getAllCommentsByPostId(long postId);
	
	CommentDto getCommentByPostId(long postId, long commentId);
	
	CommentDto updateCommentById(long postId,long commentId,CommentDto commentDto);
	
	void deleteCommentById(long postId,long commentId);
}
