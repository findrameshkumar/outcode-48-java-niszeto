//  Homework 16 - Graph Traversal
//
//  Instructions: We will be exploring further graph traversal problems in this
//  homework file.
//
//  You'll want to use the following classes in your code:
//       - Graph
//       - Stack
//       - Queue

import java.util.*;

// Helper Data Structures and Algorithms

class ListNode {
  public String str;
  public Integer value;
  public int[] intArray;
  public ListNode next;

  public ListNode(Integer value){
    this.value = value;
  }

  public ListNode(String str){
    this.str = str;
  }

  public ListNode(int[] intArray){
    this.intArray = intArray;
  }

  public String getStringID() {
    return this.str;
  }

  public Integer getIntegerID() {
    return this.value;
  }

  public int[] getIntArrayID() {
    return this.intArray;
  }

}

class LinkedList {
  public int length = 0;
  public ListNode head;
  public ListNode tail;

  public void append(int value){
    insert(value, length);
  }

  public void append(String str){
    insert(str, length);
  }

  public void append(int[] intArray) {
    insert(intArray, length);
  }

  public void insert(int value, int index){
    if(index < 0 || index > length){
      return;
    }

    ListNode newNode = new ListNode(value);

    if(length == 0){
      head = newNode;
      tail = newNode;
    } else if(index == 0){
      newNode.next = head;
      head = newNode;
    } else if(index == length){
      tail.next = newNode;
      tail = newNode;
    } else {
      ListNode prev = head;
      for(int i = 0; i < index-1; i++){
        prev = prev.next;
      }
      newNode.next = prev.next;
      prev.next = newNode;
    }
    length++;
  }

  public void insert(String str, int index){
    if(index < 0 || index > length){
      return;
    }

    ListNode newNode = new ListNode(str);

    if(length == 0){
      head = newNode;
      tail = newNode;
    } else if(index == 0){
      newNode.next = head;
      head = newNode;
    } else if(index == length){
      tail.next = newNode;
      tail = newNode;
    } else {
      ListNode prev = head;
      for(int i = 0; i < index-1; i++){
        prev = prev.next;
      }
      newNode.next = prev.next;
      prev.next = newNode;
    }
    length++;
  }

  public void insert(int[] intArray, int index){
    if(index < 0 || index > length){
      return;
    }

    ListNode newNode = new ListNode(intArray);

    if(length == 0){
      head = newNode;
      tail = newNode;
    } else if(index == 0){
      newNode.next = head;
      head = newNode;
    } else if(index == length){
      tail.next = newNode;
      tail = newNode;
    } else {
      ListNode prev = head;
      for(int i = 0; i < index-1; i++){
        prev = prev.next;
      }
      newNode.next = prev.next;
      prev.next = newNode;
    }
    length++;
  }

  public ListNode delete(int index){
    if(index < 0 || index >= length){
      return null;
    }

    ListNode result;
    if(length == 1){
      result = head;
      head = null;
      tail = null;
    } else if (index == 0){
      result = head;
      head = head.next;
    } else {
      ListNode prev = head;
      for(int i = 0; i < index-1; i++){
        prev = prev.next;
      }
      result = prev.next;
      prev.next = prev.next.next;
      if(index == length-1){
        tail = prev;
      }
    }
    length--;
    return result;
  }

  public boolean contains(Integer value){
    ListNode current = head;
    while(current != null){
      if(current.getIntegerID() == value){
        return true;
      }
      current = current.next;
    }
    return false;
  }

  public boolean contains(String str){
    ListNode current = head;
    while(current != null){
      if(current.getStringID() == str){
        return true;
      }
      current = current.next;
    }
    return false;
  }

}

class Queue {
  public LinkedList list = new LinkedList();
  public Integer length = 0;

  public void enqueue(String str) {
    list.append(str);
    length += 1;
  }

  public void enqueue(Integer value) {
    list.append(value);
    length += 1;
  }

  public void enqueue(int[] intArray) {
    list.append(intArray);
    length += 1;
  }

  public String dequeueStringID() {
    if (length == 0) {
      return null;
    } else {
      this.length -= 1;
      return list.delete(0).str;
    }
  }

  public Integer dequeueIntegerID() {
    if (length == 0) {
      return null;
    } else {
      this.length -= 1;
      return list.delete(0).value;
    }
  }

  public int[] dequeueIntArrayID() {
    if (length == 0) {
      return null;
    } else {
      this.length -= 1;
      return list.delete(0).intArray;
    }
  }

  public String peekStringID() {
    return list.head.getStringID();
  }

  public Integer peekIntegerID() {
    return list.head.getIntegerID();
  }

  public Integer size() {
    return this.length;
  }

}

class Graph {
  public static HashMap<String, ArrayList<Object>> storage = new HashMap<String, ArrayList<Object>>();

  public static void addVertex(String str) {
    if (storage.get(str) == null) {
      storage.put(str, new ArrayList<Object>());
    }
  }

