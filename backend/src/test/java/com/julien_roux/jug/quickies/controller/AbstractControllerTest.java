package com.julien_roux.jug.quickies.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import com.julien_roux.jug.quickies.QuickiesLauncher;
import com.julien_roux.jug.quickies.model.User;
import com.julien_roux.jug.quickies.repository.UserRepository;
import com.julien_roux.jug.quickies.security.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {QuickiesLauncher.class})
@WebAppConfiguration
@IntegrationTest
public abstract class AbstractControllerTest {
	
	@Autowired
	protected WebApplicationContext wac;

	@Autowired
	protected UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	protected MockMvc mockMvc;

	protected User user;
	protected User admin;

	private Authentication auth;

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(this.wac).build();
		
		userRepository.deleteAll();
		
		user = new User("julien@roux.com", "tOnPèreSuceD#sCh4me@uX", "USER_ROLE");
		userRepository.save(user);
		
		admin = new User("florian@genaudet.com", "T@MèreB4iseDesDroMadAires", "USER_ADMIN");
		userRepository.save(admin);
	}
	
	protected void connectUser() {
		userService.signin(user);
		UserDetails userDetails = userService.loadUserByUsername(user.getEmail());
		auth = new UsernamePasswordAuthenticationToken(userDetails,null);
        SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	protected void connectAdmin() {
		userService.signin(admin);
		UserDetails userDetails = userService.loadUserByUsername(admin.getEmail());
		auth = new UsernamePasswordAuthenticationToken(userDetails,null);
        SecurityContextHolder.getContext().setAuthentication(auth);
	}
 	
	protected MockHttpServletRequestBuilder prepareSecureRequest(MockHttpServletRequestBuilder builder) throws Exception {
		return prepareNonSecureRequest(builder).principal(auth);
	}
	
	protected MockHttpServletRequestBuilder prepareNonSecureRequest(MockHttpServletRequestBuilder builder) throws Exception {
		return builder; //.header("X-Requested-With", "XMLHttpRequest");
	}

	protected ResultActions executeRequest(MockHttpServletRequestBuilder request) throws Exception {
		ResultActions result = mockMvc.perform(request);
		result.andDo(print());
		return result;
	}

}
