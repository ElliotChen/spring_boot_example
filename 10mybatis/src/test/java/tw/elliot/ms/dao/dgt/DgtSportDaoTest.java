package tw.elliot.ms.dao.dgt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tw.elliot.ms.domain.dgt.Sport;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DgtSportDaoTest {

	@Autowired
	private DgtSportDao dgtSportDao;

	@Test
	public void test() {
		Assertions.assertNotNull(this.dgtSportDao);

		Sport sport = this.dgtSportDao.findById(53L);
		System.out.println(sport.getSportName());

		System.out.println("SDP Sport -- ");
		System.out.println(sport.getSportRef().getSportId());

		List<Sport> sports = this.dgtSportDao.findAll();
		System.out.println(sports.size());
	}
}