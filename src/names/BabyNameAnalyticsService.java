package names;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * The service answers the following questions as a backend service:
 * 1. Given a year, what is the top ranked name for each gender (M and F)?
 * 2. Given a year, a gender, and a letter,
 *    how many names start with that letter and how many total babies were given those names?
 */
public class BabyNameAnalyticsService {

    Map<Integer, List<Map<String, List<BabyName>>>> allYearsNameData = new HashMap<Integer, List<Map<String, List<BabyName>>>>();

    private static final int    FIRST_LETTER_INDEX  = 0;
    private static final int    TOP_RANK            = 1;
    private static final int    RANK_DNE            = -1;
    private static final int    NO_MATCH            = -1;
    private static final int    FEMALE_INDEX        = 0;
    private static final int    MALE_INDEX          = 1;
    private static final String MALE                = "M";
    private static final String FEMALE              = "F";
    private static final int    FIRST_ELEMENT       = 0;

    //Error messages
    private static final String ERROR_INVALID_GENDER    = "Invalid input - gender";
    private static final String ERROR_NO_DATA           = "No data!";
    private static final String ERROR_NO_NAMES_EXIST    = "No names exist in data set.";
    private static final String ERROR_NOT_RANKED        = "Not Ranked";

    //Labels
    private static final String LABEL_NAME              = "Name: ";
    private static final String LABEL_OCCURRENCES       = "Occurrences: ";
    private static final String LABEL_GENDER            = "Gender: ";
    private static final String LABEL_MALE              = "Male: ";
    private static final String LABEL_FEMALE            = "Female: ";
    private static final String QUESTION_NAMES_START_WITH = "How many names start with ";
    private static final String QUESTION_NAMES_OCCURENCES = "How many occurrences of these names are there";
    private static final String QUESTION_MARK           = "? ";
    private static final String COMMA                   = ", ";
    private static final String EMPTY_STRING            = "";
    private static final String WHITE_SPACE             = " ";

    /**
     * This method gives a list of names starting with the letter with the highest occurrences of names starting with
     * that letter total.
     * @param gender gender of the baby name
     * @param startYear start year for the search
     * @param endYear end year for the search
     * @return String
     * @throws FileNotFoundException when year file is missing
     */
    public String findTopLetterListOverRange(String gender, int startYear, int endYear) throws FileNotFoundException {
        if(!validGenderInput(gender)) return ERROR_INVALID_GENDER;

        populateDataForYearRange(startYear, endYear);
        int genderIndex = getGenderIndex(gender);

        String mostPopularStartingLetter = getMostPopularStartingLetter(startYear, endYear, genderIndex);

        Set<String> displayNames = getDisplayNames(startYear, endYear, genderIndex, mostPopularStartingLetter);

        return displayNames.toString();

    }

    private Set<String> getDisplayNames(int startYear, int endYear, int genderIndex,
                                        String mostPopularStartingLetter) throws FileNotFoundException {
        List<BabyName> allGenderedBabyNamesStartingWithLetter = new ArrayList<>();
        populateNamesStartingWithLetter(startYear, endYear, genderIndex,
                mostPopularStartingLetter, allGenderedBabyNamesStartingWithLetter);

        Set<String> displayNames = new TreeSet<>();
        populateNamesForDisplay(allGenderedBabyNamesStartingWithLetter, displayNames);
        return displayNames;
    }

    private String getMostPopularStartingLetter(int startYear, int endYear, int genderIndex)
            throws FileNotFoundException {
        Map<String, Integer> letterNameOccurrences = new TreeMap<>();

        letterNameOccurrences = populateLetterNameOccurrences(startYear, endYear, letterNameOccurrences, genderIndex);

        return getMostPopularStartingLetter(letterNameOccurrences);
    }

    private void populateNamesForDisplay(List<BabyName> allGenderedBabyNamesStartingWithLetter,
                                         Set<String> displayNames) {

        for(BabyName babyName: allGenderedBabyNamesStartingWithLetter) {
            displayNames.add(babyName.getName());
        }
    }

