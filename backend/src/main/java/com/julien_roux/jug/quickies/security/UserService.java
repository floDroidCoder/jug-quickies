package com.julien_roux.jug.quickies.security;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

	@PostConstruct	
	protected void initialize() {
//		userRepository.save(new User("user@user.com", "demo", "ROLE_USER"));
//		userRepository.save(new User("admin@admin.com", "admin", "ROLE_ADMIN"));
	}
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		
		System.out.println("User: " + user.getEmail() + " - Password: " + user.getPassword() + " - UserRole: " + user.getRole());
		return new SecuredUser(user);
	}

	public void signin(User account) {
		SecurityContextHolder.getContext().setAuthentication(authenticate(account));
	}
	
	private Authentication authenticate(User account) {
		return new UsernamePasswordAuthenticationToken(new SecuredUser(account), null, account.getAuthorities());
	}
	
	private static class SecuredUser extends org.springframework.security.core.userdetails.User {

		private static final long serialVersionUID = 2960622572589129994L;

		public SecuredUser(User user) {
			super(user.getEmail(), user.getPassword(), user.getAuthorities());
		}
	}
}
