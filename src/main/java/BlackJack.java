import cards.Card;
import cards.Deck;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Scanner;

import java.util.ArrayList;

/**
 * @author Daniel Jurca
 */
public class BlackJack {
    public static final int BLACKJACK = 21;
    public static final int DEALER_LIMIT = 17;

    private Dealer dealer;
    private ArrayList<Player> players;
    private Deck deck;

    public BlackJack() {
        this.dealer = new Dealer();
        this.players = new ArrayList<Player>();

    }

    public void startGame(){
        printWelcome();
        System.out.println("\n\t\t\t***GAME SETUP***");
        deckMaker();
        playerMaker();
        pressEnter();
        System.out.println("\n\t\t\t***ROUND STARTED***");
        initPlayersCards();
        pressEnter();
        playerTurn();
        dealerTurn();
        System.out.println("\n\t\t\t***ROUND ENDED***");
        pressEnter();
        System.out.println("===========================================================================");
        System.out.println("\n\t\t\t***RESULTS***");
        printResults();
        getDeck().getDeck().addAll(getDeck().getRestoreCards());

    }

    public void pressEnter(){
        System.out.println("PRESS ENTER TO CONTINUE...");
        Scanner reader = new Scanner(System.in);
        reader.nextLine();
    }

    public void printResults(){
        System.out.println("DEALER("+ getDealer().getCount() + ")");
        System.out.println("\nPLAYERS POINTS:");
        for(Player p : getPlayers()){
            StringBuilder sb = new StringBuilder().append(p.getName()).append("("+ p.getCount() + ")");
            sb.append(" -> ");
            if(p.isSurrender())sb.append("SURRENDER");
            else if(p.getCount() == getDealer().getCount() && !p.isLost()){
                sb.append("DRAW");
            }
            else if(!p.isLost() && ((getDealer().getCount() < p.getCount()) || getDealer().getCount() > BLACKJACK )){
                sb.append("WIN");
            }
            else if(p.isLost() || (getDealer().getCount() <= BLACKJACK && getDealer().getCount() > p.getCount())){
                sb.append("LOSE");
            }
            System.out.println(sb);

        }
    }


    public void playerTurn(){
        for(Player player : getPlayers()) {
            int round = 1;
            while(!player.isStop() && !player.isSurrender()){
                System.out.println("\n\t\t\t***TURN " + player.getName().toUpperCase() + "***");
                player.printPlayerStatus();
                if(round == 1){
                    System.out.println("\nSurrender (yes/no)?");
                    Scanner reader = new Scanner(System.in);
                    String sur = reader.nextLine();
                    boolean inputOk = false;
                    boolean toSurrender = false;
                    while (!inputOk) {
                        try {
                            if (inputParser(sur)) {
                                toSurrender = true;
                            } else toSurrender = false;
                            inputOk = true;
                        } catch (IllegalArgumentException e) {
                            inputOk = false;
                            e.printStackTrace();
                        }
                    }
                    if(toSurrender){
                        player.setSurrender();
                        break;
                    }
                }
                Scanner reader = new Scanner(System.in);
                boolean isOk = false;
                boolean nextCard = false;
                while (!isOk) {
                    System.out.println("\nDo you want another card (yes/no)?");
                    String yesNo = reader.nextLine();
                    try {
                        if (inputParser(yesNo)) {
                            nextCard = true;
                        } else {
                            nextCard = false;
                        }
                        isOk = true;
                    } catch (IllegalArgumentException e) {
                        isOk = false;
                        e.printStackTrace();
                    }
                }
                if(nextCard){
                    Card card = getDeck().drawCard();
                    player.addCarToHand(card);
                }
                else{
                    player.setStop();
                }
                if(player.getCount() > BLACKJACK) {
                    player.setStop();
                    player.setLost();
                }
                round++;

            }
            player.printPlayerStatus();
            if(player.isLost()){
                System.out.println(player.getName() + " LOST");
            }
            pressEnter();
        }
    }


    public void dealerTurn(){
        while(!getDealer().isStop()){
            System.out.println("\n\t\t\t***TURN DEALER***");
            getDealer().printPlayerStatus();
            if(getDealer().getCount() >= DEALER_LIMIT){
                break;
            }
            Card card = getDeck().drawCard();
            getDealer().addCarToHand(card);
            }

        }


    public void playerMaker(){
        boolean wantAddNextPlayer = true;
        int counterPlayer = 1;
        while(wantAddNextPlayer){
            Scanner reader = new Scanner(System.in);
            System.out.print("Please enter player " + counterPlayer + " name: ");
            String name = reader.nextLine();
            getPlayers().add(new Player(name));
            boolean isOk = false;
            while(!isOk){
                System.out.println("\nDo you want to add another player (yes/no)?");
                String yesNo = reader.nextLine();
                try{
                    if(inputParser(yesNo)){
                        wantAddNextPlayer = true;
                        isOk = true;
                    }
                    else{
                        wantAddNextPlayer = false;
                        isOk = true;
                    }
                }catch (IllegalArgumentException e){
                    isOk = false;
                    e.printStackTrace();
                }
            }
            counterPlayer++;

        }
        System.out.println("Players playing (" + getPlayers().size() + "):");
        for(Player p : getPlayers()){
            System.out.println(p.getName());
        }

    }

    public boolean inputParser(String input){
        input = input.trim().toLowerCase();
        switch (input){
            case "yes":
                return true;
            case "y":
                return true;
            case "no":
                return false;
            case "n":
                return false;
            default:
                throw new IllegalArgumentException("Bad input: " + input);
        }
    }

    public void initPlayersCards(){
        Card card = getDeck().drawCard();
        getDealer().addCarToHand(card);
        getDealer().printDealerStatus();
        for(Player p : getPlayers()){
            Card first = getDeck().drawCard();
            Card second = getDeck().drawCard();
            p.addCarToHand(first);
            p.addCarToHand(second);
            p.printPlayerStatus();
        }
    }

    public void deckMaker(){
        this.deck = new Deck();
        deck.shuffleDeck();
    }

    public void printWelcome(){

        System.out.println(" _____________________________________________________________________");
        StringBuilder sb = new StringBuilder();
        sb.append("WELCOME TO BLACKJACK");
        System.out.println("|\t\t\t\t\t\t " + sb + "\t\t\t\t\t\t  |");
        System.out.println("|_____________________________________________________________________|");
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
