package com.julien_roux.jug.quickies.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.julien_roux.jug.quickies.model.Quicky;
import com.julien_roux.jug.quickies.repository.QuickyRepository;

@Controller
@RequestMapping("/quickies")
public class QuickyController {
	
	private QuickyRepository quickyRepository;

	@Autowired
	public QuickyController(QuickyRepository quickyRepository) {
		this.quickyRepository = quickyRepository;
	}

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<Quicky> getAllQuickies() {
        return quickyRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Quicky addQuicky(@RequestBody Quicky quicky) {
      quicky.setId(null);
      return quickyRepository.save(quicky);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Quicky updateQuicky(@RequestBody Quicky quicky, @PathVariable BigInteger id) {
      quicky.setId(id);
      return quickyRepository.save(quicky);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteQuicky(@PathVariable Long id) {
    	quickyRepository.delete(id);
    }
}
