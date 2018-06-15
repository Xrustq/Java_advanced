package task_4;

public class Main {
    private final static String INPUT_FILE = "src\\main\\resources\\input.txt";
    private final static String OUTPUT_FILE = "src\\main\\resources\\output.txt";

    public static void main(String[] args) throws Exception {

        Utils utils = new Utils();

        utils.parse(INPUT_FILE, OUTPUT_FILE);
    }
}
