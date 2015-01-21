package com.julien_roux.jug.quickies.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.julien_roux.jug.quickies.exception.UnauthorizedActionException;

@ControllerAdvice
public class ExceptionHandlingController {
	@ExceptionHandler(UnauthorizedActionException.class)
	public ModelAndView handleError(HttpServletRequest req, Exception exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("errors/unauthorized");
		return mav;
	}
}