    private void populateNamesStartingWithLetter(int startYear,
                                                 int endYear,
                                                 int genderIndex,
                                                 String mostPopularStartingLetter,
                                                 List<BabyName> allNamesStartingWithLetter)
                                                throws FileNotFoundException {

        for(int year = startYear; year <= endYear; year++)
        {
            if(getNamesForAStartLetter(genderIndex, year, mostPopularStartingLetter) == null) {
                continue;
            }
            allNamesStartingWithLetter.addAll(getNamesForAStartLetter(genderIndex, year, mostPopularStartingLetter));

        }
    }

    private String getMostPopularStartingLetter(Map<String, Integer> letterNameOccurrences) {
        String mostPopularStartingLetter = EMPTY_STRING;
        int max = 0;
        for(String key: letterNameOccurrences.keySet()) {
            if(letterNameOccurrences.get(key) == null) {
                continue;
            }
            if(letterNameOccurrences.get(key) > max) {
                max = letterNameOccurrences.get(key);
                mostPopularStartingLetter = key;
            }
        }
        return mostPopularStartingLetter;
    }

    private Map<String, Integer> populateLetterNameOccurrences(int startYear,
                                                               int endYear,
                                                               Map<String, Integer> letterNameOccurrences,
                                                               int genderIndex) throws FileNotFoundException {
        for(int year = startYear; year <= endYear; year++)
        {
            if(allYearsNameData.get(year) == null) {
                continue;
            }
            for(String firstLetter: getNamesBasedOnGenderAndYear(genderIndex, year).keySet()) {
                List<BabyName> listOfBabyNamesStartingWithLetter =
                        getNamesForAStartLetter(genderIndex, year, firstLetter);
                for (BabyName bn : listOfBabyNamesStartingWithLetter) {
                    if(!letterNameOccurrences.containsKey(firstLetter)) {
                        letterNameOccurrences.put(firstLetter, 0);
                    }
                    letterNameOccurrences.put(firstLetter,
                            letterNameOccurrences.get(firstLetter) + bn.getNumberOfOccurences());
                }


            }
        }
        return letterNameOccurrences;
    }

    /**
     * This method returns a String that tells the top name for a given gender across a period of time based on the
     * number of times that name has ranked first for its gender. It also outputs the number of times that name has
     * ranked first.
     * @param gender gender of the baby name
     * @param startYear start year for the search
     * @param endYear end year for the search
     * @return String
     * @throws FileNotFoundException when year file is missing
     */
    public String findTopNamesByGender(String gender, int startYear, int endYear) throws FileNotFoundException {
        if(!validGenderInput(gender)) {
            return ERROR_INVALID_GENDER;
        }

        Map<String, Integer> topNameOccurrences = new HashMap<>();

        populateDataForYearRange(startYear, endYear);

        int genderIndex = getGenderIndex(gender);

        populateTopNameOccurrences(startYear, endYear, topNameOccurrences, genderIndex);

        String topName = findTopName(topNameOccurrences);

        if(topNameOccurrences.get(topName) == null) return ERROR_NO_DATA;

        return getOutputString(topNameOccurrences, topNameOccurrences.get(topName), topName);
    }

    private boolean validGenderInput(String gender) {
        return gender.equals(MALE) || gender.equals(FEMALE);
    }

    private String findTopName(Map<String, Integer> topNameOccurrences) {
        int max = 0;
        String maxKey = ERROR_NO_NAMES_EXIST;

        for(String key: topNameOccurrences.keySet()) {
            if(topNameOccurrences.get(key) == null) {
                continue;
            }
            if(topNameOccurrences.get(key) > max) {
                max = topNameOccurrences.get(key);
                maxKey = key;
            }
        }
        return maxKey;
    }

    private String getOutputString(Map<String, Integer> topNameOccurrences, int max, String maxKey) {
        if(topNameOccurrences.isEmpty()) {
            return ERROR_NO_DATA;
        }
        return LABEL_NAME + maxKey + COMMA + LABEL_OCCURRENCES + max;
    }

