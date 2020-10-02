package names;


import java.io.FileNotFoundException;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main {
    /**
     * Captures the public methods implemented for the given specifications
     */
    public static void main (String[] args) throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        final int ALL_DATA_START_YEAR = 1880;
        final int ALL_DATA_END_YEAR = 2018;

        final String SEPERATOR = "" +
                "****************************************************************************************************" +
                "";

        System.out.println("TEST PROMPTS \n" +
                SEPERATOR);

        /**
         * Test 1
         * Given a year, what is the top ranked name for each gender (M and F)?
         */
        String topRankedNamesByGender = babyNameAnalyticsService.
                getTopRankedNames(1900);
        System.out.println("Test 1 Output: \n" + topRankedNamesByGender);
        System.out.println(SEPERATOR);

        /**
         * Test 2
         * Given a year, a gender, and a letter, how many names start with that letter and how many total babies were
         * given those names?
         */
        String numberOfNames = babyNameAnalyticsService.
                getYearGenderLetterNumberOfNames(1900, "M", "M");
        System.out.println("Test 2 Output: \n" + numberOfNames);
        System.out.println(SEPERATOR);

        System.out.println("BASIC PROMPTS \n" +
                SEPERATOR);

        /**
         * Basic 1
         * Given a name and a gender, what are all rankings of that name/gender pair in the data set?
         */
        String rankingsForBabyName = babyNameAnalyticsService.
                getRankTimelineGivenNameGender("Michael", "M", ALL_DATA_START_YEAR, ALL_DATA_END_YEAR);
        System.out.println("Basic 1 Output: \n" + rankingsForBabyName);
        System.out.println(SEPERATOR);

        /**
         * Basic 2
         * Given a name, a gender, and a year, what name/gender pair has the same rank as that name in the most recent
         * year in the data set?
         */
        String sameRankMostRecent = babyNameAnalyticsService.
                getNameGenderSameRankMostRecent("Michael", "M", 1900, ALL_DATA_END_YEAR);
        System.out.println("Basic 2 Output: \n" + sameRankMostRecent);
        System.out.println(SEPERATOR);

        /**
         * Basic 3
         * Given a range of years (start and end inclusive) and a gender, what name(s) were ranked as the years' most
         * popular name for that gender the most often and how many years did they have the top rank?
         */
        String topNameByGender = babyNameAnalyticsService.
                findTopNamesByGender("M", 1900, 2000);
        System.out.println("Basic 3 Output: \n" + topNameByGender);
        System.out.println(SEPERATOR);

        /**
         * Basic 4
         * Given a range of years (start and end inclusive), what is the most popular letter that girls? names started
         * with? Answer this question with an alphabetized list of all girls' names that start with this letter in the
         * data set rather than just a single letter. In the case of a tie, return only the alphabetically first letters
         * instead of names starting with different letters.
         */
        String topLetterList = babyNameAnalyticsService.
                findTopLetterListOverRange("F", 1900, 2000);
        System.out.println("Basic 4 Output: \n" + topLetterList);
        System.out.println(SEPERATOR);

        System.out.println("COMPLETE PROMPTS \n" +
                SEPERATOR);

        /**
         * Complete 1
         * Given a name, gender, and range of years, what are the rankings of that name/gender pair within the range?
         */
        String rankingsForBabyNameOverRange = babyNameAnalyticsService.
                getRankTimelineGivenNameGender("Michael", "M", 1980, 1981);
        System.out.println("Complete 1 Output: \n" + rankingsForBabyNameOverRange);
        System.out.println(SEPERATOR);

        /**
         * Complete 2
         * Given a range of years, a name, and a gender, what is the difference in that name/gender pair's ranking from
         * only the first and the last years of the range?
         */
        String rankForGivenYears = babyNameAnalyticsService.
                getRankForGivenYears("Michael", "M", 1980, 1990);
        System.out.println("Complete 2 Output: \n" + rankForGivenYears);
        System.out.println(SEPERATOR);

        /**
         * Complete 3
         * Given a range of years, and a gender, what name's rank changed the most (up or down) from the only first and
         * the last years of the range?
         */
        String mostRankVariation = babyNameAnalyticsService.
                getMostRankVariation("M", 1980, 1990);
        System.out.println("Complete 3 Output: \n" + mostRankVariation);
        System.out.println(SEPERATOR);

        /**
         * Complete 4
         * Given a name, a gender, and a range of years, what is its average rank during that time?
         */
        double averageRank = babyNameAnalyticsService.
                getAverageRankForABabyName("Michael", "M", 1980, 1990);
        System.out.println("Complete 4 Output: \n" + averageRank);
        System.out.println(SEPERATOR);

        /**
         * Complete 5
         * Given a range of years, and a gender, what name had the highest average rank during that time?
         */
        String highestAverageRank = babyNameAnalyticsService.
                getHighestAverageRank( "M", 1980, 1990);
        System.out.println("Complete 5 Output: \n" + highestAverageRank);
        System.out.println(SEPERATOR);

        /**
         * Complete 6
         * Given a name, a gender, and a number of years, what is its average rank for the most recent number of years?
         */
        double averageRankRecentYears = babyNameAnalyticsService.
                getAverageRankInMostRecentYears( "Michael", "M", 3001, 2);
        System.out.println("Complete 6 Output: \n" + averageRankRecentYears);
        System.out.println(SEPERATOR);

        /**
         * Complete 7
         * Given a range of years, a gender, and a rank, what name(s) held that rank at each year during the range?
         */
        String namesWithRank = babyNameAnalyticsService.
                getNamesWithGivenRank( 3000, 3001, "M", 2);
        System.out.println("Complete 7 Output: \n" + namesWithRank);
        System.out.println(SEPERATOR);

        /**
         * Complete 8
         * Given a range of years, a gender, and a rank, what name(s) held that rank most often within the range and how
         * many years did they have at that rank?
         */
        String namesHeldRankOften = babyNameAnalyticsService.
                getNamesHeldRankOften( 3000, 3001, "M", 2);
        System.out.println("Complete 8 Output: \n" + namesHeldRankOften);
        System.out.println(SEPERATOR);
    }
}
