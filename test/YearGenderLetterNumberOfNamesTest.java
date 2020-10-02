import names.BabyNameAnalyticsService;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class YearGenderLetterNumberOfNamesTest {


    @Test
    void getYearGenderLetterNumberOfNames1900MY() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("How many names start with " + "Y" + "? " + "3" +
                        "How many occurrences of these names are there? " + "22",
                babyNameAnalyticsService.getYearGenderLetterNumberOfNames(1900, "M", "Y"));
    }

    @Test
    void getYearGenderLetterNumberOfNames1900FY() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("How many names start with " + "Y" + "? " + "5" +
                        "How many occurrences of these names are there? " + "135",
                babyNameAnalyticsService.getYearGenderLetterNumberOfNames(1900, "F", "Y"));
    }

    @Test
    void getYearGenderLetterNumberOfNames1900FY_INVALID_GENDER() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("How many names start with " + "Y" + "? " + "5" +
                        "How many occurrences of these names are there? " + "135",
                babyNameAnalyticsService.getYearGenderLetterNumberOfNames(1900, "A", "Y"));
    }
}

