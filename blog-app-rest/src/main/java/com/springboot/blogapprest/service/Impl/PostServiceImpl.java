package com.springboot.blogapprest.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.blogapprest.entity.Post;
import com.springboot.blogapprest.exceptions.ResourceNotFoundException;
import com.springboot.blogapprest.payload.PostDto;
import com.springboot.blogapprest.payload.PostResponse;
import com.springboot.blogapprest.repository.PostRepository;
import com.springboot.blogapprest.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	private PostRepository postRepository;
	
	private ModelMapper modelMapper;
	
	public PostServiceImpl(PostRepository postRepository,ModelMapper modelMapper) {
		this.postRepository=postRepository;
		this.modelMapper=modelMapper;
	}
	
	
	private PostDto maptToDto(Post post) {
//		PostDto postDto=new PostDto();
//		postDto.setId(post.getId());
//		postDto.setTitle(post.getTitle());
//		postDto.setDescription(post.getDescription());
//		postDto.setContent(post.getContent());
		
		PostDto postDto=modelMapper.map(post, PostDto.class);
		return postDto;
	}
	
	private Post mapToEntity(PostDto postDto) {
//		Post post=new Post();
//		post.setTitle(postDto.getTitle());
//		post.setDescription(postDto.getDescription());
//		post.setContent(postDto.getContent());
		
		Post post=modelMapper.map(postDto, Post.class);
		return post;
	}

	@Override
	public PostDto createPost(PostDto postDto) {
		Post post=mapToEntity(postDto);
		Post savedPost=postRepository.save(post);
		return maptToDto(savedPost);
	}

	@Override
	public PostResponse getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir) {
		Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		Pageable pageable=PageRequest.of(pageNo, pageSize,sort);
		Page<Post> pages=postRepository.findAll(pageable);
		List<Post> posts=pages.getContent();				
		
		List<PostDto> postDtos=new ArrayList<PostDto>();
//		List<Post> posts= postRepository.findAll();
		for(Post post:posts) {
			PostDto postDto=maptToDto(post);
			postDtos.add(postDto);
		}
		
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNo(pages.getNumber());
		postResponse.setPageSize(pages.getSize());
		postResponse.setTotalElements(Long.valueOf(pages.getTotalElements()));
		postResponse.setTotalPages(pages.getTotalPages());
		postResponse.setLast(pages.isLast());
		return postResponse;
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