  public static void addVertex(Integer value) {
    String strKey = Integer.toString(value);
    if (storage.get(strKey) == null) {
      storage.put(strKey, new ArrayList<Object>());
    }
  }

  public static void removeVertex(String str) {
    if (storage.get(str) == null) {
      return;
    }

    for (ArrayList<Object> neighbors : storage.values()) {
      neighbors.remove(str);
    }

    storage.remove(str);
  }

  public static void removeVertex(Integer value) {
    String strKey = Integer.toString(value);

    if (storage.get(strKey) == null) {
      return;
    }

    for (ArrayList<Object> neighbors : storage.values()) {
      neighbors.remove(value);
    }

    storage.remove(strKey);
  }

  public static void addEdge(String a, String b) {
    if (storage.get(a) == null) {
      addVertex(a);
    }
    if (storage.get(b) == null) {
      addVertex(b);
    }
    storage.get(a).add(b);
  }

  public static void addEdge(Integer a, Integer b) {
    String strA = Integer.toString(a);
    String strB = Integer.toString(b);
    if (storage.get(strA) == null) {
      addVertex(a);
    }
    if (storage.get(strB) == null) {
      addVertex(b);
    }
    storage.get(strA).add(b);
  }

  public static void removeEdge(String a, String b) {
    if (storage.get(a) == null) {
      return;
    }
    storage.get(a).remove(b);
  }

  public static void removeEdge(Integer a, Integer b) {
    String strA = Integer.toString(a);
    if (storage.get(strA) == null) {
      return;
    }
    storage.get(strA).remove(b);
  }

  public static boolean isVertex(String str) {
    return storage.get(str) != null;
  }

  public static boolean isVertex(Integer value) {
    String strKey = Integer.toString(value);
    return storage.get(strKey) != null;
  }

  public static ArrayList<Object> neighbors(String str) {
    if (isVertex(str)) {
      return storage.get(str);
    }
    return new ArrayList<>();
  }

  public static ArrayList<Object> neighbors(Integer value) {
    String strKey = Integer.toString(value);
    if (isVertex(strKey)) {
      return storage.get(strKey);
    }
    return new ArrayList<>();
  }

  public static ArrayList<String> getStringVertices() {
    ArrayList<String> result = new ArrayList<String>();
    for (String key : storage.keySet()) {
      result.add(key);
    }
    return result;
  }

  public static ArrayList<Integer> getIntegerVertices() {
    ArrayList<Integer> result = new ArrayList<Integer>();
    for (String key : storage.keySet()) {
      Integer intKey = Integer.parseInt(key);
      result.add(intKey);
    }
    return result;
  }
}

class GenerateAdjacencyList {

  public static Graph compute(int[][] edges) {
    Graph graph = new Graph();
    graph.storage.clear();
    Integer u;
    Integer v;

    for (int i = 0; i < edges.length; i++) {
      u = edges[i][0];
      v = edges[i][1];
      graph.addEdge(u, v);
    }

    return graph;
  }

  public static Graph compute(String[][] edges) {
    Graph graph = new Graph();
    graph.storage.clear();
    String u;
    String v;

    for (int i = 0; i < edges.length; i++) {
      u = edges[i][0];
      v = edges[i][1];
      graph.addEdge(u, v);
    }

    return graph;
  }
}

class TopologicalSort {
  private static HashSet<String> visited = new HashSet<String>();
  private static ArrayList<String> result = new ArrayList<String>();
  private static Graph graph;

  public static String[] compute(Graph inputGraph) {
    visited.clear();
    result.clear();
    graph = inputGraph;

    ArrayList<String> vertices = graph.getStringVertices();

    for (int i = 0; i < vertices.size(); i++) {
      dfs(vertices.get(i));
    }

    String[] resultFormat = new String[result.size()];
    for (int i = resultFormat.length - 1; i > -1; i--) {
      resultFormat[resultFormat.length - 1 - i] = result.get(i);
    }

    return resultFormat;
  }

