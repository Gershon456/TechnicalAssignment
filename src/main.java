import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Assumptions:
 * Random numbers are integers, from 0 - 2147483647
 * Random numbers do not have to be unique
 * Exactly 1 million numbers are generated
 * Program is able to write generated files (i.e no permission, space issues)
 * Each consecutive program run will overwrite the output file, rather than append to it
 */
public class main {
    public static int RANDOM_RANGE = Integer.MAX_VALUE;
    public static int NUM_RANDOM_NUMBERS = 1000000;

    public static void main(String[] args) {
        System.out.println("Running program");

        List<Integer> numbers = getRandomNumbers(NUM_RANDOM_NUMBERS);
        writeNumbersToFile(numbers, "numbers.txt");
        Collections.sort(numbers);
        writeNumbersToFile(numbers, "sortedNumbers.txt");

        System.out.println("Program finished execution.");
    }

    public static List<Integer> getRandomNumbers(int amount) {
        if (amount <= 0) {
            return new ArrayList<>();
        }
        List<Integer> randomNumbers = new ArrayList<>();
        for (int x = 0; x < amount; x++) {
            randomNumbers.add((int) (Math.random() * RANDOM_RANGE));
        }
        return randomNumbers;
    }

    public static void writeNumbersToFile(List<Integer> list, String fileName) {
        if (list == null || list.size() == 0 || fileName == null) {
            return;
        }
        try {
            File file = new File(fileName);
            if (!file.isFile()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            for (int number : list) {
                fileWriter.write(String.format("%d%n", number));
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing numbers to the file");
            System.out.println(e.getMessage());
        }
    }
}
