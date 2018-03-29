
import java.util.Scanner;
import java.util.ArrayList;
public class Blackjack {

	public static void main(String[] args) {
		//Kristy Mualim 260679030
		//initial chips 
		int initialChips = Integer.parseInt(args[0]);
		int finalChips = initialChips;
		CardPile deck = CardPile.makeFullDeck(4);
		//System.out.println(deck);
		Result result;
		
		//user does not have enough chips, game does not run!
		if(finalChips<=0){
			System.out.println("You have:" + finalChips + "chips. Game Over.");
		}
		//before each round, use Scanner object
		//obtain user's bet for this round - make sure
		//they only bet chips they have
		//if enter negative amount, leave game.
		
		while(deck.getnumCards()>10 && finalChips>0){
		Scanner input = new Scanner(System.in);
		System.out.println("Let's play some Blackjack!");
		System.out.println("Enter an integer for your bet. Entering a negative number will cause you to leave the game.");
		int bet = input.nextInt();
		
		while(bet>0){
			if(bet<=finalChips){
			//A round of blackjack executes!
			result = playRound(deck);
			
			//these below methods would change the number of chips according to the result
			//of each blackjack game
			if(result == Result.DEALER_WINS){
				finalChips -= bet;
				System.out.println("Your chips: " +finalChips);
				}
			else if(result==Result.BLACKJACK){
				finalChips = (int)(finalChips + bet*1.5);
				System.out.println("Your chips:" + finalChips);
			}
			else if(result==Result.PLAYER_WINS){
				finalChips += bet;
				System.out.println("Your chips:" + finalChips);
			}
			else if(result==Result.TIE){
				System.out.println("Your chips:" + finalChips);
			}
			}
			//if the bet is bigger than the chips the person has
			//then the user has to input another
			else if(bet>finalChips){
			System.out.println("Bet too big. Try again.");
			bet = input.nextInt();
			}
		}	
	}		//if deck has less than 10 cards, 
			//game does not execute
			if(deck.getnumCards()<10){
				System.out.println("We ran out of cards!");
		}
}
	
	//defining enum corresponding to different possible outcomes
	public enum Result{
		DEALER_WINS,
		PLAYER_WINS,
		TIE,
		BLACKJACK,
	}


	//this method takes as input a card object and return its score in Blackjack
	//Card value of ACE = 11
	public static int getScore(Card a){
		int Score = 0;
		//from 2-9, the value of card corresponds to it's number
		for(int i=0;i<9;i++){
			if(a.getValue()==Value.values()[i]){
				Score = i+2;
			}
		}
		//if JACK, QUEEN, KING shown, value of card =10
		for(int j=9;j<12;j++)
		if(a.getValue() == Value.values()[j]){
			Score = 10;
		}
		//if ACE shown, value of card = 11
		else if(a.getValue() == Value.ACE){
			Score = 11;
		}
		return Score;
		}
	
	//this method returns total blackjack score
	//this method returns the best possible score for a cardpile
	//"best" means as close to 21 as possible
	//ace can take the value of either 11 or 1
	public static int countValues(CardPile input){
		int sum = 0;
		for(int i=0;i<input.getnumCards();i++){
			sum += getScore(input.get(i));
		}
		//if sum is greater than 21, Ace would be 1 instead of 11
		for(int j=0;j<input.getnumCards(); j++){
			if(sum>21 && ((input.get(j)).getValue() == Value.ACE)){
			sum = sum -10;
			}	
		}
		return sum;
	}
	//this method shows the cards in the Player's hand so the user knows what the cards in his hand are
	//i thought this would make things easier
	public static String showHand(CardPile Player){
		String p =" ";
		for(int i=0;i<Player.getnumCards();i++){
			p = p + Player.get(i) + ",";
		}
		return p;
	}
	//this method executes one round of blackjack 
	//removes cards from the Cardpile that was input
	
	public static Result playRound(CardPile d){
		//defining card piles for each player
		CardPile Dealer = new CardPile();
		
		CardPile Player = new CardPile();
		
		//adds cards to player and dealer cardpiles from the deck alternatively
		Player.addToBottom((d.remove(0)));
		Dealer.addToBottom((d.remove(0)));
		Player.addToBottom((d.remove(0)));
		Dealer.addToBottom(d.remove(0));
		//this shows the first two cards dealt to the player 
		if(Player.getnumCards()>1){
			System.out.println("These are the cards in your hand: "+ showHand(Player));
		}
		//this shows the dealer's 2nd card
		if(Dealer.getnumCards()>1){
			System.out.println("Dealer's Second Card is: "+ d.remove(0));
		}

		//Play the game
		//if player hits black jack, but dealer doesn't get blackjack - player wins
		
		if(countValues(Dealer)!=21 && countValues(Player)==21){
				System.out.println("Player got Blackjack, baby!");
				return Result.BLACKJACK;
		}
		//if dealer hits blackjack & player doesn't, dealer wins
		else if(countValues(Dealer)==21&& countValues(Player)!=21){
			System.out.println("Dealer got Blackjack!");
			return Result.BLACKJACK;
		}
		//if both gets blackjack, it's a tie
		else if(countValues(Dealer)==21 && countValues(Player)==21){
			System.out.println("Both players got Blackjack?!?");
			return Result.TIE;
		}
		
		
		//if player hits, then they get additional card. 
		//if player stays, player's turn is over and dealer gets to play
		else{
			while(countValues(Player)<22){
			//print card, player can hit or stay
			//System.out.println("Player's Cards: " + Player + "and dealer's second card"+ Dealer.get(1));
				System.out.println("Hit/Stay?");
				Scanner input = new Scanner(System.in);
				//changes input to all lowercase
				String hitOrStay = input.next();
				//if the player wants to hit, a card is added 
				//and the current hand is shown
				if(hitOrStay.equalsIgnoreCase("hit")){
					Player.addToBottom(d.remove(0));
					System.out.println("Your current hand is" + showHand(Player));
			}
				//if the player wants to stay, current hand is shown.
				else if(hitOrStay.equalsIgnoreCase("stay")){
					System.out.println("Your current hand is:" + showHand(Player));
					break;
			}
				//if the input is not an integer, the game would ask the user for a proper input.
				else{
					System.out.println("We couldn't recognise your input. Please state if you'd like to Hit or Stay");		
				}
		}
			//dealer plays now
			//dealer continues to hit until he reaches value more than 18
			while(countValues(Dealer)<18){
				Dealer.addToBottom(d.remove(0));
				//if dealer gets value>21, player wins
				if(countValues(Dealer)>21 && countValues(Player)<21){
					System.out.println( "Dealer has busted, Player wins.");
					return Result.PLAYER_WINS;
				}
			}
		}
		//the possible outcomes, if dealer and player did not bust
		if(countValues(Player)<22 && countValues(Dealer)<22){
			if(countValues(Player)>countValues(Dealer)){
				//if the Player has a greater value than the Dealer
				//the player wins
				System.out.println("Player, you win! Congratulations!");
				return Result.PLAYER_WINS;
			}
			//if both parties have the same value, it's a tie
			else if(countValues(Player) == countValues(Dealer)){
				System.out.println("It's a tie, yay!");
				return Result.TIE;
			}
			else{
				//if the dealer has a greater value than the player
				//the dealer wins
				System.out.println("Dealer wins! Try again next time.");
				return Result.DEALER_WINS;
				}
			}
		//if the player has over 21, the dealer wins
		System.out.println("You busted, Dealer wins!");
		return Result.DEALER_WINS;
		}
	}


		

