
public class CardGame {

	public static void main(String[] args) {
		
		//ask for input of players
		int numPlayers = 3;
		//int numPlayers = Integer.parseInt(args[0]);
		CardPile fullDeck = new CardPile();
		//creates a handDeck for each player
		CardPile[] handDeck = new CardPile[numPlayers];
		//creates a full Deck
		fullDeck = CardPile.makeFullDeck();
		//System.out.println(fullDeck); (this is to check for makeFullDeck() method
		//creates decks for each player
		for(int i=0;i<numPlayers;i++){
			handDeck[i]= new CardPile();
		}
		
		//remove cards from full deck until full deck is empty
		//put the cards in each players' hand one after another
		for(int i=0;i<52;i++){
			if(!fullDeck.isEmpty()){
			handDeck[i%numPlayers].addToBottom(fullDeck.remove(0));
			}
			else if (fullDeck.isEmpty()){
				break;
				
			}
		}
		//this finds the player with the Ace of Spades 
		//and declares them the winner
		for(int j=0;j<numPlayers;j++){
		 if(handDeck[j].find(Suit.SPADES, Value.ACE) != -1){
			System.out.println("The winner is Player " + j);
					}
				}	
		//System.out.println(handDeck[0]);
			}
	}


