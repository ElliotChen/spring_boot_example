package tw.elliot.tcpserver.handler.impl;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.sql.ResultSet;

@Slf4j
@Data
public class DefaultEntryHandler extends BaseEntryHandler {
	
	private String entryResultPath;
	private String charEncoding;
	private String fileExtension;

	@Override
	public boolean accept(String entry) {
		return true;
	}

	@Override
	public String doHandleEntry(String entry) {

		Assert.isTrue(StringUtils.hasText(entry), "Entry Could not be empty");

		String filename = entry.endsWith("+") ? entry.substring(0, entry.length() - 1) :entry;

		filename = StringUtils.replace(filename, "*", "_");
		filename = StringUtils.replace(filename, "/", "_");
		filename = StringUtils.replace(filename, "@", "_");
		filename = StringUtils.replace(filename, " ", "_");

		String urlString = entryResultPath + File.separator + filename + "." +fileExtension;
		log.info("Try to search file [{}]", urlString);
		String result ="";
		try {
			File file = new File(urlString);
			result = StreamUtils.copyToString(new FileInputStream(file), Charset.forName(charEncoding));
		} catch (Exception e) {
			log.error("Load From [{}] failed.", urlString, e);

			result = entry;
		}
		return result;
	}
}
