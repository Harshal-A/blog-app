package com.springboot.blogapprest.payload;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PostDto {

	private Long id;
	
	@NotBlank
	@Size(min = 2,message = "Post's title should have atleast 2 characters")
	private String title;
	
	@NotBlank
	@Size(min = 5,message = "Post's description should have atleast 5 characters")
	private String description;
	
	@NotBlank
	@Size(min = 2,message = "Post's content should have atleast 2 characters")
	private String content;
	
	private Set<CommentDto> comments;
}
