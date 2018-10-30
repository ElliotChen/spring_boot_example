package com.sportradar.sdh.dao.sdp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sportradar.sdh.domain.sdp.League;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SdpLeagueDaoTest {
	@Autowired
	private SdpLeagueDao sdpLeagueDao;

	@Test
	public void test() {
		PageHelper.startPage(2, 10);
		Page<League> page = this.sdpLeagueDao.findByPage();

		System.out.println(page.getResult().size());
	}

	@Test
	public void testFindWithLanguage() {
		List<League> objects = this.sdpLeagueDao.findAllWithLanguage();

		for (League obj: objects) {
			String value = String.valueOf(obj.getCompositedId() + " " + obj.getLeagueName() + " " + obj.getLanguage().getLanguageCode() +" "+ obj.getLanguage().getLanguageName());
			System.out.println(value);
		}

	}

	@Test
	public void testFindWithAllLanguage() {
		List<League> objects = this.sdpLeagueDao.findByIdWithAllLanguage(1L);

		for (League obj: objects) {
			String value = String.valueOf(obj.getCompositedId() + " " + obj.getLeagueName() + " " + obj.getLanguage().getLanguageCode() +" "+ obj.getLanguage().getLanguageName());
			System.out.println(value);
		}

	}
}