import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class DayOne {

    public static void main(String[] args) {
        String filePath = "DayOne/inputs/dayOne.txt";

        System.out.println(countIncreases(filePath));
        System.out.println(slidingWindowCountIncrease(filePath));
    }

    private static int countIncreases(String input) {
        int amount = 0;

        try (Stream<String> stream = Files.lines(Paths.get(input))) {

            List<Integer> depthList = stream.map(Integer::parseInt).toList();

            int previous = depthList.get(0);

            for (var x : depthList) {
                if (x > previous) {
                    amount++;
                }
                previous = x;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return amount;
    }

    private static int slidingWindowCountIncrease(String input) {
        int amount = 0;

        try (Stream<String> stream = Files.lines(Paths.get(input))) {

            List<Integer> depthList = stream.map(Integer::parseInt).toList();

            int previous = depthList.get(0) + depthList.get(1) + depthList.get(2);

            for (int i = 1; i < depthList.size(); i++) {
                int total = 0;

                if (i+2 < depthList.size()) {
                     total = depthList.get(i) + depthList.get(i + 1) + depthList.get(i + 2);
                }
                if (total > previous) {
                    amount++;
                }
                previous = total;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return amount;
    }


}
