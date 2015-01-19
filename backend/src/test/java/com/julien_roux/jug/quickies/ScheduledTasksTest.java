package com.julien_roux.jug.quickies;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;



public class ScheduledTasksTest {
	@Test
	public void testParsing() {
		String tweet = "React le reacteur #UGQuickie #ReadyAiMFired #Vote_123456789";
		
		Pattern pattern = Pattern.compile("#Vote_(.*)?");
		Matcher matcher = pattern.matcher(tweet);
		if (matcher.find())
		{
		    System.out.println(matcher.group(1));
		}
	}
}
