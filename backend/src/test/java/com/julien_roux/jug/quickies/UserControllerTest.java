package com.julien_roux.jug.quickies;

import static org.fest.assertions.Assertions.assertThat;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import com.julien_roux.jug.quickies.model.User;
import com.julien_roux.jug.quickies.repository.UserRepository;

public class UserControllerTest extends AbstractControllerTest {
	
	@Autowired
	private UserRepository userRepository;
	private User user;

	@Before
	public void setup() throws Exception {
		super.setup();
		
		userRepository.deleteAll();
		user = new User();
		userRepository.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
	@Test
	public void findAll() throws Exception {
		ResultActions result = mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("description", "description").param("title", "title"));
		result.andExpect(status().isOk());
		result.andExpect(content().string(startsWith("[{\"id\":"+user.getId())));
	}
	
	@Test
	public void getUser() throws Exception {
		ResultActions result = mockMvc.perform(get("/user/"+user.getId()).contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("description", "description").param("title", "title"));
		result.andExpect(status().isOk());
		result.andExpect(view().name("/profile/profile-detail"));
		result.andExpect(model().attribute("user", user));
	}
	
	@Test
	public void editUser() throws Exception {
		ResultActions result = mockMvc.perform(get("/user/"+user.getId()+"/edit").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("description", "description").param("title", "title"));
		result.andExpect(status().isOk());
		result.andExpect(view().name("/profile/profile-edit"));
		result.andExpect(model().attribute("user", user));
	}
	
	@Test
	public void updateUser() throws Exception {
		ResultActions resultPut = mockMvc.perform(put("/user/"+user.getId()).contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("description", "description").param("title", "title").sessionAttr("user", user));
		resultPut.andExpect(status().isOk());
		resultPut.andExpect(content().string(startsWith("{\"id\":"+user.getId())));
	}
	
	@Test
	public void createUser() throws Exception {
		ResultActions resultPut = mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("description", "description").param("title", "title").sessionAttr("user", new User()));
		resultPut.andExpect(status().isOk());
		resultPut.andExpect(content().string(startsWith("{\"id\":")));
	}
	
	@Test
	public void deleteUser() throws Exception {
		ResultActions resultPut = mockMvc.perform(delete("/user/"+user.getId()).contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("description", "description").param("title", "title"));
		resultPut.andExpect(status().isOk());
		resultPut.andExpect(content().string(startsWith("")));
	}
}
