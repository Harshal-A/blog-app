package com.springboot.blogapprest.payload;

import lombok.Data;

@Data
public class PostDto {

	private Long id;
	private String title;
	private String description;
	private String content;
}
