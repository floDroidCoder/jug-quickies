package com.julien_roux.jug.quickies.controller;

import java.math.BigInteger;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.julien_roux.jug.quickies.model.Quicky;
import com.julien_roux.jug.quickies.model.User;
import com.julien_roux.jug.quickies.model.dto.QuickyDTO;
import com.julien_roux.jug.quickies.repository.QuickyRepository;
import com.julien_roux.jug.quickies.repository.UserRepository;

@Controller
public class QuickyController {

	private static final String DETAIL_PAGE = "/quickies/quicky-detail";
	private static final String EDIT_PAGE = "/quickies/quicky-edit";
	private QuickyRepository quickyRepository;
	private UserRepository userRepository;

	@Autowired
	public QuickyController(QuickyRepository quickyRepository, UserRepository userRepository) {
		this.quickyRepository = quickyRepository;
		this.userRepository = userRepository;
	}

	// ************************************************************************
	// Get
	// ************************************************************************

	@RequestMapping(value = "/quickies", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<Quicky> findAll() {
		return quickyRepository.findAll();
	}

	@RequestMapping(value = "/quicky/{id}", method = RequestMethod.GET)
	public String get(@PathVariable BigInteger id, Model model) {
		Quicky quicky = quickyRepository.findOne(id);
		model.addAttribute("quicky", new QuickyDTO(quicky));
		return DETAIL_PAGE;
	}

	// ************************************************************************
	// Create
	// ************************************************************************
	
	@RequestMapping(value = "/quicky/create", method = RequestMethod.GET)
	public String create(Model model) {
		QuickyDTO quickyDTO = new QuickyDTO();
		model.addAttribute("quicky", quickyDTO);
		return EDIT_PAGE;
	}

	@RequestMapping(value = "/quicky/create", method = RequestMethod.POST)
	public String submit(@Valid @ModelAttribute("quicky") QuickyDTO quickyDTO, BindingResult result, Principal principal, Model model) {
		if (result.hasErrors()) {
            return EDIT_PAGE;
        }
		
		Quicky quicky = new Quicky();
		quicky.setDescription(quickyDTO.getDescription());
		quicky.setUsergroup(quickyDTO.getUsergroup());
		quicky.setTitle(quickyDTO.getTitle());
		quicky.setSubmissionDate(new Date());
		quicky.setId(null);
		
		User presenter = userRepository.findByEmail(principal.getName());		
		
		quicky.setPresenter(presenter);
		quickyRepository.save(quicky);
		
		model.addAttribute("quicky", new QuickyDTO(quicky));
		return DETAIL_PAGE;
	}

	// ************************************************************************
	// Modify
	// ************************************************************************

	@RequestMapping(value = "/quicky/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable BigInteger id, Model model, Principal principal) throws Exception {
		Quicky quicky = quickyRepository.findOne(id);
		if(!quicky.getPresenter().getEmail().equals(principal.getName())) {
			//TODO 
			throw new Exception();
		}
		model.addAttribute("quicky", new QuickyDTO(quicky));
		return EDIT_PAGE;
	}

	@RequestMapping(value = "/quicky/{id}/edit", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("quicky") QuickyDTO quickyDTO, @PathVariable BigInteger id, Model model, Principal principal) throws Exception {
		Quicky quicky = quickyRepository.findOne(id);
		
		if(!quickyDTO.getEmail().equals(principal.getName())) {
			//TODO 
			throw new Exception();
		}
		
		quicky.setDescription(quickyDTO.getDescription());
		quicky.setUsergroup(quickyDTO.getUsergroup());
		quicky.setTitle(quickyDTO.getTitle());
		quicky.setDescription(quickyDTO.getDescription());
		
		quickyRepository.save(quicky);
		model.addAttribute("quicky", new QuickyDTO(quicky));
		return DETAIL_PAGE;
	}

	// ************************************************************************
	// Vote
	// ************************************************************************

	@RequestMapping(value = "/quicky/{id}/vote", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public void vote(@PathVariable BigInteger id) {
	}

	// ************************************************************************
	// Delete
	// ************************************************************************

	@RequestMapping(value = "/quicky/{id}/delete", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable BigInteger id) {
		quickyRepository.delete(id);
	}
}
