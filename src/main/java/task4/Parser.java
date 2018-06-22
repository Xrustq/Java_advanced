package task4;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public void parse(String input, String output) throws Exception {

        String str;

        try (BufferedReader reader = new BufferedReader(new FileReader(input));
             FileOutputStream outputStream = new FileOutputStream(output)) {

            while ((str = reader.readLine()) != null) {

                Pattern pattern = Pattern.compile("\\+\\d{1}\\(\\d{3}\\)\\s\\d{3}\\s\\d{2}\\s\\d{2}");
                Matcher matcher = pattern.matcher(str);

                while (matcher.find()) {
                    String numberToWrite = matcher.group().
                            replaceAll("\\s", "").
                            replaceAll("\\+", "").
                            replaceAll("\\(", "").
                            replaceAll("\\)", "");

                    outputStream.write(numberToWrite.getBytes());
                    outputStream.write('\n');
                }
            }
        }
    }
}

