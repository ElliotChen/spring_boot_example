package tw.elliot.tcpserver.handler.impl;

import tw.elliot.tcpserver.handler.EntryHandler;

import java.util.regex.Pattern;


public abstract class AbstractRegexEntryHandler implements EntryHandler {
	public abstract Pattern getPattern();

	@Override
	public boolean accept(String entry) {
		return getPattern().matcher(entry).matches();
	}
}