    private void populateTopNameOccurrences(int startYear,
                                            int endYear,
                                            Map<String, Integer> topNameOccurrences,
                                            int genderIndex) throws FileNotFoundException {
        for(int year = startYear; year <= endYear; year++)
        {
            if(getNamesBasedOnGenderAndYear(genderIndex, year).keySet() == null) {
                continue;
            }
            for(String firstLetter: getNamesBasedOnGenderAndYear(genderIndex, year).keySet()) {
                List<BabyName> listOfBabyNamesStartingWithLetter =
                        getNamesForAStartLetter(genderIndex, year, firstLetter);
                for(BabyName bn: listOfBabyNamesStartingWithLetter) {
                    if(bn.getRank() == TOP_RANK) {
                        if(!topNameOccurrences.containsKey(bn.getName())) {
                            topNameOccurrences.put(bn.getName(), 0);
                        }
                        topNameOccurrences.put(bn.getName(), topNameOccurrences.get(bn.getName()) + 1);
                    }
                }
            }
        }
    }

    private List<BabyName> getNamesForAStartLetter(int genderIndex, int year, String firstLetter) {
        if(getNamesBasedOnGenderAndYear(genderIndex, year) == null) return new ArrayList<BabyName>();
        return getNamesBasedOnGenderAndYear(genderIndex, year).get(firstLetter);
    }

    private Map<String, List<BabyName>> getNamesBasedOnGenderAndYear(int genderIndex, int year) {
        try {
            getYearData(year);
        }catch(FileNotFoundException e){
            //do nothing
        }
        if(allYearsNameData.get(year) ==null){
            return new HashMap<String, List<BabyName>>();
        }
        return allYearsNameData.get(year).get(genderIndex);
    }

    public String getNameGenderSameRankMostRecent(String name,
                                                  String gender,
                                                  int year,
                                                  int mostRecentYear) throws FileNotFoundException {

        allYearsNameData.put(year, getYearData(year));
        allYearsNameData.put(mostRecentYear, getYearData(mostRecentYear));

        int genderIndex = getGenderIndex(gender);
        int rank = getRankGivenNameGenderYear(name, year, genderIndex);
        return getNameGenderBasedOnRankMostRecent(mostRecentYear, genderIndex, rank);
    }

    private String getNameGenderBasedOnRankMostRecent(int mostRecentYear, int genderIndex, int rank) {
        Map<String, List<BabyName>> babyNameLetterMap = getNamesBasedOnGenderAndYear(genderIndex, mostRecentYear);

        for(String babyNameLetterMapKey: babyNameLetterMap.keySet()) {
            List<BabyName> babyNames = babyNameLetterMap.get(babyNameLetterMapKey);
            for(int i = 0; i < babyNames.size(); i++) {
                if(babyNames.get(i).getRank() == rank) {
                    return LABEL_NAME + babyNames.get(i).getName() +
                           LABEL_GENDER + babyNames.get(i).getGender();
                }
            }
        }

        return ERROR_NO_DATA;
    }

    private int getRankGivenNameGenderYear(String name, int year, int genderIndex) {
        List<BabyName> babyNameList = getNamesForAStartLetter(genderIndex, year,
                                            name.split("")[FIRST_LETTER_INDEX]);

        int rank = 0;
        for(BabyName b: babyNameList) {
            if(b.getName().equals(name)) {
                rank = b.getRank();
            }
        }
        return rank;
    }

    /**
     * Given a name and a gender, this method returns all rankings of that name/gender pair in the data set.
     * @param gender gender of the baby name
     * @param startYear start year for the search
     * @param endYear end year for the search
     * @return String
     * @throws FileNotFoundException when year file is missing
     */
    public String getRankTimelineGivenNameGender(String name, String gender, int startYear, int endYear)
                                                        throws FileNotFoundException {

        populateDataForYearRange(startYear, endYear);

        int genderIndex = getGenderIndex(gender);

        Map<Integer, Integer> ranks = new HashMap<Integer, Integer>();
        for(int year = startYear; year <= endYear; year++) {
            ranks = addRankByYearGivenNameGender(name, genderIndex, year, ranks);
        }
        return getRankTimelineString(ranks);
    }

