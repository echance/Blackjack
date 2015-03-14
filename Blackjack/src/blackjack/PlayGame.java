package blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayGame {

	public static void main(String[] args) {
		
		List<String> deck = new ArrayList<>();
		List<String> handArr = new ArrayList<>();
		List<String> dealHand = new ArrayList<>();
		int dealVal, handVal, chips = 1000;
		boolean cont = true;
		Scanner userIn = new Scanner(System.in);
		String hit, hand, con;
		
		deck = DeckMan.createDeck();
		
		while (cont == true){
			
			handArr.clear();
			dealHand.clear();
			handVal = 0;
			dealVal = 0;
			
			System.out.println("\nCurrent number of chips: " + chips);
			System.out.print("Place your bet: ");
			int bet = userIn.nextInt();
			chips -= bet;
			
			//deal player and dealer hands
			System.out.println("\nDealing cards...");
			handArr.add(DeckMan.dealCard(deck));
			dealHand.add(DeckMan.dealCard(deck));
			handArr.add(DeckMan.dealCard(deck));
			dealHand.add(DeckMan.dealCard(deck));
			
			handVal = DeckMan.getValue(handArr.get(0)) + DeckMan.getValue(handArr.get(1));
			dealVal = DeckMan.getValue(dealHand.get(0)) + DeckMan.getValue(dealHand.get(1));
			
			//Show initial player hand and dealer's second card
			System.out.printf("\nYour cards are %s and %s.", handArr.get(0), handArr.get(1));
			System.out.println("\nDealer has " + dealHand.get(1));
			
			System.out.println("\nWould you like to hit? (y/n)");
			hit = userIn.next();
			if (hit.equals("y")){
				do{
					handArr.add(0, DeckMan.dealCard(deck));
					System.out.println("You are dealt: " + handArr.get(0));
					handVal += DeckMan.getValue(handArr.get(0));
	
					if (handVal > 21)
						break;
					System.out.println("Again? (y/n)");
					hit = userIn.next();
				} while (hit.equals("y"));
				
				hand = DeckMan.getCards(handArr);
				handVal = DeckMan.checkAces(handArr, handVal);
				System.out.println("Final hand: " + hand + " for a value of " + handVal);
				if (handVal > 21){
					System.out.println("You busted! Say goodbye to your chips.\n");
				}
			}
			else{
				hand = DeckMan.getCards(handArr);
				handVal = DeckMan.checkAces(handArr, handVal);
				System.out.println("Final hand: " + hand + " for a value of " + handVal);
			}
			
			dealVal = DeckMan.dealerAI(deck, dealHand, dealVal);
			
			//Check the winner, award/take away chips.
			if (handVal > dealVal && handVal <= 21){
				System.out.printf("\nFinal score: \nPlayer: %d. \nDealer: %d", handVal, dealVal);
				System.out.println("\nYou Win!!");
				chips += bet*2;
			}
			else if (handVal <= 21 && dealVal > 21){
				System.out.printf("\nFinal score: \nPlayer: %d. \nDealer: %d", handVal, dealVal);
				System.out.println("\nDealer busts! You Win!!");
				chips += bet*2;
			}
			else if (handVal > 21){
				System.out.printf("\nFinal score: \nPlayer: %d. \nDealer: %d", handVal, dealVal);
				System.out.println("\nYou busted! Loser!");
			}
			else if (handVal == dealVal){
				System.out.printf("\nFinal score: \nPlayer: %d. \nDealer: %d", handVal, dealVal);
				System.out.println("\nDraw!!");
				chips += bet;
			}
			else {
				System.out.printf("\nFinal score: \nPlayer: %d. \nDealer: %d", handVal, dealVal);
				System.out.println("\nYou Lose!!");
			}
			
			System.out.println("\nContinue? (y/n)");
			con = userIn.next().toLowerCase();
			if (con.equals("n") || (con.equals("no")))
				cont = false;

		}
		
		System.out.println("Game over. Final chip count: " + chips + " chips");
		userIn.close();

	}

}
