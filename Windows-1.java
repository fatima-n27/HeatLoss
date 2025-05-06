import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Windows {
    public static final String MEASUREMENT_PROMPT = "Enter the %s of the %s: ";
    public static final String INVALID_INPUT_ERROR = "Invalid input, try again: ";

    //Constant arrays with information about the windows
    public static final String[] WINDOW_TYPES = {"single", "double"};

    private double length;
    private double width;
    private String windowType;


    public double setWidth(Scanner in) {
        width = validDoubleInput(in, String.format(MEASUREMENT_PROMPT, "width", "window"));
        return width;
    }

    public double setLength(Scanner in) {
        length = validDoubleInput(in, String.format(MEASUREMENT_PROMPT, "length", "window"));
        return length;
    }


    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    //Method to add more windows


    public String setWindowType(Scanner in) {
        windowType = validWindowType(in, "Is the window single/double glazed? ");
        return windowType;
    }

    //Method to return a specific heat Loss value based on the wall type
    public double setHeatLoss(String[] WINDOW_TYPES, double[] WINDOW_VALUES) {
        int whereInTheArray = Arrays.asList(WINDOW_TYPES).indexOf(windowType);
        return WINDOW_VALUES[whereInTheArray];

    }

    //Method to calculate area
    public double calculateArea(double width, double length) {
        return length * width;
    }

    //Method to get values for measurements and validate input (a double and above 0)
    public static double validDoubleInput (Scanner in, String prompt) {
        double value;
        do {
            System.out.print(prompt);
            while (!in.hasNextDouble()) {
                System.out.print(INVALID_INPUT_ERROR);
                in.nextLine();
            }
            value = in.nextDouble();
            in.nextLine();
            if (value <= 0) {
                System.out.print("Value must be positive, try again - ");
            }
        } while (value <= 0);

        return value;
    }

    //Method to check if the wall type entered is valid (one of the expected values)
    public static String validWindowType(Scanner in, String prompt) {
        ArrayList<String> windowTypes = new ArrayList<>(Arrays.asList(WINDOW_TYPES));
        String windowType;
        boolean isValid = false;

        do {
            System.out.print(prompt);
            windowType = in.nextLine();

            if (windowTypes.contains(windowType)) {
                isValid = true;
            } else {
                System.out.print(INVALID_INPUT_ERROR);
            }

        } while (!isValid);
        return windowType;
    }
}
