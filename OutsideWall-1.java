import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class OutsideWall {
    public static final String MEASUREMENT_PROMPT = "Enter the %s of the %s: ";
    public static final String INVALID_INPUT_ERROR = "Invalid input, try again: ";

    //Constant arrays with information about the walls
    public static final String[] WALL_TYPES = {"solid", "non-insulated", "insulated"};
    public static final double[] WALL_VALUES = {2.0, 1.0, 0.5};

    private double length;
    private double height;
    private double width;
    private String wallType;


    public double setWidth(Scanner in) {
        width = validDoubleInput(in, String.format(MEASUREMENT_PROMPT, "width", "room"));
        return width;
    }

    public double setLength(Scanner in) {
        length = validDoubleInput(in, String.format(MEASUREMENT_PROMPT, "length", "room"));
        return length;
    }

    public double setHeight(Scanner in) {
        height = validDoubleInput(in, String.format(MEASUREMENT_PROMPT, "height", "room"));
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public double getHeight() {
        return height;
    }

    public String setWallType(Scanner in) {
        wallType = validWallType(in, "Is the wall solid/insulated/non-insulated: ");
        return wallType;
    }

    //Method to return a specific heat Loss value based on the wall type
    public double setHeatLoss(String[] WALL_TYPES, double[] WALL_VALUES) {
        int whereInTheArray = Arrays.asList(WALL_TYPES).indexOf(wallType);
        return WALL_VALUES[whereInTheArray];

    }

    //Get information about outside walls
    public int numOutsideWalls(Scanner in) {
        int numOutsideWalls = validIntegerInput(in, "How many are outside walls? ", 0, 4);
        return numOutsideWalls;
    }


    public int numLengthOutsideWalls (Scanner in, int numOutsideWalls){
        int numLengthOutsideWalls = 0;

        if (numOutsideWalls != 4) {
            numLengthOutsideWalls = validIntegerInput(in, "How many are length outside walls? ",
                    0, numOutsideWalls);
        } else {
            numLengthOutsideWalls = 2;
        }
        return numLengthOutsideWalls;
    }

    public int numWidthOutsideWalls (int numLengthOutsideWalls, int numOutsideWalls) {
        int numWidthOutsideWalls = 0;

        if ((numOutsideWalls == 1) && (numLengthOutsideWalls == 0)) {
            numWidthOutsideWalls = 1;
        } else if ((numOutsideWalls == 1) && (numLengthOutsideWalls == 1)) {
            numWidthOutsideWalls = 0;
        } else if ((numOutsideWalls == 2) && (numLengthOutsideWalls == 0)) {
            numWidthOutsideWalls = 2;
        } else if ((numOutsideWalls == 2) && (numLengthOutsideWalls == 1)) {
            numWidthOutsideWalls = 1;
        } else if ((numOutsideWalls == 2) && (numLengthOutsideWalls == 2)) {
            numWidthOutsideWalls = 0;
        } else if ((numOutsideWalls == 3) && (numLengthOutsideWalls == 1)) {
            numWidthOutsideWalls = 2;
        } else if ((numOutsideWalls == 3) && (numLengthOutsideWalls == 2)) {
            numWidthOutsideWalls = 1;
        } else if ((numOutsideWalls == 4)) {
            numWidthOutsideWalls = 2;
        }
        return numWidthOutsideWalls;
    }

    public double areaExternalWalls (int numWidthOutsideWalls, int numLengthOutsideWalls, double width, double height, double length){
        return ((width*height*numWidthOutsideWalls) + (length*height*numLengthOutsideWalls));
    }

    //Method to check if the integer entered is valid and a positive number
    public static int validIntegerInput (Scanner in, String prompt, int min, int max){
        int value;
        do {
            System.out.print(prompt);
            while (!in.hasNextInt()) {
                System.out.print(INVALID_INPUT_ERROR);
                in.nextLine();
            }
            value = in.nextInt();
            in.nextLine();
            if (value<min || value>max){
                System.out.print(INVALID_INPUT_ERROR);
            }
        } while (value<min || value>max);

        return value;
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
    public static String validWallType(Scanner in, String prompt) {
        ArrayList<String> allWallTypes = new ArrayList<>(Arrays.asList(WALL_TYPES));
        String wallType;
        boolean isValid = false;

        do {
            System.out.print(prompt);
            wallType = in.nextLine();

            if (allWallTypes.contains(wallType)) {
                isValid = true;
            } else {
                System.out.print(INVALID_INPUT_ERROR);
            }

        } while (!isValid);
        return wallType;
    }
}
