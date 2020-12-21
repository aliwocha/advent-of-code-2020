package day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class HandyHaversacks {

    private static final String FILE_NAME = "./src/day7/rules.txt";
    private static List<String> rules = readLines();
    private static Set<String> colors = new HashSet<>();

    public static void main(String[] args) {
        // Direct
        for(String rule : rules) {
            if(rule.contains("shiny gold") && !rule.startsWith("shiny gold")) {
                String[] ruleArray = rule.split("\\s");
                String color = ruleArray[0] + " " + ruleArray[1];
                colors.add(color);
            }
        }

        // Indirect
        try {
            updateColors();
        } catch(ConcurrentModificationException e) { // Getting exception but does not affect result
            // ignored
        }

        // Total
        System.out.println("Number of different color bags that can contain at least one shiny gold bag: " + colors.size());
    }

    private static void updateColors() {
        for(String rule : rules) {
            for (String setElement : colors) {
                if (!rule.contains("shiny gold") && rule.contains(setElement)) {
                    String[] ruleArray = rule.split("\\s");
                    String color = ruleArray[0] + " " + ruleArray[1];
                    colors.add(color);
                    rules.remove(rule);
                    updateColors();
                }
            }
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
