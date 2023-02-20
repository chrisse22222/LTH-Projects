package lab1;

import com.google.errorprone.annotations.Var;
import graph.Graph;
import graph.SimpleGraph;

import java.util.Collection;

public class Lab1 {
  /**
   * Returns the number of vertices in the graph g.
   */
  public static int vertexCount(Graph<Integer> g) {
    // TODO(D2): implement this!
    return g.vertexCount();
  }
  /**
   * Returns the number of edges in the graph g.
   */
  public static int edgeCount(Graph<Integer> g) {
    // TODO(D2): implement this!
    int count = 0;
    for (int i = 0; i < vertexCount(g); i++) {
      count += g.neighbours(i).size();
    }

    return count;
  }

  /**
   * Returns true if there is an edge from vertex u to vertex v.
   * Returns false if u and v are not connected or if there is only an edge from v to u.
   *
   * @param g a graph containing u and v
   * @param u index of the first vertex in g
   * @param v index of the second vertex in g
   */
  public static boolean edgeBetween(Graph<Integer> g, int u, int v) {
    // TODO(D3): implement this!
    for (int i : g.neighbours(u)){
      if (i == v)
        return true;
    }

    return false;
  }

  /**
   * Returns a simple graph with at least 6 vertices and at least 10 edges.
   */
  public static Graph<Integer> buildGraph() {
    // TODO(D5): implement this!
    return new SimpleGraph(7, new int[][]{
            {1,2},
            {1,3},
            {1,6},
            {2,4},
            {2,5},
            {3,1},
            {3,6},
            {4,5},
            {6,2},
            {6,3}
    });
  }
}