  private static void dfs(String current) {
    if (visited.contains(current)) {
      return;
    }

    visited.add(current);
    ArrayList<Object> neighbors = graph.neighbors(current);
    for (int i = 0; i < neighbors.size(); i++) {
      String neighbor = (String) neighbors.get(i);
      dfs(neighbor);
    }
    result.add(current);
  }
}



 /*
  *  Predict Order
  *
  *  Practice visualizing the order of traversal for each of the following
  *  graphs. Write a valid ordering if 1) BFS, 2) DFS (pre-order), and
  *  3) topological sort is performed. The starting vertex for BFS and DFS
  *  is vertex 0
  *
  *  There are no tests for this particular problem. Additionally, for some of
  *  these graphs, there are multiple possible solutions
  *
  *
  *
  *  Graph A: https://res.cloudinary.com/outco/image/upload/v1519855558/graph-traversal/Paper.Graph_Traversal.10.png
  *
  *  1) BFS: {0,1,2,3,4,5}
  *  2) DFS: {0,1,3,4,5,2}
  *  3) Topological sort: {0,2,1,3,4,5}
  *
  *  Graph B: https://res.cloudinary.com/outco/image/upload/v1519855554/graph-traversal/Paper.Graph_Traversal.11.png
  *
  *  1) BFS: {0,1,2,4,3}
  *  2) DFS: {0,1,3,4,2}
  *  3) Topological sort: {0,2,1,3,5,4}
  *
  *  Graph C: https://res.cloudinary.com/outco/image/upload/v1519855557/graph-traversal/Paper.Graph_Traversal.12.png
  *
  *  1) BFS: {0,1,2,3,4,5,6,7}
  *  2) DFS: {0,2,4,5,7,6,1,3}
  *  3) Topological sort: {0,1,3,2,4,6,5,7}
  *
  */


 /*
  *  Redundant Connection
  *
  *  Given a directed graph (list of edges), where if one of the edges is
  *  removed, the graph will become a tree.  Return that edge.
  *
  *  Parameters:
  *
  *  Input: edges: [[Integer]]
  *  Output: [Integer]
  *
  *  Examples:
  *
  * `{{1, 2}, {1, 3}, {2, 3}} --> {2, 3}`
  * `{{1, 2}, {2, 3}, {2, 4}, {4, 5}, {5, 2}} --> {5, 2}`
  *
  *  Note:
  *  - For some inputs, there coule be multiple
  *    correct solutions
  *
  *  Resources:
  *  - https://leetcode.com/problems/redundant-connection-ii/description/
  *
  *
  */

class RedundantConnection {

  public static ArrayList<ArrayList<Integer>> candidates = new ArrayList<ArrayList<Integer>>();
  public static HashMap<Integer, Integer> parent = new HashMap<Integer, Integer>();
  public static Integer N;

  public static int[] compute(int[][] edges) {
    candidates.clear();
    parent.clear();
    N = edges.length;

    for (int i = 0; i < edges.length; i++) {
      ArrayList<Integer> edge = new ArrayList<Integer>(Arrays.asList(edges[i][0], edges[i][1]));
      int origin = edge.get(0);
      int destination = edge.get(1);

      if (parent.containsKey(destination)) {
        candidates.add(new ArrayList<Integer>(Arrays.asList(parent.get(destination), destination)));
        candidates.add(edge);
      } else {
        parent.put(destination, origin);
      }
    }

    Integer root = orbit(1).value;

    if (candidates.size() == 0) {
      HashSet<Integer> cycle = orbit(root).seen;
      int[] answer = new int[0];

      for (int i = 0; i < edges.length; i++) {
        ArrayList<Integer> edge = new ArrayList<Integer>(Arrays.asList(edges[i][0], edges[i][1]));
        int origin = edge.get(0);
        int destination = edge.get(1);

        if (cycle.contains(origin) && cycle.contains(destination)) {
          answer = new int[]{origin, destination};
        }
      }
      return answer;
    }

    HashMap<Integer, ArrayList<Integer>> children = new HashMap<Integer, ArrayList<Integer>>();

    for (Integer v : parent.keySet()) {
      if (children.get(parent.get(v)) == null) {
        children.put(parent.get(v), new ArrayList<Integer>(Arrays.asList(v)));
      } else {
        children.get(parent.get(v)).add(v);
      }
    }

    ArrayList<Boolean> seen = new ArrayList<Boolean>(Arrays.asList(true));

    for (int i = 0; i < N; i++) {
      seen.add(false);
    }

    ArrayList<Integer> stack = new ArrayList<Integer>(Arrays.asList(root));

    while (stack.size() > 0) {
      Integer node = stack.remove(stack.size() - 1);
      if (!seen.get((int) node)) {
        seen.set((int) node, true);
        if (children.containsKey(node)) {
          ArrayList<Integer> connected = children.get(node);
          for (int i = 0; i < connected.size(); i++) {
            stack.add(connected.get(i));
          }
        }
      }
    }


    for (int i = 0; i < seen.size(); i++) {
      if (!seen.get(i)) {
        return new int[]{candidates.get(0).get(0), candidates.get(0).get(1)};
      }
    }

    return new int[]{candidates.get(1).get(0), candidates.get(1).get(1)};
  }

  private static class Combo {
    public static Integer value;
    public static HashSet<Integer> seen;

    Combo(Integer val, HashSet<Integer> seenSet) {
      value = val;
      seen = seenSet;
    }
  }

  public static Combo orbit(Integer node) {
    HashSet<Integer> seen = new HashSet<Integer>();

    while (parent.containsKey(node) && !seen.contains(node)) {
      seen.add(node);
      node = parent.get(node);
    }

    return new Combo(node, seen);
  }
}


 /*
  *  Third Degree Neighbors
  *
  *  Given an undirected graph represented by a list of edges and a start
  *  vertex, return an array of the 3rd degree neighbors.
  *
  *  Parameters:
  *
  *  Input: edges: [[Integer]]
  *  Input: start: Integer
  *  Output: [Integer]
  *
  *  Example:
  *
  *  The following example with start vertex `1`:
  *  Input: `{{1,2},{2,1},{1,3},{3,1},{2,4},{4,2},{3,4},{4,3},
  *           {4,8},{8,4},{4,5},{5,4},{5,6},{6,5},{5,7},{7,5},
  *           {6,7},{7,6},{8,7},{7,8},{8,9},{9,8}}`
  *  Input: 1
  *  Output: `[5,8]` or `[8,5]`     (order does not matter)
  *  Picture here: https://res.cloudinary.com/outco/image/upload/v1519850256/graph-traversal/Paper.Graph_Traversal.2.png
  *
  *
  *
  */

