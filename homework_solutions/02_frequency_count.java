/*
 * Homework 02 - Frequency Count
 * Utilize the frequency count pattern to solve these problems.  Use a Hash Set
 * or an Array instead of a Hash Table where applicable.
 */

import java.util.*;
import java.io.*;

class Problems {
  /**
   *
   * Unique
   *
   * Given an array of integers, return an array with all duplicates removed.*
   *
   * Parameters
   * Input: arr {Array of Integers}
   * Output: {ArrayList of Integers}
   *
   * Constraints
   *
   * Time: O(N)
   * Space: O(N)
   *
   * Examples
   * [1, 2, 4, 4, 5, 6] --> [1, 2, 4, 5, 6]
   * [1, 1, 2, 2, 3, 3]' --> [1, 2, 3]
   * [1, 2, 3, 1, 2] --> [1, 2, 3]
   */

   public static ArrayList unique(int[] arr) {
      Map<Integer, Boolean> seen = new HashMap<>();
      ArrayList<Integer> results = new ArrayList<>();
      for (int i = 0; i < arr.length; i++) {
        seen.put(arr[i], true);
      }
      for(int key : seen.keySet()) {
        results.add(key);
      }
      return results;
   }

   /**
    * Word Count
    *
    * Given an body of text, return a hash table of the frequency of each word.
    *
    * Parameters
    * Input: sentence {String}
    * Output: {Hash Map <String, Integer>}
    *
    * Constraints
    *
    * Capital and lower case versions of the same word should be counted is the same word.
    *
    * Remove punctuations from all words.
    *
    * Time: O(N)
    * Space: O(N)
    * Where N is the number of characters in the string.
    *
    * **Examples**
    * 'The cat and the hat.' --> '{ the: 2, cat: 1, and: 1, hat: 1 }'`
    * 'As soon as possible.' --> '{ as: 2, soon: 1, possible: 1 }'`
    * 'It's a man, it's a plane, it's superman!' --> '{ its: 3, a: 2, man: 1, plane: 1, superman: 1 }'`
    */

    public static HashMap wordCount(String sentence) {
      if(sentence.length() == 0) {
        return new HashMap<String, Integer>();
      }

      Set<Character> punctuations = new HashSet<>(Arrays.asList('?', '!', '.', '\'',','));
      HashMap<String, Integer> wordCounts = new HashMap<String, Integer>();
      String filteredSentence = "";
      int charIndex = 0;

      while (charIndex < sentence.length()) {
        if(punctuations.contains(sentence.charAt(charIndex)) == false) {
          filteredSentence += Character.toLowerCase(sentence.charAt(charIndex));
        }
        charIndex += 1;
      }
      String[] words = filteredSentence.split("\\s+");
      for(int word = 0; word < words.length; word++) {
        if(wordCounts.containsKey(words[word])) {
          wordCounts.put(words[word], wordCounts.get(words[word]) + 1);
        } else {
          wordCounts.put(words[word], 1);
        }
      }
      return wordCounts;
    }

  /**
   * RGB Set
   *
   * Given a string of characters where each character is either 'r', 'g', or 'b',
   * determine the number of complete sets of 'rgb' that can be made with the
   * characters.
   *
   * Parameters
   * Input: str {String}
   * Output: {Integer}
   *
   * Constraints
   * Time: O(N)
   * Space: O(1)
   *
   * Examples
   * `'rgbrgb' --> 2`
   * `'rbgrbrgrgbgrrggbbbbrgrgrgrg' --> 7`
   * `'bbrr' --> 0`
   */

     public static int rgb(String string) {
       char[] chars = string.toCharArray();
       HashMap<Character, Integer> counts = new HashMap<Character, Integer>();
       for(int i = 0; i < chars.length; i++){
         if (counts.containsKey(chars[i])) {
           counts.put(chars[i], counts.get(chars[i]) + 1 );
         } else {
           counts.put(chars[i], 1);
         }
       }
       if(counts.keySet().size() < 3) {
         return 0;
       }

       int result = Integer.MAX_VALUE;

       for (Character c : counts.keySet()) {
         if(counts.get(c) < result) {
           result = counts.get(c);
         }
       }
       return result;
     }

