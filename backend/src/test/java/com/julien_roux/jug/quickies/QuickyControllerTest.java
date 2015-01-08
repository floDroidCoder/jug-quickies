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
	private Quicky quicky;

	@Before
	public void setup() throws Exception {
		super.setup();
		
		quickyRepository.deleteAll();
		quicky = new Quicky();
		quickyRepository.save(quicky);
		assertThat(quicky.getId()).isNotNull();
	}
	
	@Test
	public void findAll() throws Exception {
		ResultActions result = mockMvc.perform(get("/quickies").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("description", "description").param("title", "title"));
		result.andExpect(status().isOk());
		result.andExpect(content().string(startsWith("[{\"id\":"+quicky.getId())));
	}
	
	@Test
	public void getQuicky() throws Exception {
		ResultActions result = mockMvc.perform(get("/quicky/"+quicky.getId()).contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("description", "description").param("title", "title"));
		result.andExpect(status().isOk());
		result.andExpect(view().name("/quickies/quicky-detail"));
		result.andExpect(model().attribute("quicky", quicky));
	}
	
	@Test
	public void editQuicky() throws Exception {
		ResultActions result = mockMvc.perform(get("/quicky/"+quicky.getId()+"/edit").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("description", "description").param("title", "title"));
		result.andExpect(status().isOk());
		result.andExpect(view().name("/quickies/quicky-edit"));
		result.andExpect(model().attribute("quicky", quicky));
	}
	
	@Test
	public void newQuicky() throws Exception {
		ResultActions result = mockMvc.perform(get("/quicky/new").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("description", "description").param("title", "title"));
		result.andExpect(status().isOk());
		result.andExpect(view().name("/quickies/quicky-edit"));
	}
	
	@Test
	public void updateQuicky() throws Exception {
		ResultActions resultPut = mockMvc.perform(put("/quicky/"+quicky.getId()).contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("description", "description").param("title", "title").sessionAttr("quicky", quicky));
		resultPut.andExpect(status().isOk());
		resultPut.andExpect(content().string(startsWith("{\"id\":"+quicky.getId())));
	}
	
	@Test
	public void createQuicky() throws Exception {
		ResultActions resultPut = mockMvc.perform(post("/quicky").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("description", "description").param("title", "title").sessionAttr("quicky", new Quicky()));
		resultPut.andExpect(status().isOk());
		resultPut.andExpect(content().string(startsWith("{\"id\":")));
	}
	
	@Test
	public void deleteQuicky() throws Exception {
		ResultActions resultPut = mockMvc.perform(delete("/quicky/"+quicky.getId()).contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("description", "description").param("title", "title"));
		resultPut.andExpect(status().isOk());
		resultPut.andExpect(content().string(startsWith("")));
	}
}
