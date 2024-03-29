/*
 * Target Practice 12 - Matrices
 *
 *  Problem 1:  Robot Paths
 *
 *  Prompt:   Given a matrix of zeroes, determine how many unique paths exist
 *            from the top left corner to the bottom right corner
 *
 *  Input:    An Array of Array of Integers (matrix)
 *  Output:   Integer
 *
 *  Example:  matrix = [[0,0,0,0],
 *                      [0,0,0,0],
 *                      [0,0,0,0]]
 *
 *            robotPaths(matrix) = 38
 *
 *
 *            matrix = [[0,0,0],
 *                      [0,0,0]]
 *
 *            robotPaths(matrix) = 4
 *
 *  Note:     From any point, you can travel in the four cardinal directions
 *            (north, south, east, west). A path is valid as long as it travels
 *            from the top left corner to the bottom right corner, does not go
 *            off of the matrix, and does not travel back on itself
 */

import java.util.*;

class Problems {

  public static int robotPaths(int[][] matrix) {
    return traverse(0, 0, matrix);
  }

  private static int traverse(int x, int y, int[][] matrix) {
    if(x < 0 || y < 0 || x >= matrix[0].length || y >= matrix.length) {
      return 0;
    } else if (matrix[y][x] == 1) {
      return 0;
    } else if (x == matrix[0].length - 1 && y == matrix.length - 1) {
      return 1;
    }
    matrix[y][x] = 1;
    int sum = 0;
    sum += traverse(x+1, y, matrix);
    sum += traverse(x-1, y, matrix);
    sum += traverse(x, y+1, matrix);
    sum += traverse(x, y-1, matrix);
    matrix[y][x] = 0;
    return sum;
   }


 /*
  *  Problem 2: Matrix Spiral
  *
  *  Given an (MxN) matrix of integers, return an array in spiral order.
  *
  *
  *  Input:  matrix {Integer[][]}
  *  Output: {Integer}
  *
  *
  * Example:
  *  Input:  [[ 1, 2, 3],
  *           [ 4, 5, 6],
  *           [ 7, 8, 9]]
  *
  *  Output: [1, 2, 3, 6, 9, 8, 7, 4, 5]
  *
  */


 // Time Complexity: O(MN)
 // Auxiliary Space Complexity: O(MN)

 public static int[] matrixSpiral(int[][] matrix) {
   if(matrix.length == 0) {
     return new int[0];
   }

   int[] results = new int[matrix.length * matrix[0].length];
   int yMin = 0;
   int xMin = 0;
   int yMax = matrix.length - 1;
   int xMax = matrix[0].length - 1;
   int counter = 0;
   int i;

   while (xMin <= xMax && yMin <= yMax) {
       for(i = xMin; i <= xMax; i++){
           results[counter] = matrix[yMin][i];
           counter++;
       }
       yMin++;
       for(i = yMin; i <= yMax; i++){
           results[counter] = matrix[i][xMax];
           counter++;
       }
       xMax--;
       if(yMin <= yMax){
           for(i = xMax; i >= xMin; i--){
               results[counter] = matrix[yMax][i];
               counter++;
           }
           yMax--;
       }
       if(xMin <= xMax){
           for(i = yMax; i >= yMin; i--){
               results[counter] = matrix[i][xMin];
               counter++;
           }
           xMin++;
       }
     }

   return results;
 }

}





////////////////////////////////////////////////////////////
///////////////  DO NOT TOUCH TEST BELOW!!!  ///////////////
////////////////////////////////////////////////////////////

// use the Main class to run the test cases
class Main {
  private int[] testCount;

  // an interface to perform tests
  public interface Test {
    public boolean execute();
  }

