package tw.elliot.tcpserver.handler.impl;

import tw.elliot.tcpserver.support.SessionStatus;
import tw.elliot.tcpserver.support.TcpSession;
import tw.elliot.tcpserver.util.ThreadLocalUtil;

import java.util.regex.Pattern;

public class BSOEntryHandler extends AbstractRegexEntryHandler {
	public static final String REGEX_LOGIN = "BSO\\+";

	public static final Pattern PATTERN_LOGIN = Pattern.compile(REGEX_LOGIN);

	@Override
	public Pattern getPattern() {
		return PATTERN_LOGIN;
	}

	@Override
	public String doHandleEntry(String entry) {
		return "SINE IN ..ENTER HELP BSI";
	}
}

