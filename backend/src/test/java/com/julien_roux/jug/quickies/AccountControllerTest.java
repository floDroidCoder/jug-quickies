package com.julien_roux.jug.quickies;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import com.julien_roux.jug.quickies.model.Account;

public class AccountControllerTest extends AbstractControllerTest {

	@Test
	@Ignore("Security missing for it to work")
	public void testAccount() throws Exception {
		Account account = new Account("email", "password", "role");
		ResultActions putAccount = mockMvc.perform(post("/accounts").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("description", "").param("title", "").sessionAttr("account", account));
		putAccount.andExpect(status().isOk());
		Account object = (Account) putAccount.andReturn().getModelAndView().getModel().get("account");

		ResultActions getAccount = mockMvc.perform(get("/accounts/{id}", object.getId()));

		getAccount.andExpect(status().isOk()).andExpect(model().attribute("todo", hasProperty("id", is(1L))))
				.andExpect(model().attribute("todo", hasProperty("description", is("Lorem ipsum"))))
				.andExpect(model().attribute("todo", hasProperty("title", is("Foo"))));
	}
}
