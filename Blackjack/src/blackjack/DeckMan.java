package blackjack;

import java.util.*;

public class DeckMan{
	static int hitVal;
	static int newHandVal;
	static int total = 0;
	
	public static List<String> createDeck() {
	
	int[] values = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
	String[] suits = {"hearts", "clubs", "diamonds", "spades"};
	List<String> deck = new ArrayList<>();
	

	for (String s : suits){
		for(int v = 1; v<values.length; v++){
			if (v == 1)
				deck.add("Ace of " + s);
			else if (v == 11)
				deck.add("Jack of " + s);
			else if (v == 12)
				deck.add("Queen of " + s);
			else if (v == 13)
				deck.add("King of " + s);
			else
				deck.add(v + " of " + s);
		}
		
	}
	Collections.shuffle(deck);
	return deck;
	}
	
	public static String dealCard(List<String> d){
		String card;
		card = d.get(0);
		d.remove(0);
		return card;
	}
	
	public static int getValue(String card){
		int cardVal;
		if (card.contains("Ace"))
			cardVal = 1;
		else if (card.contains("Jack") || card.contains("Queen") || card.contains("King"))
			cardVal = 10;
		else
			cardVal = Integer.parseInt(card.replaceAll("[\\D]", ""));
		return cardVal;
		
	}
	
	public static String getCards(List<String> hnd){
		String hand = "";
		for (String h : hnd){
			hand += (h + ", ");
		}
		return hand;
	}
	
	public static int checkAces(List<String> handA, int vals){
		int val = vals;
		for (String c : handA){
			if (c.contains("Ace") && vals < 12){
				val += 10;
			}
		}
		return val;
	}
	
	public static int dealerAI(List<String> rdeck, List<String> dhand, int dvals){
		int fval = dvals;
		while (checkAces(dhand, fval) < 17){
			String hit = dealCard(rdeck);
			dhand.add(hit);
			int hitVal = getValue(hit);
			System.out.println("\nDealer hits and gets " + hit);
			fval += hitVal;
		}
		fval = checkAces(dhand, fval);
		
		System.out.println("\nDealer's final hand: ");
		for (String cds : dhand){
			System.out.println(cds);
		}
		return fval;
	}
	
}