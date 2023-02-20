package textproc;
import java.util.*;

public class GeneralWordCounter implements TextProcessor {

    private Set <String> s;
    private Map<String, Integer> map;
    private int shownWords = 100;
    public GeneralWordCounter(Set <String> s){
        this.s = s;
        map = new HashMap<>();
    }

    @Override
    public void process(String w) {
        if (!s.contains(w)){
            map.put(w, map.getOrDefault(w, 0) + 1);
        }
    }

    @Override
    public void report() {

        System.out.println("GeneralWordCounter: ");
        Set<Map.Entry<String, Integer>> wordSet = map.entrySet();
        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
        wordList.sort(new WordCountComparator());

        if (shownWords > wordList.size()){
            shownWords = wordList.size();
        }

        for (int i = 0; i <= shownWords; i++) {
            System.out.println(wordList.get(i).getKey() + ": " + wordList.get(i).getValue());
        }

        System.out.println("\n");
    }

    public List<Map.Entry<String, Integer>> getWordList(){
        return new ArrayList<>(map.entrySet());
    }
}
