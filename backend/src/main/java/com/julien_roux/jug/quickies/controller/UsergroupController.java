package com.julien_roux.jug.quickies.controller;

import java.math.BigInteger;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

import com.julien_roux.jug.quickies.exception.UnauthorizedActionException;
import com.julien_roux.jug.quickies.model.User;
import com.julien_roux.jug.quickies.model.Usergroup;
import com.julien_roux.jug.quickies.model.dto.UsergroupDTO;
import com.julien_roux.jug.quickies.repository.UserRepository;
import com.julien_roux.jug.quickies.repository.UsergroupRepository;

@Controller
public class UsergroupController {

	private static final String USERGROUP_EDIT_PAGE = "/usergroups/usergroup-edit";
	private static final String USERGROUP_DETAIL_PAGE = "/usergroups/usergroup-detail";
	private static final String ADMIN = "redirect:/admin";

	@Autowired
	private UsergroupRepository usergroupRepository;
	@Autowired
	private UserRepository userRepository;

	// ************************************************************************
	// Get
	// ************************************************************************

	@RequestMapping(value = "/usergroups", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<UsergroupDTO> findAll() {
		return usergroupsToDtoList(usergroupRepository.findAll());
	}

	@RequestMapping(value = "/usergroup/{id}", method = RequestMethod.GET)
	public String get(@PathVariable BigInteger id, Model model) {
		model.addAttribute("usergroup", new UsergroupDTO(usergroupRepository.findOne(id)));
		return USERGROUP_DETAIL_PAGE;
	}

	// ************************************************************************
	// Create
	// ************************************************************************

	@RequestMapping(value = "/usergroup/create", method = RequestMethod.GET)
	public String create(Model model, Principal principal) throws UnauthorizedActionException {
		if (principal == null) {
			throw new UnauthorizedActionException();
		}
		User currentUser = userRepository.findByEmail(principal.getName());
		if (currentUser == null || !"ADMIN_ROLE".equals(currentUser.getRole())) {
			throw new UnauthorizedActionException();
		}
		UsergroupDTO usergroupDTO = new UsergroupDTO();
		model.addAttribute("usergroup", usergroupDTO);
		return USERGROUP_EDIT_PAGE;
	}

	@RequestMapping(value = "/usergroup/create", method = RequestMethod.POST)
	public String submit(@Valid @ModelAttribute("usergroup") UsergroupDTO usergroupDTO, BindingResult result,
	            Principal principal, Model model) throws UnauthorizedActionException {
		if (result.hasErrors()) {
			return USERGROUP_EDIT_PAGE;
		}
		if (principal == null) {
			throw new UnauthorizedActionException();
		}
		User currentUser = userRepository.findByEmail(principal.getName());
		if (currentUser == null || !"ADMIN_ROLE".equals(currentUser.getRole())) {
			throw new UnauthorizedActionException();
		}

		Usergroup usergroup = usergroupDTO.toUsergroup();
		usergroup.setCreationDate(new Date());

		User creator = userRepository.findByEmail(principal.getName());
		usergroup.setCreator(creator);
		usergroup = usergroupRepository.save(usergroup);

		model.addAttribute("usergroup", new UsergroupDTO(usergroup));
		return ADMIN;
	}

	// ************************************************************************
	// Modify
	// ************************************************************************

	@RequestMapping(value = "/usergroup/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable BigInteger id, Model model, Principal principal) throws Exception {
		if (principal == null) {
			throw new UnauthorizedActionException();
		}
		User currentUser = userRepository.findByEmail(principal.getName());
		if (currentUser == null || !"ADMIN_ROLE".equals(currentUser.getRole())) {
			throw new UnauthorizedActionException();
		}
		Usergroup usergroup = usergroupRepository.findOne(id);
		model.addAttribute("usergroup", new UsergroupDTO(usergroup));
		return USERGROUP_EDIT_PAGE;
	}

	@RequestMapping(value = "/usergroup/{id}/edit", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("usergroup") UsergroupDTO usergroupDTO, @PathVariable BigInteger id,
	            BindingResult result, Model model, Principal principal) throws Exception {
		if (principal == null) {
			throw new UnauthorizedActionException();
		}
		User currentUser = userRepository.findByEmail(principal.getName());
		if (currentUser == null || !"ADMIN_ROLE".equals(currentUser.getRole())) {
			throw new UnauthorizedActionException();
		}
		if (result.hasErrors()) {
			return USERGROUP_EDIT_PAGE;
		}
		Usergroup usergroup = usergroupRepository.findOne(id);
		usergroup.setName(usergroupDTO.getName());

		usergroupRepository.save(usergroup);
		model.addAttribute("usergroup", new UsergroupDTO(usergroup));
		return ADMIN;
	}

	// ************************************************************************
	// Delete
	// ************************************************************************

	@RequestMapping(value = "/usergroup/{id}/delete", method = RequestMethod.GET)
	public String deleteUsergroup(@PathVariable BigInteger id, Principal principal) throws UnauthorizedActionException {
		if (principal == null) {
			throw new UnauthorizedActionException();
		}
		User currentUser = userRepository.findByEmail(principal.getName());
		if (currentUser == null || !"ADMIN_ROLE".equals(currentUser.getRole())) {
			throw new UnauthorizedActionException();
		}
		usergroupRepository.delete(id);

		return "redirect:/admin";
	}

	private List<UsergroupDTO> usergroupsToDtoList(List<Usergroup> usergroups) {
		return usergroups.stream().map(ug -> {
			return new UsergroupDTO(ug);
		}).collect(Collectors.toList());
	}
}
