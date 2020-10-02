package names;
import java.util.Comparator;

public class BabyNameRankComparator implements Comparator<BabyName> {
    public int compare(BabyName babyName1, BabyName babyName2) {
        return Integer.compare(babyName1.getRank(), babyName2.getRank());
    }
}