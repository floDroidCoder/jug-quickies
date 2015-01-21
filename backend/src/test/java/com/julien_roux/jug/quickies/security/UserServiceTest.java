package com.julien_roux.jug.quickies.security;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.julien_roux.jug.quickies.QuickiesLauncher;
import com.julien_roux.jug.quickies.model.User;
import com.julien_roux.jug.quickies.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {QuickiesLauncher.class})
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	protected UserRepository userRepository;

	private User user;

	@Before
	public void setup() throws Exception {
		userRepository.deleteAll();
		
		user = new User("clara@morgane.com", "tAsOeUrDoIGt£D&sB0ucS", "USER_ROLE");
		userRepository.save(user);
	}
	
	@Test(expected = UsernameNotFoundException.class)
	public void connectWrongUser() {
		userService.loadUserByUsername("susan@boyle.com");
	}
	
	@Test
	public void connectRightUser() {
		UserDetails user = userService.loadUserByUsername("clara@morgane.com");
		assertThat(user.getUsername()).isEqualTo("clara@morgane.com");
	}
}
