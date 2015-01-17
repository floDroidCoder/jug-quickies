package com.julien_roux.jug.quickies.controller;

import java.math.BigInteger;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
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
import com.julien_roux.jug.quickies.model.Quicky;
import com.julien_roux.jug.quickies.model.User;
import com.julien_roux.jug.quickies.model.Vote;
import com.julien_roux.jug.quickies.model.dto.QuickyDTO;
import com.julien_roux.jug.quickies.repository.QuickyRepository;
import com.julien_roux.jug.quickies.repository.UserRepository;
import com.julien_roux.jug.quickies.repository.VoteRepository;

@Controller
public class QuickyController {

	private static final String DETAIL_PAGE = "/quickies/quicky-detail";
	private static final String EDIT_PAGE = "/quickies/quicky-edit";

	@Autowired
	private QuickyRepository quickyRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private VoteRepository voteRepository;

	// ************************************************************************
	// Get
	// ************************************************************************

	@RequestMapping(value = "/quickies", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<QuickyDTO> findAll() {
		List<Quicky> quickies = quickyRepository.findAll();
		List<QuickyDTO> quickyList = new ArrayList<QuickyDTO>();
		for (Quicky quicky : quickies) {
			User tmpUser = userRepository.findByEmail(quicky.getPresenter().getEmail());
			List<Vote> votes = voteRepository.findByQuicky(quicky);

			quicky.setPresenter(tmpUser);
			QuickyDTO quickyDto = new QuickyDTO(quicky);
			quickyDto.setNbVote(votes.size());
			quickyList.add(quickyDto);
		}
		return quickyList;
	}

	@RequestMapping(value = "/quickies/{usergroup}/past", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<QuickyDTO> findPastQuickies(@PathVariable String usergroup) {
		List<Quicky> quickies;
		if ("ALL".equals(usergroup.toUpperCase())) {
			quickies = quickyRepository.findBySubmissionDateBefore(new Date());
		} else {
			quickies = quickyRepository.findBySubmissionDateBeforeAndUsergroupEquals(new Date(), usergroup);
		}

		List<QuickyDTO> quickyList = new ArrayList<QuickyDTO>();
		for (Quicky quicky : quickies) {
			User tmpUser = userRepository.findByEmail(quicky.getPresenter().getEmail());
			List<Vote> votes = voteRepository.findByQuicky(quicky);

			quicky.setPresenter(tmpUser);
			QuickyDTO quickyDto = new QuickyDTO(quicky);
			quickyDto.setNbVote(votes.size());
			quickyList.add(quickyDto);
		}
		return quickyList;
	}

	@RequestMapping(value = "/quickies/{usergroup}/futur", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<QuickyDTO> findFuturQuickies(@PathVariable String usergroup) {
		List<Quicky> quickies;
		if ("ALL".equals(usergroup.toUpperCase())) {
			quickies = quickyRepository.findBySubmissionDateAfter(new Date());
		} else {
			quickies = quickyRepository.findBySubmissionDateAfterAndUsergroupEquals(new Date(), usergroup);
		}
		List<QuickyDTO> quickyList = new ArrayList<QuickyDTO>();
		for (Quicky quicky : quickies) {
			User tmpUser = userRepository.findByEmail(quicky.getPresenter().getEmail());
			List<Vote> votes = voteRepository.findByQuicky(quicky);

			quicky.setPresenter(tmpUser);
			QuickyDTO quickyDto = new QuickyDTO(quicky);
			quickyDto.setNbVote(votes.size());
			quickyList.add(quickyDto);
		}
		return quickyList;
	}

	@RequestMapping(value = "/quicky/{id}", method = RequestMethod.GET)
	public String get(@PathVariable BigInteger id, Model model, Principal principal) {
		Quicky quicky = quickyRepository.findOne(id);
		if (principal != null) {
			User currentuser = userRepository.findByEmail(principal.getName());
			model.addAttribute("vote", voteRepository.findByVoterAndQuicky(currentuser, quicky));
		}
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
	public String submit(@Valid @ModelAttribute("quicky") QuickyDTO quickyDTO, BindingResult result,
	            Principal principal, Model model) {
		if (result.hasErrors()) {
			return EDIT_PAGE;
		}

		Quicky quicky = quickyDTO.toQuicky();

		User presenter = userRepository.findByEmail(principal.getName());
		quicky.setPresenter(presenter);
		quicky = quickyRepository.save(quicky);

		model.addAttribute("quicky", new QuickyDTO(quicky));
		return DETAIL_PAGE;
	}

	// ************************************************************************
	// Modify
	// ************************************************************************

	@RequestMapping(value = "/quicky/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable BigInteger id, Model model, Principal principal) throws Exception {
		Quicky quicky = quickyRepository.findOne(id);
		if (principal == null || !quicky.getPresenter().getEmail().equals(principal.getName())) {
			throw new UnauthorizedActionException();
		}
		model.addAttribute("quicky", new QuickyDTO(quicky));
		model.addAttribute("dateformat", "yyyy-MM-dd'T'HH:mm");
		return EDIT_PAGE;
	}

	@RequestMapping(value = "/quicky/{id}/edit", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("quicky") QuickyDTO quickyDTO, @PathVariable BigInteger id,
	            Model model, Principal principal) throws Exception {
		Quicky quicky = quickyRepository.findOne(id);

		if (principal == null || !StringUtils.equals(quicky.getPresenter().getEmail(), principal.getName())) {
			throw new UnauthorizedActionException();
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
	public String vote(@PathVariable BigInteger id, Model model, Principal principal)
	            throws UnauthorizedActionException {
		if (principal == null) {
			throw new UnauthorizedActionException();
		}
		User currentUser = userRepository.findByEmail(principal.getName());
		Quicky quicky = quickyRepository.findOne(id);

		Vote vote = voteRepository.findByVoterAndQuicky(currentUser, quicky);
		if (vote == null) {
			vote = new Vote();
			vote.setQuicky(quicky);
			vote.setVoter(currentUser);
			voteRepository.save(vote);
		}
		model.addAttribute("quicky", new QuickyDTO(quicky));
		model.addAttribute("vote", vote);
		return DETAIL_PAGE;
	}

	// ************************************************************************
	// Delete
	// ************************************************************************

	@RequestMapping(value = "/quicky/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable BigInteger id) {
		quickyRepository.delete(id);
		return "redirect:/";
	}
}
