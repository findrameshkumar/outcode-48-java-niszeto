/*
 *  Target Practice 05 - Math
 */

import java.util.*;

class Problems {

  /*
  *  Problem:  Trailing Zeros
  *
  *  Prompt:   Given a positive integer, return the number of trailing zeros
  *            are present on the factorial of that number.
  *
  *  Input:    Integer >= 0 (n)
  *  Output:   Integer
  *
  *  Example:  n = 10
  *
  *            trailingZeros(n) = 2
  *
  *            n = 27
  *
  *            trailingZeros(n) = 6
  *
  *  Explanation: 10! is 3628800, and so has 2 trailing zeros
  *               27! is 1088886945041835216068000000, and so has 6 trailing zeros
  *
  *  Note:     A straightforward way of solving this problem involves just
  *            taking the factorial of your input and dividing by 10 until you
  *            run out of trailing zeros. But what is the major problem with this?
  *
  *            A good way to start solving this problem is by trying a lot of examples.
  */

  // Time Complexity:
  // Auxiliary Space Complexity:

  public static int trailingZeros(int n) {
    // YOUR CODE HERE
    return -1;
  }


 /*
  *  Problem:  Group Anagrams
  *
  *  Prompt:   Given an array of words, return an array of arrays of words
  *            that groups all anagrams together.
  *
  *  Input:    words [String]
  *  Output:   [[String]]
  *
  *  Example:  words = ["eat", "tea", "tan", "bat", "ate", "tab", "ant", "tan", "and"]
  *
  *            groupAnagrams(words) =
  *
  *            [
  *             ["ate", "eat", "tea"],
  *             ["tan", "nat", "ant"],
  *             ["bat", "tab"],
  *             ["and"]
  *            ]
  *
  *
  *
  *  Note:     All inputs will be lowercase letters
  *            The order of groupings does not matter
  *            The order of the words in the groupings does not matter
  *            The words many not all be the same length
  *
  */

  // Time Complexity:
  // Auxiliary Space Complexity:

  public static ArrayList<ArrayList<String>> groupAnagrams(String[] words) {
    // YOUR CODE HERE
    return new ArrayList<ArrayList<String>>();
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
     System.out.println("Trailing Zeros Tests");

     assertTest(testCount, "should work on first example input", new Test() {
       public boolean execute() {
         return Problems.trailingZeros(10) == 2;
       }
     });

     assertTest(testCount, "should work on second example input", new Test() {
       public boolean execute() {
         return Problems.trailingZeros(27) == 6;
       }
     });

     assertTest(testCount, "should work on zero", new Test() {
       public boolean execute() {
         return Problems.trailingZeros(0) == 0;
       }
     });

     assertTest(testCount, "should work on large input", new Test() {
       public boolean execute() {
         return Problems.trailingZeros(1037) == 257;
       }
     });

     assertTest(testCount, "should work on very large input", new Test() {
       public boolean execute() {
         return Problems.trailingZeros(79204102) == 19801020;
       }
     });

     // print the result of tests passed for a module
     System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");


     // instantiate the testing of each module by resetting count and printing title of module
     testCount[0] = 0;
     testCount[1] = 0;
     System.out.println("Group Anagrams Test");

     assertTest(testCount, "should work on first example input", new Test() {
       public boolean execute() {
         ArrayList<ArrayList<String>> correctAnswer = new ArrayList<ArrayList<String>>();
         correctAnswer.add(new ArrayList<String>(Arrays.asList("ate", "eat", "tea")));
         correctAnswer.add(new ArrayList<String>(Arrays.asList("tan", "nat", "ant")));
         correctAnswer.add(new ArrayList<String>(Arrays.asList("bat", "tab")));
         correctAnswer.add(new ArrayList<String>(Arrays.asList("and")));
         ArrayList<ArrayList<String>> test = Problems.groupAnagrams(new String[]{"eat", "tea", "nat", "bat", "ate", "tab", "ant", "tan", "and"});
         return groupsMatching(correctAnswer, test);
       }
     });

     assertTest(testCount, "should work on empty input", new Test() {
       public boolean execute() {
         ArrayList<ArrayList<String>> correctAnswer = new ArrayList<ArrayList<String>>();
         ArrayList<ArrayList<String>> test = Problems.groupAnagrams(new String[]{});
         return groupsMatching(correctAnswer, test);
       }
     });

     assertTest(testCount, "should work on input with no anagrams", new Test() {
       public boolean execute() {
         ArrayList<ArrayList<String>> correctAnswer = new ArrayList<ArrayList<String>>();
         correctAnswer.add(new ArrayList<String>(Arrays.asList("hello")));
         correctAnswer.add(new ArrayList<String>(Arrays.asList("world")));
         correctAnswer.add(new ArrayList<String>(Arrays.asList("foo")));
         correctAnswer.add(new ArrayList<String>(Arrays.asList("bar")));
         correctAnswer.add(new ArrayList<String>(Arrays.asList("baz")));
         ArrayList<ArrayList<String>> test = Problems.groupAnagrams(new String[]{"hello", "world", "foo", "bar", "baz"});
         return groupsMatching(correctAnswer, test);
       }
     });

     // print the result of tests passed for a module
     System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");

   }

     // function for checking if arrays contain same elements
    // (do not need to be in the same order)
    private static boolean arraysMatching(ArrayList<String> arr1, ArrayList<String> arr2) {
      if (arr1.size() != arr2.size()) {
        return false;
      } else {
        Collections.sort(arr1);
        Collections.sort(arr2);

        for (int i = 0 ; i < arr1.size() ; i++) {
          if (arr1.get(i) != arr2.get(i)) {
            return false;
          }
        }

        return true;
      }
    }

    // function for checking if two groups of arraylist of strings have
    // the same elements

    private static boolean groupsMatching(ArrayList<ArrayList<String>> correctAnswer, ArrayList<ArrayList<String>> test) {
      for (int i = 0; i < correctAnswer.size(); i++) {
        boolean containsGroups = false;
        for (int j = 0; j < test.size(); j++) {
          if (arraysMatching(correctAnswer.get(i), test.get(j))) {
            containsGroups = true;
            break;
          }
        }
        if (!containsGroups) {
          return false;
        }
      }
      for (int i = 0; i < test.size(); i++) {
        boolean containsGroups = false;
        for (int j = 0; j < correctAnswer.size(); j++) {
          if (arraysMatching(test.get(i), correctAnswer.get(j))) {
            containsGroups = true;
            break;
          }
        }
        if (!containsGroups) {
          return false;
        }
      }
      return true;
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
