package cards;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Daniel Jurca
 */
public class Deck {
    private ArrayList<Card> deck;
    private ArrayList<Card> restoreCards;

    public Deck() {
        deck = new ArrayList<Card>();
        restoreCards = new ArrayList<Card>();
        for(Suit s : Suit.values()){
            for(Face f : Face.values()){
                deck.add(new Card(s, f));
            }
        }
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public Card drawCard() {
        Card tmp = deck.remove(0);
        getRestoreCards().add(tmp);
        return tmp;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public ArrayList<Card> getRestoreCards() {
        return restoreCards;
    }

    public void setRestoreCards(ArrayList<Card> restoreCards) {
        this.restoreCards = restoreCards;
    }

    public void shuffleDeck() {
        Collections.shuffle(this.getDeck());
    }
}
