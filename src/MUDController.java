
import java.util.*;


/**
 * MUDController (Skeleton):
 * A simple controller that reads player input and orchestrates
 * basic commands like look around, move, pick up items,
 * check inventory, show help, etc.
 */
public class MUDController {

    private Player player;
    private boolean running;
    private final Map<String, String> rooms = new HashMap<>();
    private final Map<String, List<String>> roomItems = new HashMap<>();
    private final Map<String, String> roomNpcs = new HashMap<>();
    private String currentRoom;
    private String previousRoom;

    /**
     * Constructs the controller with a reference to the current player.
     */
    public MUDController(Player player) {

        this.player = player;
        this.running = true;
        initializeGame();    }

    public void initializeGame(){
        rooms.put("legend_hall", "A grand hall filled with ancient books and artifacts, glowing softly in the dim light.");
        rooms.put("dark_sketches", "A room adorned with eerie paintings of mythical creatures and ominous scenes.");
        rooms.put("storm_tower", "A towering chamber where the winds never cease, filled with enchanted relics and ancient weapons.");

        roomItems.put("legend_hall", new ArrayList<>(Arrays.asList("healing herb")));
        roomItems.put("dark_sketches", new ArrayList<>(Arrays.asList("sword of destiny")));
        roomItems.put("storm_tower", new ArrayList<>(Arrays.asList("gold coin")));

        roomNpcs.put("legend_hall", "Guardian of the Grove");
        roomNpcs.put("dark_sketches", "Wandering Sorcerer");
        roomNpcs.put("storm_tower", "Ancient Priestess");
    }
    /**
     * Main loop method that repeatedly reads input from the user
     * and dispatches commands until the game ends.
     */
    public void runGameLoop() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome the low text based game!\nPlease select the genre of the room. Sci-Fi or Fantasy.\nType 'help' for a list of commands");
        boolean running = true;
        while (running) {
            System.out.println("> ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("quit") || input.equals("exit")) {
                running = false;
            } else {
                handleInput(input);
            }
        }
        System.out.println("Game stopped");
        scanner.close();
        // TODO: Implement a loop that:
        // 1) Prints a prompt (e.g., "> ")
        // 2) Reads user input
        // 3) Calls handleInput(input)
        // 4) Terminates when 'running' is set to false
    }

    /**
     * Handle a single command input (e.g. 'look', 'move forward', 'pick up sword').
     */
    public void handleInput(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String argument = parts.length > 1 ? parts[1] : "";

        switch (command) {
            case "look":
                lookAround(currentRoom);
                break;
            case "attack":
                attackNPC(currentRoom);
                break;

            case "talk":
                talkToNPC(currentRoom);
                break;
            case "move":
                move(argument);
                break;
            case "pick":
                if (argument.startsWith("up ")) {
                    pickUp(argument.substring(3));
                } else {
                    System.out.println("Invalid pick up command. Use 'pick up <item>'.");
                }
                break;
            case "inventory":
                checkInventory();
                break;
            case "help":
                showHelp();
                break;
            case "quit":
            case "exit":
                System.out.println("Exiting the game...");
                break;
            default:
                System.out.println("Unknown command. Type 'help' for a list of commands.");
        }
        // TODO:
        // 1) Parse the input into a command and optionally an argument
        // 2) Use a switch/case (or if/else) to call the correct method below
        //    based on the command word
    }
public void attackNPC(String currentRoom){
    System.out.println("You are MONSTER!");
    roomNpcs.remove(currentRoom);
}
public void talkToNPC(String currentRoom){
    System.out.println("You talked with " + roomNpcs.get(currentRoom));
}
    /**
     * Look around the current room: describe it and show items/NPCs.
     */
    private void lookAround(String currentRoom) {
        System.out.println("Player are in " + currentRoom + ". \n ");
        if(roomItems.get(currentRoom) != null){
            System.out.println("You see  some items " + roomItems.get(currentRoom) + ".");
        }
        else {
            System.out.println("There’s nothing interesting here.");

        }
        if(roomItems.get(currentRoom) != null){
            System.out.println("You see someone" + roomNpcs.get(currentRoom)+ ".");
        }
        else {
            System.out.println("No one else is here.");

        }
        // TODO: Print information about the player's current room
    }

    /**
     * Move the player in a given direction (forward, back, left, right).
     */
    private void move(String direction) {

        String[] availableRooms = rooms.keySet().toArray(new String[0]);
        Random random = new Random();
        switch (direction) {
            case "forward":
                System.out.println("You move forward into the next room.");
                if (currentRoom != null) {
                    previousRoom = currentRoom;
                }
                currentRoom = availableRooms[random.nextInt(availableRooms.length)];
                break;
            case "back":
                if (previousRoom != null) {
                    System.out.println("You move back to the previous room.");
                    currentRoom = previousRoom;
                    previousRoom = null;
                } else {
                    System.out.println("There's no way back.");
                }
                break;
            case "left":
                System.out.println("You turn left and walk into a new space.");
                previousRoom = currentRoom;
                currentRoom = availableRooms[random.nextInt(availableRooms.length)];
                break;
            case "right":
                System.out.println("You turn right and find a new area.");
                previousRoom = currentRoom;
                currentRoom = availableRooms[random.nextInt(availableRooms.length)];
                break;
            default:
                System.out.println("Invalid direction. Try 'forward', 'back', 'left', or 'right'.");
                return;

        }
        System.out.println("You are now in "+ currentRoom);

        // TODO: Attempt to move to the next room in the given direction
        //       If there's no room in that direction, print an error message
        //       If successfully moved, describe the new room
    }

    /**
     * Pick up an item (e.g. "pick up sword").
     */
    private void pickUp(String arg) {
        List<String> itemsInRoom = roomItems.get(currentRoom);

        if (itemsInRoom != null && itemsInRoom.contains(arg.toLowerCase())) {
            player.addItemToInventory(arg);
            itemsInRoom.remove(arg);
            System.out.println("You picked up the " + arg + ".");
        } else {
            System.out.println("There's no such item here.");
        }
        // TODO:
        // 1) Parse out the item name if 'arg' starts with "up "
        // 2) Check if that item exists in the current room
        // 3) Remove from room, add to player's inventory
    }

    /**
     * Check the player's inventory.
     */
    private void checkInventory() {
        System.out.println("Player inventory : " + player.showInventory());
        // TODO: List the items in the player's inventory
        //       If no items, indicate that the inventory is empty
    }

    /**
     * Show help commands
     */
    private void showHelp() {
        System.out.println("Available commands:");
        System.out.println("look - Describe the current room.");
        System.out.println("talk <NPC> - Talk to the NPC in the room");
        System.out.println("move <forward|back|left|right> - Move in a direction.");
        System.out.println("pick up <item> - Pick up an item.");
        System.out.println("inventory - List the items you are carrying.");
        System.out.println("help - Show this help message.");
        System.out.println("quit / exit - End the game.");
        System.out.println("attack - you can attack npc, but I strongly advise against doing so at the moment... ");

        // TODO: Print a list of available commands and brief instructions
    }

    /**
     * (Optional) Add any other methods (e.g., attack, open door, talk, etc.)
     * if you want to extend the game logic further.
     */
}