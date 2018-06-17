import cards.Card;

/**
 * @author Daniel Jurca
 */
public class Dealer extends Player {
    public Dealer() {
        super("Computer Dealer");
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\t--DEALER--").append('\n');
        sb.append("Count: ").append(super.getCount()).append('\n');
        sb.append("Cards: \n");
        for(Card c : getPlayerHand()){
            sb.append(c.stringToPicture());
            sb.append("\n");
        }
        /*
        for(int i = 0; i < getPlayerHand().size(); i++) {
            sb.append(getPlayerHand().get(i).toString());
            if (i != getPlayerHand().size() - 1){
                sb.append(", ");
            }
        }*/
        sb.append("\n---------------------------------------------------------------------------");
        return sb.toString();
    }

    public void printDealerStatus(){
        this.printPlayerStatus();
    }
}
