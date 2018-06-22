package task4;

public class Main {

    private final static String INPUT_FILE = "src\\main\\resources\\input.txt";
    private final static String OUTPUT_FILE = "src\\main\\resources\\output.txt";

    public static void main(String[] args) throws Exception {

        Parser parser = new Parser();

        parser.parse(INPUT_FILE, OUTPUT_FILE);
    }
}
