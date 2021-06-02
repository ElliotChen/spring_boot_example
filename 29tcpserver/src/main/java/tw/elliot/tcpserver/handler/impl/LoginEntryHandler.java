package tw.elliot.tcpserver.handler.impl;

import tw.elliot.tcpserver.handler.EntryHandler;

import java.util.regex.Pattern;

public class LoginEntryHandler extends AbstractRegexEntryHandler {

	public static final String REGEX_LOGIN = "I+";

	public static final Pattern PATTERN_LOGIN = Pattern.compile(REGEX_LOGIN);

	@Override
	public Pattern getPattern() {
		return PATTERN_LOGIN;
	}

	@Override
	public String handle(String entry) {
		return null;
	}


}