package com.springboot.blogapprest.service
;

import java.util.List;

import com.springboot.blogapprest.payload.PostDto;
import com.springboot.blogapprest.payload.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto);
	
	PostResponse getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir);
	
	PostDto getPostById(Long id);
	
	PostDto updatePost(Long id,PostDto postDto);
	
	void deletePostById(Long id);
}
