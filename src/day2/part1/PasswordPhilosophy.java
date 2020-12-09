package day2.part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PasswordPhilosophy {

    static final String FILE_NAME = "passwords.txt";

    public static void main(String[] args) {
        List<String> list = readLines();
        List<String> validPasswords = getValidPasswords(list);
        int numberOfValidPasswords = validPasswords.size();

        System.out.println("Number of passwords that meet given criteria: " + numberOfValidPasswords);
    }

    private static List<String> getValidPasswords(List<String> list) {
        List<String> validPasswords = new ArrayList<>();

        for(String line : list) {
            String[] data = line.split(":\\s");
            String policy = data[0];
            String password = data[1];

            String[] policyData = policy.split("\\s");
            String range = policyData[0];
            String letter = policyData[1];

            int count = calculateMatchingLetters(password, letter.charAt(0));

            String[] rangeData = range.split("-");
            int lowerLimit = Integer.parseInt(rangeData[0]);
            int upperLimit = Integer.parseInt(rangeData[1]);

            boolean isValid = checkIfValid(count, lowerLimit, upperLimit);

            if(isValid) {
                validPasswords.add(password);
            }
        }

        return validPasswords;
    }

    private static boolean checkIfValid(int count, int lowerLimit, int upperLimit) {
        return count >= lowerLimit && count <= upperLimit;
    }

    private static int calculateMatchingLetters(String password, char letter) {
        int count = 0;
        for(int i = 0; i < password.length(); i++) {
            if(password.charAt(i) == letter) {
                count++;
            }
        }
        return count;
    }

    private static List<String> readLines() {
        List<String> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(FILE_NAME));
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                list.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
        }

        return list;
    }
}
