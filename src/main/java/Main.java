import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        int implementation = chooseImplementation();
        Map<String, Double> map = (implementation == 0) ? new HashMap<>() : new SimpleHashMap<>(100);

        Statistics statistics = new Statistics(map);
        List<String> list = statistics.addStatisticalDataToMap("src/main/resources/input.txt");

        printResult(list);
    }

    private static int chooseImplementation() {
        System.out.print("Выберете способ реализации HashMap в данной задаче " +
                "(0 - стандартная, 1 - немного изменённая соломатинская): ");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static void printResult(List<String> list) {
        System.out.println("Информация о встречаемости пар букв в тексте (от наиболее часто встречаемых) представлена " +
                "в виде \"пара букв: относительная встречаемость\" ");
        System.out.println("(т.е. отношение кол-ва этих букв к общему количеству пар букв в тексте):");

        for (String string : list) {
            System.out.println(string);
        }
    }
}
