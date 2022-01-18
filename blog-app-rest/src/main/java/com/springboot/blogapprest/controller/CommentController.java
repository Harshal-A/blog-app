package com.springboot.blogapprest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blogapprest.entity.Comment;
import com.springboot.blogapprest.payload.CommentDto;
import com.springboot.blogapprest.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	private CommentService commentService;

	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(
			@PathVariable("postId") long postId,@Valid @RequestBody CommentDto commentDto){
		return new ResponseEntity<CommentDto>
		(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/posts/{postId}/comments")
	public List<CommentDto> getCommentsByPostId(@PathVariable("postId") long postId){
		return commentService.getAllCommentsByPostId(postId);
	}
	
	@GetMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> getCommentByPostId(@PathVariable("postId") long postId,@PathVariable("commentId") long commentId){
		CommentDto commentDto=commentService.getCommentByPostId(postId, commentId);
		return new ResponseEntity<CommentDto>(commentDto, HttpStatus.OK);	
	}
	
	@PutMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> updateCommentByPostId(@PathVariable("postId") long postId
			,@PathVariable("commentId") long commentId
			,@Valid @RequestBody CommentDto commentDto){
		
		CommentDto updatedCommentDto=commentService.updateCommentById(postId, commentId, commentDto);
		return new ResponseEntity<CommentDto>(updatedCommentDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<String> deleteCommentById(@PathVariable("postId") long postId
			,@PathVariable("commentId") long commentId){
		commentService.deleteCommentById(postId, commentId);
		return new ResponseEntity<String>("Comment deleted successfully!!", HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
