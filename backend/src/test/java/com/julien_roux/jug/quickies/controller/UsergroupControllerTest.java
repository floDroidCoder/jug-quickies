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

import com.julien_roux.jug.quickies.model.Usergroup;
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
	public void newUsergroupAsGuest() throws Exception {
		String url = "/usergroup/create";
		MockHttpServletRequestBuilder request = prepareNonSecureRequest(get(url));
		ResultActions result = executeRequest(request);
		result.andExpect(status().isOk());
		result.andExpect(view().name("errors/unauthorized"));
	}
	
	@Test
	public void newUsergroupAsUser() throws Exception {
		connectUser();
		String url = "/usergroup/create";
		MockHttpServletRequestBuilder request = prepareSecureRequest(get(url));
		ResultActions result = executeRequest(request);
		result.andExpect(status().isOk());
		result.andExpect(view().name("errors/unauthorized"));
	}

	@Test
	public void newUsergroupAsAdmin() throws Exception {
		connectAdmin();
		String url = "/usergroup/create";
		MockHttpServletRequestBuilder request = prepareSecureRequest(get(url));
		ResultActions result = executeRequest(request);
		result.andExpect(status().isOk());
		result.andExpect(view().name("/usergroups/usergroup-edit"));
	}

	@Test
	public void createUsergroupAsGuest() throws Exception {
		String url = "/usergroup/create";
		MockHttpServletRequestBuilder request = prepareNonSecureRequest(post(url).//
		            contentType(MediaType.APPLICATION_FORM_URLENCODED).//
		            param("name", "name"));

		ResultActions result = executeRequest(request);
		result.andExpect(status().isOk());
		result.andExpect(view().name("errors/unauthorized"));
	}

	@Test
	public void createUsergroupAsUser() throws Exception {
		connectUser();
		String url = "/usergroup/create";
		UsergroupDTO toCreate = new UsergroupDTO();
		toCreate.setCreatorId("1010101");
		toCreate.setName("usergroup");

		MockHttpServletRequestBuilder request = prepareSecureRequest(post(url).//
		            contentType(MediaType.APPLICATION_FORM_URLENCODED).//
		            param("name", toCreate.getName()));

		ResultActions result = executeRequest(request);
		result.andExpect(status().isOk());
		result.andExpect(view().name("errors/unauthorized"));
	}

	@Test
	public void createUsergroupAsAdmin() throws Exception {
		connectAdmin();
		String url = "/usergroup/create";
		UsergroupDTO toCreate = new UsergroupDTO();
		toCreate.setCreatorId("1010101");
		toCreate.setName("usergroup");

		MockHttpServletRequestBuilder request = prepareSecureRequest(post(url).//
		            contentType(MediaType.APPLICATION_FORM_URLENCODED).//
		            param("name", toCreate.getName()));

		ResultActions result = executeRequest(request);
		result.andExpect(status().is3xxRedirection());
		result.andExpect(view().name("redirect:/admin"));
	}

	// ************************************************************************
	// Modify
	// ************************************************************************

	@Test
	public void editUsergroupAsGuest() throws Exception {
		String url = "/usergroup/{0}/edit";
		MockHttpServletRequestBuilder request = prepareNonSecureRequest(get(url, usergroup.getId()));
		ResultActions result = executeRequest(request);
		result.andExpect(status().isOk());
		result.andExpect(view().name("errors/unauthorized"));
	}

	@Test
	public void editUsergroupAsUser() throws Exception {
		connectUser();
		String url = "/usergroup/{0}/edit";
		MockHttpServletRequestBuilder request = prepareSecureRequest(get(url, usergroup.getId()));
		ResultActions result = executeRequest(request);
		result.andExpect(status().isOk());
		result.andExpect(view().name("errors/unauthorized"));
	}

	@Test
	public void editUsergroupAsAdmin() throws Exception {
		connectAdmin();
		String url = "/usergroup/{0}/edit";
		MockHttpServletRequestBuilder request = prepareSecureRequest(get(url, usergroup.getId()));
		ResultActions result = executeRequest(request);
		result.andExpect(status().isOk());
		result.andExpect(view().name("/usergroups/usergroup-edit"));
		result.andExpect(model().attribute("usergroup", new UsergroupDTO(usergroup)));
	}

	@Test
	public void updateUsergroupAsGuest() throws Exception {
		String url = "/usergroup/{0}/edit";
		MockHttpServletRequestBuilder request = prepareNonSecureRequest(post(url, usergroup.getId()).//
		            contentType(MediaType.APPLICATION_FORM_URLENCODED).//
		            param("name", usergroup.getName()));
		ResultActions result = executeRequest(request);
		result.andExpect(status().isOk());
		result.andExpect(view().name("errors/unauthorized"));
	}

	@Test
	public void updateUsergroupAsUser() throws Exception {
		connectUser();
		String url = "/usergroup/{0}/edit";
		MockHttpServletRequestBuilder request = prepareSecureRequest(post(url, usergroup.getId()).//
		            contentType(MediaType.APPLICATION_FORM_URLENCODED).//
		            param("name", usergroup.getName()));
		ResultActions result = executeRequest(request);
		result.andExpect(status().isOk());
		result.andExpect(view().name("errors/unauthorized"));
	}

	@Test
	public void updateUsergroupAsAdmin() throws Exception {
		connectAdmin();
		String url = "/usergroup/{0}/edit";
		MockHttpServletRequestBuilder request = prepareSecureRequest(post(url, usergroup.getId()).//
		            contentType(MediaType.APPLICATION_FORM_URLENCODED).//
		            param("name", usergroup.getName()));
		ResultActions result = executeRequest(request);
		result.andExpect(status().is3xxRedirection());
		result.andExpect(view().name("redirect:/admin"));
	}

	// ************************************************************************
	// Delete
	// ************************************************************************

	@Test
	public void deleteUsergroupAsUser() throws Exception {
		connectUser();
		String url = "/usergroup/{0}/delete";
		MockHttpServletRequestBuilder request = prepareSecureRequest(get(url, usergroup.getId()));
		ResultActions result = executeRequest(request);
		result.andExpect(status().isOk());
		result.andExpect(view().name("errors/unauthorized"));
	}
	
	@Test
	public void deleteUsergroup() throws Exception {
		connectAdmin();
		String url = "/usergroup/{0}/delete";
		MockHttpServletRequestBuilder request = prepareSecureRequest(get(url, usergroup.getId()));
		ResultActions result = executeRequest(request);
		result.andExpect(status().is3xxRedirection());
		result.andExpect(content().string(startsWith("")));
		result.andExpect(view().name("redirect:/admin"));
	}
}
