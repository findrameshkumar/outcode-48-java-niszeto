import java.util.*;
import java.io.*;

class Main {

  public static void main(String[] args) {
    TicTacToe ttt = new TicTacToe();
    ttt.play();
  }

}

class TicTacToe {

  public Board board;
  char player;
  int size;

  public TicTacToe() {
    board = new Board();
    player = 'X';
    size = 9;
  }

  public void play() {

    while (size > 0) {
      printCurrentPlayersTurn();
      Scanner in = new Scanner(System.in);
      String response = in.nextLine();
      String[] indices = response.split(" ");
      int row = Integer.parseInt(indices[0]);
      int column = Integer.parseInt(indices[1]);

      if (!board.canPlacePiece(row, column)) {
        printInvalidMove();
        continue;
      }

      playRound(row, column);
      if (board.checkWinCondtion(player)) {
        declareWinner(player);
        return;
      }
      switchPlayer();
      decrementRounds();
    }
    declareTie();
  }

  public void playRound(int row, int col) {
    board.placePiece(row, col, this.player);
    printCurrentMove(row, col);
    board.printBoard();
  }

  public void printCurrentPlayersTurn() {
    System.out.println("It's " + this.player + "'s turn. Enter row and column.");
  }

  public void decrementRounds() {
    size--;
  }

  public void declareWinner(char player) {
    System.out.println(player + " wins!");
  }

  public void declareTie() {
    System.out.println("Cat's game");
  }

  public void printInvalidMove() {
    System.out.println("Invalid move. Please try again.");
  }

  public void printCurrentMove(int row, int col) {
    System.out
        .println("Player " + player + " has decided to place an " + player + " at row: " + row + " and column: " + col);
  }

  public void switchPlayer() {
    if (player == 'X') {
      player = 'O';
    } else {
      player = 'X';
    }
  }
}

class Board {

  char[][] brd;

  public Board() {
    brd = new char[3][3];
  }

  public void printBoard() {
    for (int row = 0; row < brd.length; row++) {
      System.out.print("| ");
      for (int column = 0; column < brd[0].length; column++) {
        if (brd[row][column] == '\u0000') {
          System.out.print("  | ");
        } else {
          System.out.print(brd[row][column] + " | ");
        }
      }
      System.out.println();
    }
  }

  public boolean canPlacePiece(int row, int col) {
    return row < brd.length && row >= 0 && col < brd[0].length && col >= 0 && brd[row][col] == '\u0000';
  }

  public void placePiece(int row, int col, char player) {
    brd[row][col] = player;
  }

  public boolean checkWinCondtion(char player) {
    return checkDiagonals(player) || checkRows(player) || checkColumns(player);
  }

  public boolean checkDiagonals(char player) {
    if ((brd[0][0] == player && brd[1][1] == player && brd[2][2] == player)
        || (brd[0][2] == player && brd[1][1] == player && brd[2][0] == player)) {
      return true;
    }
    return false;
  }

  public boolean checkRows(char player) {
    for (int row = 0; row < brd.length; row++) {
      if (brd[row][0] == player && brd[row][1] == player && brd[row][2] == player) {
        return true;
      }
    }
    return false;
  }

  public boolean checkColumns(char player) {
    for (int col = 0; col < brd[0].length; col++) {
      if (brd[0][col] == player && brd[1][col] == player && brd[2][col] == player) {
        return true;
      }
    }
    return false;
  }
}