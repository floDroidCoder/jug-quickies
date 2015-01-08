package com.julien_roux.jug.quickies.controller;

import java.math.BigInteger;
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

@Controller
public class UserController {

	private UserRepository userRepository;

	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
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
		return "/profile/profile-detail";
	}

	@RequestMapping(value = "/user/{id}/edit", method = RequestMethod.GET)
	public String editUser(@PathVariable BigInteger id, Model model) {
		model.addAttribute("user", userRepository.findOne(id));
		return "/profile/profile-edit";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public User addUser(@Valid @ModelAttribute("user") User user) {
		user.setId(null);
		user.setRole("USER_ROLE");
		return userRepository.save(user);
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
