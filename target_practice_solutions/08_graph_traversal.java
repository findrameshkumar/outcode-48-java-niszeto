/**
 *  Target Practice 09 - Graph Traversal
 *
 *  Instructions: Solve the following graph traversal problems.
 */


/**
 *  1. For the example graph below, what an expected output if we printed
 *     each vertex value from vertex 0 outwards using:
 *
 *     a. BREADTH FIRST traversal: [0,1,2,7,4,5,6,3]
 *     b. DEPTH FIRST traversal: [0,1,2,4,5,3,6,7]
 *
 *     NOTE: The traversal should take care of redundancy and not print the same
 *           vertex value twice.
 *
 *     Example Graph:
 *
 *              2
 *            /   \
 *    0 ~~~ 1       4  ~~~ 5 ~~~ 3
 *            \   /   \   /
 *              7       6
 */


import java.util.*;


// DO NOT EDIT
// Vertex class
class Vertex {
  public Integer id;
  public List<Vertex> edges;

  public Vertex(Integer id) {
    this.id = id;
    this.edges = new ArrayList<Vertex>();
  }
}

class Problems {

  // DO NOT EDIT
  // generate graph from int and array of arrays
  public static Vertex deserialize(int n, int[][] edges){
    Map<Integer, Vertex> vertices = new HashMap<>();
    for (Integer i = 0; i < n; i++) {
      vertices.put(i, new Vertex(i));
    }
    int v1;
    int v2;
    for (int i = 0; i < edges.length; i++) {
      int[] edge = edges[i];
      v1 = edge[0];
      v2 = edge[1];
      vertices.get(v1).edges.add(vertices.get(v2));
      vertices.get(v2).edges.add(vertices.get(v1));
    }
    return vertices.get(0);
  }

  /**
   *  1. Implement Breadth First Search using a queue and while loop.
   *
   *  Input: {Vertex} - the starting vertex
   *  Output: {List} - an list of vertex ids of the path
   *
   *  HINT: Use a set or hash map to handle redundancy
   */

   public static List<Integer> bfs(Vertex vertex) {
     List<Integer> result = new ArrayList<>();
     Queue<Vertex> queue = new LinkedList<Vertex>();
     Set<Integer> visited = new HashSet<Integer>();
     queue.add(vertex);
     visited.add(vertex.id);

     Vertex current;
     while (queue.peek() != null) {
       current = queue.remove();
       List<Vertex> edges = current.edges;
       for(int i = 0; i < edges.size(); i++) {
         Vertex neighbor = edges.get(i);
         if (visited.contains(neighbor.id) == false) {
           queue.add(neighbor);
           visited.add(neighbor.id);
         }
       }
       result.add(current.id);
     }
     return result;
   }

   /**
    *  2. Implement Depth First Search using a stack and while loop.
    *
    *  Input: {Vertex} - the starting vertex
    *  Output: {List} - an list of vertex ids of the path
    *
    *  HINT: Use a set or hash map to handle redundancy
    */
    public static List<Integer> dfs(Vertex vertex) {
      List<Integer> result = new ArrayList<>();
      Stack<Vertex> stack = new Stack<Vertex>();
      Set<Integer> visited = new HashSet<Integer>();
      stack.push(vertex);
      visited.add(vertex.id);

      Vertex current;
      while (!stack.empty()) {
        current = stack.pop();
        List<Vertex> edges = current.edges;
        for(int i = 0; i < edges.size(); i++) {
          Vertex neighbor = edges.get(i);
          if (visited.contains(neighbor.id) == false) {
            stack.push(neighbor);
            visited.add(neighbor.id);
          }
        }
        result.add(current.id);
      }
      return result;
    }


}


////////////////////////////////////////////////////////////
///////////////  DO NOT TOUCH TEST BELOW!!!  ///////////////
////////////////////////////////////////////////////////////

// use the Main class to run the test cases
class Main {
  private int[] testCount;

  public interface Test {
    public boolean execute();
  }

