import names.BabyNameAnalyticsService;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NamesHeldRankOftenTest {
    @Test
    void namesHeldRankMostOften_TEST_DATA() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals(" Michael Trevor", babyNameAnalyticsService.
                getNamesHeldRankOften(3000, 3001, "M", 2));
    }

    @Test
    void namesHeldRankMostOften_EXISTING_DATA() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals(" James William William William William William William William William William William",
                babyNameAnalyticsService.getNamesHeldRankOften(1900, 1910, "M", 2));
    }

    @Test
    void namesHeldRankMostOften_FILE_NOT_FOUND() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals(" Hello Michael Trevor", babyNameAnalyticsService
                .getNamesHeldRankOften(3000, 3005, "M", 2));
    }

    @Test
    void namesHeldRankMostOften_NO_MATCH_GENDER() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("", babyNameAnalyticsService
                .getNamesHeldRankOften(3000, 3002, "A", 2));
    }

    @Test
    void namesHeldRankMostOften_NO_MATCH_RANK() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("", babyNameAnalyticsService
                .getNamesHeldRankOften(3000, 3002, "A", 200000));
    }
}
