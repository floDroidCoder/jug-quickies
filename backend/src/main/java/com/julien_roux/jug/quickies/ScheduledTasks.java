package com.julien_roux.jug.quickies;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import com.julien_roux.jug.quickies.model.Quicky;
import com.julien_roux.jug.quickies.model.User;
import com.julien_roux.jug.quickies.model.Vote;
import com.julien_roux.jug.quickies.repository.QuickyRepository;
import com.julien_roux.jug.quickies.repository.VoteRepository;

@EnableScheduling
public class ScheduledTasks {
	@Autowired
	private QuickyRepository quickyRepository;

	@Autowired
	private VoteRepository voteRepository;

	@Scheduled(fixedRate = 30000)
	public void reportCurrentTime() throws TwitterException {
		Twitter twitter = TwitterFactory.getSingleton();
		Query query = new Query("#UGQuickie");
		QueryResult result = twitter.search(query);
		for (Status status : result.getTweets()) {
			String statusText = status.getText();
			if (statusText.contains("#ReadyAimFired") && statusText.contains("#Vote")) {
				Pattern pattern = Pattern.compile("#Vote_(.*)?");
				Matcher matcher = pattern.matcher(statusText);
				if (matcher.find()) {
					saveVoteForQuicky(status, matcher, twitter);
				}
			}
		}
	}

	private void saveVoteForQuicky(Status status, Matcher matcher, Twitter twitter) throws TwitterException {
		BigInteger id = new BigInteger(matcher.group(1));

		Quicky quicky = quickyRepository.findOne(id);
		String userScreenName = status.getUser().getScreenName();

		User user = new User();
		user.setEmail(userScreenName + "@twitter.com");

		Vote vote = voteRepository.findByVoterAndQuicky(user, quicky);
		if (vote == null) {
			vote = new Vote();
			quicky.setNbVote(quicky.getNbVote() + 1);

			vote.setQuicky(quicky);
			vote.setVoter(user);
			voteRepository.save(vote);
			quickyRepository.save(quicky);

			twitter.updateStatus("@" + userScreenName + " --> Vote Ok for quicky " + id);
		}
	}
}
