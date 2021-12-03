import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class DayThree {

    public static void main(String[] args) {
        final String input = "DayThree/inputs/dayThree.txt";

        System.out.println(partOne(input));
    }

    private static int partOne(final String input) {
        short gamma = 0;
        short epsilon = 0;

        try (Stream<String> stream = Files.lines(Paths.get(input))) {
            List<char[]> binaries = stream.map(String::toCharArray).toList();
            int binariesLength = binaries.size();

            int[] totals = {0,0,0,0,0,0,0,0,0,0,0,0};


            for (var b : binaries) {
                for (int i = 0; i < totals.length; i++) {
                    totals[i] += Character.getNumericValue(b[i]);
                }
            }

            for (int i = 0; i<totals.length; i++) {
                if (totals[i] > binariesLength/2.0) {
                    gamma |= 1<<totals.length - i - 1;
                } else {
                    epsilon |= 1<<totals.length - i - 1;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return gamma*epsilon;
    }
}