package textproc;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.StandardSocketOptions;
import java.util.*;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {

		long t0 = System.nanoTime();

		Scanner scan = new Scanner(new File("C:\\Users\\Christoffer\\IdeaProjects\\edaa30-workspace\\lab1\\undantagsord.txt"));
		Set<String> stopWords = new HashSet<>();

		while (scan.hasNext()) {
			stopWords.add(scan.next().toLowerCase());
		}

		ArrayList<TextProcessor> r = new ArrayList<>();
		r.add(new SingleWordCounter("nils"));
		r.add(new MultiWordCounter(REGIONS));
		r.add((new GeneralWordCounter(stopWords)));

		Scanner s = new Scanner(new File("C:\\Users\\Christoffer\\IdeaProjects\\edaa30-workspace\\lab1\\nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			for (int i = 0; i < r.size(); i++) {
				r.get(i).process(word);
			}
		}

		s.close();
		for (int i = 0; i < r.size(); i++) {
			r.get(i).report();
		}
		long t1 = System.nanoTime();
		System.out.println("tid: " + (t1 - t0) / 1000000.0 + " ms");
		// Hashmap 167 ms i median, 100 simuleringar med 1000 utskrifter i GeneralWord
		// Treemap 172 ms i median, 100 simuleringar med 1000 utskrifter i GeneralWord
		// Med Treemap påverkas inte ordningen och programmet fungerar fortfarande, exekveringstiden blir längre.
		// Treemap lägger in elementen sorterade efter naturlig ordning, vilket tar längre tid utöver att min
		// comparator sorterar listan en gång till gör att vi får en längre exekveringstid.
	}
}