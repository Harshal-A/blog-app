package com.springboot.blogapprest.service.Impl;

import org.springframework.stereotype.Service;

import com.springboot.blogapprest.entity.Post;
import com.springboot.blogapprest.payload.PostDto;
import com.springboot.blogapprest.repository.PostRepository;
import com.springboot.blogapprest.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	private PostRepository postRepository;
	
	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository=postRepository;
	}

	@Override
	public PostDto createPost(PostDto postDto) {
		Post post=new Post();
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		
		Post savedPost=postRepository.save(post);
		
		PostDto savedPostDto=new PostDto();
		savedPostDto.setId(savedPost.getId());
		savedPostDto.setTitle(savedPost.getTitle());
		savedPostDto.setDescription(savedPost.getDescription());
		savedPostDto.setContent(savedPost.getContent());
		return savedPostDto;
	}

}
