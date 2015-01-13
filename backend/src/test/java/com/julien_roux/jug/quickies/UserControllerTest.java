package com.julien_roux.jug.quickies;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.julien_roux.jug.quickies.model.dto.UserDTO;

public class UserControllerTest extends AbstractControllerTest {
	
	// ************************************************************************
	// Get
	// ************************************************************************
	
	@Test
	public void findAll() throws Exception {
		connectUser();
		String url = "/users";
		MockHttpServletRequestBuilder request = prepareSecureRequest(get(url));
		ResultActions result = executeRequest(request);
		result.andExpect(content().string(startsWith("[{\"id\":"+user.getId())));
	}
	
	@Test
	public void getProfile() throws Exception {
		connectUser();
		String url = "/profile";
		MockHttpServletRequestBuilder request = prepareSecureRequest(get(url));
		ResultActions result = executeRequest(request);
		result.andExpect(view().name("/profile/profile-detail"));
		result.andExpect(model().attribute("user", new UserDTO(user)));
	}
	
	@Test
	public void getUser() throws Exception {
		connectUser();
		String url = "/user/{0}";
		MockHttpServletRequestBuilder request = prepareSecureRequest(get(url, user.getId()));
		ResultActions result = executeRequest(request);
		result.andExpect(view().name("/users/user-detail"));
		result.andExpect(model().attribute("user", user));
	}

	// ************************************************************************
	// Create
	// ************************************************************************

	@Test
	public void createUser() throws Exception {
		connectUser();
		String url = "/user";
		UserDTO toCreate = new UserDTO(user);
		toCreate.setPassword(user.getPassword());
		
		MockHttpServletRequestBuilder request = prepareSecureRequest(post(url).//
				contentType(MediaType.APPLICATION_FORM_URLENCODED).//
				param("lastname", toCreate.getLastname()).//
				param("firstname", toCreate.getFirstname()).//
				param("email", toCreate.getEmail()).//
				param("password", toCreate.getPassword()).//
				param("company", toCreate.getCompany()).//
				param("about", toCreate.getAbout()).//
				param("shamefulTechnologie", toCreate.getShamefulTechnologie()));
		
		ResultActions result = executeRequest(request);
		result.andExpect(view().name("/profile/profile-detail"));
		result.andExpect(model().attributeExists("user"));
	}

	// ************************************************************************
	// Modify
	// ************************************************************************

	@Test
	public void editUser() throws Exception {
		connectUser();
		String url = "/profile/edit";
		MockHttpServletRequestBuilder request = prepareSecureRequest(get(url, user.getId()));
		ResultActions result = executeRequest(request);
		result.andExpect(view().name("/profile/profile-edit"));
		result.andExpect(model().attribute("user", new UserDTO(user)));
	}
	
	@Test
	public void updateUser() throws Exception {
		connectUser();
		String url = "/profile/edit";
		MockHttpServletRequestBuilder request = prepareSecureRequest(post(url, user.getId()).//
				contentType(MediaType.APPLICATION_FORM_URLENCODED).//
				param("lastname", user.getLastname()).//
				param("firstname", user.getFirstname()).//
				param("email", user.getEmail()).//
				param("company", user.getCompany()).//
				param("about", user.getAbout()).//
				param("shamefulTechnologie", user.getShamefulTechnologie()));
		ResultActions result = executeRequest(request);
		result.andExpect(view().name("/profile/profile-detail"));
		result.andExpect(model().attributeExists("user"));
	}

	// ************************************************************************
	// Delete
	// ************************************************************************

	@Test
	public void deleteUser() throws Exception {
		connectUser();
		String url = "/user/{0}/delete";
		MockHttpServletRequestBuilder request = prepareSecureRequest(get(url, user.getId()));
		ResultActions result = executeRequest(request);
		result.andExpect(content().string(startsWith("")));
	}
}
