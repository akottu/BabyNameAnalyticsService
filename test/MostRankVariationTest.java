import names.BabyNameAnalyticsService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MostRankVariationTest {

    @Test
    void mostRankVariation() {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("Zennie", babyNameAnalyticsService.getMostRankVariation("M", 1900, 1910));
    }

    /**
     * Different dataset
     */
    @Test
    void mostRankVariation_TESTDATA() {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("Trevor", babyNameAnalyticsService
                .getMostRankVariation("M", 3000, 3002));
    }

    /**
     * Different dataset
     */
    @Test
    void mostRankVariation_FILE_NOT_FOUND() {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("Hello", babyNameAnalyticsService
                .getMostRankVariation("M", 3000, 3005));
    }

    /**
     * Different gender
     */
    @Test
    void mostRankVariation_NOMATCH_GENDER() {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("", babyNameAnalyticsService
                .getMostRankVariation("A", 3000, 3005));
    }

}
