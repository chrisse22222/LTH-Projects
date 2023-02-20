package lab5;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Lab5 {
  /**
   * Computes the maximum flow for a flow network.
   * @param g a graph with edge capacities and a source and sink
   * @return shortest distance between start and end
   */
  public static int maxFlow(FlowGraph g, int source, int sink) {
    // TODO(D2): Impelementera Edmonds-Karp varianten av Ford-Fulkerson algoritmen.
    // Det vill säga, använd bredden först-sökning för att hitta en väg med positivt flöde,
    // subtrahera det flödet och upprepa tills det inte går att skicka igenom mer flöden.
    int [][] residual = new int[g.vertexCount()][g.vertexCount()];
    // lägger in alla värden i residual (en kopia av grafens kapaciteter)
    for (int i = 0; i < residual.length; i++) {
      for (int j = 0; j < residual[0].length; j++) {
        residual[i][j] = g.getCapacity(i, j);
      }
    }
    
    int pred[] = new int[g.vertexCount()];
    int maxFlow = 0;

    // så länge det finns kapacitet från source till sink
    while (bfs(g.vertexCount(), source, sink, residual, pred)){
      int pathFlowBottleNeck = Integer.MAX_VALUE;
      // Hittar minsta kapaciteten på vägen som bfs hittade, börjar vid slutnoden (sink)
      for (int v = sink; v != source; v = pred[v]) {
        int u = pred[v]; // Föregångaren till v
        // hittar minsta flödet mellan u, v och tidigare hittat bottleneck
        pathFlowBottleNeck = Math.min(pathFlowBottleNeck, residual[u][v]);
      }

      maxFlow += pathFlowBottleNeck;

      // Går igenom vägen som bfs hittade och uppdaterar residualerna
      for (int v = sink; v != source; v = pred[v]){
        int u = pred[v];
        // minskar kapaciteten från u -> v
        residual[u][v] -= pathFlowBottleNeck;
        // ökar kapaciteten mellan v -> u
        residual [v][u] += pathFlowBottleNeck;
      }
    }

    return maxFlow;
  }

  /**
   *
   * @param numVertex number of vertexes in the graph
   * @param start
   * @param end
   * @param residual
   * @param pred
   * @return true if there is a path with capacity grater than 0 in the residual matrix
   */
  private static boolean bfs(int numVertex, int start, int end, int[][] residual, int [] pred){
    boolean visited [] = new boolean[numVertex]; // alla är obesökta från början
    // kö, snabb för att sätta in och ta ut O(1), vilket är allt som behövs
    LinkedList<Integer> queue = new LinkedList<>();
    queue.add(start);
    visited[start] = true;

    while (queue.size() != 0){
      int element = queue.poll();
      // Går igenom alla noder i grafen
      for (int i = 0; i < numVertex; i++) {
        // Om vi inte redan har besökt noden och vägen har ett flöde > 0
        if (!visited[i] && residual[element][i] > 0){
          queue.add(i);
          pred[i] = element; // sparar i vägen vi valde
          visited[i] = true; // markerar den besökt
        }
      }
    }

    // returnar true om vi har besökt sink noden
    return visited[end] == true ;
  }

  /**
   * Read a flowgraph from a file.
   */
  public static FlowGraph loadFlowGraph(Path path) throws IOException {
    // TODO(D3): läs in ett flödesnätverk från fil.
    // Filen börjar med ett heltal som anger antalet noder,
    // sedan följer ett tal m som är antalet bågar.
    // Resten av filen består av m rader där varje rad anger en båge i
    // formatet u v c som beskriver en båge från en nod u till v med kapacitet c.
    try(Reader in = Files.newBufferedReader(path)){
      Scanner scan = new Scanner(in);
      int vertexes = scan.nextInt();
      int arches = scan.nextInt();

      FlowEdge [] edges = new FlowEdge[arches];
      for (int i = 0; i < arches; i++) {
        int source = scan.nextInt();
        int dest = scan.nextInt();
        int capacity = scan.nextInt();
        if (capacity < 0)
          capacity = Integer.MAX_VALUE;

        edges[i] = new FlowEdge(source, dest, capacity);
      }

      return new FlowGraph(vertexes, edges);

    } catch (Exception e){
      throw  new IOException();
    }
  }
}
