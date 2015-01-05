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
@RequestMapping("/accounts")
public class AccountController {

	private final Logger logger = LoggerFactory.getLogger(AccountController.class);

	private AccountRepository accountRepository;

	@Autowired
	public AccountController(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Account getAccount(@PathVariable BigInteger id) {
		logger.debug("get account " + id);
		Account account = accountRepository.findOne(id);
		logger.debug("get account " + id + " performed");
		return account;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<Account> getAllAccounts() {
		logger.debug("get all accounts");
		List<Account> accounts = accountRepository.findAll();
		logger.debug("get all accounts performed");
		return accounts;
	}

	@RequestMapping(method = RequestMethod.POST)
	public Account addAccount(@Valid @ModelAttribute("account") Account account) {
		logger.debug("add account");
		account.setId(null);
		Account save = accountRepository.save(account);
		logger.debug("add account performed");
		return save;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Account updateAccount(@RequestBody Account account, @PathVariable BigInteger id) {
		logger.debug("update account " + id);
		account.setId(id);
		Account save = accountRepository.save(account);
		logger.debug("update account performed");
		return save;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteAccount(@PathVariable BigInteger id) {
		logger.debug("delete account " + id);
		accountRepository.delete(id);
		logger.debug("delete account performed");
	}
}
