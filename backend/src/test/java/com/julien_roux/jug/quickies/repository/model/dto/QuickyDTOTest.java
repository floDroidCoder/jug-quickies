package com.julien_roux.jug.quickies.repository.model.dto;

import java.util.Date;

import com.julien_roux.jug.quickies.model.dto.QuickyDTO;
import com.julien_roux.jug.quickies.repository.model.AbstractModelTest;

public class QuickyDTOTest extends AbstractModelTest<QuickyDTO> {

	@Override
	public QuickyDTO getEntity() {
		QuickyDTO dto = new QuickyDTO();
		dto.setDescription("description dto");
		dto.setId("id dto");
		dto.setLocation("location dto");
		dto.setNbVote(1);
		dto.setPresenterId("123456789");
		dto.setPresenterName("Georges Wolinski");
		dto.setSubmissionDate(new Date());
		dto.setTitle("title dto");
		dto.setUsergroup("usergroup dto");
		return dto;
	}

}
