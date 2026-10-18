import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class PlayerMaintains4CardsTest {
    CardGame game;
    CardGame.DrawDiscardThread thread1;
    CardGame.DrawDiscardThread thread2;
    CardGame.DrawDiscardThread thread3;
    CardGame.DrawDiscardThread thread4;

    @BeforeEach
    void setUp() throws Exception{
        game = new CardGame();
        game.quickInitialize(4);
        game.validateAndLoadPack(4, "cardPackFile4.txt");
        game.distributeCards();
    }

    @Test
    void playerOneDrawDiscardTest(){
        try {
            game.makeThreads();
            System.out.println(game.getPlayer(0).getHand());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
