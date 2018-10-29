package com.sportradar.sdh.dao.sdp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sportradar.sdh.domain.sdp.Sport;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SdpSportDaoTest {

	@Autowired
	private SdpSportDao sdpSportDao;

	@Test
	public void test() {
		Assert.assertNotNull(this.sdpSportDao);

		Sport sport = this.sdpSportDao.findById(53L);
		System.out.println(sport.getSportName());

		System.out.println("DgtSport -- ");
		for (com.sportradar.sdh.domain.dgt.Sport sp : sport.getDgtSportXRefs()) {
			System.out.println(sp.getSportId());
		}

		System.out.println("BrSport -- ");
		for (com.sportradar.sdh.domain.br.Sport sp : sport.getBrSportXRefs()) {
			System.out.println(sp.getSportId());
		}

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
}