   /**
    * Missing Number
    *
    * Given range of 1 to N and an array of unique integers that are within that
    * range, return the missing integers not found in the array
    *
    * Parameters
    * Input: n {Integer}
    * Input: arr {Array of Integers}
    * Output: {ArrayList of Integers}
    *
    * Constraints
    * Time: O(N)
    * Space: O(N)
    *
    * Examples
    * `4, [1, 4, 2] --> [3]`
    * `8, [4, 7, 1, 6] --> [2, 3, 5, 8]`
    * `6, [6, 4, 2, 1] --> [3, 5]`
    */

    public static ArrayList missingNumber(int n, int[] arr) {
      HashSet nums = new HashSet<Integer>();
      ArrayList result = new ArrayList<Integer>();

      for(int i = 0; i < arr.length; i++) {
        nums.add(arr[i]);
      }
      for (int i = 1; i <= n; i++) {
        if(!nums.contains(i)) {
          result.add(i);
        }
      }
      return result;
    }

  /**
   * Letter Sort
   *
   * Given a string of lowercase letters, return the letters in sorted order.
   *
   * Parameters
   * Input: str {String}
   * Output: {String}
   *
   * Constraints
   * Time: O(N)
   * Space: O(1)
   *
   * Examples
   * `hello --> ehllo`
   * `whiteboard --> abdehiortw`
   * `one --> eno`
   */

   public static String letterSort(String string) {
     int[] chars = new int[26];
     Arrays.fill(chars, 0);
     String result = "";
     for(int i = 0; i < string.length(); i++) {
       int charIndex = (int)Character.toLowerCase(string.charAt(i)) - 97;
       chars[charIndex] = chars[charIndex] + 1;
     }
     for(int i = 0; i < chars.length; i++) {
       while(chars[i] > 0) {
         char letter = (char) (i + 97);
         result += letter;
         chars[i] -= 1;
       }
     }
     return result;
   }

   /**
    * Character Mode
    *
    * Given a string, find the most frequent occurring letter(s) in the string
    *
    * Parameters
    * Input: string {String}
    * Output: {String}
    *
    * Constraints
    * If more than one letter is tied for the most frequent, return a string of all
    * the letters in one string.
    *
    * Time: O(N)
    * Space: O(N)
    *
    * Examples
    * 'hello' --> 'l'
    * 'A walk in the park' --> 'a'
    * 'noon' --> 'no'
    */

    public static String characterMode(String string) {
      int[] chars = new int[26];
      Arrays.fill(chars, 0);
      String result = "";

      for(int i = 0; i < string.length(); i++) {
        if(!(string.charAt(i) == ' ')) {
          int charIndex = (int)Character.toLowerCase(string.charAt(i)) - 97;
          chars[charIndex] = chars[charIndex] + 1;
        }
      }
      int max_frequency = 0;

      for(int c = 0; c < chars.length; c++) {
        if(chars[c] > max_frequency) {
          max_frequency = chars[c];
        }
      }
      for(int c = 0; c < chars.length; c++) {
        if (chars[c] == max_frequency) {
          result += (char) (c + 97);
        }
      }
      return result;
    }

  /**
   * Sort Digits
   *
   * Given an integer, sort the digits in ascending order and return the new integer.
   * Ignore leading zeros.
   *
   * Parameters
   * Input: num {Integer}
   * Output: {Integer}
   *
   * Constraints
   * Do not convert the integer into a string or other data type.
   *
   * Time: O(N) where N is the number of digits.
   * Space: O(1)
   *
   * Examples
   * 8970 --> 789
   * 32445 --> 23445
   * 10101 --> 111
   */

   public static int sortDigits(int n) {
     HashMap<Integer, Integer> digitCounts = new HashMap<>();
     int result = 0;
     while(n > 0) {
       int digit = n % 10;
       if(!digitCounts.containsKey(digit)) {
         digitCounts.put(digit, 1);
       } else {
         digitCounts.put(digit, digitCounts.get(digit) + 1);
       }
       n = n/10;
     }
     int power = 0;
     for(int i = 9; i > 0; i--) {
       if(digitCounts.containsKey(i)) {
         while(digitCounts.get(i) >= 1) {
           result += i * Math.pow(10, power);
           power += 1;
           digitCounts.put(i, digitCounts.get(i) - 1);
         }
       }
     }
     return result;
   }

