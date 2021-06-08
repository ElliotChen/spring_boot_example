package tw.elliot.tcpserver.handler.impl;

import tw.elliot.tcpserver.handler.EntryHandler;
import tw.elliot.tcpserver.support.SessionKeeper;
import tw.elliot.tcpserver.support.TcpSession;
import tw.elliot.tcpserver.util.ThreadLocalUtil;

import java.util.regex.Pattern;


public abstract class AbstractRegexEntryHandler extends BaseEntryHandler {
	public abstract Pattern getPattern();

	@Override
	public boolean accept(String entry) {
		return getPattern().matcher(entry).matches();
	}

}