    private String getRankTimelineString(Map<Integer, Integer> ranks) {
        String answer = EMPTY_STRING;
        for(Integer year: ranks.keySet()){
            if(ranks.get(year) != RANK_DNE) {
                answer += year + ": " +  ranks.get(year);
            } else {
                answer += year + ": " + ERROR_NOT_RANKED;
            }
        }
        return answer;
    }

    private Map<Integer, Integer> addRankByYearGivenNameGender(String name,
                                                               int genderIndex,
                                                               int year, Map<Integer, Integer> ranks) {
        boolean matchFound = false;
        List<BabyName> babyNamesWithMatchingStartLetter =
                getNamesForAStartLetter(genderIndex, year, name.substring(0,1));
        if(babyNamesWithMatchingStartLetter == null) {
            ranks.put(year, RANK_DNE);
        } else {
            for (BabyName babyName : babyNamesWithMatchingStartLetter) {
                if (babyName.getName().equals(name)) {
                    ranks.put(year, babyName.getRank());
                    matchFound = true;
                }
            }
            if (matchFound == false) {
                ranks.put(year, RANK_DNE);
            }
        }

        return ranks;
    }

    private int getGenderIndex(String gender) {
        int genderIndex = NO_MATCH;
        if(gender.equals(MALE)) {
            genderIndex = MALE_INDEX;
        }else if (gender.equals(FEMALE)) {
            genderIndex = FEMALE_INDEX;
        }
        return genderIndex;
    }

    private void populateDataForYearRange(int startYear, int endYear) throws FileNotFoundException {

        for(int year = startYear; year <= endYear; year++) {
            allYearsNameData.put(year, getYearData(year));
        }
    }

    /**
     * Given a year, what is the top ranked name for each gender (M and F)?
     * @param year year used for analysis
     * @param gender gender of the baby name
     * @param letter
     * @return
     * @throws FileNotFoundException
     */
    public String getYearGenderLetterNumberOfNames(int year, String gender, String letter)
                                    throws FileNotFoundException {

        List<Map<String, List<BabyName>>> namesInAYear = getYearData(year);
        Map<String, List<BabyName>> mapByLetter = getMapByGender(namesInAYear, gender);

        int numberOfTotalOccurrences = getTotalNumberOfOccurrencesOfNameStartingWithLetter(mapByLetter.get(letter));

        return QUESTION_NAMES_START_WITH + letter + QUESTION_MARK + mapByLetter.get(letter).size() +
                QUESTION_NAMES_OCCURENCES + QUESTION_MARK + numberOfTotalOccurrences;

    }

    private int getTotalNumberOfOccurrencesOfNameStartingWithLetter(List<BabyName> babyNames) {

        int occurrences = 0;
        for (int i = 0; i < babyNames.size(); i++) {
            occurrences += babyNames.get(i).getNumberOfOccurences();
        }
        return occurrences;

    }

    /**
     * Given a year, a gender, and a letter, how many names start with that letter
     * and how many total babies were given those names?
     * @param year
     * @return
     * @throws FileNotFoundException
     */
    public String getTopRankedNames(int year) {
        String topRankedFemaleName = null;
        String topRankedMaleName = null;
        try {
             topRankedFemaleName = getTopRankedNameByGender(year, FEMALE);
             topRankedMaleName = getTopRankedNameByGender(year, MALE);
        }
        catch(FileNotFoundException e){
            return "";
        }
        return LABEL_FEMALE + topRankedFemaleName + COMMA + LABEL_MALE + topRankedMaleName;

    }

    private List<Map<String, List<BabyName>>> getYearData(int year) throws FileNotFoundException {

        if (!allYearsNameData.containsKey(year)) {
            allYearsNameData.put(year, BabyNameFileReader.readYearFile(year));
        }
        return allYearsNameData.get(year);

    }

    public String getTopRankedNameByGender(int year, String gender) throws FileNotFoundException{

        Map<String, List<BabyName>> babyNames = getMapByGender(getYearData(year), gender);

        for (String key : babyNames.keySet()) {
            BabyName babyName = babyNames.get(key).get(FIRST_ELEMENT);
            if (babyName.getRank() == TOP_RANK) {
                return babyName.getName();
            }
        }
        return null;
    }

