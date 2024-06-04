import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Card {

    private BufferedImage image;
    private ArrayList<BufferedImage> cards;
    private ArrayList<Integer> cardNums;
    private int xCoord;
    private int yCoord;

    public Card(int x, int y) {
        cards = new ArrayList<BufferedImage>();
        cardNums = new ArrayList<Integer>();
        xCoord = x;
        yCoord = y;
    }

    public void randomArray() {
        for (int i = 0; i < 5; i++) {
            int randInt = (int) (Math.random() * 5) + 1;
            while (!isAvailable(randInt)) {
                randInt = (int) (Math.random() * 5) + 1;
            }
            cardNums.add(randInt);
            System.out.println(cardNums.get(i));
        }
    }

    public boolean isAvailable(int num) {
        for (int i = 0; i < cardNums.size(); i++) {
            if (cardNums.get(i) == num) {
                return false;
            }
        }
        return true;
    }

}
