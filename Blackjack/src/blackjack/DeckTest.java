package blackjack;

import java.util.ArrayList;
import java.util.List;

public class DeckTest {

	public static void main(String[] args) {
		
		int counter = 1;
		
		List<String> deck = DeckMan.createDeck();
		List<String> deck2 = DeckMan.createDeck();
		List<String> doubleDeck = new ArrayList<String>();
		
		doubleDeck.addAll(deck);
		doubleDeck.addAll(deck2);
		
		for(String d : deck){
			System.out.println(counter + "\t" + d);
			counter++;
		}
		

	}

}
