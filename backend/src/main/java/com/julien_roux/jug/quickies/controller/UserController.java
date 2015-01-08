package com.julien_roux.jug.quickies.controller;

import java.math.BigInteger;
import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

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
import com.julien_roux.jug.quickies.repository.UserRepository;
import com.julien_roux.jug.quickies.security.UserService;

@Controller
public class UserController {

	private UserRepository userRepository;
	private UserService userService;

	@Autowired
	public UserController(UserRepository userRepository, UserService userService) {
		this.userRepository = userRepository;
		this.userService = userService;
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public String getUser(@PathVariable BigInteger id, Model model) {
		model.addAttribute("user", userRepository.findOne(id));
		return "/user/user-detail";
	}


	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String getProfile(Principal principal, Model model) {
		model.addAttribute("user", userRepository.findByEmail(principal.getName()));
		return "/profile/profile-detail";
	}

	@RequestMapping(value = "/profile/edit", method = RequestMethod.GET)
	public String editProfile(Principal principal, Model model) {
		model.addAttribute("user", userRepository.findByEmail(principal.getName()));
		return "/profile/profile-edit";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String addUser(@Valid @ModelAttribute("user") User user, Model model) {
		user.setId(null);
		user.setRole("USER_ROLE");
		userRepository.save(user);
		userService.signin(user);
		model.addAttribute("user", user);
		return "/profile/profile-detail";
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public User updateUser(@Valid @ModelAttribute("user") User user, @PathVariable BigInteger id) {
		user.setId(id);
		return userRepository.save(user);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public void deleteUser(@PathVariable BigInteger id) {
		userRepository.delete(id);
	}
}
