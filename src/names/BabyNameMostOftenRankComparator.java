package names;

import java.util.Comparator;


public class BabyNameMostOftenRankComparator implements Comparator<BabyNameRankSummary> {
    public int compare(BabyNameRankSummary babyNameRankSummary1, BabyNameRankSummary babyNameRankSummary2) {
        return Integer.compare(babyNameRankSummary1.getTotalOccurrencesForAllYears(), babyNameRankSummary2.getTotalOccurrencesForAllYears());
    }

}