
public class Card {

	private Suit suit;
	private Value value;
	
//constructor
	public Card(Suit theSuit, Value theValue){
		this.suit = theSuit;
		this.value = theValue;
	}
	//getter methods for Suit & value
	public Suit getSuit(){
		return this.suit;
	}
	public Value getValue(){
		return this.value;
	}
	public String toString(){
		return this.value + " of " + this.suit;
	}
}
