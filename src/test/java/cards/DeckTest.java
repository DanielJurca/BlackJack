package cards;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Daniel Jurca
 */
public class DeckTest {

    @Test
    public void drawCard() {
        Deck d = new Deck();
        Card c = d.getDeck().get(0);
        d.drawCard();
        assertThat(d.getDeck()).doesNotContain(c);
    }

    @Test
    public void shuffleDeck() {
        Deck d = new Deck();
        Card c = d.getDeck().get(0);
        d.shuffleDeck();
        assertTrue(d.getDeck().get(0) != c);
    }
}