package task_4;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private final static String INPUT_FILE = "src\\main\\resources\\input.txt";
    private final static String OUTPUT_FILE = "src\\main\\resources\\output.txt";

    public static void main(String[] args) throws IOException {

        String str;

        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
             FileOutputStream outputStream = new FileOutputStream(OUTPUT_FILE)) {

            while ((str = reader.readLine()) != null) {
                System.out.println(str);

                String regex = "\\+\\d{1}\\(\\d{3}\\)\\s\\d{3}\\s\\d{2}\\s\\d{2}";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(str);

                while (matcher.find()) {
                    String strToWrite = matcher.group().
                            replaceAll("\\s", "").
                            replaceAll("\\+", "").
                            replaceAll("\\(", "").
                            replaceAll("\\)", "");

                    System.out.println(strToWrite);
                    outputStream.write(strToWrite.getBytes());
                    outputStream.write('\n');
                }
            }
        }
    }
}
