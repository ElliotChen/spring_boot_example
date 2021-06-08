package tw.elliot.tcpserver.util;

import tw.elliot.tcpserver.support.TcpSession;

public class ThreadLocalUtil {
	public static ThreadLocal<TcpSession> TCP_SEESION =  new ThreadLocal<>();

}
