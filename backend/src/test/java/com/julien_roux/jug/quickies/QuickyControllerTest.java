package com.julien_roux.jug.quickies;

import static org.fest.assertions.Assertions.assertThat;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import com.julien_roux.jug.quickies.model.Quicky;
import com.julien_roux.jug.quickies.repository.QuickyRepository;

public class QuickyControllerTest extends AbstractControllerTest {
	
	@Autowired
	private QuickyRepository quickyRepository;
	private Quicky entity;

	@Before
	public void setup() throws Exception {
		super.setup();
		
		quickyRepository.deleteAll();
		entity = new Quicky();
		quickyRepository.save(entity);
		assertThat(entity.getId()).isNotNull();
	}
	
	@Test
	public void getQuicky() throws Exception {
		ResultActions result = mockMvc.perform(get("/quicky/"+entity.getId()).contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("description", "description").param("title", "title"));
		result.andExpect(status().isOk());
		result.andExpect(view().name("/quickies/quicky-details"));
		result.andExpect(model().attribute("quicky", entity));
	}
	
	@Test
	public void editQuicky() throws Exception {
		ResultActions result = mockMvc.perform(get("/quicky/"+entity.getId()+"/edit").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("description", "description").param("title", "title"));
		result.andExpect(status().isOk());
		result.andExpect(view().name("/quickies/quicky-edit"));
		result.andExpect(model().attribute("quicky", entity));
	}
	
	@Test
	public void testUpdateQuicky() throws Exception {
		System.out.println("test");
		ResultActions resultPut = mockMvc.perform(put("/quicky/"+entity.getId()).contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("description", "description").param("title", "title").sessionAttr("quicky", entity));
		resultPut.andExpect(status().isOk());
		resultPut.andExpect(content().string(startsWith("{\"id\":"+entity.getId())));
	}
	
	@Test
	public void testCreateQuicky() throws Exception {
		ResultActions resultPut = mockMvc.perform(post("/quicky").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("description", "description").param("title", "title").sessionAttr("quicky", new Quicky()));
		resultPut.andExpect(status().isOk());
		resultPut.andExpect(content().string(startsWith("{\"id\":")));
	}
	
	@Test
	public void testDeleteQuicky() throws Exception {
		ResultActions resultPut = mockMvc.perform(delete("/quicky/"+entity.getId()).contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("description", "description").param("title", "title"));
		resultPut.andExpect(status().isOk());
		resultPut.andExpect(content().string(startsWith("")));
	}
}
