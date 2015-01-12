package com.julien_roux.jug.quickies;

import static org.fest.assertions.Assertions.assertThat;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.julien_roux.jug.quickies.model.Quicky;
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
		quicky.setSubmissionDate(new Date());
		quicky.setPresenter(user);
		quickyRepository.save(quicky);
		assertThat(quicky.getId()).isNotNull();
	}
	
	@Test
	public void findAll() throws Exception {
		String url = "/quickies";
		MockHttpServletRequestBuilder request = prepareSecureRequest(get(url));
		ResultActions result = executeRequest(request);
		result.andExpect(content().string(startsWith("[{\"id\":"+quicky.getId())));
	}
	
	@Test
	public void getQuicky() throws Exception {
		String url = "/quicky/{0}";
		MockHttpServletRequestBuilder request = prepareSecureRequest(get(url, quicky.getId()));
		ResultActions result = executeRequest(request);
		result.andExpect(view().name("/quickies/quicky-detail"));
		result.andExpect(model().attribute("quicky", quicky));
	}

	// ************************************************************************
	// Create
	// ************************************************************************
	
	@Test
	public void newQuicky() throws Exception {
		String url = "/quicky/create";
		MockHttpServletRequestBuilder request = prepareSecureRequest(get(url));
		ResultActions result = executeRequest(request);
		result.andExpect(view().name("/quickies/quicky-edit"));
	}
	
	@Test
	public void createQuicky() throws Exception {
		String url = "/quicky/create";
		Quicky toCreate = new Quicky("titleTest", "descriptionTest", "usergroupTest");
		toCreate.setSubmissionDate(new Date());
		toCreate.setUsergroup("usergroupTest");
		MockHttpServletRequestBuilder request = prepareSecureRequest(post(url).sessionAttr("quicky", toCreate));
		ResultActions result = executeRequest(request);
		result.andExpect(view().name("/quickies/quicky-detail"));
		result.andExpect(model().attributeExists("quicky"));
		result.andExpect(model().attribute("quicky", toCreate));
	}

	// ************************************************************************
	// Modify
	// ************************************************************************
	
	@Test
	public void editQuicky() throws Exception {
		String url = "/quicky/{0}/edit";
		MockHttpServletRequestBuilder request = prepareSecureRequest(get(url, quicky.getId()));
		ResultActions result = executeRequest(request);
		result.andExpect(view().name("/quickies/quicky-edit"));
		result.andExpect(model().attribute("quicky", quicky));
	}
	
	@Test
	public void updateQuicky() throws Exception {
		String url = "/quicky/{0}/edit";
		MockHttpServletRequestBuilder request = prepareSecureRequest(post(url, quicky.getId()).sessionAttr("quicky", quicky));
		ResultActions result = executeRequest(request);
		result.andExpect(view().name("/quickies/quicky-detail"));
		result.andExpect(model().attributeExists("quicky"));
		result.andExpect(model().attribute("quicky", quicky));
	}
	
	@Test
	public void deleteQuicky() throws Exception {
		String url = "/quicky/{0}/delete";
		MockHttpServletRequestBuilder request = prepareSecureRequest(get(url, quicky.getId()));
		ResultActions result = executeRequest(request);
		result.andExpect(content().string(startsWith("")));
	}
}
