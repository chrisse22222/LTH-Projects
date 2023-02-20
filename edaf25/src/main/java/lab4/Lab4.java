package lab4;

import java.util.*;

/** Helper class for the priority queue in Dijkstras algorithm. */
class PQElement {
  int node;
  int distance;

  public PQElement(int node, int dist) {
    this.node = node;
    this.distance = dist;
  }
}

public class Lab4 {
  /**
   * Computes the shortest distance between start and end in the graph g using Dijkstra's
   * algorithm.
   * This version handles only graphs with integer edge distances.
   *
   * @param g     a graph with distance information attached to the edges
   * @param start start vertex
   * @param end   end vertex
   * @return shortest distance between start and end
   */
  public static int distance(DistanceGraph g, int start, int end) {
    // En Comparator skapas för att hålla listan med bågar sorterad:
    Comparator<PQElement> cmp = Comparator.comparingInt(e -> e.distance);
    PriorityQueue<PQElement> queue = new PriorityQueue<>(cmp);
    queue.add(new PQElement(start, 0));

    // TODO(D2): slutför Dijkstras algoritm för att hitta kortaste avstånd start->end.
    Map<Integer, Integer> distance = new HashMap<>();

    while (!queue.isEmpty()) {
      PQElement element = queue.poll();
      if (element.node == end) {
        return element.distance;
      } else {
        // för varje "granne" (edges) till elementet
        for (Edge edge : g.edges(element.node)) {
          // Avståndet till edges beräknas genom att addera elementets avstånd med edges
          int newDist = element.distance + edge.distance;
          // hämtar gamla avståndet till edgen om den finns, annars kostar den oändligt
          int wDist = distance.getOrDefault(edge.destination, Integer.MAX_VALUE);
          // om gamla avståndet är större än nya eller mappen inte redan innehåller destinationen
          if (!distance.containsKey(edge.destination) || newDist < wDist) {
            queue.add(new PQElement(edge.destination, newDist)); // adderar elementet till kön, för att sedan söka igenom dess edges
            distance.put(edge.destination, newDist); // lägger in nya avståndet i mappen
          }
        }
      }
    }

    return -1; // om det ej hittas en väg, returneras -1
  }

  /**
   * Finds a shortest path between start and end in a graph g Dijkstra's
   * algorithm.
   * The graph can have any distance unit.
   *
   * @param g     a graph with distance information attached to the edges
   * @param start start vertex
   * @param end   end vertex
   * @return a list containing the nodes on the shortest path from start to end
   */
  public static List<Integer> shortestPath(DistanceGraph g, int start, int end) {
    if(start == end){
      return Collections.emptyList();
    }

    Comparator<PQElement> cmp = Comparator.comparingInt(e -> e.distance);
    PriorityQueue<PQElement> queue = new PriorityQueue<>(cmp);
    queue.add(new PQElement(start, 0));
    // TODO(D3): slutför algoritmen och returnera vägen från start till end.
    // T.ex. om kortaste vägen mellan 0 och 10 är 0->1->5->10 ska listan [0,1,5,10] returneras.
    // Lägger in ett PQElement i mappen istället för att kunna "tracka" den billigaste vägen den tar till end dest
    Map<Integer, PQElement> distance = new HashMap<>();
    distance.put(start, new PQElement(start, 0));
    List<Integer> path = new LinkedList<>();

    while (!queue.isEmpty()) {
      PQElement element = queue.poll();
      if (!distance.containsKey(element)){
        for (Edge edge : g.edges(element.node)) {
          int newDist = element.distance + edge.distance;
          int wDist = distance.getOrDefault(edge.destination, new PQElement(edge.source, Integer.MAX_VALUE)).distance;
          if (newDist < wDist) {
            queue.add(new PQElement(edge.destination, newDist));
            //lägger in nya avståndet i mappen och länkar destinationen med föregående nod (edge.source)
            distance.put(edge.destination, new PQElement(edge.source, newDist));
          }
        }
      }
    }

    int step = end; // börjar på slutnord och stegrar bakåt
    while (step != start){ // så länge vi inte har gått förbi startnoden
      path.add(step);
      step = distance.get(step).node; // hämtar föregående nod
    }

    path.add(start); // lägger till startnoden
    Collections.reverse(path); // reversar pathen den tagit för att den ska gå från start -> slut
    return path;
  }
}

