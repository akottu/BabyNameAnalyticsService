import names.BabyNameAnalyticsService;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests prompt # 4 for "complete implementation" section
 * Given a name, a gender, and a range of years, what is its average rank during that time?
 */
public class AverageRankForABabyNameTest {

    @Test
    void getAverageRankForABabyName_TESTDATA() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals(1.5, babyNameAnalyticsService
                .getAverageRankForABabyName("Michael","M", 3000, 3001));
    }

    @Test
    void getAverageRankForABabyName_EXISTING_DATA() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals(47.54545454545455, babyNameAnalyticsService
                .getAverageRankForABabyName("Michael","M", 1900, 1910));
    }

    /**
     * The following scenarios are tested with this test case
     * a) an invalid or empty data source (i.e., a non-existent file name or URL or one that exists but contains
     * no data)
     * b) ranges of years that are empty, do not fit completely within the years in the given source of data, or
     * are otherwise nonsensical
     */
    @Test
    void getAverageRankForABabyName_NOFILE() {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals(1.5, babyNameAnalyticsService
                .getAverageRankForABabyName("Michael","M", 3000, 3005));
    }

    /**
     * Test scenario - names that do not match the exact case of those in the various data files
     */
    @Test
    void getAverageRankForABabyName_NOMATCHNAME() {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals(0, babyNameAnalyticsService
                .getAverageRankForABabyName("Michael2","M", 3000, 3005));
    }
    /**
     * genders that are not either M or F (the only ones given in the data files)
     */
    @Test
    void getAverageRankForABabyName_NOMATCHGENDER() {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals(-1, babyNameAnalyticsService
                .getAverageRankForABabyName("Michael","A", 3000, 3005));
    }
}
