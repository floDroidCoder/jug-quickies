package com.julien_roux.jug.quickies.controller;

import static org.fest.assertions.Assertions.assertThat;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.julien_roux.jug.quickies.model.Quicky;
import com.julien_roux.jug.quickies.model.Usergroup;
import com.julien_roux.jug.quickies.model.dto.QuickyDTO;
import com.julien_roux.jug.quickies.model.dto.UsergroupDTO;
import com.julien_roux.jug.quickies.repository.UsergroupRepository;

public class UsergroupControllerTest extends AbstractControllerTest {

	@Autowired
	private UsergroupRepository usergroupRepository;
	
	private Usergroup usergroup;

	@Before
	public void setup() throws Exception {
		super.setup();

		usergroupRepository.deleteAll();
		usergroup = new Usergroup();
		usergroup.setName("usergroup test controller");
		usergroup.setCreationDate(new Date());
		usergroupRepository.save(usergroup);
		assertThat(usergroup.getId()).isNotNull();
	}

	@Test
	public void findAll() throws Exception {
		connectUser();
		String url = "/usergroups";
		MockHttpServletRequestBuilder request = prepareSecureRequest(get(url));
		ResultActions result = executeRequest(request);
		result.andExpect(status().isOk());
		result.andExpect(content().string(startsWith("[{\"id\":\"" + usergroup.getId() + "\"")));
	}

	@Test
	public void getUsergroup() throws Exception {
		connectUser();
		String url = "/usergroup/{0}";
		MockHttpServletRequestBuilder request = prepareSecureRequest(get(url, usergroup.getId()));
		ResultActions result = executeRequest(request);
		result.andExpect(status().isOk());
		result.andExpect(view().name("/usergroups/usergroup-detail"));
		result.andExpect(model().attribute("usergroup", new UsergroupDTO(usergroup)));
	}

	@Test
	public void getUsergroupPost() throws Exception {
		connectUser();
		String url = "/usergroup/{0}";
		MockHttpServletRequestBuilder request = prepareSecureRequest(post(url, usergroup.getId()));
		ResultActions result = executeRequest(request);
		result.andExpect(status().is4xxClientError());
		result.andExpect(status().isMethodNotAllowed());
	}

	// ************************************************************************
	// Create
	// ************************************************************************

	@Test
	public void newUsergroup() throws Exception {
		connectUser();
		String url = "/usergroup/create";
		MockHttpServletRequestBuilder request = prepareSecureRequest(get(url));
		ResultActions result = executeRequest(request);
		result.andExpect(status().isOk());
		result.andExpect(view().name("/usergroups/usergroup-edit"));
	}

	@Test
	public void createUsergroup() throws Exception {
		connectUser();
		String url = "/usergroup/create";
		UsergroupDTO toCreate = new UsergroupDTO();
		toCreate.setCreationDate(new Date());
		toCreate.setCreatorId("1010101");
		toCreate.setName("usergroup");

		MockHttpServletRequestBuilder request = prepareSecureRequest(post(url).//
		            contentType(MediaType.APPLICATION_FORM_URLENCODED).//
		            param("creationDate", "2015-01-01T12:00").//
		            param("creatorId", toCreate.getCreatorId()).//
		            param("name", toCreate.getName()));

		ResultActions result = executeRequest(request);
		result.andExpect(status().isOk());
		result.andExpect(view().name("/usergroups/usergroup-detail"));
		result.andExpect(model().attributeExists("usergroup"));
	}

	// ************************************************************************
	// Modify
	// ************************************************************************

	@Test
	public void editUsergroup() throws Exception {
		connectUser();
		String url = "/usergroup/{0}/edit";
		MockHttpServletRequestBuilder request = prepareSecureRequest(get(url, usergroup.getId()));
		ResultActions result = executeRequest(request);
		result.andExpect(status().isOk());
		result.andExpect(view().name("/usergroups/usergroup-edit"));
		result.andExpect(model().attribute("usergroup", new UsergroupDTO(usergroup)));
	}

	@Test
	public void updateUsergroup() throws Exception {
		connectUser();
		String url = "/usergroup/{0}/edit";
		MockHttpServletRequestBuilder request = prepareSecureRequest(post(url, usergroup.getId()).//
		            contentType(MediaType.APPLICATION_FORM_URLENCODED).//
		            param("creationDate", "2015-01-01T12:00").//
		            param("creatorId", usergroup.getCreatorId()).//
		            param("name", usergroup.getName()));
		ResultActions result = executeRequest(request);
		result.andExpect(status().isOk());
		result.andExpect(view().name("/usergroups/usergroup-detail"));
		result.andExpect(model().attributeExists("usergroup"));
	}

	// ************************************************************************
	// Delete
	// ************************************************************************

	@Test
	public void deleteUsergroup() throws Exception {
		connectUser();
		String url = "/usergroup/{0}/delete";
		MockHttpServletRequestBuilder request = prepareSecureRequest(get(url, usergroup.getId()));
		ResultActions result = executeRequest(request);
		result.andExpect(status().is3xxRedirection());
		result.andExpect(content().string(startsWith("")));
		result.andExpect(view().name("redirect:/admin"));
	}
}
