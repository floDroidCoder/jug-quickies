package com.julien_roux.jug.quickies.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.julien_roux.jug.quickies.exception.UnauthorizedActionException;
import com.julien_roux.jug.quickies.model.User;
import com.julien_roux.jug.quickies.repository.UserRepository;

@Controller
public class AdminController {
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String get(Model model, Principal principal) throws UnauthorizedActionException {
		if (principal == null) {
			throw new UnauthorizedActionException();
		}
		User currentUser = userRepository.findByEmail(principal.getName());
		if (currentUser == null || !"ADMIN_ROLE".equals(currentUser.getRole())) {
			throw new UnauthorizedActionException();
		}

		model.addAttribute("users", userRepository.findAll());
		model.addAttribute("groups", null);
		return "/admin/admin";
	}
}
