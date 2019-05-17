import java.util.*;
import java.io.*;

class TicTacToe {

  public TicTacToe() {
    // Current Player starts at X
    // Number of Rounds
    // Create 3x3 board
    board = new Board();
    currentPlayer = 0;
    remaingRound = MAX_ROUND;
    ongoing = true;
  }

  public void play() {
    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    while (remaingRound > 0 && ongoing) {
      printCurrentPlayersTurn();
      board.printBoard();
      if (!in.hasNextInt()) {
        printInvalidMove();
      } else {
        int row = in.nextInt();
        if (!in.hasNextInt()) {
          printInvalidMove();
        } else {
          int col = in.nextInt();
          if (!board.canPlacePiece(row, col)) {
            printInvalidMove();
          } else {
            playRound(row, col);

          }
        }
      }
      in.nextLine();


      // int row = in.nextInt();
      // if (!in.hasNextInt()) {
      //   printInvalidMove();
      //   in.nextLine();
      //   continue;
      // }
      // int col = in.nextInt();
      //
      // if (!board.canPlacePiece(row, col)) {
      //   printInvalidMove();
      //   in.nextLine();
      //   continue;
      // }
      // playRound(row, col);
      // if (board.checkWinCondtion(currentPlayer)) {
      //   declareWinner(currentPlayer);
      //   break;
      // }
      // switchPlayer();
      // decrementRounds();
      // in.nextLine();
    }
    if (remaingRound == 0) declareTie();
    board.printBoard();
  }

  public void playRound(int row, int col) {
    board.placePiece(row, col, currentPlayer);
    printCurrentMove(row, col);
    if (board.checkWinCondtion(currentPlayer)) {
      declareWinner(currentPlayer);
      ongoing = false;
    } else {
      switchPlayer();
      decrementRounds();
    }
  }

  public void printCurrentPlayersTurn() {
    System.out.println("This is Player " + getCurrPlayer() + "'s turn.");
  }

  public void decrementRounds() {
    remaingRound--;
  }

  public void declareWinner(char player) {
    System.out.println("Player " + getCurrPlayer() + " won!");
  }

  public void declareTie() {
    System.out.println("The game is tied");
  }

  public void printInvalidMove(){
    System.out.println("Please enter valid row (0 <= row <3) and column (0 <= column <3) (e.g. 1 0)");
  }

  public void printCurrentMove(int row, int col) {
    System.out.println("Player " + getCurrPlayer() + " make a move at " + row + " row and " + col + " column!");
  }

  public void switchPlayer() {
    if (currentPlayer == 0) {
      currentPlayer = 1;
    } else {
      currentPlayer = 0;
    }
  }

}

class Board {

  public Board() {
    matrix = new Character[3][3];
    symbol = new char[]{'O', 'X'};
  }

  public void printBoard() {
    StringBuilder output = new StringBuilder();
    for (int i=0; i<matrix.length; i++) {
      output.append("|");
      for (int j=0; j<matrix[i].length; j++) {
        output.append(" ");
        output.append(matrix[i][j] == null ? '-' : matrix[i][j]);
        output.append(" ");
        output.append("|");
      }
      output.append("\n");
    }
    System.out.println(output.toString());
  }

  public boolean canPlacePiece(int row, int col) {
    if (!(row < 3 && row >= 0)) return false;
    if (!(col < 3 && col >= 0)) return false;
    return matrix[row][col] == null;
  }

  public void placePiece(int row, int col, char player) {
    if (canPlacePiece(row, col)) {
      matrix[row][col] = symbol[player];
    }
  }

  public boolean checkWinCondtion(char player) {
    if (checkRows(player)) return true;
    if (checkColumns(player)) return true;
    return checkDiagonals(player);
  }

  public boolean checkDiagonals(char player) {
    boolean hasWin = true;
    for (int i=0; i<matrix.length; i++) {
      if (matrix[i][i] == null || matrix[i][i] != symbol[player]) {
        hasWin = false;
        break;
      }
    }
    if (hasWin) return true;
    hasWin = true;
    for (int i=0; i<matrix.length; i++) {
      if (matrix[i][matrix.length-1 - i] == null || matrix[i][matrix.length-1 - i] != symbol[player]) {
        hasWin = false;
        break;
      }
    }

    return hasWin;
  }

  public boolean checkRows(char player) {
    for (int i=0; i<matrix.length; i++) {
      boolean hasWin = true;
      for (int j=0; j<matrix[i].length; j++) {
        if (matrix[i][j] == null || matrix[i][j] != symbol[player]) {
          hasWin = false;
          break;
        }
      }
      if (hasWin) return true;
    }
    return false;
  }

  public boolean checkColumns(char player) {
    for (int i=0; i<matrix[0].length; i++) {
      boolean hasWin = true;
      for (int j=0; j<matrix.length; j++) {
        if (matrix[j][i] == null || matrix[j][i] != symbol[player]) {
          hasWin = false;
          break;
        }
      }
      if (hasWin) return true;
    }
    return false;
  }
}
