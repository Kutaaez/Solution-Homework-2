import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<String> inventory;

    public Player() {
        this.inventory = new ArrayList<>();

    }

    public Player(String name) {
        this.name = name;
        this.inventory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }


    public void addItemToInventory(String item) {
        inventory.add(item);
        System.out.println(item + " added to your inventory.");
    }

    public String showInventory() {
        if (inventory.isEmpty()) {
            return " is empty.";
        } else {
            return " " + inventory;
        }
    }
}