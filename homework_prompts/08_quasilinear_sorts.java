
/**
 *  Homework 08 - Quasilinear Sorts
 *
 *
 *  Problem 1: Mergesort
 *
 *  Prompt:    Given an unsorted array of integers, return the array
 *             sorted using mergesort.
 *
 *  Input:     {Array}
 *  Output:    {Array}
 *
 *  Example:   [3,9,1,4,7] --> [1,3,4,7,9]
 */

import java.util.*;

// Worst Time Complexity: nlogn
// Worst Total (Call Stack + Auxiliary) Space Complexity: n
// Average Time Complexity: nlogn
// Average Total (Call Stack + Auxiliary) Space Complexity: n 
// Stability:
class Mergesort {
  public static int[] compute(int[] input) {
    if (input.length < 2) {
      return input;
    }

    int arrayHalfLength = input.length / 2;

    int[] leftArray = new int[arrayHalfLength];
    int[] rightArray = new int[input.length - arrayHalfLength];

    for (int index = 0; index < arrayHalfLength; index++) {
      leftArray[index] = input[index];
    }

    for (int index = arrayHalfLength; index < input.length; index++) {
      rightArray[index - arrayHalfLength] = input[index];
    }

    int[] sortedLeft = compute(leftArray);
    int[] sortedRight = compute(rightArray);
    int[] fullySortedArray = merge(sortedLeft, sortedRight);

    return fullySortedArray;

  }

  private static int[] merge(int[] array1, int[] array2) {
    int[] result = new int[array1.length + array2.length];

    int resultIndex = 0;
    int firstArrayIndex = 0;
    int secondArrayIndex = 0;

    while (firstArrayIndex < array1.length && secondArrayIndex < array2.length) {
      if (array1[firstArrayIndex] <= array2[secondArrayIndex]) {
        result[resultIndex] = array1[firstArrayIndex];
        firstArrayIndex++;
      } else {
        result[resultIndex] = array2[secondArrayIndex];
        secondArrayIndex++;
      }

      resultIndex++;
    }

    while (firstArrayIndex < array1.length) {
      result[resultIndex] = array1[firstArrayIndex];
      resultIndex++;
      firstArrayIndex++;
    }

    while (secondArrayIndex < array2.length) {
      result[resultIndex] = array2[secondArrayIndex];
      resultIndex++;
      secondArrayIndex++;
    }

    return result;
  }
}

////////////////////////////////////////////////////////////
/////////////// DO NOT TOUCH TEST BELOW!!! ///////////////
////////////////////////////////////////////////////////////

// use the Main class to run the test cases
class Main {
  private int[] testCount;

  // an interface to perform tests
  public interface Test {
    public boolean execute();
  }

  public static void main(String[] args) {

    int[] testCount = { 0, 0 };
    System.out.println("Merge Sort Tests");

    // tests are in the form as shown
    assertTest(testCount, "should sort example input", new Test() {
      public boolean execute() {
        Mergesort mergesort = new Mergesort();
        return arraysEqual(mergesort.compute(new int[] { 3, 9, 1, 4, 7 }), new int[] { 1, 3, 4, 7, 9 });
      }
    });

    assertTest(testCount, "should return empty array for empty input", new Test() {
      public boolean execute() {
        Mergesort mergesort = new Mergesort();
        return arraysEqual(mergesort.compute(new int[] {}), new int[] {});
      }
    });

    assertTest(testCount, "should sort single-element input", new Test() {
      public boolean execute() {
        Mergesort mergesort = new Mergesort();
        return arraysEqual(mergesort.compute(new int[] { 10 }), new int[] { 10 });
      }
    });

    assertTest(testCount, "should sort moderate-sized input", new Test() {
      public boolean execute() {
        Mergesort mergesort = new Mergesort();
        int[] input = new int[1000];
        for (int i = 0; i < input.length; i++) {
          input[i] = (int) Math.floor(Math.random() * 1000);
        }
        int[] inputSorted = new int[1000];

        System.arraycopy(input, 0, inputSorted, 0, input.length);

        int[] solution = new int[1000];
        System.arraycopy(input, 0, solution, 0, input.length);
        input = mergesort.compute(input);

        Arrays.sort(solution);
        return isSorted(input) && arraysEqual(input, solution);
      }
    });

    assertTest(testCount, "should sort large-sized input", new Test() {
      public boolean execute() {
        Mergesort mergesort = new Mergesort();
        int[] input = new int[1000000];
        for (int i = 0; i < input.length; i++) {
          input[i] = (int) Math.floor(Math.random() * 1000000);
        }
        int[] inputSorted = new int[1000000];

        System.arraycopy(input, 0, inputSorted, 0, input.length);

        int[] solution = new int[1000000];
        System.arraycopy(input, 0, solution, 0, input.length);
        input = mergesort.compute(input);

        Arrays.sort(solution);
        return isSorted(input) && arraysEqual(input, solution);
      }
    });

    // print the result of tests passed for a module
    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");

  }

  // function for checking if arrays are equal
  private static boolean arraysEqual(int[] arr1, int[] arr2) {
    if (arr1.length != arr2.length) {
      return false;
    }

    for (int i = 0; i < arr1.length; i++) {
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

    for (int i = 1; i < input.length; i++) {
      if (input[i - 1] > input[i]) {
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
    } catch (Exception e) {
    }
    String result = "  " + (count[1] + ")   ").substring(0, 5) + pass + " : " + name;
    System.out.println(result);
  }
}
