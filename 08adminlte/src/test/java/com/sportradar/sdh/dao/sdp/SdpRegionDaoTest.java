package com.sportradar.sdh.dao.sdp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sportradar.sdh.domain.sdp.Region;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SdpRegionDaoTest {

	@Autowired
	private SdpRegionDao sdpRegionDao;

	@Test
	public void test() {
		PageHelper.startPage(2, 10);
		Page<Region> page = this.sdpRegionDao.findByPage();

		System.out.println(page.getResult().size());
	}

	@Test
	public void testFindWithLanguage() {
		List<Region> objects = this.sdpRegionDao.findAllWithLanguage();

		for (Region obj: objects) {
			String value = String.valueOf(obj.getCompositedId() + " " + obj.getRegionFullName() + " " + obj.getLanguage().getLanguageCode() +" "+ obj.getLanguage().getLanguageName());
			System.out.println(value);
		}

	}

	@Test
	public void testFindWithAllLanguage() {
		List<Region> objects = this.sdpRegionDao.findByIdWithAllLanguage(709);

		for (Region obj: objects) {
			String value = String.valueOf(obj.getCompositedId() + " " + obj.getRegionFullName() + " " + obj.getLanguage().getLanguageCode() +" "+ obj.getLanguage().getLanguageName());
			System.out.println(value);
		}

	}
}