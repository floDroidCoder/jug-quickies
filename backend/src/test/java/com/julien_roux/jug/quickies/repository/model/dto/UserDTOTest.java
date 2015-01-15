package com.julien_roux.jug.quickies.repository.model.dto;

import com.julien_roux.jug.quickies.model.dto.UserDTO;
import com.julien_roux.jug.quickies.repository.model.AbstractModelTest;

public class UserDTOTest extends AbstractModelTest<UserDTO> {

	@Override
	public UserDTO getEntity() {
		UserDTO dto = new UserDTO();
		dto.setAbout("about");
		dto.setCompany("company");
		dto.setEmail("email");
		dto.setFirstname("firstname");
		dto.setLastname("lastname");
		dto.setPassword("password");
		dto.setRole("role");
		dto.setShamefulTechnologie("shamefulTechnologie");
		dto.setId("id");
		return dto;
	}

}
