package tw.elliot.ms.dao.sdp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tw.elliot.ms.domain.sdp.Sport;

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

		System.out.println("DgtSport -- ");
		for (tw.elliot.ms.domain.dgt.Sport sp : sport.getDgtSportXRefs()) {
			System.out.println(sp.getSportId());
		}

		System.out.println("BrSport -- ");
		for (tw.elliot.ms.domain.br.Sport sp : sport.getBrSportXRefs()) {
			System.out.println(sp.getSportId());
		}

		List<Sport> sports = this.sdpSportDao.findAll();
		System.out.println(sports.size());
	}
}