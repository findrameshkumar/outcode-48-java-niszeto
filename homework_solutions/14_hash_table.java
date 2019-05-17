/**
 *  Homework 14 - Hash Table
 *
 *  Problem: Hash Table
 *
 *  Prompt: Create a hash table class using separate chaining.
 *
 *  The HashTable will have the following properties:
 *
 *         storage:  {ArrayList[]} - an array of array lists containing key-value pairs
 *                                   key-value pairs are String[]{key, value}
 *         buckets:  {Integer} - the maximum number of buckets that your
 *                   storage can contain. Initially set to 8.
 *           size:   {Integer} count of key-value pairs in the storage
 *
 *  The HashTable will also have the following methods:
 *
 *           hash:   Method that takes a key and bucket number and provides a
 *                   hashed value. The dbjb2 hashing function has been
 *                   provided.
 *
 *                   Input:      key {String}
 *                   Input:      buckets {Integer}
 *                   Output:     index {Integer}
 *
 *         insert:   Method that adds a key-value pair into the storage. If the
 *                   key already exists, the value should be updated. Use
 *                   separate chaining to handle collisions.
 *
 *                   Input:      key {String}
 *                   Input:      value {String}
 *                   Output:     {Void}
 *
 *            get:   Method that given a key, return its corresponding value.
 *                   If the key does not exist, return null.
 *
 *                   Input:      key {String}
 *                   Output:     value {String}
 *
 *         remove:   Method that takes a key as its input, and looks for the
 *                   and removes the key-value pair from the bucket.
 *
 *                   Input:      key {String}
 *                   Output:     {Void}
 *
 *         resize:   If the load factor reaches a critical 0.75 or higher,
 *                   double the number of buckets. If the load factor is 0.25
 *                   or less, half the number of buckets. Make sure the number
 *                   of buckets do not fall below 8 and re-index all key-value
 *                   pairs if bucket size is changed.
 *
 *                   Input:      key {String}
 *                   Output:     {Void}
 *
 *  Input: N/A
 *  Output: A HashTable instance
 */

import java.util.*;


class HashTable {

  public int buckets = 8;
  public int size = 0;
  public List[] storage = new ArrayList[buckets];

  // Time Complexity: O(K) - where K is the length of the key
  // Auxiliary Space Complexity: O(1)
  public int hash(String key, int buckets) {
    int hash = 5381;
    for (int i = 0; i < key.length(); i++) {
      hash = ((hash << 5) + hash) + key.charAt(i);
    }
    return hash % buckets;
  }

  // Time Complexity (amortized): O(K+N) - where K is the length of the key
  // Auxiliary Space Complexity (amortized): O(1)
  public void insert(String key, String value) {
    int index = hash(key, this.buckets);
    if (storage[index] == null) {
      this.storage[index] = new ArrayList<String[]>();
    }

    List<String[]> bucket = this.storage[index];
    for (int i = 0; i < bucket.size(); i++) {
      if (bucket.get(i)[0] == key) {
        bucket.get(i)[1] = value;
        return;
      }
    }
    bucket.add(new String[]{key, value});
    this.size++;
    resize();
  }

  // Time Complexity: O(K+N) - where K is the length of the key
  // Auxiliary Space Complexity: O(1)
  public String get(String key) {
    int index = hash(key, this.buckets);
    List<String[]> bucket = this.storage[index];
    if (bucket == null) {
      return null;
    }
    for (int i = 0 ; i < bucket.size() ; i++) {
      if (bucket.get(i)[0] == key) {
        return bucket.get(i)[1];
      }
    }
    return null;
  }

  // Time Complexity (amortized): O(K+N) - where K is the length of the key
  // Auxiliary Space Complexity (amortized): O(1)
  public void remove(String key) {
    int index = hash(key, this.buckets);
    List<String[]> bucket = this.storage[index];
    if (bucket == null) {
      return;
    }
    for (int i = 0; i < bucket.size(); i++) {
      if (bucket.get(i)[0] == key) {
        bucket.remove(i);
        this.size--;
        resize();
        return;
      }
    }
  }

