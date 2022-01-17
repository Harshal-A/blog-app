package com.springboot.blogapprest.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.blogapprest.entity.Post;
import com.springboot.blogapprest.exceptions.ResourceNotFoundException;
import com.springboot.blogapprest.payload.PostDto;
import com.springboot.blogapprest.repository.PostRepository;
import com.springboot.blogapprest.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	private PostRepository postRepository;
	
	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository=postRepository;
	}
	
	
	private PostDto maptToDto(Post post) {
		PostDto postDto=new PostDto();
		postDto.setId(post.getId());
		postDto.setTitle(post.getTitle());
		postDto.setDescription(post.getDescription());
		postDto.setContent(post.getContent());
		return postDto;
	}
	
	private Post mapToEntity(PostDto postDto) {
		Post post=new Post();
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		return post;
	}

	@Override
	public PostDto createPost(PostDto postDto) {
		Post post=mapToEntity(postDto);
		Post savedPost=postRepository.save(post);
		return maptToDto(savedPost);
	}

	@Override
	public List<PostDto> getAllPosts() {
		List<PostDto> postDtos=new ArrayList<PostDto>();
		List<Post> posts= postRepository.findAll();
		for(Post post:posts) {
			PostDto postDto=maptToDto(post);
			postDtos.add(postDto);
		}
		return postDtos;
	}


	@Override
	public PostDto getPostById(Long id) {
		Post post=postRepository.findById(id).
				orElseThrow(()->new ResourceNotFoundException("Post","id", id));
		return maptToDto(post);
	}


	@Override
	public PostDto updatePost(Long id, PostDto postDto) {
		Post post=postRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Post","id",id));
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		Post updatedPost=postRepository.save(post);
		return maptToDto(updatedPost);
	}


	@Override
	public void deletePostById(Long id) {
		postRepository.findById(id)
		.orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
		postRepository.deleteById(id);
	}

}
