import names.BabyNameAnalyticsService;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NamesWithGivenRankTest {

    @Test
    void namesWithGivenRankWithTestData() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals(" Trevor Michael", babyNameAnalyticsService
                .getNamesWithGivenRank(3000, 3001, "M", 2));
    }
    @Test
    void namesWithGivenRank_EXISTING_DATA() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals(" William William William William William William William William William William James",
                babyNameAnalyticsService.getNamesWithGivenRank(1900, 1910, "M", 2));
    }

    @Test
    void namesWithGivenRank_NOMATCH_GENDER() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("", babyNameAnalyticsService
                .getNamesWithGivenRank(1900, 1910, "A", 2));
    }

    @Test
    void namesWithGivenRank_INVALID_FILE() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("", babyNameAnalyticsService
                .getNamesWithGivenRank(3000, 3005, "A", 2));
    }
}
