
public class CardPile {
	
	private Card[] cards;
	private int numCards;

	//a constructor
	public CardPile(){
		this.cards = new Card[52]; 
		this.numCards = 0;
		
	}
	//method finds the smallest index i for which value
	//of cards[i] is equal to null and place c in that position
	public void addToBottom(Card c){
		for(int i=0;i<52;i++){
			if(this.cards[i]==null){
				this.cards[i]=c;
				numCards++;
				break;
			}
		}
		
	}
	//method returns boolean representing whether
	//card pile is empty
	public boolean isEmpty(){
		boolean answer = true;
		
		for(int i=0;i<52;i++){
			if(cards[i]==null){
				continue;
				}
			else{
				return !answer;
			}
		}
		return answer;
	}
	//method takes input int and returns Card object
	//at that specific location
	//if i<cards.length && i>numCards, return null
	public Card get(int i){
		if(i<cards.length && i>numCards){
			return null;
		}
		return this.cards[i];
		}
	//this method removes element located at i from private property cards
	//& returns its value
	public Card remove(int i){
		Card removed = this.cards[i];
		Card[] newArray = new Card[52];
		int w=0;
		for(int j=0;j<cards.length;j++){
			if(i!=j){
				newArray[w]= cards[j];
				w++;
			}
			}
		this.numCards--;
		cards = newArray;
		return removed;
	}
	//this method takes input Suit enum and Value enum 
	//returns int representing index of where card with specified Suit and Value
	//can be found
	public int find(Suit s, Value v){
		for(int i=0;i<numCards;i++){
			if(cards[i].getSuit()==s && cards[i].getValue()==v){
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
			s = s + y + cards[i]+ " ";
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
		UtilityCode.shuffle(fullDeck.cards,52);		
		return fullDeck;
	}
	
}
