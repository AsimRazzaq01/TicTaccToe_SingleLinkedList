import java.util.Scanner;

public class SinglyLinkedList {
    private Node head;  // Head of the linked list
    private int size = 9;
    private String currentPlayer;
    private int position;
    private String Player1 = "X ";
    private String Player2 = "O ";
    Scanner scanner = new Scanner(System.in);

    // Constructor to initialize an empty linked list
    public SinglyLinkedList() {
        this.head = null;
        this.currentPlayer = Player1;  // 'X' starts the game
        initializeBoard();
    }

    // Method to insert a new node at the end of the linked list
    private void insert(String data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Method to initialize the board w/ data empty cell -* -
    private void initializeBoard() {
        for (int i = 1; i <= size; i++) {
            insert("* ");
        }
    }

// Method to display the Tic Tac Toe board
    public void displayBoard() {
        Node current = head;
        int count = 1;

        // GAME BOARD FOR TIC TAC TOE IS 1- 9 SO  SIZE < 10
        while (current != null && count < 10) {
            System.out.print(current.getData() );    // Print the data in the current node
            if (count % 3 == 0) {
                System.out.println();     // After every third element, go to the next line
            }else {
                System.out.print("| ");  // Print the pipe between columns
            }
            current = current.next;
            count++;
        }
    }

    // Method for getting player
    public void PlayerMove() {
        // Show who is playing X or O
        System.out.println("Current Player: " + currentPlayer );
        int position = getPositionValue();
        updatePosition(position,currentPlayer);
        //Switch current-player for players
        if (currentPlayer.equals(Player1)) {
            currentPlayer = Player2;
        }
        else if (currentPlayer.equals(Player2)) {
            currentPlayer = Player1;
        }
    }

    // Method to get The current player
    public String getCurrentPlayer() {
        return currentPlayer ;
    }

    // Method to get The Winning player
    public String getWinner() {
        String Winner = "";
        if (currentPlayer.equals(Player1)) {
            Winner = Player2 ;
        }
        if (currentPlayer.equals(Player2)) {
            Winner = Player1 ;
        }
        return Winner;
    }

    // Method to get the value at a specific position
    public int getPositionValue (){
        // get position from user if available
        while ( position < 1 || position > 9 || !isPositionAvailable(position)) {
            System.out.println("Please enter Board position 1- 9");
            position = scanner.nextInt();
        }
        return position;
    }

    // Method to check if the position is available
    private boolean isPositionAvailable(int position) {
        // get position for current node
        Node current = getNodeAtPosition(position);
        // Returns true if the position is empty
        return current.getData().equals("* ");
    }

    // Method to update a specific position with player data (X or O)
    public void updatePosition(int position, String data) {
        Node current = head;
        // Iterate through the list to update the position w/ data
        for (int i = 1; i < position; i++) {
            current = current.next;
        }
        current.data = data;
    }

    // Method to check if the board is full (for draw condition)
    public boolean isBoardFull () {
        Node current = head;
        while (current != null) {
            if (current.data.equals("* ")) {  // Means empty SPACE so board not full
                return false;                 // BOARD IS NOT FULL
            }
            current = current.next;           // go to next node
        }
        return true;
    }

    // Method to get the node at a specific position
    private Node getNodeAtPosition(int position) {
        Node current = head;
        int count = 1;

        // This will iterate through the linked list to get the position
        while (current != null && count < position) {
            current = current.next;
            count++;
        }
        // This will return the node at the given position
        return current;
    }

    // Method to check if 3 specific nodes contain the same value
    private boolean check3Nodes(int pos1, int pos2, int pos3) {
        Node n1 = getNodeAtPosition(pos1);
        Node n2 = getNodeAtPosition(pos2);
        Node n3 = getNodeAtPosition(pos3);

        // Check if all three nodes have the same non-empty value
        if (!n1.getData().equals("* ") && n1.getData().equals(n2.getData()) && n1.getData().equals(n3.getData())){
            return true;
        }
        return false;
    }

    // Method to check for a win
    public boolean checkWinCondition () {
        //  row check
        if (check3Nodes(1,2,3)) return true;   // First row
        if (check3Nodes(4,5,6)) return true;   // Second row
        if (check3Nodes(7,8,9)) return true;   // Third row

        //  column check
        if (check3Nodes(1, 4, 7)) return true;  // First column
        if (check3Nodes(2, 5, 8)) return true;  // Second column
        if (check3Nodes(3, 6, 9)) return true;  // Third column

        // Check diagonals
        if (check3Nodes(1, 5, 9)) return true;  // Diagonal from top-left to bottom-right
        if (check3Nodes(3, 5, 7)) return true;  // Diagonal from top-right to bottom-left

        // No win condition found
        return false;
    }

    // Method to reset the board
    public void resetBoard () {
        // To be implemented
        Node current = head;
        while (current != null) {
            current.setData("* ");  // Reset each node's data to "* " (empty space)
            current = current.next;
        }
        System.out.println("The board has been reset.");
    }
    //END
}
