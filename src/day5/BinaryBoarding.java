package day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BinaryBoarding {

    static final String FILE_NAME = "plane_seats.txt";

    public static void main(String[] args) {
        List<String> list = readLines();
        List<Integer> seats = new ArrayList<>();

        int highestSeatId = 0;
        for(String seatCode : list) {
            int seatId = getSeatId(seatCode);
            seats.add(seatId);
            if(seatId > highestSeatId) {
                highestSeatId = seatId;
            }
        }

        int mySeatId = findSeatId(seats);

        System.out.println("The highest seat ID on a boarding pass: " + highestSeatId); // Part 1
        System.out.println("My seat ID: " + mySeatId); // Part 2
    }

    private static int findSeatId(List<Integer> seats) {
        List<Integer> sortedList = seats.stream().sorted().collect(Collectors.toList());

        int takenSeatId = sortedList.get(0);
        int index = 0;
        for(int i = sortedList.get(0); i < sortedList.size(); i++) {
            takenSeatId = sortedList.get(index);
            index++;
            if(i != takenSeatId) {
                break;
            }
        }

        return takenSeatId - 1;
    }

    private static int getSeatId(String seatCode) {
        int rowId = getRowId(seatCode);
        int columnId = getColumnId(seatCode);
        return rowId * 8 + columnId;
    }

    private static int getRowId(String seatCode) {
        int lowerBound = 0;
        int upperBound = 128;
        int[] rows = IntStream.range(lowerBound, upperBound).toArray();

        for(int i = 0; i < 7; i++) {
            char tmp = seatCode.charAt(i);
            if(tmp == 'F') { // lower half
                upperBound -= rows.length / 2;
                rows = IntStream.range(lowerBound, upperBound).toArray();
            } if(tmp == 'B') { // upper half
                lowerBound += rows.length / 2;
                rows = IntStream.range(lowerBound, upperBound).toArray();
            }
        }

        return rows[0];
    }

    private static int getColumnId(String seatCode) {
        int lowerBound = 0;
        int upperBound = 8;
        int[] columns = IntStream.range(lowerBound, upperBound).toArray();

        for(int i = 7; i < seatCode.length(); i++) {
            char tmp = seatCode.charAt(i);
            if(tmp == 'L') { // lower half
                upperBound -= columns.length / 2;
                columns = IntStream.range(lowerBound, upperBound).toArray();
            } if(tmp == 'R') { // upper half
                lowerBound += columns.length / 2;
                columns = IntStream.range(lowerBound, upperBound).toArray();
            }
        }

        return columns[0];
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