class ThirdDegreeNeighbors {

  public static int[] compute(int[][] edges, Integer start) {
    Graph graph = GenerateAdjacencyList.compute(edges);
    ArrayList<Integer> result = new ArrayList<Integer>();
    Queue queue = new Queue();
    HashSet<Integer> seen = new HashSet<Integer>();

    seen.add(start);
    queue.enqueue(new int[]{start, 0});

    while (queue.size() > 0) {
      int[] current = queue.dequeueIntArrayID();
      int val = current[0];
      int distance = current[1];
      if (distance == 3) {
        result.add(val);
      }

      ArrayList<Object> neighbors = graph.neighbors(val);

      for (int i = 0; i < neighbors.size(); i++) {
        Integer neighbor = (Integer) neighbors.get(i);
        if (!seen.contains(neighbor)) {
          seen.add(neighbor);
          queue.enqueue(new int[]{neighbor, distance + 1});
        }
      }
    }

    int[] resultFormat = new int[result.size()];
    for (int i = 0; i < resultFormat.length; i++) {
      resultFormat[i] = result.get(i).intValue();
    }

    return resultFormat;
  }
}



 /*
  *  Detect Cycle in Graph (Undirected)
  *
  *  Given edges that represent an undirected graph, determine if the graph
  *  has a cycle. A cycle has a minimum of 3 vertices.
  *
  *  Parameters:
  *
  *  Input: edges: [[Integer]]
  *  Output: Boolean
  *
  *  Example:
  *
  *  Input: `{{1,2},{2,1},{2,3},{3,2},{3,1},{1,3},
  *           {3,4},{4,3},{5,4},{4,5},{5,6},{6,5},
  *           {4,7},{7,4}}`
  *  Output: True
  *
  *  Resources:
  *  - https://www.geeksforgeeks.org/detect-cycle-undirected-graph/
  *
  */

class DetectCycleInGraph {

  public static boolean compute(int[][] edges) {
    Graph graph = GenerateAdjacencyList.compute(edges);
    HashSet<Integer> seen = new HashSet<Integer>();
    Queue queue = new Queue();

    ArrayList<Integer> vertices = graph.getIntegerVertices();

    for (int i = 0; i < vertices.size(); i++) {
      Integer vertex = vertices.get(i);
      if (!seen.contains(vertex)) {
        seen.add(vertex);
        queue.enqueue(vertex);
      }
      while (queue.size() > 0) {
        Integer current = queue.dequeueIntegerID();
        ArrayList<Object> neighbors = graph.neighbors(current);
        int neighborsVisited = 0;
        Boolean flag = false;

        for (int j = 0; j < neighbors.size(); j++) {
          Integer neighbor = (Integer) neighbors.get(j);
          if (!seen.contains(neighbor)) {
            seen.add(neighbor);
            queue.enqueue(neighbor);
          } else {
            neighborsVisited += 1;
          }
          if (neighborsVisited > 1) {
            flag = true;
          }
        }
        if (flag) {
          return flag;
        }
      }
    }
    return false;
  }
}




 /*
  *  Friend Circles
  *
  *  A friend circle is a group of people who are direct or indirect friends.
  *  Given a NxN bitset matrix, where a 1 in the i,j coordinate signifies a
  *  friendship between person i and person j, determine how many circles of
  *  friends there are.
  *
  *  Parameters:
  *
  *  Input: Graph: [[Integer]] (adjacency matrix)
  *  Output: Integer
  *
  *  Example:
  *
  *  Input: `{{1,1,0}, {1,1,0}, {0,0,1}}`
  *  Output: 2
  *
  *  Input: `{{1,1,0}, {1,1,1}, {0,1,1}}`
  *  Output: 1
  *
  *  Resources:
  *  - https://leetcode.com/problems/friend-circles/description/
  *
  */

class FriendCircles {

