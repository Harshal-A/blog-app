package com.springboot.blogapprest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blogapprest.entity.Post;
import com.springboot.blogapprest.payload.PostDto;
import com.springboot.blogapprest.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService=postService;
	}

	
	@PostMapping
	public ResponseEntity<PostDto> create(@RequestBody PostDto postDto){
		return new ResponseEntity<PostDto>(postService.createPost(postDto), HttpStatus.CREATED);
	}
}
 