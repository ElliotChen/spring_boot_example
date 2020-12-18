package com.sportradar.sdh.dao.sdp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sportradar.sdh.domain.sdp.MarketOption;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SdpMarketOptionDaoTest {

	@Autowired
	private SdpMarketOptionDao sdpMarketOptionDao;

	@Test
	public void testFindByPage() {
		PageHelper.startPage(3, 10);
		Page<MarketOption> page = sdpMarketOptionDao.findByPage();

		System.out.println(page.getResult().size());

	}

	@Test
	public void testFindWithLanguage() {
		List<MarketOption> objects = this.sdpMarketOptionDao.findAllWithLanguage();

		for (MarketOption obj: objects) {
			String value = String.valueOf(obj.getCompositedId() + " " + obj.getShortName() + " " + obj.getFullName() + " " + obj.getLanguage().getLanguageCode() +" "+ obj.getLanguage().getLanguageName());
			System.out.println(value);
		}

	}

	@Test
	public void testFindWithAllLanguage() {
		List<MarketOption> objects = this.sdpMarketOptionDao.findByIdWithAllLanguage(38L, 1);

		for (MarketOption obj: objects) {
			String value = String.valueOf(obj.getCompositedId() + " " + obj.getShortName() + " " + obj.getFullName() + " " + obj.getLanguage().getLanguageCode() +" "+ obj.getLanguage().getLanguageName());
			System.out.println(value);
		}

	}
}