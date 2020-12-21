package day2.part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PasswordPhilosophy {

    static final String FILE_NAME = "./src/day2/passwords.txt";

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

            String[] rangeData = range.split("-");
            int firstPosition = Integer.parseInt(rangeData[0]);
            int secondPosition = Integer.parseInt(rangeData[1]);

            boolean isValid = checkIfValid(firstPosition, secondPosition, letter.charAt(0), password);

            if(isValid) {
                validPasswords.add(password);
            }
        }

        return validPasswords;
    }

    private static boolean checkIfValid(int firstPosition, int secondPosition, char letter, String password) {
        if(password.charAt(firstPosition - 1) == letter && password.charAt(secondPosition - 1) != letter) {
            return true;
        } else if(password.charAt(secondPosition - 1) == letter && password.charAt(firstPosition - 1) != letter) {
            return true;
        } else {
            return false;
        }
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
