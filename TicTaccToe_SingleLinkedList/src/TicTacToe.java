import java.util.Scanner;

public class TicTacToe {
    Scanner scanner = new Scanner(System.in);

    private SinglyLinkedList board;  // The board represented by a linked list


    // Constructor to initialize the game
    public TicTacToe() {
        this.board = new SinglyLinkedList();  // Initialize the board
    }

    public void playGame() {
        boolean gameWin = false;
        System.out.println("Welcome to Tic-Tac-Toe!");
        board.displayBoard();

        while (!gameWin && !board.isBoardFull()) {
            board.PlayerMove();
            board.displayBoard();
            gameWin = board.checkWinCondition();
            if (gameWin) {
                System.out.println("Player: " + board.getWinner() + "Wins !");
            } else if (board.isBoardFull()) {
                System.out.println("Its a Draw !");
                if (askToPlayAgain()){
                    board.resetBoard();
                    gameWin = false;
                }else {
                    System.out.println("Thanks for playing");
                    break;
                }
            }
        }

    }

    // Helper method to ask if the user wants to play again
    private boolean askToPlayAgain() {
        System.out.println("Would you like to play again? (Y/N)");
        String response = scanner.nextLine().trim().toUpperCase();
        return response.equals("Y");
    }
}
