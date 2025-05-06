import java.util.ArrayList;
import java.util.Scanner;

public class House{
    //ArrayList for the rooms of the house
    private ArrayList<Room> allRoomsInHouse = new ArrayList<>();
    private String name;

    public void addRoom(Room room) {
        allRoomsInHouse.add(room);
    }

    public ArrayList<Room> allRoomsArray() {
        return allRoomsInHouse;
    }
    public int getArraySize () {
        return allRoomsInHouse.size();
    }
    public void setName(Scanner in) {
        this.name = in.nextLine();
    }

    public String getName() {
        return name;
    }
}
