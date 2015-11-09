import java.util.Collections;
import java.util.ArrayList;

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
		if (curr_index == SIZE) {
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

		public String toString() {
			return Integer.toString(num) + ", " + suit;
		}
	}

	/** For testing **/
	public static void main(String[] args) {
		System.out.println("Testing Deck...");
		Deck deck = new Deck();
		System.out.println("Printing all cards in generated order.");
		ArrayList<Card> other_deck = new ArrayList<Card>();
		for (int i = 0; i < SIZE; i += 1) {
			Card c = deck.GetNextCard();
			System.out.println(c.toString());
			other_deck.add(c);
		}
		System.out.println();
		System.out.println("Testing pulling cards when deck is empty.");
		System.out.println("Should display error message.");
		deck.GetNextCard();
		System.out.println();
		System.out.println("Shuffling..");
		deck.Shuffle();
		System.out.println();
		System.out.println("Printing out cards in shuffled order.");
		System.out.println("Also checking to make sure all cards are accounted for.");
		for (int i = 0; i < SIZE; i += 1) {
			Card c = deck.GetNextCard();
			System.out.println(c.toString());
			if (!other_deck.contains(c)) {
				System.out.println("Error! A card has not been accounted for.");
			}
		}
		System.out.println("If there were no error messages, all cards have been accounted.");
	}
}