class BigBoard {
  private int winner;
  private MiniBoard[][] boardState = new MiniBoard[3][3];
  private final static String ESCAPE_SEQ = "\u001b";
  private int currentTurn = 1;
  private int lastMove = -1;
  
  public BigBoard() {
    winner = 0;
    for (int i=0; i<boardState.length; i++) {
      for (int j=0; j<boardState[0].length; j++) {
        boardState[i][j] = new MiniBoard();
      }
    }
  }


  public String getCurrentTurn() {
    return boardState[0][0].intToString(currentTurn);
  }
  public String toString() {
    String repr = " ";
    for (int i=0; i<boardState.length; i++) { // the row of the BigBoard
      for (int j=0; j<boardState[0].length; j++) { // the row of the MiniBoards in that row
        for (int k=0; k<3; k++) { // the tile column of the current selected MiniBoard
          boolean colored = (lastMove == -1);
          String color = Colors.BRIGHT_GREEN;
          if (!colored) {
            if (i*3 + k + 1 == lastMove) {
              colored = true;
            }
            
            int[] lastMoveIndices = numToIndices(lastMove);
            if (boardState[lastMoveIndices[0]][lastMoveIndices[1]].getWinner() != 0) {
              colored = true;
            }
            if (boardState[i][k].getWinner() != 0) {
              colored = true;
              color = (boardState[i][k].getWinner() == 1) ? Colors.DARK_BLUE : Colors.BRIGHT_MAGENTA;
            }
          }
          if (colored) {
            repr += Colors.colorString(boardState[i][k].rowToString(j), color) + "  |  ";
          } else {
            repr += boardState[i][k].rowToString(j) + "  |  ";
          }
          
        }
        repr = repr.substring(0, repr.length() - 5);
        repr += "\n ";
      }
      repr = repr.substring(0, repr.length() - 1);
      if (i<boardState.length - 1)
        repr += "———————————————————————————\n ";
    }
    return repr;
  }

  private int[] numToIndices(int num) {
    int[] returnArr = {((num-1) / 3), ((num-1) % 3)};
    return returnArr;
  }
  
  /*
    adds the current player's move to the board at the specified pos
    MiniBoard is the number of which MiniBoard in which the move will be made
    pos is the number of the position in that MiniBoard where the move will be made
  */
  public boolean playMove(int miniBoard, int pos) {
    int[] miniBoardIndices = numToIndices(miniBoard);
    int[] posIndices = numToIndices(pos);
    int[] lastMoveIndices = numToIndices(lastMove);
    MiniBoard selected = boardState[miniBoardIndices[0]][miniBoardIndices[1]];
    MiniBoard necessaryBoard = new MiniBoard();
    if (lastMove != -1) {
      necessaryBoard = boardState[lastMoveIndices[0]][lastMoveIndices[1]];
      if (lastMove != miniBoard && necessaryBoard.getWinner() == 0) return false;
      if (selected.getWinner() != 0) return false;
      if (selected.getTile(posIndices[0], posIndices[1]) != 0) return false;
    }
    
    selected.playMove(posIndices[0], posIndices[1], currentTurn);

    selected.updateWinner(selected.intToString(currentTurn));
    updateWinner();
    currentTurn *= -1;
    lastMove = pos;
    return true;
  }

  public boolean needToInputBoard() {
    if (lastMove == -1) return true;
    int[] lastMoveIndices = numToIndices(lastMove);
    MiniBoard necessaryBoard = boardState[lastMoveIndices[0]][lastMoveIndices[1]];
    return (necessaryBoard.getWinner() != 0);
  }

  public void updateWinner() {
    int[][] boardRepr = new int[3][3];
    for (int i=0; i<3; i++) {
      for (int j=0; j<3; j++) {
        boardRepr[i][j] = boardState[i][j].getWinner();
      }
    }
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
        if (boardRepr[row][col] != currentTurn) possible = false;
      }
      if (possible) winner = currentTurn;
    }
    
  }

  public int getWinner() {
    return winner;
  }
}