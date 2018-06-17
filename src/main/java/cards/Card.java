package cards;

/**
 * @author Daniel Jurca
 */
public class Card
{
    private int value;
    private Suit suit;
    private Face face;

    public Card(Suit suit, Face face) {
        this.suit = suit;
        this.face = face;
        value = face.getValue();
    }

    public int getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    public Face getFace() {
        return face;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(face);
        sb.append(" of ").append(suit);
        return sb.toString();
    }

    public String stringToPicture(){
        final StringBuilder sb = new StringBuilder();
        String f = "";
        String s = "";
        switch (this.getFace()){
            case ACE:
                f = "A";
                break;
            case TWO:
            case THREE:
            case FOUR:
            case FIVE:
            case SIX:
            case SEVEN:
            case EIGHT:
            case NINE:
            case TEN:
                f = "" + this.getValue();
                break;
            case JACK:
                f = "J";
                break;
            case QUEEN:
                f = "Q";
                break;
            case KING:
                f = "K";
                break;
            default:
                break;
        }

        switch (this.getSuit()){
            case CLUBS:
                s = "♣";
                break;
            case HEARTS:
                s = "♥";
                break;
            case SPADES:
                s = "♠";
                break;
            case DIAMONDS:
                s = "♦";
                break;
            default:
                break;
        }
        if(f.compareTo("10") == 0){
            sb.append(" _____");
            sb.append("\n│" + f + "   │");
            sb.append("\n│  " + s + "  │");
            sb.append("\n│   " + f + "│");
            sb.append("\n ¯¯¯¯¯");
        }else{
            sb.append(" _____");
            sb.append("\n│" + f + "    │");
            sb.append("\n│  " + s + "  │");
            sb.append("\n│    " + f + "│");
            sb.append("\n ¯¯¯¯¯");
        }
        return sb.toString();
    }
}