package com.sportradar.sdh.dao.sdp;

import com.sportradar.sdh.domain.sdp.Competitor;
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
public class SdpCompetitorDaoTest {
    @Autowired
    private SdpCompetitorDao competitorDao;

    @Test
    public void testNot() {
        Assert.assertNotNull(competitorDao);
    }

    @Test
    public void testFindWithAllLanguage() {
        List<Competitor> sports = this.competitorDao.findByIdWithAllLanguage(1L);
        for (Competitor sport: sports) {
            String value = String.valueOf(sport.getCompositedId() + " " + sport.getCompetitorFullName() + " " + sport.getLanguage().getLanguageCode() +" "+ sport.getLanguage().getLanguageName());
            System.out.println(value);
        }

    }
}