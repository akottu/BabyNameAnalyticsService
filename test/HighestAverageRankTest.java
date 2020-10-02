import names.BabyNameAnalyticsService;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HighestAverageRankTest {

    @Test
    void mostRankVariation_EXISTING_DATA() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("Zennie", babyNameAnalyticsService
                .getHighestAverageRank("M", 1900, 1910));
    }

    @Test
    void mostRankVariationWithTestData() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("Quinn", babyNameAnalyticsService
                .getHighestAverageRank("M", 3000, 3001));
    }

    @Test
    void mostRankVariation_NOTMATCH_GENDER() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("", babyNameAnalyticsService
                .getHighestAverageRank("A", 3000, 3001));
    }
}