  public static Integer compute(int[][] matrix) {
    HashSet<Integer> seen = new HashSet<Integer>();
    int circles = 0;
    Queue queue = new Queue();

    for (int row = 0; row < matrix.length; row++) {
      int person = row;

      if (!seen.contains(person)) {
        queue.enqueue(person);
        seen.add(person);
        circles += 1;
      }

      while (queue.size() > 0) {
        Integer current = queue.dequeueIntegerID();
        for (int friend = 0; friend < matrix[current].length; friend++) {
          if (matrix[current][friend] == 1 && !seen.contains(friend)) {
            seen.add(friend);
            queue.enqueue(friend);
          }
        }
      }
    }
    return circles;
  }
}



 /*
  *  Longest Path I
  *
  *  Given a DAG (directed acyclic graph), find the longest path in the graph.
  *
  *  Parameters:
  *
  *  Input: Graph: [[Integer]] (adjacency matrix)
  *  Output: Integer
  *
  *  Example:
  *
  *  Input: {{1,2},{2,3},{1,3}}
  *  Output: {1,2,3}
  *
  *  Input: {{6,5},{6,4},{5,4},{4,3},{2,3},{1,2},{4,1}}
  *  Output: {6,5,4,1,2,3}
  *
  *  Resources:
  *  - https://www.geeksforgeeks.org/find-longest-path-directed-acyclic-graph/
  *
  */

class LongestPathI {

  private static ArrayList<Integer> result = new ArrayList<Integer>();
  private static HashSet<Integer> visited = new HashSet<Integer>();
  private static Graph graph = new Graph();

  public static int[] compute(int[][] edges) {
    graph = GenerateAdjacencyList.compute(edges);
    result.clear();
    visited.clear();

    ArrayList<Integer> vertices = graph.getIntegerVertices();
    for (int i = 0; i < vertices.size(); i++) {
      dfs(vertices.get(i), new ArrayList<>(Arrays.asList(vertices.get(i))));
    }


    int[] resultFormat = new int[result.size()];
    for (int i = 0; i < resultFormat.length; i++) {
      resultFormat[i] = result.get(i).intValue();
    }

    return resultFormat;
  }

  private static void dfs(Integer current, ArrayList<Integer> path) {
    if (visited.contains(current)) {
      return;
    }

    if (path.size() > result.size()) {
      result = new ArrayList<Integer>(path);
    }
    visited.add(current);

    ArrayList<Object> neighbors = graph.neighbors(current);
    for (int i = 0; i < neighbors.size(); i++) {
      Integer neighbor = (Integer) neighbors.get(i);
      path.add(neighbor);
      dfs(neighbor, path);
      path.remove(path.size() - 1);
    }

    visited.remove(current);
  }
}




 /*
  *  Course Schedule
  *
  *  A collection of courses at a University has prerequisite courses that must
  *  be taken first.  Given an array of course pairs [A, B] where A is the
  *  prerequisite of B, determine a valid sequence of courses a student can
  *  take to complete all the courses.
  *
  *  Parameters:
  *
  *  Input: courses: [[String]]
  *  Output: [String]
  *
  *  Example:
  *
  *  Input: {{"a","b"},{"a","c"},{"b","d"},{"c","d"}}
  *  Output: {"a","b","c","d"} or {"a","c","b","d"}
  *
  *  Input: {{"a","b"},{"b","c"},{"c","d"}}
  *  Output: {"a","b","c","d"}
  *
  *
  */

class CourseSchedule {

  public static String[] compute(String[][] edges) {
    Graph graph = GenerateAdjacencyList.compute(edges);
    String[] result = TopologicalSort.compute(graph);

    return result;
  }
}

 /*
  *  Cryptic Dictionary
  *
  *  An array of strings in lexicographical (alphabetical) order has been put
  *  through a [simple substitution cypher](https://en.wikipedia.org/wiki/Substitution_cipher)
  *  where each letter is now substituted for another letter. Determine a valid
  *  ordering of characters in the cypher.
  *
  *  Parameters:
  *
  *  Input: words: [String]
  *  Output: [String]
  *
  *  Example:
  *
  *  Input: {"baa", "abcd", "abca", "cab", "cad"}
  *  Output: {"b", "d", "a", "c"}
  *
  *  Input: {"caa", "aaa", "aab"}
  *  Output: {"c", "a", "b"}
  *
  *  Source: https://www.geeksforgeeks.org/given-sorted-dictionary-find-precedence-characters/
  */

class CrypticDictionary {

  public static String[] compute(String[] words) {
    ArrayList<String[]> edges = new ArrayList<String[]>();
    for (int i = 0; i < words.length - 1; i++) {
      String word = words[i];
      String nextWord = words[i + 1];
      edges.add(firstLetterDifference(word, nextWord));
    }

    String[][] edgesFormat = new String[edges.size()][];
    for (int i = 0; i < edgesFormat.length; i++) {
      edgesFormat[i] = edges.get(i);
    }

    Graph graph = GenerateAdjacencyList.compute(edgesFormat);
    String[] result = TopologicalSort.compute(graph);
    return result;
  }

