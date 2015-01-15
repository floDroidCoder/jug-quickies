package com.julien_roux.jug.quickies.repository.model.dto;

import java.util.Date;

import com.julien_roux.jug.quickies.model.dto.QuickyDTO;
import com.julien_roux.jug.quickies.repository.model.AbstractModelTest;

public class QuickyDTOTest extends AbstractModelTest<QuickyDTO> {

	@Override
	public QuickyDTO getEntity() {
		QuickyDTO dto = new QuickyDTO();
		dto.setDescription("description");
		dto.setEmail("email");
		dto.setSubmissionDate(new Date());
		dto.setTitle("title");
		dto.setUsergroup("usergroup");
		dto.setId("id");
		return dto;
	}

}
