package com.julien_roux.jug.quickies;

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

	private Authentication auth;

	private UserDetails userDetails;
	
	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
		userRepository.deleteAll();
		user = new User("julien@roux.com", "tOnP#reSuceDesCh4meauX", "USER_ROLE");
		user.setAbout("about");
		user.setCompany("company");
		user.setFirstname("firstname");
		user.setLastname("lastname");
		user.setShamefulTechnologie("shamefulTechnologie");
		
		user = userRepository.save(user);
		
		userService.signin(user);
        userDetails = userService.loadUserByUsername(user.getEmail());
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
		result.andExpect(status().isOk());
		return result;
	}

}
