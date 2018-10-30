package com.sportradar.sdh.dao.sdp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sportradar.sdh.domain.sdp.Market;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SdpMarketDaoTest {

	@Autowired
	private SdpMarketDao sdpMarketDao;

	@Test
	public void testFindByPage() {
		PageHelper.startPage(1, 10);
		Page<Market> page = sdpMarketDao.findByPage();

		System.out.println(page.getResult().size());

	}

	@Test
	public void testFindWithLanguage() {
		List<Market> objects = this.sdpMarketDao.findAllWithLanguage();

		for (Market obj: objects) {
			String value = String.valueOf(obj.getCompositedId() + " " + obj.getMarketName() + " " + obj.getLanguage().getLanguageCode() +" "+ obj.getLanguage().getLanguageName());
			System.out.println(value);
		}

	}

	@Test
	public void testFindWithAllLanguage() {
		List<Market> objects = this.sdpMarketDao.findByIdWithAllLanguage(38L);

		for (Market obj: objects) {
			String value = String.valueOf(obj.getCompositedId() + " " + obj.getMarketName() + " " + obj.getLanguage().getLanguageCode() +" "+ obj.getLanguage().getLanguageName());
			System.out.println(value);
		}

	}
}