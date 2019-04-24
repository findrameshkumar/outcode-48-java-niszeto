import java.io.*;
import java.util.*;

class Problems {

  /*
   *  Problem 1: Lattice Paths (Dynamic Programming Approach)
   *
   *  Prompt:    Count the number of unique paths to travel from the top left
   *             to the bottom right of a lattice of squares.
   *
   *             NOTE: You are traveling along the **EDGES** of the lattice
   *
   *  Input:     An integer N (which is the width of the lattice)
   *             An integer M (which is the height of the lattice)
   *
   *  Output:    An interger (which represents the number of unique paths)
   *
   *  Example:   input: 2
   *
   *             (2 x 3 lattice of squares)
   *              __ __ __
   *             |__|__|__|
   *             |__|__|__|
   *
   *             output: 10 (number of unique paths from top left corner to bottom
   *                     right)
   *
   *             Diagram:
   *
   *             1__1__1__1
   *             |  |  |  |
   *             1__2__3__4
   *             |  |  |  |
   *             1__3__6__10
   *
   *  Notes:     What is the time and auxilliary space complexity of your solution?
   *
   *             When moving through the lattice, you can only move either down or
   *             to the right.
   *
   *             You did this problem before with recursion. Try implementing it
   *             now with dynamic programming!
   *
   *  Resources:
   *    1: https://projecteuler.net/problem=15
   *    2: https://en.wikipedia.org/wiki/Lattice_path
   *
   */

  // Time Complexity:
  // Auxiliary Space Complexity:
   public static int latticePaths(int m, int n) {
     //YOUR WORK HERE
     return -1;
   }


/*
 *  Problem 2: Minimum Steps to One - Memoization or Tabulation
 *
 *  Prompt:    Given a positive integer, you can perform any combination of these 3
 *             steps:
 *             1.) Subtract 1 from it.
 *             2.) If its divisible by 2, divide by 2.
 *             3.) If its divisible by 3, divide by 3.
 *
 *             Find the minimum number of steps that it takes get from N to 1
 *
 *  Input:     Positive Integer N
 *  Output:    Integer
 *
 *  Example:   input: 10
 *
 *             output: 3
 */

   // Time Complexity:
   // Auxiliary Space Complexity:
   public static int minStepsToOne(int n) {
     //YOUR WORK HERE
     return -1;
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

     int[] testCount = {0, 0};
     System.out.println("Lattice Paths Tests");

     assertTest(testCount, "should work on a 2 x 3 lattice", new Test() {
       public boolean execute() {
         return Problems.latticePaths(2, 3) == 10;
       }
     });

     assertTest(testCount, "should return the same for a 3 x 2 lattice", new Test() {
       public boolean execute() {
         return Problems.latticePaths(3, 2) == 10;
       }
     });

     assertTest(testCount, "should return the same for a 0 x 0 lattice", new Test() {
       public boolean execute() {
         return Problems.latticePaths(0, 0) == 1;
       }
     });

     assertTest(testCount, "should work for a 10 x 10 lattice (square input)", new Test() {
       public boolean execute() {
         return Problems.latticePaths(10, 10) == 184756;
       }
     });

     assertTest(testCount, "should work for a 17 x 14 lattice (large input)", new Test() {
       public boolean execute() {
         return Problems.latticePaths(17, 14) == 265182525;
       }
     });

     // print the result of tests passed for a module
     System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");


     // instantiate the testing of each module by resetting count and printing title of module
     testCount[0] = 0;
     testCount[1] = 0;

     System.out.println("Minimum Steps to One Tests");

     // tests are in the form as shown
     assertTest(testCount, "should return 3 for 10", new Test() {
       public boolean execute() {
         int output = Problems.minStepsToOne(10);
         return output == 3;
       }
     });

     assertTest(testCount, "should return 0 for 1", new Test() {
       public boolean execute() {
         int output = Problems.minStepsToOne(1);
         return output == 0;
       }
     });

     assertTest(testCount, "should work for large numbers", new Test() {
       public boolean execute() {
         int output = Problems.minStepsToOne(1334425);
         return output == 22;
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
 }
