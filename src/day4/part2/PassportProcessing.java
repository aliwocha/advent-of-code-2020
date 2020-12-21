package day4.part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PassportProcessing {

    static final String FILE_NAME = "./src/day4/passports.txt";
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
                boolean isPropertyValid = checkProperty(property);
                if(!isPropertyValid) {
                    break;
                }
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

    private static boolean checkProperty(String[] property) {
        String key = property[0];
        String value = property[1];

        if(key.equals("byr") && value.matches("19[2-9][0-9]|200[0-2]")) {
            return true;
        } else if(key.equals("iyr") && value.matches("201[0-9]|2020")) {
            return true;
        } else if(key.equals("eyr") && value.matches("202[0-9]|2030")) {
            return true;
        } else if(key.equals("hgt") && value.matches("1[5-8][0-9]cm|19[0-3]cm|59in|6[0-9]in|7[0-6]in")) {
            return true;
        } else if(key.equals("hcl") && value.matches("#[0-9a-f]{6}")) {
            return true;
        } else if(key.equals("ecl") && value.matches("amb|blu|brn|gry|grn|hzl|oth")) {
            return true;
        } else if(key.equals("pid") && value.matches("[0-9]{9}")) {
            return true;
        } else if(key.equals("cid")) {
            return true;
        }

        return false;
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
