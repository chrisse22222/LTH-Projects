package map;

import java.util.HashSet;
import java.util.Random;

public class Main {
    public static void main (String [] args){
        SimpleHashMap<Integer, Integer> map = new SimpleHashMap<>(10);
        Random random = new Random(420);
        for (int i = 0; i < 1000; i++) {
            int randomNumber = random.nextInt();
           map.put(randomNumber, randomNumber);
       }

        System.out.println(map.show());
    }
}
