package com.sportradar.sdh.dao.sdp;

import com.sportradar.sdh.domain.sdp.Competitor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SdpCompetitorDaoTest {
    @Autowired
    private SdpCompetitorDao competitorDao;

    @Test
    public void testNot() {
        Assertions.assertNotNull(competitorDao);
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