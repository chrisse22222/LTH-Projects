package textproc;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BookReaderApplication {

    public static void main (String[] args) throws FileNotFoundException {

        Scanner scan = new Scanner(new File("C:\\Users\\Christoffer\\IdeaProjects\\edaa30-workspace\\lab1\\undantagsord.txt"));
        Set<String> stopWords = new HashSet<>();

        while (scan.hasNext()) {
            stopWords.add(scan.next().toLowerCase());
        }

        GeneralWordCounter general = new GeneralWordCounter(stopWords);
        Scanner s = new Scanner(new File("C:\\Users\\Christoffer\\IdeaProjects\\edaa30-workspace\\lab1\\nilsholg.txt"));
        s.findWithinHorizon("\uFEFF", 1);
        s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

        while (s.hasNext()) {
            String word = s.next().toLowerCase();
            general.process(word);
        }

        s.close();
        new BookReaderController(general);
    }
}
