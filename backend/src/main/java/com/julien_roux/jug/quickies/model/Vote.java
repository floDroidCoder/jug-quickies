package com.julien_roux.jug.quickies.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "jug_vote")
public class Vote extends Entity {

	private Quicky quicky;
	
	private User voter;

	@DateTimeFormat(pattern="YYYY-MM-DD hh:mm")
	private Date submissionDate;
}
