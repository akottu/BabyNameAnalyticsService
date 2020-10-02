import names.BabyNameAnalyticsService;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This test class is for Basic Implementation Prompt 2
 * "Given a name, a gender, and a year, what name/gender pair has the same rank as that name in the most recent year in
 * the data set?"
 */

public class NameGenderMostRecentSameRankTest {

    @Test
    void getNameGenderSameRankMostRecentMatchExists_EXISTING_DATA() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("No data!",
                babyNameAnalyticsService
                        .getNameGenderSameRankMostRecent("Trevor", "M", 1900, 1910));
    }

    @Test
    void getNameGenderSameRankMostRecentMatchExists_TEST_DATA() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("Name: Nathan" +
                        "Gender: M",
                babyNameAnalyticsService
                        .getNameGenderSameRankMostRecent("Trevor", "M", 3001, 3002));
    }

    @Test
    void getNameGenderSameRankMostRecentNoMatch() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("No data!",
                babyNameAnalyticsService
                        .getNameGenderSameRankMostRecent("Quinn", "M", 3001, 3002));
    }

    @Test
    void getNameGenderSameRankMostRecentNameNotInFile() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("No data!",
                babyNameAnalyticsService
                        .getNameGenderSameRankMostRecent("TrevorABC", "M", 3001, 3002));
    }


    @Test
    void getNameGenderSameRankMostRecentNameNoRecentYearFile() throws FileNotFoundException {
        Exception exception = assertThrows(FileNotFoundException.class, () -> {
            BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
            babyNameAnalyticsService
                    .getNameGenderSameRankMostRecent("TrevorABC", "M", 3001, 3004);
        });
    }

    @Test
    void getNameGenderSameRankMostRecentNameNoYearFile() throws FileNotFoundException {
        Exception exception = assertThrows(FileNotFoundException.class, () -> {
            BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
            babyNameAnalyticsService
                    .getNameGenderSameRankMostRecent("TrevorABC", "M", 3004, 3001);
        });
    }
}
