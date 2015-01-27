package com.julien_roux.jug.quickies.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.julien_roux.jug.quickies.model.dto.UserDTO;

public class AdminControllerTest extends AbstractControllerTest {

	@Test
	public void getAdminAsNonConnected() throws Exception {
		String url = "/admin";
		MockHttpServletRequestBuilder request = prepareNonSecureRequest(get(url));
		ResultActions result = executeRequest(request);
		result.andExpect(view().name("errors/unauthorized"));
	}

	@Test
	public void getAdminAsUser() throws Exception {
		connectUser();
		String url = "/admin";
		MockHttpServletRequestBuilder request = prepareSecureRequest(get(url));
		ResultActions result = executeRequest(request);
		result.andExpect(view().name("errors/unauthorized"));
	}
	
	@Test
	public void getAdminAsAdmin() throws Exception {
		connectAdmin();
		String url = "/admin";
		MockHttpServletRequestBuilder request = prepareSecureRequest(get(url));
		ResultActions result = executeRequest(request);
		result.andExpect(view().name("/admin/admin"));
	}
	
}
