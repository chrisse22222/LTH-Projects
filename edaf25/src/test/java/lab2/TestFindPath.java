package lab2;

import graph.Graph;
import graph.SimpleGraph;
import org.junit.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class TestFindPath {
  @Test public final void testOneVertex() {
    Graph<Integer> g = new SimpleGraph(1);
    List<Integer> path = Lab2.findPath(g, 0, 0);
    assertThat(path).containsExactly(0);
  }

  @Test public final void testTwoVerticesNoPath() {
    Graph<Integer> g = new SimpleGraph(2);
    List<Integer> path = Lab2.findPath(g, 0, 1);
    assertThat(path).isEmpty();
  }

  @Test public final void testPathOfTwoVertices() {
    Graph<Integer> g = new SimpleGraph(2, new int[][] {
        {0, 1}, {1, 0},
    });
    List<Integer> path = Lab2.findPath(g, 0, 1);
    assertThat(path).containsExactly(0, 1).inOrder();
  }

  @Test public final void testPathOfThreeVertices() {
    Graph<Integer> g = new SimpleGraph(3, new int[][] {
        {0, 1}, {1, 0},
        {0, 2}, {2, 0},
    });
    List<Integer> path = Lab2.findPath(g, 1, 2);
    assertThat(path).containsExactly(1, 0, 2).inOrder();
  }

  @Test public final void testPathOfFiveVertices() {
    Graph<Integer> g = new SimpleGraph(5, new int[][] {
        {0, 1}, {1, 0},
        {1, 2}, {2, 1},
        {2, 3}, {3, 2},
        {3, 4}, {4, 3},
    });
    List<Integer> path = Lab2.findPath(g, 4, 0);
    assertThat(path).containsExactly(4, 3, 2, 1, 0).inOrder();
  }

  @Test public final void testalote() {
    Graph<Integer> g = new SimpleGraph(16, new int[][] {
            {0, 1}, {1, 0},
            {1, 2}, {2, 4},
            {4, 5}, {6, 3},
            {5,7}, {7, 2},
            {7,3}, {8,14},
            {7,6}, {7,8},
            {2,3}, {2,7}
    });
    List<Integer> path = Lab2.findPath(g, 0, 14);
    for (int i: path){
      System.out.println(i);
    }

    //assertThat(path).containsExactly(15,14,13,12,11,10,9,8,7,6,5,4, 3, 2, 1, 0).inOrder();
  }
}
