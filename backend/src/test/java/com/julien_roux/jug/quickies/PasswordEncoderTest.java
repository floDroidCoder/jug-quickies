package com.julien_roux.jug.quickies;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class PasswordEncoderTest {

	private static final String PASSWORD = "ThisIsMyPassword";

	@Test
	public void encodePassword() throws Exception {
		String pass = WebSecurityConfig.encodePassword(PASSWORD);
		String pass2 = WebSecurityConfig.encodePassword(PASSWORD);
		System.out.println(pass);
		Assertions.assertThat(pass).isNotEqualTo(pass2);
	}
	
}
