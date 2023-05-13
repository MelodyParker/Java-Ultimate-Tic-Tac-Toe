class MiniBoard {
  private int[][] boardState = new int[3][3]; // stores the state of the board
  private int winner; // stores the winner if there has been one(1 for X, -1 for O), 0 otherwise
  public MiniBoard() {
    winner = 0;
    for (int i=0; i<3; i++) {
      for (int j=0; j<3; j++) {
        boardState[i][j] = 0;
      }
    }
  }

  /* 
    returns the indices({row, column}) to the num from this pattern:
    
    1 2 3
    4 5 6
    7 8 9
  
    example: numToIndices(8) = {2, 1}
  */
  private int[] numToIndices(int num) {
    int[] returnArr = {((num-1) / 3), ((num-1) % 3)};
    return returnArr;
  }


  
  // returns the human-readable representation of a tile value 
  public String intToString(int val) {
    if (val == -1)
      return "O";
    if (val == 1)
      return "X";
    return "-";
  }


  // returns the computer-readable representation of a human-readable value
  public int stringToInt(String val) {
    if (val == "O") return -1;
    if (val == "X") return 1;
    return 0;
  }


  // returns the human-readable value at the specified index using the intToString method
  public String getValAtPos(int row, int col) {
    return intToString(boardState[row][col]);
  }

  // accessor method
  public int getTile(int row, int col) {
    return boardState[row][col];
  }

  
  public String toString() {
    String repr = "";
    for (int i=0; i<3; i++) {
      for (int j=0; j<3; j++) {
        repr += getValAtPos(i, j) + " ";
      }
      repr = repr.substring(0, repr.length() - 1);
      repr += "\n";
    }
    return repr.substring(0, repr.length() - 1);
  }

  public boolean playMove(int row, int col, int player) {
    if (boardState[row][col] != 0) return false;
    boardState[row][col] = player;
    return true;
  }

  public void updateWinner(String player) {
    if (winner != 0) return;
    int[][][] possibleWins = {
      {{0,0}, {0,1}, {0,2}},
      {{1,0}, {1,1}, {1,2}},
      {{2,0}, {2,1}, {2,2}},
      {{0,0}, {1,0}, {2,0}},
      {{0,1}, {1,1}, {2,1}},
      {{0,2}, {1,2}, {2,2}},
      {{0,0}, {1,1}, {2,2}},
      {{0,2}, {1,1}, {2,0}}
    };
    for (int i=0; i<possibleWins.length; i++) {
      int[][] necessarySquares = possibleWins[i];
      boolean possible = true;
      for (int[] square : necessarySquares) {
        int row = square[0];
        int col = square[1];
        if (!getValAtPos(row, col).equals(player)) possible = false;
      }
      if (possible) winner = stringToInt(player);
    }
  }
  public int getWinner() {
    return winner;
  }

  public String rowToString(int row) {
    String repr = "";
    for (int col = 0; col<boardState[row].length; col++) {
      repr += intToString(boardState[row][col]) + " ";
    }
    return repr.substring(0, repr.length() - 1);
  }
}