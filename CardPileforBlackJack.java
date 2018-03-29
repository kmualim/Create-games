import java.util.Collections;
import java.util.ArrayList;
public class CardPileforBlackJack {
	
	private ArrayList<Card> cards;
	private int numCards;

	//a constructor
	public CardPile(){
		this.cards = new ArrayList<Card>(); 
		this.numCards = 0;
		
	}
	//method finds the smallest index i for which value
	//of cards[i] is equal to null and place c in that position
	public void addToBottom(Card c){
		cards.add(c);
		numCards++;		
			}
	
	//method returns boolean representing whether
	//card pile is empty
	public boolean isEmpty(){
		return cards.isEmpty();
	}
	//method takes input int and returns Card object
	//at that specific location
	//if i<cards.length && i>numCards, return null
	public Card get(int i){
		if(i<cards.size() && i>numCards){
			return null;
		}
		return this.cards.get(i);
		}
	//this method removes element located at i from private property cards
	//& returns its value
	public Card remove(int i){
		Card removed = cards.get(i);
		cards.remove(i);
		this.numCards--;
		return removed;
	}
	
	//this method takes input Suit enum and Value enum 
	//returns int representing index of where card with specified Suit and Value
	//can be found
	public int find(Suit s, Value v){
		for(int i=0;i<numCards;i++){
			if(cards.get(i).getSuit()==s && cards.get(i).getValue()==v){
					return i;
				}
			}
		return -1;
	}
	//this method returns a String representation of the CardPile
	public String toString(){
		String s = " ";
		for(int i=0;i<numCards;i++){
			String y = (i+1) + ".";
			s = s + y + cards.get(i)+ " ";
		}
		return s;
	}
	//this method creates a deck of size 52 which is filled with all the possible cards,
	//it then shuffles the deck and returns the shuffled deck
	public static CardPile makeFullDeck(){
		CardPile fullDeck =new CardPile();
		
			for(Value cardValue: Value.values()){
				for(Suit cardSuit: Suit.values()){
					fullDeck.addToBottom(new Card(cardSuit, cardValue));
					
				}
			}
			
		//}
		
		Collections.shuffle(fullDeck.cards);		
		return fullDeck;
		}
	//this method makes a full deck depending on the number of Players
	//if number of Players = 4, this method makes 4 copies of every card.
	public static CardPile makeFullDeck(int n){
		CardPile fullDeck = new CardPile();
		
		
		for(int i=0;i<n;i++){
			CardPile nFullDeck = new CardPile();
			nFullDeck = CardPile.makeFullDeck();
			
			fullDeck.cards.addAll(nFullDeck.cards);
			fullDeck.numCards+=52;
		}
		Collections.shuffle(fullDeck.cards);
		return fullDeck;
	}
	//this method returns the number of Cards
	public int getnumCards(){
		int numCards =0;
		for(int i=0;i<cards.size();i++){
			if(cards.get(i)!=null){
				numCards++;
			}
		}
		return numCards;
	}
}
