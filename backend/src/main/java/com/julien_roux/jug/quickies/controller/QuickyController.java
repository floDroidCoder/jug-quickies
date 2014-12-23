package com.julien_roux.jug.quickies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.julien_roux.jug.quickies.model.Account;
import com.julien_roux.jug.quickies.model.Quicky;
import com.julien_roux.jug.quickies.repository.QuickyRepository;

@Controller
public class QuickyController {
	
	private QuickyRepository quickyRepository;

	@Autowired
	public QuickyController(QuickyRepository quickyRepository) {
		this.quickyRepository = quickyRepository;
	}

    @RequestMapping(value = "/quickies", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<Quicky> getAllQuickies() {
        return quickyRepository.findAll();
    }
}
