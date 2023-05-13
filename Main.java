import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    BigBoard ultimateBoard = new BigBoard();
    int chosenBoard = -1;
    for (;;) {
      if (ultimateBoard.getWinner() != 0) {
        clear();
        System.out.println(ultimateBoard);
        System.out.println(((ultimateBoard.getWinner() == 1) ? "X" : "O") + " wins!");
        break;
      }
      try {
        clear();
        System.out.println(ultimateBoard);
        System.out.println("Current turn: " + ultimateBoard.getCurrentTurn());
        if (ultimateBoard.needToInputBoard()) {
          System.out.print("Board Number: ");
          chosenBoard = scan.nextInt();
        }
        
        System.out.print("Position: ");
        int pos = scan.nextInt();
        ultimateBoard.playMove(chosenBoard, pos);
        chosenBoard = pos;
      } catch(Exception e) {
        System.out.println("Invalid Input. Please try again. (Press ENTER to continue)");
        scan.nextLine();
        scan.nextLine();
      }
    }
  }
  public static void clear() {
    System.out.print("\033[H\033[2J");  
    System.out.flush(); 
  }
}