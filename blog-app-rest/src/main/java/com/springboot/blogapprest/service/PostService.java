package com.springboot.blogapprest.service
;

import com.springboot.blogapprest.payload.PostDto;

public interface PostService {

	PostDto createPost(PostDto postDto);
}
