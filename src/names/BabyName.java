package names;

public class BabyName {

    private String name;
    private String gender;
    private int rank;
    private int numberOfOccurences;

    public BabyName(String name, String gender, int rank, int numberOfOccurences) {
        this.name = name;
        this.gender = gender;
        this.rank = rank;
        this.numberOfOccurences = numberOfOccurences;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getNumberOfOccurences() {
        return numberOfOccurences;
    }

    public void setNumberOfOccurences(int numberOfOccurences) {
        this.numberOfOccurences = numberOfOccurences;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
