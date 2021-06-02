package tw.elliot.tcpserver.handler.impl;

import tw.elliot.tcpserver.handler.EntryHandler;

public class DefaultEntryHandler implements EntryHandler {
	@Override
	public boolean accept(String entry) {
		return true;
	}

	@Override
	public String handle(String entry) {
		return entry;
	}
}