    private Map<String, List<BabyName>> getMapByGender(List<Map<String, List<BabyName>>> namesInAYear, String gender) {

        int index = FEMALE_INDEX;
        if (gender.equals(MALE)) {
            index = MALE_INDEX;
        }
        return namesInAYear.get(index);
    }

    /**
     * Get rank for a given name and gender in the start and end years
     * @param name
     * @param gender
     * @param startYear
     * @param endYear
     * @return Result String which consists ranks for each year
     * @throws FileNotFoundException
     */
    public String getRankForGivenYears(String name, String gender, int startYear, int endYear)  {
        try {
            getYearData(startYear);
            getYearData(endYear);
        }catch(FileNotFoundException e){
            return EMPTY_STRING;
        }
        Map<Integer, Integer> ranks = getRanksForTheYears(name, gender, startYear, endYear);
        if (ranks == null) return EMPTY_STRING;
        return getRankTimelineString(ranks);
    }

    private Map<Integer, Integer> getRanksForTheYears(String name, String gender, int startYear, int endYear) {
        Map<Integer, Integer> ranks = new HashMap<Integer, Integer>();
        if(getGenderIndex(gender) == NO_MATCH) return null;
        addRankByYearGivenNameGender(name, getGenderIndex(gender), startYear, ranks);
        addRankByYearGivenNameGender(name, getGenderIndex(gender), endYear, ranks);
        return ranks;
    }

    /**
     * Get most rank variation for a given name and gender in the start and end years
     * @param gender
     * @param startYear
     * @param endYear
     * @return Result String which consists ranks for each year
     * @throws FileNotFoundException
     */
    public String getMostRankVariation(String gender, int startYear, int endYear) {
        Map<String, Integer> rankVariation = new HashMap<String, Integer>();
        addRankByYear(getGenderIndex(gender), startYear, rankVariation);
        addRankByYear(getGenderIndex(gender), endYear, rankVariation);
        return getMostVariation(rankVariation);
    }

    private String getMostVariation(Map<String, Integer> rankVariation) {
        Integer mostVariation = 0;
        String nameWithMostVariation = EMPTY_STRING;
        for(String name: rankVariation.keySet()) {
            if(Math.abs(rankVariation.get(name)) > mostVariation) {
                mostVariation = Math.abs(rankVariation.get(name));
                nameWithMostVariation = name;
            }
        }
        return nameWithMostVariation;
    }

    private Map<String, Integer> addRankByYear(int genderIndex, int year, Map<String, Integer> rankVariation) {
        if(genderIndex == NO_MATCH) return rankVariation;

        Map<String, List<BabyName>> babyNames = getNamesBasedOnGenderAndYear(genderIndex, year);

        for (String staringLetter : babyNames.keySet()) {
            List<BabyName> namesWithSameStartingLetter = babyNames.get(staringLetter);
            for (BabyName babyName : namesWithSameStartingLetter) {
                if (rankVariation.get(babyName.getName()) != null) {
                    int newRank = Math.abs(babyName.getRank() - rankVariation.get(babyName.getName()));
                    rankVariation.put(babyName.getName(), newRank);
                }
                rankVariation.put(babyName.getName(), babyName.getRank());
            }
        }
        return rankVariation;
    }

    /**
     * Returs the babyname that has the highest average rank within the given range of years.
     * @param gender
     * @param startYear
     * @param endYear
     * @return Names that has highest average rank
     */
    public String getHighestAverageRank(String gender, int startYear, int endYear) throws FileNotFoundException {

        Map<String, BabyNameRankSummary> babyNamesWithRankSummary = new HashMap<String, BabyNameRankSummary>();
        int genderIndex = getGenderIndex(gender);
        if(genderIndex == NO_MATCH) return EMPTY_STRING;

        for(int year = startYear; year <= endYear; year++) {
            Map<String, List<BabyName>> babyNames = getNamesBasedOnGenderAndYear(genderIndex, year);
            processYearDataForAverageRank(babyNamesWithRankSummary, babyNames);
        }
        List<BabyNameRankSummary> babyNameRankSummaries = getListOfBabyNameRankSummaries(babyNamesWithRankSummary);
        Collections.sort(babyNameRankSummaries, new BabyNameAverageRankComparator());
        BabyNameRankSummary babyNameWithHighestRank = babyNameRankSummaries.get(babyNameRankSummaries.size() - 1);
        return babyNameWithHighestRank.getName();
    }

