package textproc;
import java.util.*;

public class MultiWordCounter implements TextProcessor {

    private Map<String, Integer> map;

    public MultiWordCounter(String[] words) {
        map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], 0);
        }
    }

    @Override
    public void process(String w) {
        for (String key: map.keySet()) {
            if (w.equals(key)){
                map.put(key, map.get(key) + 1);
            }
        }
    }

    @Override
    public void report(){
        System.out.println("MultiWordCounter:");

        Set<Map.Entry<String, Integer>> wordSet = map.entrySet();
        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
        wordList.sort(new AlphabeticalComparator());

        for (int i = 0; i < wordList.size(); i++) {
            System.out.println(wordList.get(i).getKey() + ": " + wordList.get(i).getValue());
        }

       /*for (String key: map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }*/
        System.out.println("\n");
    }
}
