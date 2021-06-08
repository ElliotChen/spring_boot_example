package tw.elliot.tcpserver.support;

import org.springframework.integration.ip.tcp.serializer.AbstractPooledBufferByteArraySerializer;
import org.springframework.integration.ip.tcp.serializer.SoftEndOfStreamException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * if resMode == true will append "\n>+" after output string.
 */
public class ResScreenSerializer extends AbstractPooledBufferByteArraySerializer {

	private static final byte[] END = "\n>+".getBytes();

	private boolean resMode;

	public ResScreenSerializer() {
		this.resMode = true;
	}
	public ResScreenSerializer(boolean resMode) {
		this.resMode = resMode;
	}

	@Override
	public byte[] doDeserialize(InputStream inputStream, byte[] buffer) throws IOException {
		int n = fillBuffer(inputStream, buffer);
		return copyToSizedArray(buffer, n);
	}

	public int fillBuffer(InputStream inputStream, byte[] buffer) throws IOException {
		int n = 0;
		int bite;
		int available = inputStream.available();
		logger.debug(() -> "Available to read: " + available);

		try {
			while (n < available) {
				bite = inputStream.read();
				if (bite < 0 && n == 0) {
					throw new SoftEndOfStreamException("Stream closed between payloads");
				}
				checkClosure(bite);
				buffer[n++] = (byte) bite;
			}
			return n;
		}
		catch (SoftEndOfStreamException e) { // NOSONAR catch and throw
			throw e; // it's an IO exception and we don't want an event for this
		}
		catch (IOException | RuntimeException e) {
			publishEvent(e, buffer, n);
			throw e;
		}
	}

	/**
	 * Writes the byte[] to the stream.
	 */
	@Override
	public void serialize(byte[] bytes, OutputStream outputStream) throws IOException {
		outputStream.write(bytes);
		if (resMode) {
			outputStream.write(END);
		}
	}

}


