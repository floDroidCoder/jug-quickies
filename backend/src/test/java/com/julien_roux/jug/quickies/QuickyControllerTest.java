package com.julien_roux.jug.quickies;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import com.julien_roux.jug.quickies.model.Quicky;

public class QuickyControllerTest extends AbstractControllerTest {
	
	@Test
	public void testQuicky() throws Exception {
		String title = "";
		String description = "";

		Quicky quicky = new Quicky();
		ResultActions resultPut = mockMvc.perform(post("/quickies").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("description", description).param("title", title).sessionAttr("quicky", quicky));
		resultPut.andExpect(status().isOk());
		Quicky object = (Quicky) resultPut.andReturn().getModelAndView().getModel().get("quicky");

		ResultActions resultGet = mockMvc.perform(get("/quickies/{id}", object.getId()));
		
		resultGet.andExpect(status().isOk()).andExpect(content().string(startsWith("{\"id\":" + object.getId())));
		
		ResultActions resultDelete = mockMvc.perform(delete("/quickies/{id}", object.getId()));
		resultDelete.andExpect(status().isOk());

		resultGet = mockMvc.perform(get("/quickies/{id}", object.getId()));
		resultGet.andExpect(status().isOk()).andExpect(content().string(""));
	}
}
