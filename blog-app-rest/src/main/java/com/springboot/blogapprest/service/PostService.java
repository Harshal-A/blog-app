package com.springboot.blogapprest.service
;

import java.util.List;

import com.springboot.blogapprest.payload.PostDto;

public interface PostService {

	PostDto createPost(PostDto postDto);
	
	List<PostDto> getAllPosts();
	
	PostDto getPostById(Long id);
	
	PostDto updatePost(Long id,PostDto postDto);
}
