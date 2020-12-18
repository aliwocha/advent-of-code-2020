package day6.part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CustomCustoms {

    private static final String FILE_NAME = "answers.txt";

    public static void main(String[] args) {
        String[] answers = readLines().split("\\s\\s");
        System.out.println("The sum of unique counts: " + sumUniqueCounts(answers));
    }

    private static int sumUniqueCounts(String[] answers) {
        int countUnique = 0;
        for(String groupAnswer : answers) {
            Set<Character> uniqueLetters = new HashSet<>();
            for(int i = 0; i < groupAnswer.length(); i++) {
                Character letter = groupAnswer.charAt(i);
                if(!letter.toString().equals(" ")) {
                    uniqueLetters.add(letter);
                }
            }
            countUnique += uniqueLetters.size();
        }

        return countUnique;
    }

    private static String readLines() {
        StringBuilder out = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(FILE_NAME));
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                out.append(line).append(" ");
            }
        } catch(FileNotFoundException e) {
            System.out.println("File does not exist.");
        }

        return out.toString();
    }
}
