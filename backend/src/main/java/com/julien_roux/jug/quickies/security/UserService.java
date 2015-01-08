package com.julien_roux.jug.quickies.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.julien_roux.jug.quickies.model.User;
import com.julien_roux.jug.quickies.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		
		System.out.println("User: " + user.getEmail() + " - Password: " + user.getPassword() + " - UserRole: " + user.getRole());
		return new SecuredUser(user);
	}

	private static class SecuredUser extends org.springframework.security.core.userdetails.User {

		private static final long serialVersionUID = 2960622572589129994L;

		public SecuredUser(User user) {
			super(user.getEmail(), user.getPassword(), user.getAuthorities());
		}
	}
}
