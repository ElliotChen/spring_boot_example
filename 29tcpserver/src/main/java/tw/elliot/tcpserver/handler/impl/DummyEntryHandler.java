package tw.elliot.tcpserver.handler.impl;

import tw.elliot.tcpserver.handler.EntryHandler;

public class DummyEntryHandler extends BaseEntryHandler {
	@Override
	public boolean accept(String entry) {
		return !"1111".equals(entry);
	}

	@Override
	public String doHandleEntry(String entry) {
		return entry;
	}
}