  // Time Complexity: O(N)
  // Auxiliary Space Complexity: O(N)
  public void resize() {
    float loadFactor = this.size / (float)this.buckets;
    if (loadFactor > 0.25 && loadFactor < 0.75) { return; }
    if (loadFactor <= 0.25 && this.buckets == 8) { return; }

    List[] temp = this.storage;
    this.buckets = loadFactor >= 0.75 ? this.buckets * 2 : this.buckets / 2;
    this.storage = new ArrayList[this.buckets];

    for (int i = 0; i < temp.length; i++) {
      List<String[]> bucket = temp[i];
      if (bucket != null) {
        for (int j = 0 ; j < bucket.size(); j++) {
          this.size--;
          this.insert(bucket.get(j)[0], bucket.get(j)[1]);
        }
      }
    }
  }
}


////////////////////////////////////////////////////////////
///////////////  DO NOT TOUCH TEST BELOW!!!  ///////////////
////////////////////////////////////////////////////////////

class Main {
  private int[] testCount;

  public interface Test {
    public boolean execute();
  }

  public static void main(String[] args) {

    int[] testCount = {0, 0};
    System.out.println("HashTable Class");

    assertTest(testCount, "able to create an instance", new Test() {
      public boolean execute() {
        HashTable hashTable = new HashTable();
        return hashTable instanceof HashTable;
      }
    });

    assertTest(testCount, "has storage field", new Test() {
      public boolean execute() {
        HashTable hashTable = new HashTable();
        try {
          hashTable.getClass().getDeclaredField("storage");
          return true;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    assertTest(testCount, "has buckets field", new Test() {
      public boolean execute() {
        HashTable hashTable = new HashTable();
        try {
          hashTable.getClass().getDeclaredField("buckets");
          return true;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    assertTest(testCount, "has size field", new Test() {
      public boolean execute() {
        HashTable hashTable = new HashTable();
        try {
          hashTable.getClass().getDeclaredField("size");
          return true;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    assertTest(testCount, "default storage set to an array", new Test() {
      public boolean execute() {
        HashTable hashTable = new HashTable();
        return hashTable.storage.getClass().isArray();
      }
    });

    assertTest(testCount, "default buckets set to 8", new Test() {
      public boolean execute() {
        HashTable hashTable = new HashTable();
        return hashTable.buckets == 8;
      }
    });

    assertTest(testCount, "default size set to 0", new Test() {
      public boolean execute() {
        HashTable hashTable = new HashTable();
        return hashTable.size == 0;
      }
    });

    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");


    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("HashTable Hash method");

    assertTest(testCount, "has hash method", new Test() {
      public boolean execute() {
        HashTable hashTable = new HashTable();
        try {
          hashTable.getClass().getMethod("hash", new Class[] { String.class, int.class });
          return true;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    assertTest(testCount, "should hash a string in to a number less than bucket size", new Test() {
      public boolean execute() {
        HashTable hashTable = new HashTable();
        try {
          for(int i = 1; i < 100; i++) {
            int index = hashTable.hash("hello", i);
            if(index >= i) {
              return false;
            }
          }
          return true;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");


    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("HashTable Insert method");

    assertTest(testCount, "has insert method", new Test() {
      public boolean execute() {
        HashTable hashTable = new HashTable();
        try {
          hashTable.getClass().getMethod("insert", new Class[] { String.class, String.class });
          return true;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    assertTest(testCount, "can insert a key-value pair into hash table", new Test() {
      public boolean execute() {
        HashTable hashTable = new HashTable();
        hashTable.insert("hello", "world");
        int index = hashTable.hash("hello", hashTable.buckets);
        return hashTable.size == 1 &&
               hashTable.storage[index] instanceof ArrayList &&
               hashTable.storage[index].size() == 1;
      }
    });

    assertTest(testCount, "can insert a second key-value pair into hashtable", new Test() {
      public boolean execute() {
        HashTable hashTable = new HashTable();
        hashTable.insert("hello", "world");
        hashTable.insert("foo", "bar");
        int index1 = hashTable.hash("hello", hashTable.buckets);
        int index2 = hashTable.hash("foo", hashTable.buckets);
        return hashTable.size == 2 && hashTable.storage[index1] instanceof ArrayList && hashTable.storage[index2] instanceof ArrayList;
      }
    });

    assertTest(testCount, "can overwrite value if key already exists", new Test() {
      public boolean execute() {
        HashTable hashTable = new HashTable();
        hashTable.insert("hello", "world");
        hashTable.insert("hello", "universe");
        int index = hashTable.hash("hello", hashTable.buckets);
        return hashTable.size == 1 &&
               hashTable.storage[index] instanceof ArrayList;
      }
    });

    // print the result of tests passed for a module
    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");


    // instantiate the testing of each module by resetting count and printing title of module
    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("HashTable Get method");

    assertTest(testCount, "has get method", new Test() {
      public boolean execute() {
        HashTable hashTable = new HashTable();
        try {
          hashTable.getClass().getMethod("get", new Class[] { String.class });
          return true;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    assertTest(testCount, "should return correct value for existing input key", new Test() {
      public boolean execute() {
        HashTable hashTable = new HashTable();
        hashTable.insert("hello", "world");
        return hashTable.get("hello") == "world";
      }
    });

    assertTest(testCount, "should return null if key does not exist", new Test() {
      public boolean execute() {
        HashTable hashTable = new HashTable();
        hashTable.insert("hello", "world");
        return hashTable.get("cat") == null;
      }
    });

    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");


    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("HashTable Remove method");

    assertTest(testCount, "has remove method", new Test() {
      public boolean execute() {
        HashTable hashTable = new HashTable();
        try {
          hashTable.getClass().getMethod("remove", new Class[] { String.class });
          return true;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    assertTest(testCount, "can remove a key-value pair", new Test() {
      public boolean execute() {
        HashTable hashTable = new HashTable();
        hashTable.insert("hello", "world");
        hashTable.remove("hello");
        int index = hashTable.hash("hello", hashTable.buckets);
        return hashTable.size == 0 &&
          hashTable.storage[index] instanceof ArrayList &&
          hashTable.storage[index].size() == 0;
      }
    });

    assertTest(testCount, "does not remove a key-pair that does not exist", new Test() {
      public boolean execute() {
        HashTable hashTable = new HashTable();
        hashTable.insert("hello", "world");
        hashTable.remove("cat");
        int index = hashTable.hash("hello", hashTable.buckets);
        return hashTable.size == 1 &&
          hashTable.storage[index] instanceof ArrayList;
      }
    });

    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");


    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("HashTable Resize method");

    assertTest(testCount, "has resize method", new Test() {
      public boolean execute() {
        HashTable hashTable = new HashTable();
        try {
          hashTable.getClass().getMethod("resize", new Class[] {});
          return true;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    assertTest(testCount, "doubles hashtable number of buckets if size exceeds 75% of the number of buckets", new Test() {
      public boolean execute() {
        HashTable hashTable = new HashTable();
        String[] keys = {"zero", "one", "two", "three", "four", "five", "six"};
        String[] values = {"0", "1", "2", "3", "4", "5", "6"};
        for (int i = 0; i < 5; i++) {
          hashTable.insert(keys[i], values[i]);
          if (hashTable.buckets != 8) { return false; }
        }
        hashTable.insert(keys[5], values[5]);
        if (hashTable.buckets != 16) { return false; }
        hashTable.insert(keys[6], values[6]);
        return hashTable.buckets == 16;
      }
    });

    assertTest(testCount, "halves hashtable number of buckets if size drops below 25% of the number of buckets", new Test() {
      public boolean execute() {
        HashTable hashTable = new HashTable();
        String[] keys = {"zero", "one", "two", "three", "four", "five", "six"};
        String[] values = {"0", "1", "2", "3", "4", "5", "6"};
        for (int i = 0; i < keys.length; i++) {
          hashTable.insert(keys[i], values[i]);
        }
        int buckets = hashTable.buckets;
        hashTable.remove("four");
        hashTable.remove("five");
        hashTable.remove("six");
        return buckets == hashTable.buckets * 2;
      }
    });

    // print the result of tests passed for a module
    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");

  }

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
