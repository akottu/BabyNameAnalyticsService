package names;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This class is used to read and parse the given input files located in the data directory and store the parsed
 * information in a complex data structure.
 */
public class BabyNameFileReader {

    private static String[] capitalLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private static final int FIRST_ELEMENT = 0;
    private static final int FIRST_LETTER_INDEX = 0;
    private static final int SECOND_ELEMENT = 1;
    private static final int THIRD_ELEMENT = 2;
    private static final String NEW_LINE = "\n";
    private static final String DATA_FILE_PATH = "data/ssa_complete/yob";
    private static final String FILE_EXTENSION = ".txt";

    /**
     * This method iterates through the content of a specific file based on the year parameter, and returns a complex
     * list to the Service class for its use.
     * @param year
     * @return
     * @throws FileNotFoundException
     */
    public static List<Map<String, List<BabyName>>> readYearFile(int year) throws FileNotFoundException {

        List<Map<String, List<BabyName>>> listForYear = new ArrayList<>();
        Map femaleBabies = new HashMap<String, ArrayList<BabyName>>();
        listForYear.add(femaleBabies);
        Map maleBabies = new HashMap<String, ArrayList<BabyName>>();
        listForYear.add(maleBabies);

        String file = DATA_FILE_PATH + year + FILE_EXTENSION;
        Scanner scanner = new Scanner(new File(file));
        scanner.useDelimiter(NEW_LINE);
        int counter = 0;
        boolean reachedFirstMaleBaby = false;
        while(scanner.hasNext()) {
            counter++;
            BabyName babyName = parseYearFileLine(scanner.next(), counter);
            if(babyName.getGender().equals("F")) {
                addBabyNameByGender(babyName, femaleBabies);
            } else {
                if(reachedFirstMaleBaby == false) {
                    counter = 1;
                    reachedFirstMaleBaby = true;
                    babyName.setRank(counter);
                }
                addBabyNameByGender(babyName, maleBabies);
            }

        }
        scanner.close();
        return listForYear;
    }

    private static BabyName parseYearFileLine(String next, int counter) {
        String[] babyNameLineEntries = next.split(",");

        return new BabyName(babyNameLineEntries[FIRST_ELEMENT],
                            babyNameLineEntries[SECOND_ELEMENT], counter,
                            Integer.parseInt(babyNameLineEntries[THIRD_ELEMENT]));
    }

    private static void addBabyNameByGender(BabyName babyName, Map<String, ArrayList<BabyName>> babyMap) {

        String firstLetterOfBabyName = babyName.getName().split("")[FIRST_LETTER_INDEX];
        if(!babyMap.containsKey(firstLetterOfBabyName)) {
            ArrayList<BabyName> babyNames = new ArrayList<>();
            babyNames.add(babyName);
            babyMap.put(firstLetterOfBabyName, babyNames);
        } else {
            babyMap.get(firstLetterOfBabyName).add(babyName);
        }
    }
}
