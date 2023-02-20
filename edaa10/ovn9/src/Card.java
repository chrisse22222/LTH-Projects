public class Card {
	public static final int SPADES = 1;
	public static final int HEARTS = SPADES + 1;
	public static final int DIAMONDS = SPADES + 2;
	public static final int CLUBS = SPADES + 3;
	private int rank;	// valör
	private int suit;   // färg
	
	/** Skapar ett spelkort med färgen suit (SPADES, HEARTS, DIAMONDS, CLUBS) 
	    och valören rank (1-13) */
	public Card(int suit, int rank) {
		this.suit = suit;
		this.rank = rank;
	}

	/** Tar reda på färgen */
	public int getSuit() {
		return suit;
	}

	/** Tar reda på valören */
	public int getRank() {
		return rank;
	}

	/** Returnerar en läsbar representation av kortet, t ex "spader ess" */
	public String toString(){
		String suitString = "";		
		switch(suit) {
			case SPADES: suitString = "spader"; break;
			case HEARTS: suitString = "hjärter"; break;
			case DIAMONDS: suitString = "ruter"; break;
			case CLUBS: suitString = "klöver"; break;
		}
		String rankString = "";
		switch(rank) {
			case 1: rankString = "ess"; break;
			case 11: rankString = "knekt"; break;
			case 12: rankString = "dam"; break;
			case 13: rankString = "kung"; break;
			default: rankString = String.valueOf(rank);
		}
		return suitString + " " + rankString;
	}
}