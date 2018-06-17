import cards.Card;
import cards.Face;
import cards.Suit;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.*;

/**
 * @author Daniel Jurca
 */
public class PlayerTest {

    @Test
    public void addCarToHand() {
        Player p = new Player("Thomas");
        Card first = new Card(Suit.DIAMONDS, Face.QUEEN);
        Card second = new Card(Suit.HEARTS, Face.ACE);
        Card third = new Card(Suit.SPADES, Face.FOUR);
        p.addCarToHand(first);
        p.addCarToHand(second);
        p.addCarToHand(third);
        assertThat(p.getPlayerHand()).containsOnly(first, second, third);
    }

    @Test
    public void updateCount() {
        Player p = new Player("Eleven");
        assertThat(p.getCount()).isEqualTo(0);
        Card first = new Card(Suit.DIAMONDS, Face.QUEEN);
        Card second = new Card(Suit.HEARTS, Face.ACE);
        p.addCarToHand(first);
        assertThat(p.getCount()).isEqualTo(10);
        p.addCarToHand(second);
        assertThat(p.getCount()).isEqualTo(21);
    }
}