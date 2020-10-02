import names.BabyNameAnalyticsService;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This test class is for Complete Implementation Prompt 2
 * Given a range of years, a name, and a gender, what is the difference in that name/gender pair's ranking from only the first and the last years of the range?
 */

public class RankForStartAndEndYearsTest {

    @Test
    void getRankTimelineTestData() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("3000: 1" +
                        "3001: 2",
                babyNameAnalyticsService.getRankForGivenYears("Michael", "M",
                        3000, 3001));
    }

    @Test
    void getRankTimelineRealData() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("1980: 1" +
                        "1981: 1",
                babyNameAnalyticsService.getRankForGivenYears("Michael", "M",
                        1980, 1981));
    }

    @Test
    void getRankTimelineNoNameMatch() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("1980: Not Ranked" +
                        "1981: Not Ranked",
                babyNameAnalyticsService.getRankForGivenYears("NO MATCH", "M",
                        1980, 1981));
    }

    @Test
    void getRankTimelineNotInOneFile() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("3000: 1" + "3002: Not Ranked",
                babyNameAnalyticsService.getRankForGivenYears("Michael", "M",
                        3000, 3002));
    }

    @Test
    void getRankTimelineNotInOneFile_NOMATCH_GENDER() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("",
                babyNameAnalyticsService.getRankForGivenYears("Michael", "A",
                        3000, 3002));
    }

    @Test
    void getRankTimelineFileNotFoundException() {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("",    babyNameAnalyticsService.getRankForGivenYears("Michael", "M",
                    3000, 3003));


    }
}
