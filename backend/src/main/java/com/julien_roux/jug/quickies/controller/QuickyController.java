package com.julien_roux.jug.quickies.controller;

import java.math.BigInteger;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	private final Logger logger = LoggerFactory.getLogger(QuickyController.class);

	private QuickyRepository quickyRepository;

	@Autowired
	public QuickyController(QuickyRepository quickyRepository) {
		this.quickyRepository = quickyRepository;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Quicky getQuicky(@PathVariable BigInteger id) {
		logger.debug("get quicky " + id);
		Quicky quicky = quickyRepository.findOne(id);
		logger.debug("get quicky performed");
		return quicky;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<Quicky> getAllQuickies() {
		logger.debug("get all quickies");
		List<Quicky> findAll = quickyRepository.findAll();
		logger.debug("get all quickies performed");
		return findAll;
	}

	@RequestMapping(method = RequestMethod.POST)
	public Quicky addQuicky(@Valid @ModelAttribute("quicky") Quicky quicky) {
		logger.debug("add quicky");
		quicky.setId(null);
		Quicky save = quickyRepository.save(quicky);
		logger.debug("add quicky performed");
		return save;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Quicky updateQuicky(@RequestBody Quicky quicky, @PathVariable BigInteger id) {
		logger.debug("update quicky " + id);
		quicky.setId(id);
		Quicky save = quickyRepository.save(quicky);
		logger.debug("update quicky performed");
		return save;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public void deleteQuicky(@PathVariable BigInteger id) {
		logger.debug("delete quicky " + id);
		quickyRepository.delete(id);
		logger.debug("delete quicky performed");
	}
}
