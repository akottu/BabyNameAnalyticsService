import names.BabyNameAnalyticsService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AverageRankInRecentYearsTest {

    @Test
    void averageRankInRecentYears_TEST_DATA() {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals(1.5, babyNameAnalyticsService
                .getAverageRankInMostRecentYears("Michael", "M", 3001, 2));
    }

    @Test
    void averageRankInRecentYears_EXISTING_DATA() {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals(51.0, babyNameAnalyticsService
                .getAverageRankInMostRecentYears("Michael", "M", 1900, 2));
    }

    /**
     * names that do not match the exact case of those in the various data files
     */
    @Test
    void averageRankInRecentYears_NOMATCH_NAME(){
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals(-1.0, babyNameAnalyticsService
                .getAverageRankInMostRecentYears("Michael2", "M", 3001, 2));
    }

    /**
     * Gender does not match
     */
    @Test
    void averageRankInRecentYears_NOMATCH_GENDER() {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals(-1.0, babyNameAnalyticsService
                .getAverageRankInMostRecentYears("Michael", "A", 3001, 2));
    }

    /**
     * File does not exist
     */
    @Test
    void averageRankInRecentYears_NO_FILE(){
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals(-1.0, babyNameAnalyticsService
                .getAverageRankInMostRecentYears("Michael", "A", 3020, 2));
    }
}
