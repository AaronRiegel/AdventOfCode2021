import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class DayTwo {


    public static void main(String[] args) {
        final String input = "DayTwo/inputs/dayTwo.txt";

        System.out.println(traverse(input));
        System.out.println(traverseWithMultiplication(input));
    }

    private static int traverse(final String input) {
        int x = 0;
        int y = 0;

        try (Stream<String> stream = Files.lines(Paths.get(input))) {

            List<String> directions = stream.toList();

            for (var s : directions) {
                String direction = s.split(" ")[0];
                int velocity = Integer.parseInt(s.split(" ")[1]);

                switch (direction) {
                    case "forward" -> x += velocity;
                    case "down" -> y += velocity;
                    case "up" -> y -= velocity;
                    default -> {
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return x * y;
    }

    private static int traverseWithMultiplication(final String input) {
        int horizontalPos = 0;
        int depth = 0;
        int aim = 0;

        try (Stream<String> stream = Files.lines(Paths.get(input))) {

            List<String> directions = stream.toList();

            for (var s : directions) {
                String direction = s.split(" ")[0];
                int velocity = Integer.parseInt(s.split(" ")[1]);

                switch (direction) {
                    case "forward" -> {
                        horizontalPos += velocity;
                        depth += velocity * aim;
                        }
                    case "down" -> {
                        aim +=velocity;
                    }
                    case "up" -> {
                        aim -= velocity;
                    }
                    default -> {
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return horizontalPos * depth;
    }
}
