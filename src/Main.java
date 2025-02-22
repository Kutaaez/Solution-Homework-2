public class Main {
    public static void main(String[] args) {
        Player player = new Player();
        MUDController game = new MUDController(player);
        game.runGameLoop();
    }
}