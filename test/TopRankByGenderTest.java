import names.BabyNameAnalyticsService;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TopRankByGenderTest {
    @Test
    void topRankByGender() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("Female: Mary, Male: John", babyNameAnalyticsService.getTopRankedNames(1900));
    }

    @Test
    void topRankByGender_TEST_DATA()  {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("Female: Michelle, Male: Michael", babyNameAnalyticsService.getTopRankedNames(3000));
    }

    @Test
    void topRankByGender_NO_MATCH_FILE()  {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("", babyNameAnalyticsService.getTopRankedNames(3005));
    }
}
