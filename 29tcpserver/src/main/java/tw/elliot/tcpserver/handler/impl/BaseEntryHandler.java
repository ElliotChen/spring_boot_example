package tw.elliot.tcpserver.handler.impl;

import tw.elliot.tcpserver.handler.EntryHandler;
import tw.elliot.tcpserver.support.TcpSession;
import tw.elliot.tcpserver.util.ThreadLocalUtil;

public abstract class BaseEntryHandler implements EntryHandler {

	@Override
	public String handle(String entry) {
		TcpSession tcpSession = ThreadLocalUtil.TCP_SEESION.get();
		if (null != tcpSession) {
			tcpSession.getEntries().add(entry);
		}

		return doHandleEntry(entry);
	}

	public abstract String doHandleEntry(String entry);
}
