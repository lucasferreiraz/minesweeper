import models.Board;
import models.ConsoleBoard;

public class App {
    public static void main(String[] args) throws Exception {
        
        Board board = new Board(6, 6, 3);

        new ConsoleBoard(board);

    }
}