  public static void main(String[] args) {

    // instantiate the testing of each module by resetting count and printing title of module
    int[] testCount = {0, 0};
    System.out.println("Robot Paths Tests");

    // tests are in the form as shown
    assertTest(testCount, "should work on example input", new Test() {
      public boolean execute() {
        int[][] matrix = {{0,0,0,0},
                          {0,0,0,0},
                          {0,0,0,0}};
        return Problems.robotPaths(matrix) == 38;
      }
    });

    // tests are in the form as shown
    assertTest(testCount, "should work on second example input", new Test() {
      public boolean execute() {
        int[][] matrix = {{0,0,0},
                          {0,0,0}};
        return Problems.robotPaths(matrix) == 4;
      }
    });

    // tests are in the form as shown
    assertTest(testCount, "should work on single-element input", new Test() {
      public boolean execute() {
        int[][] matrix = {{0}};
        return Problems.robotPaths(matrix) == 1;
      }
    });

    // tests are in the form as shown
    assertTest(testCount, "should work on single-row input", new Test() {
      public boolean execute() {
        int[][] matrix = {{0,0,0,0,0,0}};
        return Problems.robotPaths(matrix) == 1;
      }
    });

    // tests are in the form as shown
    assertTest(testCount, "should work on single-column input", new Test() {
      public boolean execute() {
        int[][] matrix = {{0},
                          {0},
                          {0},
                          {0},
                          {0}};
        return Problems.robotPaths(matrix) == 1;
      }
    });

    assertTest(testCount, "should work on a 5 x 8 matrix input", new Test() {
      public boolean execute() {
        int[][] matrix = {{0,0,0,0,0,0,0,0},
                          {0,0,0,0,0,0,0,0},
                          {0,0,0,0,0,0,0,0},
                          {0,0,0,0,0,0,0,0},
                          {0,0,0,0,0,0,0,0}};
        System.out.println("  Please be patient, test 6 may take longer to run");
        return Problems.robotPaths(matrix) == 7110272;
      }
    });

    // print the result of tests passed for a module
    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");

    // instantiate the testing of each module by resetting count and printing title of module
    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Matrix Spiral Tests");

    int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    Problems.matrixSpiral(matrix);

    // tests are in the form as shown
    assertTest(testCount, "should work for empty matrix input", new Test() {
      public boolean execute() {
        int[][] matrix = {{}};
        int[] test = Problems.matrixSpiral(matrix);
        int[] expected = {};
        return arraysEqual(test, expected);
      }
    });

    assertTest(testCount, "should work for single element matrix input", new Test() {
      public boolean execute() {
        int[][] matrix = {{1}};
        int[] test = Problems.matrixSpiral(matrix);
        int[] expected = {1};
        return arraysEqual(test, expected);
      }
    });

    assertTest(testCount, "should work for single column matrix input", new Test() {
      public boolean execute() {
        int[][] matrix = {{1},
                          {2},
                          {3},
                          {4},
                          {5}};
        int[] test = Problems.matrixSpiral(matrix);
        int[] expected = {1, 2, 3, 4, 5};
        return arraysEqual(test, expected);
      }
    });

    assertTest(testCount, "should work for square matrix input", new Test() {
      public boolean execute() {
        int[][] matrix = {{1, 2},
                          {4, 3}};
        int[] test = Problems.matrixSpiral(matrix);
        int[] expected = {1, 2, 3, 4};
        return arraysEqual(test, expected);
      }
    });

    assertTest(testCount, "should work for single row matrix input", new Test() {
      public boolean execute() {
        int[][] matrix = {{1, 2, 3, 4}};
        int[] test = Problems.matrixSpiral(matrix);
        int[] expected = {1, 2, 3, 4};
        return arraysEqual(test, expected);
      }
    });

    assertTest(testCount, "should work on 3 x 5 matrix input", new Test() {
      public boolean execute() {
        int[][] matrix = {{1, 2, 3},
                          {4, 5, 6},
                          {7, 8, 9},
                          {10, 11, 12},
                          {13, 14, 15}};
        int[] test = Problems.matrixSpiral(matrix);
        int[] expected = {1, 2, 3, 6, 9, 12, 15, 14, 13, 10, 7, 4, 5, 8, 11};
        return arraysEqual(test, expected);
      }
    });

    assertTest(testCount, "should work on 5 x 3 matrix input", new Test() {
      public boolean execute() {
        int[][] matrix = {{1, 2, 3, 4, 5},
                          {12, 13, 14, 15, 6},
                          {11, 10, 9, 8, 7}};
        int[] test = Problems.matrixSpiral(matrix);
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        return arraysEqual(test, expected);
      }
    });

    // print the result of tests passed for a module
    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");

  }


  // do not edit below, this is to wrap the test and check for exceptions
  private static void assertTest(int[] count, String name, Test test) {
    String pass = "false";
    count[1]++;

    try {
      if (test.execute()) {
        pass = " true";
        count[0]++;
      }
    } catch(Exception e) {}
    String result = "  " + (count[1] + ")   ").substring(0, 5) + pass + " : " + name;
    System.out.println(result);
  }

  // function for checking if arrays are equal
  private static boolean arraysEqual(int[] arr1, int[] arr2) {
    if (arr1.length != arr2.length) {
      return false;
    }

    for (int i = 0 ; i < arr1.length ; i++) {
      if (arr1[i] != arr2[i]) {
        return false;
      }
    }

    return true;
  }
}
