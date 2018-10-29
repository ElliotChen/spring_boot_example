package com.sportradar.sdh.dao.sdp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sportradar.sdh.domain.sdp.League;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}