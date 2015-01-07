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

import com.julien_roux.jug.quickies.model.Account;
import com.julien_roux.jug.quickies.repository.AccountRepository;

@Controller
public class AccountController {

	private AccountRepository accountRepository;

	@Autowired
	public AccountController(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@RequestMapping(value = "/rest/users", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Account getAccount(@PathVariable BigInteger id) {
		return accountRepository.findOne(id);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
	public Account addAccount(@Valid @ModelAttribute("account") Account account) {
		account.setId(null);
		return accountRepository.save(account);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
	public Account updateAccount(@RequestBody Account account, @PathVariable BigInteger id) {
		account.setId(id);
		return accountRepository.save(account);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public void deleteAccount(@PathVariable BigInteger id) {
		accountRepository.delete(id);
	}
}
