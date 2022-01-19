package com.springboot.blogapprest.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderGenerator {

	public static void main(String[] args) {
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		String result= encoder.encode("12345");
		System.out.println(result);
	}
}
