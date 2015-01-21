package com.julien_roux.jug.quickies.repository.model.dto;

import java.util.Date;

import com.julien_roux.jug.quickies.model.dto.UsergroupDTO;
import com.julien_roux.jug.quickies.repository.model.AbstractModelTest;

public class UsergroupDTOTest extends AbstractModelTest<UsergroupDTO> {

	@Override
	public UsergroupDTO getEntity() {
		UsergroupDTO dto = new UsergroupDTO();
		dto.setCreatorId("creatorId dto");
		dto.setCreatorName("creatorName dto");
		dto.setId("id dto");
		dto.setName("name dto");
		dto.setCreationDate(new Date());
		return dto;
	}

}
