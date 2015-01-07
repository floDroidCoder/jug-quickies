package com.julien_roux.jug.quickies.controller;

import java.math.BigInteger;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@RequestMapping(value = "/quicky/{id}", method = RequestMethod.GET)
	public String getQuicky(@PathVariable BigInteger id, Model model) {
		model.addAttribute("quicky", quickyRepository.findOne(id));
		return "/quickies/quicky-detail";
	}

	@RequestMapping(value = "/quicky/{id}/edit", method = RequestMethod.GET)
	public String editQuicky(@PathVariable BigInteger id, Model model) {
		model.addAttribute("quicky", quickyRepository.findOne(id));
		return "/quickies/quicky-edit";
	}

	@RequestMapping(value = "/quicky", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Quicky addQuicky(@Valid @ModelAttribute("quicky") Quicky quicky) {
		quicky.setId(null);
		return quickyRepository.save(quicky);
	}

	@RequestMapping(value = "/quicky/{id}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Quicky updateQuicky(@Valid @ModelAttribute("quicky") Quicky quicky, @PathVariable BigInteger id) {
		System.out.println("udpdate");
		quicky.setId(id);
		return quickyRepository.save(quicky);
	}

	@RequestMapping(value = "/quicky/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public void deleteQuicky(@PathVariable BigInteger id) {
		quickyRepository.delete(id);
	}
}
