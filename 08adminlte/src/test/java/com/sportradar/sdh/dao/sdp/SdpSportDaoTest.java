package com.sportradar.sdh.dao.sdp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sportradar.sdh.domain.common.BaseSport;
import com.sportradar.sdh.domain.sdp.Sport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SdpSportDaoTest {

	@Autowired
	private SdpSportDao sdpSportDao;

	@Test
	public void test() {
		Assertions.assertNotNull(this.sdpSportDao);

		Sport sport = this.sdpSportDao.findById(53L);
		System.out.println(sport.getSportName());

		List<BaseSport> sportXRefs = sport.getSportXRefs();
		for (BaseSport baseSport: sportXRefs) {
			System.out.println(baseSport.getCompositedId());
		}
		/*
		System.out.println("DgtSport -- ");
		for (com.sportradar.sdh.domain.dgt.Sport sp : sport.getDgtSportXRefs()) {
			System.out.println(sp.getSportId());
		}

		System.out.println("BrSport -- ");
		for (com.sportradar.sdh.domain.br.Sport sp : sport.getBrSportXRefs()) {
			System.out.println(sp.getSportId());
		}
		*/

		List<Sport> sports = this.sdpSportDao.findAll();
		System.out.println(sports.size());
	}

	@Test
	public void testFindByPage() {
		PageHelper.startPage(2, 10);
		Page<Sport> sports = this.sdpSportDao.findByPage();
		System.out.println(sports.getResult().size());

		Sport sport = sports.getResult().get(0);
		System.out.println(sport.getSportId());
	}

	@Test
	public void testFindWithLanguage() {
		List<Sport> sports = this.sdpSportDao.findAllWithLanguage();

		for (Sport sport: sports) {
			String value = String.valueOf(sport.getCompositedId() + " " + sport.getSportName() + " " + sport.getLanguage().getLanguageCode() +" "+ sport.getLanguage().getLanguageName());
			System.out.println(value);
		}

	}

	@Test
	public void testFindWithAllLanguage() {
		List<Sport> sports = this.sdpSportDao.findByIdWithAllLanguage(8L);

		for (Sport sport: sports) {
			String value = String.valueOf(sport.getCompositedId() + " " + sport.getSportName() + " " + sport.getLanguage().getLanguageCode() +" "+ sport.getLanguage().getLanguageName());
			System.out.println(value);
		}

	}


}