  public static void main(String[] args) {

    Problems problems = new Problems();

    int[] testCount = {0, 0};
    System.out.println("Breadth First Search tests");

    assertTest(testCount, "able to return the elements of a graph in breadth first manner", new Test() {
      public boolean execute() {
        try {
          Vertex test = problems.deserialize(8, new int[][]{{0, 1}, {1, 2}, {2, 4}, {3, 5}, {4, 5}, {1, 7}, {4, 6}, {4, 7}, {5, 6}});
          List<Integer> results = problems.bfs(test);
          return validBfs(results, test);
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    assertTest(testCount, "should return only starting initial node if no edges exist in graph", new Test() {
      public boolean execute() {
        try {
          Vertex test = problems.deserialize(8, new int[][]{});
          List<Integer> results = problems.bfs(test);
          return validBfs(results, test);
        } catch (Exception e) {
          return false;
        }
      }
    });

    assertTest(testCount, "should return elements of simple graph: 0 - 1 - 2 starting at 0", new Test() {
      public boolean execute() {
        try {
          Vertex test = problems.deserialize(3, new int[][]{{0,1}, {1,2}});
          List<Integer> results = problems.bfs(test);
          return validBfs(results, test);
        } catch (Exception e) {
          return false;
        }
      }
    });

    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");


    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Depth First Search Tests");

    assertTest(testCount, "able to return the elements of a graph in breadth first manner", new Test() {
      public boolean execute() {
        try {
          Vertex test = problems.deserialize(8, new int[][]{{0, 1}, {1, 2}, {2, 4}, {3, 5}, {4, 5}, {1, 7}, {4, 6}, {4, 7}, {5, 6}});
          List<Integer> results = problems.dfs(test);
          return validDfs(results, test);
        } catch (Exception e) {
          return false;
        }
      }
    });

    assertTest(testCount, "should return only starting initial node if no edges exist in graph", new Test() {
      public boolean execute() {
        try {
          Vertex test = problems.deserialize(8, new int[][]{});
          List<Integer> results = problems.dfs(test);
          return validDfs(results, test);
        } catch (Exception e) {
          return false;
        }
      }
    });

    assertTest(testCount, "should return elements of simple graph: 0 - 1 - 2 starting at 0", new Test() {
      public boolean execute() {
        try {
          Vertex test = problems.deserialize(3, new int[][]{{0,1}, {1,2}});
          List<Integer> results = problems.dfs(test);
          return validDfs(results, test);
        } catch (Exception e) {
          return false;
        }
      }
    });

    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");
  }

  private static List<Vertex> getNeighbors(Vertex vertex, Map<Integer, Vertex> visited) {
    List<Vertex> results = new ArrayList<>();
    List<Vertex> edges = vertex.edges;
    Vertex neighbor;
    for (int i = 0; i < edges.size(); i++) {
      neighbor = edges.get(i);
      if(!visited.containsKey(neighbor.id)) {
        results.add(neighbor);
      }
    }
    return results;
  }

  private static List<Integer> getValues(List<Vertex> vertices) {
    List<Integer> results = new ArrayList<>();
    for (int i = 0; i < vertices.size(); i++) {
      results.add(vertices.get(i).id);
    }
    return results;
  }

  private static List<Vertex> removeVisited(List<Vertex> vertices, Set<Vertex> visited) {
    List<Vertex> results = new ArrayList<>();
    for (int i = 0; i < vertices.size(); i++) {
      Vertex vertex = vertices.get(i);
      if (!visited.contains(vertex)) {
        results.add(vertex);
      }
    }
    return results;
  }

  // function for checking if arrays contain same elements
  // (do not need to be in the same order)
  private static boolean listsMatching(List<Integer> list1, List<Integer> list2) {
    if (list1.size() != list2.size()) {
      System.out.println("listMatching: not equal length");
      return false;
    }

    Map<Integer, Integer> cache = new HashMap<>();
    for (int i = 0 ; i < list1.size() ; i++) {
      int value = list1.get(i);
      int count = cache.containsKey(value) ? cache.get(value) : 0;
      cache.put(value, count + 1);
    }

    for (int j = 0; j < list2.size(); j++) {
      int value = list2.get(j);
      if (!cache.containsKey(value) || cache.get(value) == 0) {
        return false;
      }
      int count = cache.get(value);
      cache.put(value, count - 1);
    }

    return true;
  }

  // takes in an array of path and a vertex start point and determines whether
  // the bfs is valid
  private static boolean validBfs(List<Integer> path, Vertex start) {
    if (path.size() == 0 && start != null) {
      return false;
    }
    if (path.get(0) != start.id) {
      System.out.println("false1");
      return false;
    }

    Vertex current = start;
    Map<Integer, Vertex> visited = new HashMap<>();
    visited.put(current.id, current);
    for (int i = 0, j = 1; i < path.size() - 1; i++) {
      if (i >= j) {
        return false;
      }
      List<Vertex> neighbors = getNeighbors(current, visited);

      List<Integer> values = getValues(neighbors);
      List<Integer> subPath = path.subList(j, j + values.size());
      if (!listsMatching(values, subPath)) {
        return false;
      }
      j += values.size();
      for(int k = 0; k < neighbors.size(); k++) {
        visited.put(neighbors.get(k).id, neighbors.get(k));
      }

      current = visited.get(path.get(i + 1));
    }

    return true;
  }

  // takes in an array of path and a vertex start point and determines whether
  // the dfs is valid
  private static boolean validDfs(List<Integer> path, Vertex start) {
    if (path.get(0) != start.id) {
      return false;
    }

    Stack<List<Vertex>> stack = new Stack<>();
    Set visited = new HashSet();
    List<Vertex> moves = new LinkedList<>();
    moves.add(start);

    for (int i = 0; i < path.size(); i++) {
      int id = path.get(i);
      while (moves.size() == 0) {
        if (stack.isEmpty()) {
          return false;
        }
        moves = removeVisited(stack.pop(), visited);
      }
      Vertex current = null;
      for (int j = 0; j < moves.size(); j++) {
        if(moves.get(j).id == id) {
          current = moves.get(j);
        }
      }
      if (current == null) {
        return false;
      }
      visited.add(current);
      stack.push(moves);

      moves = removeVisited(current.edges, visited);
    }
    while (!stack.isEmpty()) {
      if (removeVisited(stack.pop(), visited).size() > 0) {
        return false;
      }
    }
    return true;
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
