package names;

public class BabyNameRankSummary extends BabyName{
    private int totalRank;
    private int totalOccurrencesForAllYears;

    public BabyNameRankSummary(String name, String gender, int rank, int numberOfOccurences) {
        super(name, gender, rank, numberOfOccurences);
    }

    public BabyNameRankSummary(BabyName babyName) {
        super(babyName.getName(), babyName.getGender(), babyName.getRank(), babyName.getNumberOfOccurences());
    }

    public int getTotalRank() {
        return totalRank;
    }

    public void setTotalRank(int totalRank) {
        this.totalRank = totalRank;
    }

    public int getTotalOccurrencesForAllYears() {
        return totalOccurrencesForAllYears;
    }

    public void setTotalOccurrencesForAllYears(int totalOccurrencesForAllYears) {
        this.totalOccurrencesForAllYears = totalOccurrencesForAllYears;
    }

    public void incrementRank(int rank){
        totalRank = totalRank + rank;
    }

    public void incrementTotalCount(){
        totalOccurrencesForAllYears++;
    }

    public double getAverageRank(){
        return totalRank * 1.0 / totalOccurrencesForAllYears;
    }


}
