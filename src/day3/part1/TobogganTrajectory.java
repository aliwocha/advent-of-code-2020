package day3.part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TobogganTrajectory {

    static final String FILE_NAME = "trees.txt";
    static final int MOVE_DOWN = 1;
    static final int MOVE_RIGHT = 3;

    public static void main(String[] args) {
        List<String> list = readLines();
        List<String> extendedList = repeatToRight(list);
        printMap(extendedList);

        int verticalPosition = 0;
        int horizontalPosition = 0;
        System.out.println("\nStarting point at " + "(" + verticalPosition + ", " + horizontalPosition + ")");

        int trees = countTrees(extendedList, verticalPosition, horizontalPosition);
        System.out.println("Number of encountered trees: " + trees);
    }

    private static void printMap(List<String> list) {
        for(String line : list) {
            System.out.println(line);
        }
    }

    private static int countTrees(List<String> list, int verticalPosition, int horizontalPosition) {
        char[][] map = createCharMap(list);
        int countTrees = 0;

        while(verticalPosition < list.size()) {
            char currentPosition = map[verticalPosition][horizontalPosition];
            if (currentPosition == '#') {
                countTrees++;
            }
            verticalPosition += MOVE_DOWN;
            horizontalPosition += MOVE_RIGHT;
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

    private static List<String> repeatToRight(List<String> list) {
        int lines = list.size();
        int rows = list.get(0).length();
        int repetitionsRequired = (int) Math.ceil((MOVE_RIGHT * lines) / (double) rows);

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
