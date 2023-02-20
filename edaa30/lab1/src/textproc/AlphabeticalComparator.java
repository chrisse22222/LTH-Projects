package textproc;

import java.util.Comparator;
import java.util.Map;

public class AlphabeticalComparator implements Comparator<Map.Entry<String, Integer>> {

    @Override
    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        return o1.getKey().charAt(0) - o2.getKey().charAt(0);
    }
}
