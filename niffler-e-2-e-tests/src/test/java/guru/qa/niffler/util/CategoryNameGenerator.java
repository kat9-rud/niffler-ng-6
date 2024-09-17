package guru.qa.niffler.util;

import java.util.Random;

public class CategoryNameGenerator {
    public static String generateRandomCategoryName(){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder categoryName = new StringBuilder("Category-");
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            categoryName.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }

        return categoryName.toString();
    }
}
