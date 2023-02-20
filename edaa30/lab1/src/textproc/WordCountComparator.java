package textproc;

import java.util.Comparator;
import java.util.Map;

public class WordCountComparator implements Comparator<Map.Entry<String, Integer>> {
    @Override
    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        int value = o2.getValue() - o1.getValue();
        // Exempel o1 = 2, o2 = 4
        // o2 - o1 = 2
        // Då byter o2 plats med o1 (o2 är större).

        if (value == 0){ // Om orden förekommer lika ofta, sorteras de efter bokstavsordning
            value = o1.getKey().charAt(0) - o2.getKey().charAt(0);
            // Första bokstaven i o1 är a = 141 i ASCII, Första i o2 är "b" = 142 i ASCII
            // 141 - 142 = -1;
            // Då byter inte o1 plats med o2, dvs A är före B i alfabetet.
        }

        return value;
    }
}
