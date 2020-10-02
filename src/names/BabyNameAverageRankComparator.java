package names;

import java.util.Comparator;

public class BabyNameAverageRankComparator implements Comparator<BabyNameRankSummary> {
    public int compare(BabyNameRankSummary babyNameRankSummary1, BabyNameRankSummary babyNameRankSummary2){
        return Double.compare(babyNameRankSummary1.getAverageRank(),  babyNameRankSummary2.getAverageRank());
    }


}
