package com.springboot.blogapprest.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.blogapprest.entity.Role;
import com.springboot.blogapprest.entity.User;
import com.springboot.blogapprest.exceptions.ResourceNotFoundException;
import com.springboot.blogapprest.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;
	
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		User user= userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
		.orElseThrow(()-> new UsernameNotFoundException("User not found with username or email : "+usernameOrEmail));
		
		return new org.springframework.security.core.userdetails
				.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
		//convert roles in collection of granted authorities
		List<SimpleGrantedAuthority> authorities=new ArrayList<SimpleGrantedAuthority>();
		for(Role role:roles) {
			SimpleGrantedAuthority authority=new SimpleGrantedAuthority(role.getName());
			authorities.add(authority);
		}
		
		return authorities;
	}
	

}