   /**
    *  Get Duplicates
    *
    *  Given an array of values, return only the values that have duplicates in the
    *  array
    *
    *  Parameters
    *  Input: arr {Array}
    *  Output: {ArrayList}
    *
    *  Constraints
    *  Time: O(N)
    *  Space: O(N)
    *
    *  Examples
    *  [1, 2, 4, 2] --> [2]
    *  [3, 2, 3, 2, 3, 3, 4] --> [3, 2]
    *  [1, 2, 3, 4] --> []
    */

   public static ArrayList getDuplicates(int[] arr) {
     ArrayList<Integer> result = new ArrayList<Integer>();
     HashMap<Integer, Integer> seen = new HashMap<Integer, Integer>();

     for(int i = 0; i < arr.length; i++) {
       if(!seen.containsKey(arr[i])) {
         seen.put(arr[i], 1);
       } else if(seen.get(arr[i]) == 1) {
         result.add(arr[i]);
         seen.put(arr[i], 2);
       }
     }
     return result;
   }

   /**
    *  Anagram Pair
    *
    *  Given two strings, determine if the strings are anagrams of one another.
    *
    *  Two words are anagrams of one another if they contain the same letters.
    *
    *  Parameters
    *  Input: str1 {String}
    *  Input: str2 {String}
    *  Output: {Boolean}
    *
    *  Constraints
    *  With N as the length of the first string, and M as the length of the second string.
    *
    *  Time: O(N)
    *  Space: O(N)
    *
    *  Examples
    *  "cat", "act" --> true
    *  "cat", "dog" --> false
    *  "racecar", "aaccrres" --> false
    */

    public static Boolean anagramPair(String string1, String string2) {
      int[] chars = new int[26];
      Arrays.fill(chars, 0);

      for(int i = 0; i < string1.length(); i++) {
        int charIndex = (int)Character.toLowerCase(string1.charAt(i)) - 97;
        chars[charIndex] = chars[charIndex] + 1;
      }

      for(int i = 0; i < string2.length(); i++) {
        int charIndex = (int)Character.toLowerCase(string2.charAt(i)) - 97;
        chars[charIndex] = chars[charIndex] - 1;
      }

      for(int c = 0; c < chars.length; c++) {
        if(chars[c] != 0) {
          return false;
        }
      }

      return true;
    }

    /**
     *  Anagram Palindrome
     *
     *  Given a string, determine if the string can be rearranged to form a palindrome.
     *
     *  A palindrome is a word that is the same as its reversed. For example: "racecar"
     *  and "noon" are palindromes because they match their reversed version
     *  respectively. On the other hand, "cat" is not a palindrome because "cat"
     *  does not equal "tac".
     *
     *  Parameters
     *  Input: str {String}
     *  Output: {Boolean}
     *
     *  Constraints
     *
     *  Assume the string only contains lowercase letters and no spaces.
     *
     *  Time: O(N)
     *  Space: O(1)
     *
     *  Examples
     *  `"carrace" --> true ("carrace" can be rearranged to "racecar")`
     *  `"cat" --> false`
     */

