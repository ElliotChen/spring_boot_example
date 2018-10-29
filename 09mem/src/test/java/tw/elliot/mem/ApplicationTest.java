package tw.elliot.mem;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tw.elliot.mem.dao.br.BrSportDao;
import tw.elliot.mem.dao.dgt.DgtSportDao;
import tw.elliot.mem.dao.sdp.SdpSportDao;
import tw.elliot.mem.domain.sdp.Sport;

import javax.persistence.Table;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

	@Autowired
	private SdpSportDao sdpSportDao;

	@Autowired
	private DgtSportDao dgtSportDao;

	@Autowired
	private BrSportDao brSportDao;

	@Test
	public void testDaoExists() {
		Assert.assertNotNull(sdpSportDao);
		Optional<tw.elliot.mem.domain.sdp.Sport> ssport = this.sdpSportDao.findById(0L);
		if (ssport.isPresent()) {
			System.out.println(ssport.get().getSportName());
		}

		Assert.assertNotNull(dgtSportDao);
		Optional<tw.elliot.mem.domain.dgt.Sport> dsport = this.dgtSportDao.findById(0L);
		if (dsport.isPresent()) {
			System.out.println(dsport.get().getSportName());
		}

		Assert.assertNotNull(brSportDao);
		Optional<tw.elliot.mem.domain.br.Sport> bsport = this.brSportDao.findById(0L);
		if (bsport.isPresent()) {
			System.out.println(bsport.get().getSportName());
		}
	}
}