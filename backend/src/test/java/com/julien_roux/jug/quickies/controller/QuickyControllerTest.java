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
import com.julien_roux.jug.quickies.model.dto.QuickyDTO;
import com.julien_roux.jug.quickies.repository.QuickyRepository;

public class QuickyControllerTest extends AbstractControllerTest {
	
	@Autowired
	private QuickyRepository quickyRepository;
	private Quicky quicky;

	@Before
	public void setup() throws Exception {
		super.setup();
		
		quickyRepository.deleteAll();
		quicky = new Quicky("Jsf en environnement pro", "du troll de qualité", "jug");
		quicky.setLocation("HEPIA");
		quicky.setSubmissionDate(new Date());
		quicky.setPresenter(user);
		quickyRepository.save(quicky);
		assertThat(quicky.getId()).isNotNull();
	}
	
	@Test
	public void findAll() throws Exception {
		connectUser();
		String url = "/quickies";
		MockHttpServletRequestBuilder request = prepareSecureRequest(get(url));
		ResultActions result = executeRequest(request);
		result.andExpect(status().isOk());
		result.andExpect(content().string(startsWith("[{\"id\":\""+quicky.getId() + "\"")));
	}
	
	@Test
	public void getQuicky() throws Exception {
		connectUser();
		String url = "/quicky/{0}";
		MockHttpServletRequestBuilder request = prepareSecureRequest(get(url, quicky.getId()));
		ResultActions result = executeRequest(request);
		result.andExpect(status().isOk());
		result.andExpect(view().name("/quickies/quicky-detail"));
		result.andExpect(model().attribute("quicky", new QuickyDTO(quicky)));
	}
	
	@Test
	public void getQuickyPost() throws Exception {
		connectUser();
		String url = "/quicky/{0}";
		MockHttpServletRequestBuilder request = prepareSecureRequest(post(url, quicky.getId()));
		ResultActions result = executeRequest(request);
		result.andExpect(status().is4xxClientError());
		result.andExpect(status().isMethodNotAllowed());
	}

	// ************************************************************************
	// Create
	// ************************************************************************
	
	@Test
	public void newQuicky() throws Exception {
		connectUser();
		String url = "/quicky/create";
		MockHttpServletRequestBuilder request = prepareSecureRequest(get(url));
		ResultActions result = executeRequest(request);
		result.andExpect(status().isOk());
		result.andExpect(view().name("/quickies/quicky-edit"));
	}
	
	@Test
	public void createQuicky() throws Exception {
		connectUser();
		String url = "/quicky/create";
		QuickyDTO toCreate = new QuickyDTO();
		toCreate.setDescription("description");
		toCreate.setTitle("title");
		toCreate.setUsergroup("usergroup");
		toCreate.setLocation("location");
		
		MockHttpServletRequestBuilder request = prepareSecureRequest(post(url).//
				contentType(MediaType.APPLICATION_FORM_URLENCODED).//
				param("description", toCreate.getDescription()).//
				param("title", toCreate.getTitle()).//
				param("usergroup", toCreate.getUsergroup()).
				param("location", toCreate.getLocation())
				);
		
		ResultActions result = executeRequest(request);
		result.andExpect(status().isOk());
		result.andExpect(view().name("/quickies/quicky-detail"));
		result.andExpect(model().attributeExists("quicky"));
	}

	// ************************************************************************
	// Modify
	// ************************************************************************
	
	@Test
	public void editQuicky() throws Exception {
		connectUser();
		String url = "/quicky/{0}/edit";
		MockHttpServletRequestBuilder request = prepareSecureRequest(get(url, quicky.getId()));
		ResultActions result = executeRequest(request);
		result.andExpect(status().isOk());
		result.andExpect(view().name("/quickies/quicky-edit"));
		result.andExpect(model().attribute("quicky", new QuickyDTO(quicky)));
	}
	
	@Test
	public void updateQuicky() throws Exception {
		connectUser();
		String url = "/quicky/{0}/edit";
		MockHttpServletRequestBuilder request = prepareSecureRequest(post(url, quicky.getId()).//
				contentType(MediaType.APPLICATION_FORM_URLENCODED).//
				param("description", quicky.getDescription()).//
				param("email", quicky.getPresenter().getEmail()).//
				param("title", quicky.getTitle()).//
				param("usergroup", quicky.getUsergroup()).//
				param("location", quicky.getLocation()));
		ResultActions result = executeRequest(request);
		result.andExpect(status().isOk());
		result.andExpect(view().name("/quickies/quicky-detail"));
		result.andExpect(model().attributeExists("quicky"));
	}

	@Test
	public void updateQuickyWrongUser() throws Exception {
		connectWrongUser();
		String url = "/quicky/{0}/edit";
		MockHttpServletRequestBuilder request = prepareSecureRequest(post(url, quicky.getId()).//
				contentType(MediaType.APPLICATION_FORM_URLENCODED).//
				param("description", quicky.getDescription()).//
				param("title", quicky.getTitle()).//
				param("usergroup", quicky.getUsergroup()));
		ResultActions result = executeRequest(request);
		result.andExpect(status().isOk());
		result.andExpect(view().name("errors/unauthorized"));
	}
	
	// ************************************************************************
	// Delete
	// ************************************************************************
	
	@Test
	public void deleteQuicky() throws Exception {
		connectUser();
		String url = "/quicky/{0}/delete";
		MockHttpServletRequestBuilder request = prepareSecureRequest(get(url, quicky.getId()));
		ResultActions result = executeRequest(request);
		result.andExpect(status().isOk());
		result.andExpect(content().string(startsWith("")));
		result.andExpect(view().name("index"));
	}
}
