import models.Board;

public class App {
    public static void main(String[] args) throws Exception {
        
        Board board = new Board(6, 6, 3);

        board.open(2, 2);

        System.out.println(board);

    }
}
