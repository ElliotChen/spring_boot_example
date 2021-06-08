package tw.elliot.tcpserver.handler.impl;

import tw.elliot.tcpserver.support.SessionStatus;
import tw.elliot.tcpserver.support.TcpSession;
import tw.elliot.tcpserver.util.ThreadLocalUtil;

import java.util.regex.Pattern;

public class IgnoreEntryHandler extends AbstractRegexEntryHandler {
	public static final String REGEX_LOGIN = "I\\+";

	public static final Pattern PATTERN_LOGIN = Pattern.compile(REGEX_LOGIN);

	@Override
	public Pattern getPattern() {
		return PATTERN_LOGIN;
	}

	@Override
	public String doHandleEntry(String entry) {
		TcpSession tcpSession = ThreadLocalUtil.TCP_SEESION.get();
		if (null != tcpSession && SessionStatus.AUTHORIZED.equals(tcpSession.getStatus())) {
			return "TRANSACTION IGNORED";
		}
		return "SINE IN ..ENTER HELP BSI";
	}
}