    private void processYearDataForAverageRank(Map<String, BabyNameRankSummary> babyNamesWithRankSummary,
                                               Map<String, List<BabyName>> babyNames) {
        for (String staringLetter : babyNames.keySet()) {
            List<BabyName> namesWithSameStartingLetter = babyNames.get(staringLetter);
            for (BabyName babyName : namesWithSameStartingLetter) {
                if(!babyNamesWithRankSummary.containsKey(babyName.getName())) {
                    BabyNameRankSummary babyNameRankSummary = new BabyNameRankSummary(babyName);
                    babyNamesWithRankSummary.put(babyName.getName(), babyNameRankSummary);
                }
                babyNamesWithRankSummary.get(babyName.getName()).incrementRank(babyName.getRank());
                babyNamesWithRankSummary.get(babyName.getName()).incrementTotalCount();
            }
        }
    }

    private List<BabyNameRankSummary> getListOfBabyNameRankSummaries(Map<String, BabyNameRankSummary>
                                                                             babyNamesWithRankSummary) {
        List<BabyNameRankSummary> babyNameRankSummaries = new ArrayList<>();
        for(String babyNames: babyNamesWithRankSummary.keySet()){
            babyNameRankSummaries.add(babyNamesWithRankSummary.get(babyNames));
        }
        return babyNameRankSummaries;
    }

    public double getAverageRankForABabyName(String name, String gender, int startYear, int endYear) {
        int genderIndex = getGenderIndex(gender);
        if(genderIndex == NO_MATCH) return NO_MATCH;
        BabyNameRankSummary babyNameRankSummary = null;

        for (int year = startYear; year <= endYear; year++) {
            babyNameRankSummary = getBabyNameRankSummary(name, genderIndex, babyNameRankSummary, year);
        }
        if(babyNameRankSummary == null) {
            return 0;
        }else {
            return babyNameRankSummary.getAverageRank();
        }
    }

    private BabyNameRankSummary getBabyNameRankSummary(String name,
                                                       int genderIndex,
                                                       BabyNameRankSummary babyNameRankSummary, int year) {
        List<BabyName> babyNames = null;

        babyNames = getNamesForAStartLetter(genderIndex, year, name.substring(0, 1));
        babyNameRankSummary = getBabyNameRankSummary(name, babyNameRankSummary, babyNames);
        return babyNameRankSummary;
    }

    private BabyNameRankSummary getBabyNameRankSummary(String name, BabyNameRankSummary babyNameRankSummary,
                                                       List<BabyName> babyNames) {
        if(babyNames != null) {
            for (BabyName babyName : babyNames) {
                if(babyName.getName().equals(name)) {
                    if (babyNameRankSummary == null) {
                        babyNameRankSummary = new BabyNameRankSummary(babyName);
                    }
                    babyNameRankSummary.incrementRank(babyName.getRank());
                    babyNameRankSummary.incrementTotalCount();
                }
            }
        }
        return babyNameRankSummary;
    }

    public double getAverageRankInMostRecentYears(String name, String gender, int recentYear, int numberOfYears) {
        int genderIndex = getGenderIndex(gender);
        if(genderIndex == NO_MATCH) return NO_MATCH;
        BabyNameRankSummary babyNameRankSummary = null;

        for (int year = recentYear; year > recentYear - numberOfYears; year--) {
            babyNameRankSummary = getBabyNameRankSummaryForRecentYears(name, genderIndex, babyNameRankSummary, year);
        }
        if(babyNameRankSummary  == null) {
            return -1;
        }else {
            return babyNameRankSummary.getAverageRank();
        }
    }

