package ru.vsu.cs.novichikhin;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        int implementation = chooseImplementation();
        Map<String, Double> map = (implementation == 0) ? new HashMap<>() : new SimpleHashMap<>(100);

        Statistics statistics = new Statistics(map);
        statistics.addStatisticalDataToMap("C:\\Users\\ВЯЧЕСЛАВ\\ВГУ\\АиСД\\Sixth_task\\input.txt");

        printResult(map);
    }

    private static int chooseImplementation() {
        System.out.print("Выберете способ реализации HashMap в данной задаче " +
                "(0 - стандартная, 1 - немного изменённая соломатинская): ");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static void printResult(Map<String, Double> map) {
        System.out.println("Информация о встречаемости пар букв в тексте (от наиболее часто встречаемых) представлена " +
                "в виде \"пара букв: относительная встречаемость\" ");
        System.out.println("(т.е. отношение кол-ва этих букв к общему количеству пар букв в тексте):");

        map.entrySet().stream().sorted((a, b) -> b.getValue().compareTo(a.getValue())).forEach(a ->
                System.out.println(a.getKey() + ": " + a.getValue()));
    }
}