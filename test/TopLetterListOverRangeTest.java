import names.BabyNameAnalyticsService;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This test class is for Basic Implementation Prompt 4
 * "Given a range of years (start and end inclusive), what is the most popular letter that girls’ names started with?
 * Answer this question with an alphabetized list of all girls' names that start with this letter in the data set
 * rather than just a single letter. In the case of a tie, return only the alphabetically first letters instead of
 * names starting with different letters."
 */
public class TopLetterListOverRangeTest {

    @Test
    void getTopLetterListOverRangeTestFiles() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("[Marcy, Michelle, Molly]",
                babyNameAnalyticsService.findTopLetterListOverRange("F", 3000, 3002));
    }

    @Test
    void getTopLetterListOverRangeTestFiles_EXISTING_DATA() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("[Mabel, Mabell, Mabelle, Mable, Macel, Macey, Macie, Mackie, Macy, Mada, " +
                        "Madalene, Madaline, Madalyn, Maddie, Madelaine, Madeleine, Madelene, Madeline, " +
                        "Madelon, Madelyn, Madge, Madgie, Madie, Madline, Madlyn, Madolin, Madolyn, Madonna," +
                        " Madora, Mae, Maebell, Maebelle, Magdalen, Magdalena, Magdalene, Maggie, Magnolia, " +
                        "Mahala, Mahalia, Mahalie, Maida, Maidie, Maie, Maire, Maisie, Majel, Majorie, Malinda, " +
                        "Malissa, Malissia, Malissie, Mallie, Malvina, Mamie, Mammie, Manda, Mandie, Mandy, Manerva," +
                        " Manie, Manila, Manilla, Mannie, Manuela, Manuelita, Maple, Maranda, Marceline, Marcella, " +
                        "Marcelle, Marcelline, Marcia, Mardie, Maree, Mareta, Margaret, Margarete, Margaretha, " +
                        "Margarett, Margaretta, Margarette, Margarita, Margarite, Marge, Margeret, Margert, " +
                        "Margery, Margie, Margot, Margret, Margrett, Marguerita, Marguerite, Margueritte, " +
                        "Margurite, Maria, Mariah, Mariam, Marian, Mariana, Marianna, Marianne, Marie, Marien, " +
                        "Marietta, Marilla, Marilyn, Marina, Marinda, Marine, Marion, Marita, Marjorie, Marjory, " +
                        "Mark, Marquerite, Marrie, Marry, Marta, Martha, Martie, Martin, Martina, Marvel, Mary, " +
                        "Maryann, Maryanna, Marybelle, Marye, Maryellen, Maryetta, Maryjane, Maryland, Marylee, " +
                        "Marylou, Maryon, Marzella, Massie, Mata, Mathilda, Mathilde, Matie, Matilda, Matilde, " +
                        "Mattie, Mattye, Maud, Maude, Maudie, Maudine, Maureen, Maurice, Maurine, Mavis, Maxie, " +
                        "Maxine, May, Maybel, Maybell, Maybelle, Maye, Mayme, Maymie, Mayola, Maysel, Mazie, Mearl, " +
                        "Meda, Media, Medora, Melanie, Melba, Melda, Melina, Melinda, Melissa, Melita, Mellie, " +
                        "Melva, Melvie, Melvina, Melvinia, Mennie, Mercedes, Mercy, Meredith, Merle, Merlie, Merlin, " +
                        "Merna, Mertice, Mertie, Meryl, Meta, Metha, Metta, Mettie, Micaela, Mickey, Mignon, Mila, " +
                        "Milda, Mildred, Millicent, Millie, Milly, Milton, Mima, Mimi, Mina, Minda, Mineola, " +
                        "Minerva, Minervia, Minette, Minna, Minnette, Minnie, Minta, Mintie, Mira, Miranda, " +
                        "Miriam, Mirtie, Missie, Missouri, Mittie, Modena, Modie, Mollie, Molly, Mollye, Mona, " +
                        "Monica, Monie, Monna, Monnie, Monta, Montie, Mora, Moselle, Mossie, Mozel, Mozell, " +
                        "Mozella, Mozelle, Murel, Murial, Muriel, Murrel, Myra, Myrl, Myrle, Myrna, Myrta, " +
                        "Myrtha, Myrtice, Myrtie, Myrtis, Myrtle]",
                babyNameAnalyticsService.findTopLetterListOverRange("F", 1900, 1901));
    }

    @Test
    void getTopLetterListOverRangeLetterNotInFiles() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("[]",
                babyNameAnalyticsService.findTopLetterListOverRange("F", 3010, 3011));
    }

    @Test
    void getTopLetterListOverRangeFileNotFoundException() {
        Exception exception = assertThrows(FileNotFoundException.class, () -> {
            BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
            babyNameAnalyticsService.findTopLetterListOverRange("F", 3000, 3003);
        });
    }

    @Test
    void getTopLetterListOverRangeInvalidGender() throws FileNotFoundException {
        BabyNameAnalyticsService babyNameAnalyticsService = new BabyNameAnalyticsService();
        assertEquals("Invalid input - gender",
                babyNameAnalyticsService.findTopLetterListOverRange("A", 3010, 3011));
    }
}
