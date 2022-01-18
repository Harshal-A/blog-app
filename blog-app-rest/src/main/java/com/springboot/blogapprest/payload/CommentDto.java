package com.springboot.blogapprest.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

	private Long id;
	
	@NotBlank
	@Size(min = 2,max = 20,message = "name must have between 2 and 20 characters")
	private String name;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Size(min = 5,message ="body must contain atleast 5 characters" )
	private String body;
	
	
}
