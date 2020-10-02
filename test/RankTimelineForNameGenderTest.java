import names.BabyNameAnalyticsService;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This test class is for Basic Implementation Prompt 1
 * "Given a name and a gender, what are all rankings of that name/gender pair in the data set?"
 */

public class RankTimelineForNameGenderTest {

    @Test
    void getRankTimelineTestData() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("3000: 1" +
                        "3001: 2",
                babyNameAnalyticsService.getRankTimelineGivenNameGender("Michael", "M",
                        3000, 3001));
    }

    @Test
    void getRankTimelineRealData() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("1980: 1" +
                        "1981: 1",
                babyNameAnalyticsService.getRankTimelineGivenNameGender("Michael", "M",
                        1980, 1981));
    }

    @Test
    void getRankTimelineNoNameMatch() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("1980: Not Ranked" +
                        "1981: Not Ranked",
                babyNameAnalyticsService.getRankTimelineGivenNameGender("NO MATCH", "M",
                        1980, 1981));
    }

    @Test
    void getRankTimelineNotInOneFile() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("3000: 1" +
                        "3001: 2" +
                        "3002: Not Ranked",
                babyNameAnalyticsService.getRankTimelineGivenNameGender("Michael", "M",
                        3000, 3002));
    }

    @Test
    void getRankTimelineFileNotFoundException() throws FileNotFoundException {
        Exception exception = assertThrows(FileNotFoundException.class, () -> {
            BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
            babyNameAnalyticsService.getRankTimelineGivenNameGender("Michael", "M",
                    3000, 3003);
        });

    }
}
