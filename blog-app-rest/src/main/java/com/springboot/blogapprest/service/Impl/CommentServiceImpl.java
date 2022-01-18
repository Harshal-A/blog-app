package com.springboot.blogapprest.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.blogapprest.entity.Comment;
import com.springboot.blogapprest.entity.Post;
import com.springboot.blogapprest.exceptions.BlogAPIException;
import com.springboot.blogapprest.exceptions.ResourceNotFoundException;
import com.springboot.blogapprest.payload.CommentDto;
import com.springboot.blogapprest.repository.Commentrepository;
import com.springboot.blogapprest.repository.PostRepository;
import com.springboot.blogapprest.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService {

	private Commentrepository commentrepository;
	
	private PostRepository postRepository;
	
	public CommentServiceImpl(Commentrepository commentrepository,PostRepository postRepository) {
		this.commentrepository=commentrepository;
		this.postRepository=postRepository;
	}
	
	
	private CommentDto mapToDto(Comment comment) {
		CommentDto commentDto=new CommentDto();
		commentDto.setId(comment.getId());
		commentDto.setName(comment.getName());
		commentDto.setEmail(comment.getEmail());
		commentDto.setBody(comment.getBody());
		
		return commentDto;
	}
	
	private Comment mapToEntity(CommentDto commentDto) {
		Comment comment=new Comment();
		comment.setId(commentDto.getId());
		comment.setName(commentDto.getName());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());
		
		return comment;
	}
	
	
	
	
	@Override
	public CommentDto createComment(long postId, CommentDto commentDto) {
		Comment comment=mapToEntity(commentDto);
		
		Post post=postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post","id", postId));
		
		comment.setPost(post);
		Comment savedComment= commentrepository.save(comment);
		return mapToDto(savedComment);
	}


	@Override
	public List<CommentDto> getAllCommentsByPostId(long postId) {
		Post post=postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post","id", postId));
		
		List<Comment> comments=commentrepository.findByPostId(postId);
		List<CommentDto> commentDtos=new ArrayList<CommentDto>();
		for(Comment comment:comments) {
			CommentDto commentDto=mapToDto(comment);
			commentDtos.add(commentDto);
		}
		return commentDtos;
	}


	@Override
	public CommentDto getCommentByPostId(long postId,long commentId) {
		Post post=postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post","id", postId));
		
		Comment comment=commentrepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment","id", commentId));
		
		//if comment does not belong to the post
		
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to the post ");
		}
		
		return mapToDto(comment);
	}


	@Override
	public CommentDto updateCommentById(long postId, long commentId, CommentDto commentDto) {
		Post post=postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post","id", postId));
		
		Comment comment=commentrepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment","id", commentId));
		
		//if comment does not belong to the post
		
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to the post ");
		}
		
		comment.setName(commentDto.getName());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());
		Comment updatedComment=commentrepository.save(comment);
		return mapToDto(updatedComment);
	}


	@Override
	public void deleteCommentById(long postId, long commentId) {
		Post post=postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post","id", postId));
		
		Comment comment=commentrepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment","id", commentId));
		
		//if comment does not belong to the post
		
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to the post ");
		}
		
		commentrepository.delete(comment);
		
	}



}
