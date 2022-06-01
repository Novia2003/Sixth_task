import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.*;

public class Statistics {
    private Map<String, Double> map;

    public Statistics(Map<String, Double> map) {
        this.map = map;
    }

    public List<String> addStatisticalDataToMap(String fileName) throws FileNotFoundException {
        List<String> lines = ListUtils.readLinesFromFile(fileName);
        enterAllPairsLettersIntoDictionary(lines, map);

        double quantityPairs = 0;
        Collection<Double> values = map.values();

        for (Double value : values) {
            quantityPairs += value;
        }

        Set<String> keys = map.keySet();
        List<String> list = new ArrayList<>();

        for (String key : keys) {
            map.replace(key, map.get(key) / quantityPairs);
            list.add(key);
        }

        list.sort((a, b) -> {
            int result = map.get(b).compareTo(map.get(a));

            if (result != 0) {
                return result;
            } else {
                return a.compareTo(b);
            }
        });

        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        for (int i = 0; i < list.size(); i++) {
            list.set(i, i + 1 + ". " + list.get(i) + ": " + decimalFormat.format(map.get(list.get(i))));
        }

        return list;
    }

    private void enterAllPairsLettersIntoDictionary(List<String> lines, Map<String, Double> map) {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).replaceAll("[\\s\\d-,.?!:;()'\"]+", "");

            StringBuilder builder = new StringBuilder(line);
            replaceUppercaseLettersWithLowercase(builder);

            while (builder.length() > 1) {
                String pair = builder.substring(0, 2);

                if (map.containsKey(pair)) {
                    map.replace(pair, map.get(pair) + 1);
                } else {
                    map.put(pair, 1.);
                }
                builder.delete(0, 1);
            }

            if (i != lines.size() - 1) {
                lines.set(i + 1, builder + lines.get(i + 1));
            }

        }
    }

    private void replaceUppercaseLettersWithLowercase(StringBuilder builder) {
        for (int j = 0; j < builder.length(); j++) {
            char letter = builder.charAt(j);

            if (Character.isUpperCase(letter)) {
                builder.setCharAt(j, Character.toLowerCase(letter));
            }
        }
    }
}
