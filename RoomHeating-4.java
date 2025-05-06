import java.util.ArrayList;
import java.util.Scanner;

public class RoomHeating{
    //Constants used in calculations
    public static final int TEMPERATURE_DIFFERENCE = 22;
    public static final double HEAT_LOSS_THROUGH_FLOOR = 0.3;

    //String output
    public static final String INVALID_INPUT_ERROR = "Invalid input, try again: ";

    //ArrayLists for wall information
    public static final String[] WALL_TYPES = {"solid", "non-insulated", "insulated"};
    public static final double[] WALL_VALUES = {2.0, 1.0, 0.5};

    //ArrayLists for window information
    public static final String[] WINDOW_TYPES = {"single", "double"};
    public static final double[] WINDOW_VALUES = {5.7, 3.0};

    //ArrayList to store all the room names
    static ArrayList<String> allRoomNames = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double energyRequired;
        String moreRooms;

        double lengthWindow;
        double widthWindow;

        String moreWindows;
        double totalEnergy = 0;
        ArrayList<Double> roomHeatRequired = new ArrayList<>();

        House myHouse = new House();

        //Prompt for the house' name
        System.out.print("What is the name for your house? ");
        myHouse.setName(in);

        //Gets name of the house
        myHouse.getName();


        do {
            OutsideWall outsideWall = new OutsideWall();
            //Prompts to get information about the walls
            double heightRoom = outsideWall.setHeight(in);
            double lengthRoom = outsideWall.setLength(in);
            double widthRoom = outsideWall.setWidth(in);

            outsideWall.setWallType(in);

            Room myRoom = new Room("myRoom", lengthRoom, heightRoom, widthRoom);
            allRoomNames.add(myRoom.setName(in));



            myRoom.outsideWallArrayList.add(outsideWall);

            do {
                Windows window = new Windows();
                //Gets information about the window
                lengthWindow = window.setLength(in);

                while (lengthWindow>lengthRoom) {
                    System.out.println(INVALID_INPUT_ERROR);
                    lengthWindow = in.nextDouble();
                }

                widthWindow = window.setWidth(in);

                while (widthWindow>widthRoom) {
                    System.out.print(INVALID_INPUT_ERROR);
                    widthWindow = in.nextDouble();
                }

                window.setWindowType(in);

                myRoom.windowsArrayList.add(window);

                System.out.print("Do you want to add more windows for this room? ");
                moreWindows = in.nextLine();

            } while (moreWindows.equalsIgnoreCase("yes"));


            int numOutsideWalls = outsideWall.numOutsideWalls(in);

            //Sets the number of outsideWalls
            int numLengthOutsideWalls = outsideWall.numLengthOutsideWalls(in, numOutsideWalls);
            int numWidthOutsideWalls = outsideWall.numWidthOutsideWalls(numLengthOutsideWalls, numOutsideWalls);

            //Calculates area of the room
            double areaRoom = myRoom.calculateArea(lengthRoom, widthRoom);

            //Sets heatLoss from walls
            double heatLossWalls = outsideWall.setHeatLoss(WALL_TYPES, WALL_VALUES);

            //Calculate area of each window in the array list
            double areaWindow = 0;
            double totalWindowArea = 0;
            double lossWindow = 0;
            double totalWindowLoss = 0;
            double heatLossWindow = 0;

            for (Windows thisWindow : myRoom.windowsArrayList) {
                areaWindow = thisWindow.calculateArea(thisWindow.getWidth(), thisWindow.getLength());
                heatLossWindow = thisWindow.setHeatLoss(WINDOW_TYPES, WINDOW_VALUES);
                lossWindow = heatLossCalculations(areaWindow, heatLossWindow, TEMPERATURE_DIFFERENCE);

                totalWindowArea += areaWindow;
                totalWindowLoss += lossWindow;
            }


            double areaExternalWalls = (outsideWall.areaExternalWalls(numWidthOutsideWalls, numLengthOutsideWalls,
                    widthRoom, heightRoom, lengthRoom)) - totalWindowArea;


            //Calculates the heat lost from each
            double lossWalls = heatLossCalculations(areaExternalWalls, heatLossWalls, TEMPERATURE_DIFFERENCE);
            double lossFloor = heatLossCalculations(areaRoom, HEAT_LOSS_THROUGH_FLOOR, TEMPERATURE_DIFFERENCE);


            //Calculates total energy required for the room
            energyRequired = lossFloor + lossWalls + totalWindowLoss;
            System.out.println("Total heat loss is: " + energyRequired);

            roomHeatRequired.add(energyRequired);
            myHouse.allRoomsArray().add(myRoom);

            System.out.println(" ");
            System.out.print("Do you want to add data for more rooms? ");
            moreRooms = in.nextLine();

            //Running total of total energy required for all rooms
            totalEnergy += energyRequired;


        } while (moreRooms.equalsIgnoreCase("yes"));

        //Prints out total heat loss for all rooms
        for(int i = 0; i <myHouse.getArraySize(); i++) {
            Room myHouseRooms = myHouse.allRoomsArray().get(i);
            double heatLoss = roomHeatRequired.get(i);

            System.out.print(myHouseRooms + "" + heatLoss + "\n");
        }

        System.out.println("The total heat loss for " + myHouse.getName() + " is: " + totalEnergy);

    }

    //Method to calculate the heatLoss
    public static double heatLossCalculations (double area, double heatLossFactor, double tempDifference) {
        return area*heatLossFactor*tempDifference;
    }
}
