package org.example;

import org.junit.jupiter.api.*;
import java.io.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;


class NumberProcessorTest {
    private static final String TEST_FILE = "test_numbers.txt";
    private static final String TEST_PERFORMANCE_FILE = "test_performance_numbers.txt";


    @BeforeAll
    static void setUp() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TEST_FILE))) {
            writer.println("1 4 2 3");
        }
    }

    @Test
    void testMin() throws IOException {
        List<Integer> numbers = NumberProcessor.readNumbersFromFile(TEST_FILE);
        assertEquals(1, NumberProcessor._min(numbers));
    }

    @Test
    void testMax() throws IOException {
        List<Integer> numbers = NumberProcessor.readNumbersFromFile(TEST_FILE);
        assertEquals(4, NumberProcessor._max(numbers));
    }

    @Test
    void testSum() throws IOException {
        List<Integer> numbers = NumberProcessor.readNumbersFromFile(TEST_FILE);
        assertEquals(10, NumberProcessor._sum(numbers));
    }

    @Test
    void testMult() throws IOException {
        List<Integer> numbers = NumberProcessor.readNumbersFromFile(TEST_FILE);
        assertEquals(24, NumberProcessor._mult(numbers));
    }

    @Test
    void testPerformance() throws IOException {
        // Генерация файла с 1 миллионом чисел
        try (PrintWriter writer = new PrintWriter(new FileWriter(TEST_PERFORMANCE_FILE))) {
            for (int i = 1; i <= 1000000; i++)
                writer.print(i + " ");
        }

        List<Integer> numbers = NumberProcessor.readNumbersFromFile(TEST_PERFORMANCE_FILE);

        long startTime = System.nanoTime();
        NumberProcessor._min(numbers);
        long endTime = System.nanoTime();
        System.out.println("Время выпронения функции `min`: " + (endTime - startTime) + " нс.");

        startTime = System.nanoTime();
        NumberProcessor._max(numbers);
        endTime = System.nanoTime();
        System.out.println("Время выпронения функции `max`: " + (endTime - startTime) + " нс.");

        startTime = System.nanoTime();
        NumberProcessor._sum(numbers);
        endTime = System.nanoTime();
        System.out.println("Время выпронения функции `sum`: " + (endTime - startTime) + " нс.");

        startTime = System.nanoTime();
        NumberProcessor._mult(numbers);
        endTime = System.nanoTime();
        System.out.println("Время выпронения функции `mult`: " + (endTime - startTime) + " нс.");
    }
}
