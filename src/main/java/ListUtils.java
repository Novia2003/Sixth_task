import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ListUtils {

    public static List<String> readLinesFromFile(String fileName) throws FileNotFoundException {
        List<String> lines;
        try (Scanner scanner = new Scanner(new File(fileName))) {
            lines = new ArrayList<>();
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
        }
        return lines;
    }
}

