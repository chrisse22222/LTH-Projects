package lab3;

import graph.Graph;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WordGraph implements Graph<String> {
  private final Map<String, Set<String>> graph = new HashMap<>();

  public WordGraph(Path wordfile, WordCriteria criteria) throws IOException {
    // TODO(D1): load all words from file (wordfile).
    // TODO(D3): compute word neighbours (according to criteria).

    try(Reader in = Files.newBufferedReader(wordfile)){
      Scanner scan = new Scanner(in);
      while(scan.hasNext()){
        graph.put(scan.nextLine(), new HashSet<String>()); // Lägger in orden i grafen, varje ord en nod
      }
    }catch(Exception e){
        throw new IOException();
      }
    // Jämför word1 med resterande ord, sedan jämförs nästa ord med resterande ord...
    for (String word1 : graph.keySet()){
      for (String word : graph.keySet()){
        if (criteria.adjacent(word1, word)){ // om de uppfyller villkoren
          graph.get(word1).add(word);
        }
      }
    }
  }

  @Override public int vertexCount() {
    // TODO(D1): implement this!
    return graph.size();
  }

  @Override public Collection<String> vertexSet() {
    // TODO(D1): implement this!
    return graph.keySet();
  }

  @Override public Collection<String> neighbours(String v) {
    return graph.getOrDefault(v, Collections.emptySet());
  }
}
