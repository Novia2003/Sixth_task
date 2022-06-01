import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RunWith(Parameterized.class)
public class StatisticsTest {
    private final String inputFileName;
    private final String outputFileName;

    public StatisticsTest(String inputFileName, String outputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
    }


    @Parameterized.Parameters
    public static List<String[]> cases() {
        return Arrays.asList(new String[]{"src/test/resources/inputForOneWord.txt",
                        "src/test/resources/outputForOneWord.txt"},
                new String[]{"src/test/resources/inputForOneSentence.txt",
                        "src/test/resources/outputForOneSentence.txt"},
                new String[]{"src/test/resources/inputForStringsWithOneLetter.txt",
                        "src/test/resources/outputForStringsWithOneLetter.txt"},
                new String[]{"src/test/resources/inputForExcerptFromSong.txt",
                        "src/test/resources/outputForExcerptFromSong.txt"},
                new String[]{"src/test/resources/inputForPlainText.txt",
                        "src/test/resources/outputForPlainText.txt"});
    }

    @Test
    public void addStatisticalDataToMap() throws FileNotFoundException {
        Map<String, Double> map = new SimpleHashMap<>(100);

        Statistics statistics = new Statistics(map);
        List<String> list = statistics.addStatisticalDataToMap(inputFileName);

        List<String> expectedList = ListUtils.readLinesFromFile(outputFileName);

        Assert.assertEquals(expectedList, list);
    }
}
