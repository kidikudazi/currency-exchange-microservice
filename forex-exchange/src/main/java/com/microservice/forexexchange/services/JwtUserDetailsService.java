package com.microservice.forexexchange.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.microservice.forexexchange.models.Admin;
import com.microservice.forexexchange.repositories.AdminRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService{
	String ROLE_PREFIX = "ROLE_";
	 @Autowired
	 private AdminRepository adminRepository;


	 @Override
	 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	     Optional<Admin> admin = adminRepository.findByUsername(username);
	     if (admin == null) {
	          throw new UsernameNotFoundException("User not found with username: " + username);
	     }
	     return new org.springframework.security.core.userdetails.User(admin.get().getUsername(), admin.get().getPassword(),
	             new ArrayList<>());
	}
	 
}
