package tw.elliot.ms.dao.dgt;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tw.elliot.ms.domain.dgt.Sport;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DgtSportDaoTest {

	@Autowired
	private DgtSportDao dgtSportDao;

	@Test
	public void test() {
		Assert.assertNotNull(this.dgtSportDao);

		Sport sport = this.dgtSportDao.findById(53L);
		System.out.println(sport.getSportName());

		System.out.println("SDP Sport -- ");
		System.out.println(sport.getSportRef().getSportId());

		List<Sport> sports = this.dgtSportDao.findAll();
		System.out.println(sports.size());
	}
}