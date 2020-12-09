package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReportRepair {

    static final String FILE_NAME = "expenses.txt";
    static final int SUM = 2020;

    public static void main(String[] args) {
        int[] tab = readNumbers();

        System.out.println("The result for two numbers is: " + getResultForTwo(tab));
        System.out.println("-------");
        System.out.println("The result for three numbers is: " + getResultForThree(tab));
    }

    private static long getResultForTwo(int[] tab) {
        long result = 0;
        boolean numbersFound = false;

        for(int i = 0; i < tab.length - 1; i++) {
            for(int j = i + 1; j < tab.length; j++) {
                if (tab[i] + tab[j] == SUM) {
                    System.out.println(tab[i] + " + " + tab[j] + " = " + SUM);
                    result = tab[i] * tab[j];
                    numbersFound = true;
                    break;
                }
            }
            if(numbersFound)
                break;
        }

        return result;
    }

    private static long getResultForThree(int[] tab) {
        long result = 0;
        boolean numbersFound = false;

        for(int i = 0; i < tab.length - 2; i++) {
            for(int j = i + 1; j < tab.length - 1; j++) {
                for(int k = j + 1; k < tab.length; k++) {
                    if (tab[i] + tab[j] + tab[k] == SUM) {
                        System.out.println(tab[i] + " + " + tab[j] +  " + " + tab[k] + " = " + SUM);
                        result = tab[i] * tab[j] * tab[k];
                        numbersFound = true;
                        break;
                    }
                }
            }
            if(numbersFound)
                break;
        }

        return result;
    }

    private static int[] readNumbers() {
        List<Integer> list = new ArrayList<>();
        int[] array = new int[list.size()];
        try {
            Scanner scanner = new Scanner(new File(FILE_NAME));
            while(scanner.hasNextInt()) {
                int number = scanner.nextInt();
                list.add(number);
            }
            array = list.stream().mapToInt(i -> i).toArray();
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
        }

        return array;
    }
}
