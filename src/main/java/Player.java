import cards.Card;
import cards.Face;

import java.util.ArrayList;

/**
 * @author Daniel Jurca
 */
public class Player{
    private ArrayList<Card> playerHand = new ArrayList<Card>();
    private final String playerName;
    private int count = 0;
    private boolean stop = false;
    private boolean lost = false;
    private boolean surrender = false;


    public Player(String name){
        this.playerName = name;
    }

    public String getName(){
        return this.playerName;
    }

    public boolean isSurrender() {
        return surrender;
    }

    public void setSurrender() {
        this.surrender = true;
    }

    public boolean isLost() {
        return lost;
    }

    public void setLost() {
        this.lost = true;
    }

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    public ArrayList<Card> addCarToHand(Card card){
        playerHand.add(card);
        updateCount();
        return this.playerHand;
    }

    public void updateCount(){
        ArrayList<Card> ace = new ArrayList<Card>();
        int tmpCount = 0;
        for(Card c : getPlayerHand()){
            if(c.getFace() != Face.ACE){
                tmpCount += c.getValue();
            }
            else{
                ace.add(c);
            }
        }
        for(Card a : ace){
            if(tmpCount + a.getValue() > BlackJack.BLACKJACK){
                tmpCount += 1;
            }
            else{
                tmpCount += a.getValue();
            }
        }
        setCount(tmpCount);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\t--").append(playerName).append("--\n");
        sb.append("Count: ").append(count).append('\n');
        sb.append("Cards: \n");
        for(Card c : getPlayerHand()){
            sb.append(c.stringToPicture());
            sb.append("\n");
        }
        /*for(int i = 0; i < getPlayerHand().size(); i++) {
            sb.append(getPlayerHand().get(i).toString());
            if (i != getPlayerHand().size() - 1){
                sb.append(", ");
            }
        }
        */
        sb.append("\n---------------------------------------------------------------------------");
        return sb.toString();
    }

    public void printPlayerStatus(){
        System.out.println(this.toString());
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop() {
        this.stop = true;
    }
}