  public static Boolean anagramPalindrome(String str) {
    HashMap<Character, Integer> counts = new HashMap<Character, Integer>();
    int odds = 0;

    for(int i = 0; i < str.length(); i++) {
      if(counts.containsKey(str.charAt(i))) {
        int num = counts.get(str.charAt(i)) + 1;
        counts.put(str.charAt(i), num);
      } else {
        counts.put(str.charAt(i), 1);
      }
    }

    for (Character c : counts.keySet()) {
      if(counts.get(c) % 2 == 0) {
      } else {
        odds++;
      }
    }
    return odds <= 2;
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
    System.out.println("Unique Tests");

    // tests are in the form as shown
    assertTest(testCount, "should return unique values from sorted list with duplicates", new Test() {
      public boolean execute() {
        ArrayList<Integer> output = Problems.unique(new int[]{1, 2, 4, 4, 5, 6});
        ArrayList<Integer> test = new ArrayList<Integer>() {{
          add(1);
          add(2);
          add(4);
          add(5);
          add(6);
        }};
        return arrayListsEqual(output, test);
      }
    });

        assertTest(testCount, "should return single value for list with all duplicates", new Test() {
      public boolean execute() {
        ArrayList<Integer> output = Problems.unique(new int[]{2, 2, 2, 2, 2, 2});
        ArrayList<Integer> test = new ArrayList<Integer>() {{
          add(2);
        }};
        return arrayListsEqual(output, test);
      }
    });

        assertTest(testCount, "should return unique values from unsorted list with duplicates", new Test() {
      public boolean execute() {
        ArrayList<Integer> output = Problems.unique(new int[]{1,2,3,1,2});
        ArrayList<Integer> test = new ArrayList<Integer>() {{
          add(1);
          add(2);
          add(3);
        }};
        return arrayListsEqual(output, test);
      }
    });

        assertTest(testCount, "should return an empty list from empty input", new Test() {
      public boolean execute() {
        ArrayList<Integer> output = Problems.unique(new int[]{});
        ArrayList<Integer> test = new ArrayList<Integer>() {{
        }};
        return arrayListsEqual(output, test);
      }
    });

    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");

    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Word Count Tests");

    // tests are in the form as shown
    assertTest(testCount, "should return an object with each word and its frequency", new Test() {
      public boolean execute() {
        HashMap<String, Integer> output = Problems.wordCount("The cat and the hat.");

        return output.get("the").intValue() == 2 && output.get("hat").intValue() == 1 && output.get("cat").intValue() == 1 && output.get("and").intValue() == 1;
      }
    });

    assertTest(testCount, "should return object with each word excluding punctuations", new Test() {
      public boolean execute() {
        HashMap<String, Integer> output = Problems.wordCount("It's a man, it's a plane, it's superman!");

        return output.get("its").intValue() == 3 && output.get("a").intValue() == 2 && output.get("man").intValue() == 1 && output.get("plane").intValue() == 1 && output.get("superman").intValue() == 1;
      }
    });

    assertTest(testCount, "should return empty object for empty string input", new Test() {
      public boolean execute() {
        HashMap<String, Integer> output = Problems.wordCount("");
        return output.isEmpty();
      }
    });

    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");


    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("rgb Count Tests");

    // tests are in the form as shown
    assertTest(testCount, "should return number correct number of rgb from input", new Test() {
      public boolean execute() {
        int output = Problems.rgb("rgbrgb");
        return output == 2;
      }
    });

    assertTest(testCount, "should return correct number of rgb from input despite characters out of sequence", new Test() {
      public boolean execute() {
        int output = Problems.rgb("rbgrbrgrgbgrrggbbbbrgrgrgrg");
        return output == 7;
      }
    });

    assertTest(testCount, "should return 0 as output for no number of rgb", new Test() {
      public boolean execute() {
        int output = Problems.rgb("bbrr");
        return output == 0;
      }
    });

    assertTest(testCount, "should return 0 for empty input", new Test() {
      public boolean execute() {
        int output = Problems.rgb("");
        return output == 0;
      }
    });

    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");


    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Missing Number Tests");

    // tests are in the form as shown
    assertTest(testCount, "should return [3] for input of [1, 4, 2]", new Test() {
      public boolean execute() {
        ArrayList<Integer> output = Problems.missingNumber(4, new int[]{1, 4, 2});
        ArrayList<Integer> test = new ArrayList<Integer>() {{
          add(3);
        }};
        return arrayListsEqual(output, test);
      }
    });


    assertTest(testCount, "should return [2, 3, 5, 8] for input of [4, 7, 1, 6]", new Test() {
      public boolean execute() {
        ArrayList<Integer> output = Problems.missingNumber(8, new int[]{4, 7, 1, 6});
        ArrayList<Integer> test = new ArrayList<Integer>() {{
          add(2);
          add(3);
          add(5);
          add(8);
        }};
        return arrayListsEqual(output, test);
      }
    });

    assertTest(testCount, "should return [3, 5] for input of [6, 4, 2, 1]", new Test() {
      public boolean execute() {
        ArrayList<Integer> output = Problems.missingNumber(6, new int[]{6, 4, 2, 1});
        ArrayList<Integer> test = new ArrayList<Integer>() {{
          add(3);
          add(5);
        }};
        return arrayListsEqual(output, test);
      }
    });

    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");


    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Letter Sort Tests");

    assertTest(testCount, "should return 'ehllo' for input 'hello'", new Test() {
      public boolean execute() {
        String output = Problems.letterSort("hello");
        return output.equals("ehllo");
      }
    });

    assertTest(testCount, "should return 'abdehiortw' for input of 'whiteboard'", new Test() {
      public boolean execute() {
        String output = Problems.letterSort("whiteboard");
        return output.equals("abdehiortw");
      }
    });

    assertTest(testCount, "should return 'eno' for input 'one'", new Test() {
      public boolean execute() {
        String output = Problems.letterSort("one");
        return output.equals("eno");
      }
    });


    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");

    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Character Mode Tests");

    assertTest(testCount, "should return 'l' for input 'hello'", new Test() {
      public boolean execute() {
        String output = Problems.characterMode("hello");
        return output.equals("l");
      }
    });

    assertTest(testCount, "should return 'a' when input is 'A walk in the park'", new Test() {
      public boolean execute() {
        String output = Problems.characterMode("A walk in the park");
        return output.equals("a");
      }
    });

    assertTest(testCount, "should return 'no' when input is 'noon'", new Test() {
      public boolean execute() {
        String output = Problems.characterMode("noon");
        return output.equals("no");
      }
    });

    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");



    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Sort Digits Tests");

    assertTest(testCount, "should return '789' when input is '8970'", new Test() {
      public boolean execute() {
        int output = Problems.sortDigits(8970);
        return output == 789;
      }
    });

    assertTest(testCount, "should return '23445' when input is '32445'", new Test() {
      public boolean execute() {
        int output = Problems.sortDigits(32445);
        return output == 23445;
      }
    });

    assertTest(testCount, "should return '111' when input is '10101'", new Test() {
      public boolean execute() {
        int output = Problems.sortDigits(10101);
        return output == 111;
      }
    });

    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");


    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Get Duplicates Tests");

    assertTest(testCount, "should return '[2]' when input is '[1, 2, 4, 2]'", new Test() {
      public boolean execute() {
        ArrayList<Integer> output = Problems.getDuplicates(new int[] {1, 2, 4, 2});
        ArrayList<Integer> test = new ArrayList<Integer>() {{
          add(2);
        }};
        return arrayListsEqual(output, test);
      }
    });

    assertTest(testCount, "should return '[3, 2]' or '[2, 3]' when input is '[3, 2, 3, 2, 3, 3, 4]'", new Test() {
      public boolean execute() {
        ArrayList<Integer> output = Problems.getDuplicates(new int[] {3, 2, 3, 2, 3, 3, 4});
        ArrayList<Integer> test1 = new ArrayList<Integer>() {{
          add(2);
          add(3);
        }};

        ArrayList<Integer> test2 = new ArrayList<Integer>() {{
          add(3);
          add(2);
        }};
        return arrayListsEqual(output, test1) || arrayListsEqual(output, test2);
      }
    });

    assertTest(testCount, "should return '[]' when input is '[1, 2, 3, 4]'", new Test() {
      public boolean execute() {
        ArrayList<Integer> output = Problems.getDuplicates(new int[] {1, 2, 3, 4});
        return output.size() == 0;
      }
    });

    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");



    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Anagram Pair Tests");

    assertTest(testCount, "should return true when input is 'cat, act'", new Test() {
      public boolean execute() {
        return Problems.anagramPair("cat", "act");
      }
    });

    assertTest(testCount, "should return false when input is 'cat, dog'", new Test() {
      public boolean execute() {
        return !Problems.anagramPair("cat", "dog");
      }
    });

    assertTest(testCount, "should return false when input is 'racecar, aaccrres'", new Test() {
      public boolean execute() {
        return !Problems.anagramPair("racecar", "aaccrres");
      }
    });

    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");



    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Anagram Palindrome Tests");

    assertTest(testCount, "should return true when input is 'carrace'", new Test() {
      public boolean execute() {
        return Problems.anagramPalindrome("carrace");
      }
    });

    assertTest(testCount, "should return false when input is 'cat'", new Test() {
      public boolean execute() {
        return !Problems.anagramPalindrome("cat");
      }
    });

    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");
  }

  private static boolean arrayListsEqual(ArrayList<Integer> arr1, ArrayList<Integer> arr2) {
    if(arr1.size() != arr2.size()) {
      return false;
    }

    for(int i = 0; i < arr1.size(); i++) {
      if(arr1.get(i) != arr2.get(i)) {
        return false;
      }
    }
    return true;
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

  // checks if array is sorted in linear time
  private static boolean isSorted(int[] input) {
    if (input.length < 2) {
      return true;
    }

    for (int i = 1 ; i < input.length ; i++) {
      if (input[i-1] > input[i]) {
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