  private static String[] firstLetterDifference(String word1, String word2) {
    for (int letter = 0; letter < Math.min(word1.length(), word2.length()); letter++) {
      if (word1.charAt(letter) != word2.charAt(letter)) {
        return new String[] {Character.toString(word1.charAt(letter)), Character.toString(word2.charAt(letter))};
      }
    }

    return new String[0];
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
    System.out.println("Redundant Connection Tests");

    // tests are in the form as shown
    assertTest(testCount, "should work for first example case", new Test() {
      public boolean execute() {
        RedundantConnection rc = new RedundantConnection();
        int[] solution = rc.compute(new int[][] {{1,2},{1,3},{2,3}});
        return arraysEqual(solution, new int[] {2,3}) ||
        arraysEqual(solution, new int[] {1,3});
      }
    });

    assertTest(testCount, "should work for second example case", new Test() {
      public boolean execute() {
        RedundantConnection rc = new RedundantConnection();
        int[] solution = rc.compute(new int[][] {{1,2},{2,3},{2,4},{4,5},{5,2}});
        return arraysEqual(solution, new int[] {5,2});
      }
    });

    assertTest(testCount, "should work for cyclic graph", new Test() {
      public boolean execute() {
        RedundantConnection rc = new RedundantConnection();
        int[] solution = rc.compute(new int[][] {{1,2},{2,3},{3,1}});
        return arraysEqual(solution, new int[] {1,2}) ||
        arraysEqual(solution, new int[] {2,3}) ||
        arraysEqual(solution, new int[] {3,1});
      }
    });

    assertTest(testCount, "should work for cyclic graph with branches coming off cyclical portion", new Test() {
      public boolean execute() {
        RedundantConnection rc = new RedundantConnection();
        int[] solution = rc.compute(new int[][] {{1,2},{2,3},{3,1},{3,6},{2,5},{1,4}});
        return arraysEqual(solution, new int[] {1,2}) ||
        arraysEqual(solution, new int[] {2,3}) ||
        arraysEqual(solution, new int[] {3,1});
      }
    });

    // print the result of tests passed for a module
    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");


    // instantiate the testing of each module by resetting count and printing title of module
    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Third Degree Neighbors Test");

    assertTest(testCount, "should work for example case", new Test() {
      public boolean execute() {
        ThirdDegreeNeighbors tdn = new ThirdDegreeNeighbors();
        int[] solution = tdn.compute(new int[][] {{1,2},{2,1},{1,3},{3,1},{2,4},
                                                  {4,2},{3,4},{4,3},{4,8},{8,4},
                                                  {4,5},{5,4},{5,6},{6,5},{5,7},
                                                  {7,5},{6,7},{7,6},{8,7},{7,8},
                                                  {8,9},{9,8}}, 1);
        return arraysMatching(solution, new int[] {5,8});
      }
    });

    assertTest(testCount, "should work for graph with no third degree neighbors", new Test() {
      public boolean execute() {
        ThirdDegreeNeighbors tdn = new ThirdDegreeNeighbors();
        int[] solution = tdn.compute(new int[][] {{1,2},{2,1},{1,3},{3,1},{2,4},{4,2},{3,4},{4,3}}, 1);
        return arraysMatching(solution, new int[] {});
      }
    });

    assertTest(testCount, "should work for graph with no edges", new Test() {
      public boolean execute() {
        ThirdDegreeNeighbors tdn = new ThirdDegreeNeighbors();
        int[] solution = tdn.compute(new int[][] {}, 1);
        return arraysMatching(solution, new int[] {});
      }
    });

    assertTest(testCount, "should work for branching graph", new Test() {
      public boolean execute() {
        ThirdDegreeNeighbors tdn = new ThirdDegreeNeighbors();
        int[] solution = tdn.compute(new int[][] {{1,2},{2,1},{2,3},{3,2},{3,4},
                                                  {4,3},{3,5},{5,3},{3,6},{6,3},
                                                  {1,7},{7,1},{7,8},{8,7},{8,9},
                                                  {9,8},{8,10},{10,8},{8,11},{11,8}}, 1);
        return arraysMatching(solution, new int[] {4,5,6,9,10,11});
      }
    });

    assertTest(testCount, "should work for linear graph", new Test() {
      public boolean execute() {
        ThirdDegreeNeighbors tdn = new ThirdDegreeNeighbors();
        int[] solution = tdn.compute(new int[][] {{1,2},{2,1},{2,3},{3,2},{3,4},
                                                  {4,3},{4,5},{5,4},{5,6},{6,5},
                                                  {6,1},{1,6}}, 1);
        return arraysMatching(solution, new int[] {4});
      }
    });

    assertTest(testCount, "should work for cyclic graph", new Test() {
      public boolean execute() {
        ThirdDegreeNeighbors tdn = new ThirdDegreeNeighbors();
        int[] solution = tdn.compute(new int[][] {{1,2},{2,1},{2,3},{3,2},{3,4},
                                                  {4,3},{4,5},{5,4},{5,6},{6,5},
                                                  {6,7},{7,6},{7,8},{8,7},{8,1},
                                                  {1,8}}, 1);
        return arraysMatching(solution, new int[] {4,6});
      }
    });




    // print the result of tests passed for a module
    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");

    // instantiate the testing of each module by resetting count and printing title of module
    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Detect Cycle In Graph Tests");

    assertTest(testCount, "should work for example case", new Test() {
      public boolean execute() {
        DetectCycleInGraph dcig = new DetectCycleInGraph();
        boolean solution = dcig.compute(new int[][] {{1,2},{2,1},{2,3},{3,2},{3,1},{1,3},
                                                     {3,4},{4,3},{5,4},{4,5},{5,6},{6,5},
                                                     {4,7},{7,4}});
        return solution == true;
      }
    });

    assertTest(testCount, "should return false when cycle does not exist", new Test() {
      public boolean execute() {
        DetectCycleInGraph dcig = new DetectCycleInGraph();
        boolean solution = dcig.compute(new int[][] {{1,2},{2,1},{1,3},{3,1},{3,4},{4,3},
                                                     {4,5},{5,4},{5,6},{6,5},{4,7},{7,4}});
        return solution == false;
      }
    });

    assertTest(testCount, "should return false when no edges exist in graph", new Test() {
      public boolean execute() {
        DetectCycleInGraph dcig = new DetectCycleInGraph();
        boolean solution = dcig.compute(new int[][] {});
        return solution == false;
      }
    });

    assertTest(testCount, "should return true for one large loop", new Test() {
      public boolean execute() {
        DetectCycleInGraph dcig = new DetectCycleInGraph();
        boolean solution = dcig.compute(new int[][] {{1,2},{2,1},{1,3},{3,1},{3,5},{5,3},
                                                     {5,6},{6,5},{6,4},{4,6},{4,2},{2,4}});
        return solution == true;
      }
    });

    assertTest(testCount, "should return false for single element graph", new Test() {
      public boolean execute() {
        DetectCycleInGraph dcig = new DetectCycleInGraph();
        boolean solution = dcig.compute(new int[][] {{1,1}});
        return solution == false;
      }
    });

    assertTest(testCount, "should return false for two element graph connected by edge", new Test() {
      public boolean execute() {
        DetectCycleInGraph dcig = new DetectCycleInGraph();
        boolean solution = dcig.compute(new int[][] {{1,2},{2,1}});
        return solution == false;
      }
    });

    // print the result of tests passed for a module
    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");



    // instantiate the testing of each module by resetting count and printing title of module
    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Friend Circles");

    assertTest(testCount, "should work for first example case", new Test() {
      public boolean execute() {
        FriendCircles fc = new FriendCircles();
        Integer solution = fc.compute(new int[][] {{1,1,0}, {1,1,0}, {0,0,1}});
        return solution == 2;
      }
    });

    assertTest(testCount, "should work for second example case", new Test() {
      public boolean execute() {
        FriendCircles fc = new FriendCircles();
        Integer solution = fc.compute(new int[][] {{1,1,0}, {1,1,1}, {0,1,1}});
        return solution == 1;
      }
    });

    assertTest(testCount, "should work for an empty matrix", new Test() {
      public boolean execute() {
        FriendCircles fc = new FriendCircles();
        Integer solution = fc.compute(new int[][] {});
        return solution == 0;
      }
    });

    assertTest(testCount, "should work when no one is friends with anyone else", new Test() {
      public boolean execute() {
        FriendCircles fc = new FriendCircles();
        Integer solution = fc.compute(new int[][] {{1,0,0,0},{0,1,0,0},{0,0,1,0},{0,0,0,1}});
        return solution == 4;
      }
    });

    assertTest(testCount, "should work when everyone is friends with everyone else", new Test() {
      public boolean execute() {
        FriendCircles fc = new FriendCircles();
        Integer solution = fc.compute(new int[][] {{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1}});
        return solution == 1;
      }
    });

    // print the result of tests passed for a module
    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");



    // instantiate the testing of each module by resetting count and printing title of module
    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Longest Path I");

    assertTest(testCount, "should work for first example case", new Test() {
      public boolean execute() {
        LongestPathI fc = new LongestPathI();
        int[] solution = fc.compute(new int[][] {{1,2},{2,3},{1,3}});
        return arraysEqual(solution, new int[] {1,2,3});
      }
    });

    assertTest(testCount, "should work for second example case", new Test() {
      public boolean execute() {
        LongestPathI fc = new LongestPathI();
        int[] solution = fc.compute(new int[][] {{6,5},{6,4},{5,4},{4,3},{2,3},{1,2},{4,1}});
        return arraysEqual(solution, new int[] {6,5,4,1,2,3});
      }
    });

    assertTest(testCount, "should work for three-element linear graph", new Test() {
      public boolean execute() {
        LongestPathI fc = new LongestPathI();
        int[] solution = fc.compute(new int[][] {{2,3},{3,1}});
        return arraysEqual(solution, new int[] {2,3,1});
      }
    });

    assertTest(testCount, "should work for graph with two equivalent paths", new Test() {
      public boolean execute() {
        LongestPathI fc = new LongestPathI();
        int[] solution = fc.compute(new int[][] {{1,2},{2,4},{4,6},{1,3},{3,5},{5,7}});
        return arraysEqual(solution, new int[] {1,2,4,6}) ||
               arraysEqual(solution, new int[] {1,3,5,7});
      }
    });

    assertTest(testCount, "should work for single-element graph", new Test() {
      public boolean execute() {
        LongestPathI fc = new LongestPathI();
        int[] solution = fc.compute(new int[][] {{1,1}});
        return arraysEqual(solution, new int[] {1});
      }
    });

    assertTest(testCount, "should handle graph with multiple paths equivalent in length", new Test() {
      public boolean execute() {
        LongestPathI fc = new LongestPathI();
        int[] solution = fc.compute(new int[][] {{1,2},{1,3},{1,4},{1,5}});
        return arraysEqual(solution, new int[] {1,2}) ||
        arraysEqual(solution, new int[] {1,3}) ||
        arraysEqual(solution, new int[] {1,4}) ||
        arraysEqual(solution, new int[] {1,5});
      }
    });

    // print the result of tests passed for a module
    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");





    // instantiate the testing of each module by resetting count and printing title of module
    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Course Schedule");

    assertTest(testCount, "should work for first example case", new Test() {
      public boolean execute() {
        CourseSchedule fc = new CourseSchedule();
        String[] solution = fc.compute(new String[][] {{"a","b"},{"a","c"},{"b","d"},{"c","d"}});
        return stringArraysEqual(solution, new String[] {"a","b","c","d"}) ||
               stringArraysEqual(solution, new String[] {"a","c","b","d"});
      }
    });

    assertTest(testCount, "should work for second example case", new Test() {
      public boolean execute() {
        CourseSchedule fc = new CourseSchedule();
        String[] solution = fc.compute(new String[][] {{"a","b"},{"b","c"},{"c","d"}});
        return stringArraysEqual(solution, new String[] {"a","b","c","d"});
      }
    });

    assertTest(testCount, "should work for courseload with small number of courses", new Test() {
      public boolean execute() {
        CourseSchedule fc = new CourseSchedule();
        String[] solution = fc.compute(new String[][] {{"a","c"},{"a","b"}});
        return stringArraysEqual(solution, new String[] {"a","c","b"}) ||
               stringArraysEqual(solution, new String[] {"a","b","c"});
      }
    });

    assertTest(testCount, "should work for large courseload", new Test() {
      public boolean execute() {
        CourseSchedule fc = new CourseSchedule();
        String[] solution = fc.compute(new String[][] {{"a","b"},{"a","c"},{"b","d"},{"d","e"},
                                                       {"d","c"},{"c","e"},{"e","f"},{"f","h"},
                                                       {"e","h"},{"e","g"},{"h","g"}});
        return stringArraysEqual(solution, new String[] {"a","b","d","c","e","f","h","g"});
      }
    });

    assertTest(testCount, "should work for empty input array", new Test() {
      public boolean execute() {
        CourseSchedule fc = new CourseSchedule();
        String[] solution = fc.compute(new String[][] {});
        return stringArraysEqual(solution, new String[] {});
      }
    });

    // print the result of tests passed for a module
    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");


    // instantiate the testing of each module by resetting count and printing title of module
    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Cryptic Dictionary");

    assertTest(testCount, "should work for first example case", new Test() {
      public boolean execute() {
        CrypticDictionary cd = new CrypticDictionary();
        String[] solution = cd.compute(new String[] {"baa","abcd","abca","cab","cad"});
        return stringArraysEqual(solution, new String[] {"b","d","a","c"});
      }
    });

    assertTest(testCount, "should work for second example case", new Test() {
      public boolean execute() {
        CrypticDictionary cd = new CrypticDictionary();
        String[] solution = cd.compute(new String[] {"caa","aaa","aab"});
        return stringArraysEqual(solution, new String[] {"c","a","b"});
      }
    });

    assertTest(testCount, "should work for two word input case", new Test() {
      public boolean execute() {
        CrypticDictionary cd = new CrypticDictionary();
        String[] solution = cd.compute(new String[] {"bbb","bab"});
        return stringArraysEqual(solution, new String[] {"b","a"});
      }
    });

    assertTest(testCount, "should work for words that have two characters", new Test() {
      public boolean execute() {
        CrypticDictionary cd = new CrypticDictionary();
        String[] solution = cd.compute(new String[] {"bm","bn","bo","mo"});
        return stringArraysEqual(solution, new String[] {"b","m","n","o"});
      }
    });

    // print the result of tests passed for a module
    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");


  }


  // function for checking if arrays contain same elements
  // (do not need to be in the same order)
  private static boolean arraysMatching(int[] arr1, int[] arr2) {
    if (arr1.length != arr2.length) {
      return false;
    } else {
      Arrays.sort(arr1);
      Arrays.sort(arr2);

      for (int i = 0 ; i < arr1.length ; i++) {
        if (arr1[i] != arr2[i]) {
          return false;
        }
      }

      return true;
    }
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


  // function for checking if arrays are equal
  private static boolean stringArraysEqual(String[] arr1, String[] arr2) {
    if (arr1.length != arr2.length) {
      return false;
    }

    for (int i = 0 ; i < arr1.length ; i++) {
      if (!arr1[i].equals(arr2[i])) {
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
