import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<String> inventory;
    private String currentRoom;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
        this.inventory = new ArrayList<>();
        this.currentRoom = "Starting Room"; // Initial room
    }

    public String getName() {
        return name;
    }

    public String getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(String newRoom) {
        this.currentRoom = newRoom;
    }

    public void addItemToInventory(String item) {
        inventory.add(item);
        System.out.println(item + " added to your inventory.");
    }

    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("Your inventory: " + inventory);
        }
    }
}