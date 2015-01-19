package com.julien_roux.jug.quickies;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;



public class ScheduledTasksTest {
	@Test
	public void testParsing() {
		String tweet = "#UGQuickie #ReadyAimFired #Vote_26225296480733527651036323651 Un autre test telnet";
		
		Pattern pattern = Pattern.compile("#Vote_([0-9]*)?");
		Matcher matcher = pattern.matcher(tweet);
		if (matcher.find())
		{
		    System.out.println(matcher.group(1));
		}
	}
}
