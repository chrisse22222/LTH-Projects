package lab2;

import graph.Graph;

import java.util.*;

public class Lab2 {
  private Set<Integer> test;

  /**
   * Generic depth first search in a graph, starting from the vertex u.
   *
   * @param g       the graph to search in
   * @param u       the start vertex
   * @param visited set of visited vertices (should be empty for a full search)
   * @param <T>     the vertex type
   */
  private static <T> void dfs(Graph<T> g, T u, Set<T> visited) {
    visited.add(u);
    for (T v : g.neighbours(u)) {
      if (!visited.contains(v)) {
        dfs(g, v, visited);
      }
    }
  }

  public static boolean isConnected(Graph<Integer> g) {
    // TODO(D2): implement this!

    if (g.vertexCount() > 0) {
      Set<Integer> set = new HashSet<>();
      dfs(g, g.vertexSet().iterator().next(), set);

      for (int i = 0; i < g.vertexCount(); i++) {
        if (!set.contains(i)) {
          return false;
        }
      }

      return true;
    }

    return false;
  }

  public static int nbrOfComponents(Graph<Integer> g) {
    // TODO(D3): implement this!
    int count = 0;

    Iterator<Integer> it = g.vertexSet().iterator();
    Set<Integer> set = new HashSet<>();

    while (it.hasNext()) {
      int temp = it.next();
      if (!set.contains(temp)) {
        dfs(g, temp, set);
        count++;
      }
    }

    return count;
  }

  public static boolean pathExists(Graph<Integer> g, int u, int v) {
    // TODO(D4): implement this!

    if (u == v) {
      return true;
    } else {
      ArrayDeque<Integer> nodes = new ArrayDeque<>();
      Set<Integer> visited = new HashSet<>();
      nodes.push(u);
      visited.add(u);

      while (!nodes.isEmpty()) {
        int element = nodes.pop();
        for (int i : g.neighbours(element)) {
          if (i == v) {
            return true;
          } else if (!visited.contains(i)) {
            nodes.push(i);
            visited.add(i);
          }
        }
      }
    }

    return false;

    /*if (u == v){
      return true;
    } else{
      return doesPathExist(g, v, g.neighbours(u), new HashSet<>());
    }*/
  }

  private static boolean doesPathExist(Graph<Integer> g, int v, Collection<Integer> col, Set<Integer> visited) {

    for (int i : col) {
      if (i == v) {
        visited.add(i);
        return true;
      } else {
        if (!visited.contains(i)) {
          visited.add(i);
          doesPathExist(g, v, g.neighbours(i), visited);
        }
      }
    }

    if (visited.contains(v)) {
      return true;
    } else {
      return false;
    }
  }

  public static List<Integer> findPath(Graph<Integer> g, int u, int v) {
    // TODO(D5): implement this!

    List<Integer> list = new ArrayList<>();
    if (u == v) {
      list.add(u);
      return list;
    } else {
      ArrayDeque<Integer> nodes = new ArrayDeque<>();
      Set<Integer> visited = new HashSet<>();
      nodes.push(u);
      visited.add(u);

      while (!nodes.isEmpty()) {
        int element = nodes.pop();
        list.add(element);
        for (int i : g.neighbours(element)) {
          if (i == v) {
            list.add(i);
            return list;
          } else if (!visited.contains(i)) {
            nodes.push(i);
            visited.add(i);
          }
        }
      }
    }

    return Collections.emptyList();
  }
}

