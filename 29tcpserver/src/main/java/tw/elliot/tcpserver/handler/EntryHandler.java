package tw.elliot.tcpserver.handler;

public interface EntryHandler {
	boolean accept(String entry);
	String handle(String entry);
}
