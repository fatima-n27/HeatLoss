import java.util.ArrayList;
import java.util.Scanner;

public class Room {
    private String name;
    private double length;
    private double height;
    private double width;

    ArrayList<OutsideWall> outsideWallArrayList = new ArrayList<>();
    ArrayList<Windows> windowsArrayList = new ArrayList<>();

    //Constructor
    public Room(String name, double length, double height, double width) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    //Set name
    public String setName(Scanner in) {
        System.out.print("Enter the room name: ");
        name = in.nextLine();
        return name;
    }

    public double calculateArea(double length, double width) {
        return length*width;
    }

    public String toString() {
        return "The heat loss from the " + name + " is: ";
    }
}
