package com.springboot.blogapprest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blogapprest.entity.Post;
import com.springboot.blogapprest.payload.PostDto;
import com.springboot.blogapprest.payload.PostResponse;
import com.springboot.blogapprest.service.PostService;
import com.springboot.blogapprest.utils.AppConstants;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService=postService;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<PostDto> create(@Valid @RequestBody PostDto postDto){
		return new ResponseEntity<PostDto>(postService.createPost(postDto), HttpStatus.CREATED);
	}
	
	@GetMapping
	public PostResponse getAllPosts(
			@RequestParam(name = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NO,required = false) int pageNo,
			@RequestParam(name = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
			@RequestParam(name = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
			@RequestParam(name = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false) String sortDir
			){
		return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Long postId){
		return new ResponseEntity<PostDto>(postService.getPostById(postId), HttpStatus.OK);
	}
	
	@PutMapping("/{postId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PostDto> updatePostById( @PathVariable("postId") Long postId
			,@Valid @RequestBody PostDto postDto){
		return new ResponseEntity<PostDto>(postService.updatePost(postId, postDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/{postId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deletePostById(@PathVariable("postId") Long postId){
		postService.deletePostById(postId);
		return new ResponseEntity<String>("Post deleted successfully", HttpStatus.OK);
	}
}
 