package Archive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Quera_Question {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Integer> columnVariables = new ArrayList<>();
        String columnStringVariables = input.next();
        for (char variable : columnStringVariables.toCharArray())
            columnVariables.add((int) variable);

        ArrayList<Integer> rowVariables = new ArrayList<>();
        String rowStringVariables = input.next();
        for (char variable : rowStringVariables.toCharArray())
            rowVariables.add((int) variable);

        String[] columnArr = gray(columnVariables.size());
        String[] rowArr = gray(rowVariables.size());

        ArrayList<Integer> allVariables = new ArrayList<>();
        allVariables.addAll(columnVariables);
        allVariables.addAll(rowVariables);
        Collections.sort(allVariables);

        for (String i : rowArr) {
            System.out.print("|");
            for (String j : columnArr) {
                StringBuilder keeper = new StringBuilder();
                for (int k : allVariables) {
                    if (columnVariables.contains(k))
                        keeper.append(j.charAt(columnVariables.indexOf(k)));
                    else
                        keeper.append(i.charAt(rowVariables.indexOf(k)));
                }
                System.out.print(convertBinaryToDecimal(keeper.toString()));
                System.out.print("|");
            }
            System.out.println();
        }
    }

    private static String convertBinaryToDecimal(String input) {
        int binaryNum = Integer.parseInt(input);
        int value = 1;
        int result = 0;
        while (binaryNum != 0) {
            result += (binaryNum % 10) * value;
            binaryNum /= 10;
            value *= 2;
        }
        return String.valueOf(result);
    }

    private static String[] gray(int n) {
        if (n == 0) {
            String[] empty = new String[1];
            empty[0] = "";
            return empty;
        }
        String[] code1 = gray(n - 1);
        String[] code2 = reverseArray(code1);

        for (int i = 0; i < code1.length; i++) {
            code1[i] = "0".concat(code1[i]);
            code2[i] = "1".concat(code2[i]);
        }
        return concatArray(code1, code2);
    }

    private static String[] concatArray(String[] str1, String[] str2) {
        int len1 = str1.length;
        int len2 = str2.length;
        String[] both = new String[len1 + len2];
        int index;
        for (index = 0; index < len1; index++)
            both[index] = str1[index];
        for (index = 0; index < len2; index++)
            both[index + len1] = str2[index];
        return both;
    }

    private static String[] reverseArray(String[] str) {
        int n = str.length;
        String[] temp = new String[n];
        for (int i = 0; i < n; i++)
            temp[n - i - 1] = str[i];
        return temp;
    }
}
