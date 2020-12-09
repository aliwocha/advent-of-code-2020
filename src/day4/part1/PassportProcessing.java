package day4.part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PassportProcessing {

    static final String FILE_NAME = "passports.txt";
    static final List<String> REQUIRED_PROPERTIES = Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");

    public static void main(String[] args) {
        String[] data = readLines().split("\\s\\s");
        System.out.println(Arrays.toString(data));

        String[][] passports = splitData(data);
        System.out.println(Arrays.deepToString(passports));

        int validPassports = countValidPassports(passports);
        System.out.println("Number of valid passports: " + validPassports);
    }

    private static int countValidPassports(String[][] passports) {
        int validPassports = 0;

        for (String[] passport : passports) {
            Set<String> properties = new HashSet<>();
            for (String s : passport) {
                String[] property = s.split(":");
                properties.add(property[0]);
            }

            if (properties.containsAll(REQUIRED_PROPERTIES)) {
                validPassports++;
            }
        }

        return validPassports;
    }

    private static String[][] splitData(String[] data) {
        String[][] passports = new String[data.length][];
        for(int i = 0; i < data.length; i++) {
            passports[i] = data[i].split("\\s");
        }
        return passports;
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
