package day3.part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TobogganTrajectory {

    static final String FILE_NAME = "./src/day3/trees.txt";

    public static void main(String[] args) {
        List<String> list = readLines();

        Slope[] slopes = new Slope[5];
        slopes[0] = new Slope(1, 1);
        slopes[1] = new Slope(1, 3);
        slopes[2] = new Slope(1, 5);
        slopes[3] = new Slope(1, 7);
        slopes[4] = new Slope(2, 1);

        long productOfTreesEncountered = multiplyTreesEncountered(list, slopes);

        System.out.println("The number received after multiplying together " +
                "the number of trees encountered: " + productOfTreesEncountered);
    }

    private static void printMap(List<String> list) {
        for(String line : list) {
            System.out.println(line);
        }
    }

    private static long multiplyTreesEncountered(List<String> list, Slope[] slopes) {
        List<String> extendedList;

        int verticalPosition = 0;
        int horizontalPosition = 0;
        System.out.println("\nStarting point at " + "(" + verticalPosition + ", " + horizontalPosition + ")");

        int index = 1;
        long productOfTreesEncountered = 1;

        for(Slope slope : slopes) {
            extendedList = repeatToRight(list, slope.getMoveRight());
            int treesEncountered = countTrees(slope, extendedList, verticalPosition, horizontalPosition);
            System.out.println("Number of trees encountered in configuration " + index + ": " + treesEncountered);
            productOfTreesEncountered *= treesEncountered;
            index++;
        }

        return  productOfTreesEncountered;
    }

    private static int countTrees(Slope slope, List<String> list, int verticalPosition, int horizontalPosition) {
        char[][] map = createCharMap(list);
        int countTrees = 0;

        while(verticalPosition < list.size()) {
            char currentPosition = map[verticalPosition][horizontalPosition];
            if (currentPosition == '#') {
                countTrees++;
            }
            verticalPosition += slope.getMoveDown();
            horizontalPosition += slope.getMoveRight();
        }

        return countTrees;
    }

    private static char[][] createCharMap(List<String> list) {
        int lines = list.size();
        int rows = list.get(0).length();

        char[][] map = new char[lines][rows];
        for(int i = 0; i < lines; i++) {
            map[i] = list.get(i).toCharArray();
        }
        return map;
    }

    private static List<String> repeatToRight(List<String> list, int moveRight) {
        int lines = list.size();
        int rows = list.get(0).length();
        int repetitionsRequired = (int) Math.ceil((moveRight * lines) / (double) rows);

        List<String> extendedList = new ArrayList<>();
        for(String line : list) {
            line = String.valueOf(line).repeat(repetitionsRequired);
            extendedList.add(line);
        }
        return extendedList;
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
