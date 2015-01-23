package com.julien_roux.jug.quickies.repository.model.dto;

import com.julien_roux.jug.quickies.model.dto.UserDTO;
import com.julien_roux.jug.quickies.repository.model.AbstractModelTest;

public class UserDTOTest extends AbstractModelTest<UserDTO> {

	@Override
	public UserDTO getEntity() {
		UserDTO dto = new UserDTO();
		dto.setAbout("about dto");
		dto.setCompany("company dto");
		dto.setEmail("email dto");
		dto.setFirstname("firstname dto");
		dto.setLastname("lastname dto");
		dto.setPassword("password dto");
		dto.setRole("role dto");
		dto.setShamefulTechnologie("shamefulTechnologie dto");
		dto.setId("id dto");
		return dto;
	}
	
}
