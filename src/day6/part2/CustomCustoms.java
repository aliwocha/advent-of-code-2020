package day6.part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CustomCustoms {

    private static final String FILE_NAME = "answers.txt";

    public static void main(String[] args) {
        String[] answers = readLines().split("\\s\\s");

        int sum = 0;
        for(String answer : answers) {
            String[] groupAnswers = answer.split("\\s");
            sum += sumRepetitiveCounts(groupAnswers);
        }

        System.out.println("The sum of repetitive counts: " + sum);
    }

    private static int sumRepetitiveCounts(String[] groupAnswers) {
        List<Character> characters = new ArrayList<>();

        String firstPersonAnswer = groupAnswers[0];
        for(int i = 0; i < firstPersonAnswer.length(); i++) {
            Character letter = firstPersonAnswer.charAt(i);
            characters.add(letter);
        }

        int countRepetitive = 0;
        for(Character c : characters) {
            int count = 0;
            for(String answer : groupAnswers) {
                for(int i = 0; i < answer.length(); i++) {
                    Character letter = answer.charAt(i);
                    if(c == letter) {
                        count++;
                        break;
                    }
                }
            }

            if(count == groupAnswers.length) {
                countRepetitive++;
            }
        }

        return countRepetitive;
    }

    private static String readLines() {
        StringBuilder out = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(FILE_NAME));
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                out.append(line).append(" ");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
        }

        return out.toString();
    }
}
