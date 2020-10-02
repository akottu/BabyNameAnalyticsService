import names.BabyNameAnalyticsService;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This test class is for Basic Implementation Prompt 3
 * "Given a range of years (start and end inclusive) and a gender, what name(s) were ranked as the years' most popular
 * name for that gender the most often and how many years did they have the top rank?"
 */
public class TopNamesByGenderAcrossYearRangeTest {

    @Test
    void getTopNamesByGenderAcrossYearRangeTest() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("Name: Michael, Occurrences: 1",
                babyNameAnalyticsService.findTopNamesByGender("M", 3000, 3001));
    }

    @Test
    void getTopNamesByGenderAcrossYearRangeRealData() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("Name: Michael, Occurrences: 44",
                babyNameAnalyticsService.findTopNamesByGender("M", 1880, 2018));
    }

    @Test
    void getTopNamesByGenderAcrossYearRangeFileNotFoundException() {
        Exception exception = assertThrows(FileNotFoundException.class, () -> {
            BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
            babyNameAnalyticsService.findTopNamesByGender("M",
                    3000, 3003);
        });
    }

    @Test
    void getTopNamesByGenderAcrossYearRangeNoData() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("No data!",
                babyNameAnalyticsService.findTopNamesByGender("M", 3010, 3011));
    }

    @Test
    void getTopNamesByGenderAcrossYearRangeInvalidGender() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("Invalid input - gender",
                babyNameAnalyticsService.findTopNamesByGender("A", 3010, 3011));
    }
}
