import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayThree {

    public static void main(String[] args) {
        final String input = "DayThree/inputs/dayThree.txt";

        System.out.println(partOne(input));

        System.out.println("\n" +partTwo(input));
    }

    private static int partOne(final String input) {
        short gamma = 0;
        short epsilon = 0;

        try (Stream<String> stream = Files.lines(Paths.get(input))) {
            List<char[]> binaries = stream.map(String::toCharArray).toList();

            int[] totals = mostPopular(binaries);
            int binariesLength = binaries.size();

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

    private static int partTwo(final String input) {

        int oxygenRating = 0;
        int cO2Rating = 0;

        try (Stream<String> stream = Files.lines(Paths.get(input))) {
            List<String> binariesInput = stream.collect(Collectors.toList());
            List<char[]> binaries = binariesInput.stream().map(String::toCharArray).toList();

            String oxygenString = Arrays.stream(mostPopular(binaries)).boxed().map(x -> {
                if (x >= binaries.size()/2.0) {
                    return "1";
                } else {
                    return "0";
                }

            }).collect(Collectors.joining());


            for (int i = 0; i< oxygenString.length(); i++) {
                final int index = i;
                List<String> matches = binariesInput.stream().filter(x -> x.startsWith(oxygenString.substring(0, oxygenString.length() - index))).toList();

                if (matches.size() == 1) {
                    System.out.println(oxygenString);
                    System.out.println(matches.get(0));
                    oxygenRating = Integer.parseInt(matches.get(0), 2);
                    break;
                }

            }

            System.out.println("");

            StringBuilder builder = new StringBuilder();

            for (var c : oxygenString.toCharArray()) {
                if (c == '1') {
                    builder.append("0");
                } else {
                    builder.append("1");
                }
            }

            String carbonDioxideString = builder.toString();


            for (int i = 0; i< carbonDioxideString.length(); i++) {
                final int index = i;
                List<String> matches = binariesInput.stream().filter(x -> x.startsWith(carbonDioxideString.substring(0, carbonDioxideString.length() - index))).toList();

                if (matches.size() == 1) {
                    System.out.println(carbonDioxideString);
                    System.out.println(matches.get(0));
                    cO2Rating = Integer.parseInt(matches.get(0), 2);
                    break;
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return oxygenRating * cO2Rating;
    }

    private static int[] mostPopular(List<char[]> input) {

        int[] totals = {0,0,0,0,0,0,0,0,0,0,0,0};

        for (var b : input) {
            for (int i = 0; i < totals.length; i++) {
                totals[i] += Character.getNumericValue(b[i]);
            }
        }

        return totals;
    }
}