

public class Deck {

	public static final int SIZE = 52;
	public static final String[] SUITS = {"hearts", "clubs", "diamonds", "spades"};

	private int curr_index;
	private ArrayList<Card> cards;

	/** Populates the ArrayList cards with Card objects corresponding
	 ** to the SUITS and SIZE indicated above.
	 **/
	public Deck() {
		cards = new ArrayList<Card>();
		curr_index = 0;
		for (int i = 0; i < SUITS.length; i += 1) {
			for (int j = 1; j <= SIZE / SUITS.length; j += 1) {
				Card c = new Card(SUITS[i], j);
				cards.add(c);
			}
			
		}
	}

	/** Shuffles the ArrayList cards using Java Collections API
	 ** Brings all cards back into deck by making curr_index 0
	 **/
	public void Shuffle() {
		Collections.shuffle(cards);
		curr_index = 0;
	}

	/** Returns the next card in the deck and increments our pointer
	 ** tracking where we are in cards
	 **/
	public Card GetNextCard() {
		if (curr_index == SIZE - 1) {
			System.out.println("No cards are remaining!");
			return null;
		}
		Card c = cards.get(curr_index);
		curr_index += 1;
		return c;
	}

	public class Card {

		private String suit;
		private int num;

		public Card(String s, int n) {
			suit = s;
			num = n;
		}
	}
}