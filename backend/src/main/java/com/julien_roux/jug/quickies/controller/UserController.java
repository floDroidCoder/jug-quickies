package com.julien_roux.jug.quickies.controller;

import java.math.BigInteger;
import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.julien_roux.jug.quickies.model.User;
import com.julien_roux.jug.quickies.model.dto.UserDTO;
import com.julien_roux.jug.quickies.repository.UserRepository;
import com.julien_roux.jug.quickies.security.UserService;

@Controller
public class UserController {

	private static final String PROFILE_EDIT_PAGE = "/profile/profile-edit";
	private static final String PROFILE_DETAIL_PAGE = "/profile/profile-detail";
	private static final String USER_DETAIL_PAGE = "/users/user-detail";
	private UserRepository userRepository;
	private UserService userService;

	@Autowired
	public UserController(UserRepository userRepository, UserService userService) {
		this.userRepository = userRepository;
		this.userService = userService;
	}

	// ************************************************************************
	// Get
	// ************************************************************************

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public String get(@PathVariable BigInteger id, Model model) {
		model.addAttribute("user", userRepository.findOne(id));
		return USER_DETAIL_PAGE;
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String getProfile(Principal principal, Model model) {
		User user = userRepository.findByEmail(principal.getName());
		System.out.println(user);
		model.addAttribute("user", new UserDTO(user));
		return PROFILE_DETAIL_PAGE;
	}

	// ************************************************************************
	// Create
	// ************************************************************************

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("user") UserDTO userDTO, Model model, Principal principal) throws Exception {
		if(!userDTO.getEmail().equals(principal.getName())) {
			//TODO 
			throw new Exception();
		}
		
		User user = new User();
		user.setAbout(userDTO.getAbout());
		user.setCompany(userDTO.getCompany());
		user.setEmail(userDTO.getEmail());
		user.setFirstname(userDTO.getFirstname());
		user.setId(null);
		user.setLastname(userDTO.getLastname());
		user.setPassword(userDTO.getPassword());
		user.setRole("USER_ROLE");
		user.setShamefulTechnologie(userDTO.getShamefulTechnologie());
		
		userRepository.save(user);
		userService.signin(user);
		model.addAttribute("user", new UserDTO(user));
		return PROFILE_DETAIL_PAGE;
	}

	// ************************************************************************
	// Modify
	// ************************************************************************

	@RequestMapping(value = "/profile/edit", method = RequestMethod.GET)
	public String editProfile(Principal principal, Model model) {
		User user = userRepository.findByEmail(principal.getName());
		model.addAttribute("user", new UserDTO(user));
		return PROFILE_EDIT_PAGE;
	}
	
	@RequestMapping(value = "/profile/edit", method = RequestMethod.POST)
	public String updateUser(@Valid @ModelAttribute("user") User userDTO, Model model, Principal principal) throws Exception {
		if(principal == null || !StringUtils.equals(userDTO.getEmail(), principal.getName())) {
			throw new Exception();
			//TODO
		}
		User user = userRepository.findByEmail(principal.getName());
		user.setAbout(userDTO.getAbout());
		user.setCompany(userDTO.getCompany());
		user.setEmail(userDTO.getEmail());
		user.setFirstname(userDTO.getFirstname());
		//user.setId(null);
		user.setLastname(userDTO.getLastname());
		user.setPassword(userDTO.getPassword());
		//user.setRole("USER_ROLE");
		user.setShamefulTechnologie(userDTO.getShamefulTechnologie());
		
		userRepository.save(user);
		model.addAttribute("user", new UserDTO(user));
		return PROFILE_DETAIL_PAGE;
	}

	// ************************************************************************
	// Delete
	// ************************************************************************

	@RequestMapping(value = "/user/{id}/delete", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public void deleteUser(@PathVariable BigInteger id) {
		userRepository.delete(id);
	}
}