    private BabyNameRankSummary getBabyNameRankSummaryForRecentYears(String name,
                                                                     int genderIndex,
                                                                     BabyNameRankSummary babyNameRankSummary,
                                                                     int year){
        List<BabyName> babyNames = null;
        babyNames = getNamesForAStartLetter(genderIndex, year, name.substring(0, 1));

        if(babyNames != null) {
            for (BabyName babyName : babyNames) {
                if (babyName.getName().equals(name)) {
                    if (babyNameRankSummary == null) {
                        babyNameRankSummary = new BabyNameRankSummary(babyName);
                    }
                    babyNameRankSummary.incrementRank(babyName.getRank());
                    babyNameRankSummary.incrementTotalCount();
                }
            }
        }
        return babyNameRankSummary;
    }

    /**
     * Gets all the baby names with the given rank within the range of years provided.
     * @param startYear
     * @param endYear
     * @param gender
     * @param rank
     * @throws FileNotFoundException
     */
    public String getNamesWithGivenRank(int startYear, int endYear, String gender, int rank)
                                                throws FileNotFoundException {
        int genderIndex = getGenderIndex(gender);
        if(genderIndex == NO_MATCH) return "";
        String babyNames = EMPTY_STRING;
        for (int year = startYear; year <= endYear; year++) {
            babyNames = babyNames + WHITE_SPACE + getNameWithGivenRank(genderIndex, year, rank);
        }
        return babyNames;
    }

    private String getNameWithGivenRank(int genderIndex, int year, int rank) throws FileNotFoundException {
        List<BabyName> babyNames = getNamesListForGenderAndYear(genderIndex, year);
        if(babyNames == null) return EMPTY_STRING;
        Collections.sort(babyNames, new BabyNameRankComparator());
        for(BabyName babyName: babyNames){
            if(babyName.getRank() == rank){
                return babyName.getName();
            }
        }
        return EMPTY_STRING;
    }

    private List<BabyName> getNamesListForGenderAndYear(int genderIndex, int year) throws FileNotFoundException {
        List<BabyName> allBabyNames = new ArrayList<BabyName>();
        Map<String, List<BabyName>> allNamesInMap = getNamesBasedOnGenderAndYear(genderIndex, year);
        for(String startLetter: allNamesInMap.keySet()){
            allBabyNames.addAll(allNamesInMap.get(startLetter));
        }
        return allBabyNames;
    }

    public String getNamesHeldRankOften(int startYear, int endYear, String gender, int rank)
                                            throws FileNotFoundException {
        int genderIndex = getGenderIndex(gender);
        if(genderIndex == NO_MATCH) return EMPTY_STRING;
        List<BabyNameRankSummary> babyNameRankSummaries = new ArrayList<BabyNameRankSummary>();
        getBabyNameRankSummary(startYear, endYear, gender, rank, genderIndex, babyNameRankSummaries);
        return getMostOftenRankedName(babyNameRankSummaries);
    }

    private String getMostOftenRankedName(List<BabyNameRankSummary> babyNameRankSummaries) {
        Collections.sort(babyNameRankSummaries, new BabyNameMostOftenRankComparator());
        String babyNames = EMPTY_STRING;
        Collections.reverse(babyNameRankSummaries);
        int maxOccurences = babyNameRankSummaries.get(FIRST_ELEMENT).getTotalOccurrencesForAllYears();
        for(BabyNameRankSummary babyNameRankSummary: babyNameRankSummaries){
            if(babyNameRankSummary.getTotalOccurrencesForAllYears() == maxOccurences) {
                babyNames = babyNames + WHITE_SPACE + babyNameRankSummary.getName();
            }
        }
        return babyNames;
    }

    private void getBabyNameRankSummary(int startYear, int endYear, String gender, int rank, int genderIndex,
                                        List<BabyNameRankSummary> babyNameRankSummaries) throws FileNotFoundException {
        for (int year = startYear; year <= endYear; year++) {
            String name = getNameWithGivenRank(genderIndex, year, rank);
            if(!name.equals(EMPTY_STRING)){
                BabyNameRankSummary babyNameRankSummary =
                        new BabyNameRankSummary(name, gender, rank, 0);
                babyNameRankSummary.incrementTotalCount();
                babyNameRankSummaries.add(babyNameRankSummary);
            }
        }
    }
}
