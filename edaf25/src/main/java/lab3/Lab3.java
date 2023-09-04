package lab3;

import graph.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Lab3 {
  public static <T> int distance(Graph<T> g, T source, T dest) {
    // TODO(D4): breadth first search to find shortest distance from source to dest.

    int distance = 0;
    Set<T> visited = new HashSet<>();
    Set<T> curLevel = new HashSet<>();
    curLevel.add(source);
    visited.add(source);

    while (!curLevel.isEmpty()){ // så länge currentlevel level inte är tom
      Set<T> nextLevel = new HashSet<>(); // nya nivån
      for (T w : curLevel){ // stegra igenom nuvarande nivå och kolla om det finns något som stämmer med destinationen
        if (w.equals(dest)){
          return distance;
        }

        for (T n : g.neighbours(w)){ // Kolla alla grannar till W och lägg till dem i nästa nivå
          if (!visited.contains(n)){ // om denna noden inte redan är besökt, lägg till den
            visited.add(n);
            nextLevel.add(n);
          }
        }
      }

      distance++;
      curLevel = nextLevel; // flyttar vår currentlevel referens till nextlevel och repeterar ovanstående
    }

    return -1;
  }
}
