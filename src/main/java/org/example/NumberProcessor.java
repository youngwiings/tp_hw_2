package org.example;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class NumberProcessor {

    public static List<Integer> readNumbersFromFile(String filename) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));

        return Arrays.stream(bufferedReader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static int _min(List<Integer> numbers) {
        int min_number = Integer.MAX_VALUE;
        for (int number : numbers)
            if (number < min_number)
                min_number = number;

        return min_number;
    }

    public static int _max(List<Integer> numbers) {
        int max_number = Integer.MIN_VALUE;
        for (int number : numbers)
            if (number > max_number)
                max_number = number;

        return max_number;
    }

    public static int _sum(List<Integer> numbers) {
        int sum_result = 0;
        for (int number : numbers)
            sum_result += number;

        return sum_result;
    }

    public static long _mult(List<Integer> numbers) {
        long mult_result = 1;
        for (int number : numbers)
            mult_result *= number;

        return mult_result;
    }

    public static void main(String[] args) {
        try {
            List<Integer> numbers = readNumbersFromFile("numbers.txt");
            System.out.println("Минимальное число во входном файле: " + _min(numbers));
            System.out.println("Максимальное во входном файле: " + _max(numbers));
            System.out.println("Сумма чисел во входном файле: " + _sum(numbers));
            System.out.println("Произведение чисео во входном файле: " + _mult(numbers));
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }
}
