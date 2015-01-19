package com.julien_roux.jug.quickies;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.List;

import org.crsh.cli.Argument;
import org.crsh.cli.Command;
import org.crsh.cli.Man;
import org.crsh.cli.Option;
import org.crsh.cli.Required;
import org.crsh.cli.Usage;
import org.crsh.command.BaseCommand;
import org.crsh.command.InvocationContext;
import org.crsh.command.ScriptException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;

import com.julien_roux.jug.quickies.model.Quicky;
import com.julien_roux.jug.quickies.model.User;
import com.julien_roux.jug.quickies.model.Vote;
import com.julien_roux.jug.quickies.repository.QuickyRepository;
import com.julien_roux.jug.quickies.repository.UserRepository;
import com.julien_roux.jug.quickies.repository.VoteRepository;

@Usage("Quicky app")
@Component
public class quickies extends BaseCommand {

	@Command
	@Usage("List all quickies")
	public String list(InvocationContext<Object> context, @Usage("specify the usergroup") @Argument String usergroup) {
		StringBuilder response = new StringBuilder();
		QuickyRepository repo = (QuickyRepository) getRepository(context, QuickyRepository.class);

		List<Quicky> quickies = usergroup == null ? repo.findAll() : repo.findByUsergroup(usergroup);
		for (Quicky quicky : quickies) {
			response.append(String.format("Id: %s  |  Title: %s  | Date: %s  |  Location: %s  |  Usergroup: %s\n",
			            quicky.getId().toString(), quicky.getTitle(),
			            new SimpleDateFormat("yyyy-DD-mm HH:mm").format(quicky.getSubmissionDate()),
			            quicky.getLocation(), quicky.getUsergroup()));
		}

		return response.toString();
	}

	@Command
	@Usage("Create a quicky")
	@Man("-u <user@email> -t <title> -d <desc> -x <date> -l <location> -g <usergroup>\n date format is yyyy-DD-mm'T'HH:mm\ni.e 2014-01-01T12:00")
	public String create(InvocationContext<Object> context, @Required @Usage("user email") @Option(names = { "u",
	            "user" }) final String userEmail,
	            @Required @Usage("quickies title") @Option(names = { "t", "title" }) final String title,
	            @Required @Usage("quickies description") @Option(names = { "d", "desc" }) final String desc,
	            @Required @Usage("quickies date") @Option(names = { "x", "date" }) final String date,
	            @Required @Usage("quickies location") @Option(names = { "l", "location" }) final String location,
	            @Required @Usage("quickies usergroup") @Option(names = { "g", "usergroup" }) final String usergroup) {
		Quicky quicky = new Quicky();
		try {
			UserRepository userRepository = (UserRepository) getRepository(context, UserRepository.class);
			QuickyRepository quickyRepository = (QuickyRepository) getRepository(context, QuickyRepository.class);

			quicky.setTitle(title);
			quicky.setDescription(desc);
			quicky.setSubmissionDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(date));
			quicky.setLocation(location);
			quicky.setUsergroup(usergroup);
			quicky.setPresenter(userRepository.findByEmail(userEmail));

			quicky = quickyRepository.save(quicky);
		} catch (Exception e) {
			throw new ScriptException("Unable to create a quicky");
		}

		return quicky.getId().toString();
	}

	@Command
	@Usage("Vote for a quicky")
	@Man("-u <user@email> -i <quicky_id>")
	public String vote(InvocationContext<Object> context,
	            @Required @Usage("user email") @Option(names = { "u", "user" }) final String userEmail,
	            @Required @Usage("quickies title") @Option(names = { "i", "id" }) final String id) {
		try {
			UserRepository userRepository = (UserRepository) getRepository(context, UserRepository.class);
			QuickyRepository quickyRepository = (QuickyRepository) getRepository(context, QuickyRepository.class);
			VoteRepository voteRepository = (VoteRepository) getRepository(context, VoteRepository.class);

			User currentUser = userRepository.findByEmail(userEmail);
			Quicky quicky = quickyRepository.findOne(new BigInteger(id));
			if (currentUser == null || quicky == null) {
				throw new Exception();
			}

			Vote vote = voteRepository.findByVoterAndQuicky(currentUser, quicky);
			if (vote == null) {
				vote = new Vote();
				quicky.setNbVote(quicky.getNbVote() + 1);

				vote.setQuicky(quicky);
				vote.setVoter(currentUser);
				vote = voteRepository.save(vote);

				quickyRepository.save(quicky);
			}
		} catch (Exception e) {
			throw new ScriptException("Unable to vote for this quicky");
		}

		return "OK";
	}

	private Object getRepository(InvocationContext<Object> context, Class clazz) {
		BeanFactory beanFactory = (BeanFactory) context.getAttributes().get("spring.beanfactory");
		return beanFactory.getBean(clazz);
	}
}