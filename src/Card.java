import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Card {

    private BufferedImage[] cards;
    private ArrayList<Integer> cardNums;
    private int xCoord;
    private int yCoord;

    public Card(int x, int y) {
        cards = new BufferedImage[5];
        cardNums = new ArrayList<Integer>();
        randomArray();
        for (int i = 0; i < cardNums.size(); i++) {
            try {
                if (cardNums.get(i) == 1) {
                    cards[i] = ImageIO.read(new File("src/Assets/log.png"));
                } else if (cardNums.get(i) == 2) {
                    cards[i] = ImageIO.read(new File("src/Assets/corn.png"));
                } else if (cardNums.get(i) == 3) {
                    cards[i] = ImageIO.read(new File("src/Assets/apple.png"));
                } else if (cardNums.get(i) == 4) {
                    cards[i] = ImageIO.read(new File("src/Assets/watermelon.png"));
                } else {
                    cards[i] = ImageIO.read(new File("src/Assets/orange.png"));
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        xCoord = x;
        yCoord = y;
    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public BufferedImage[] getCards() {
        return cards;
    }

    private void randomArray() {
        for (int i = 0; i < 5; i++) {
            int randInt = (int) (Math.random() * 5) + 1;
            while (!isAvailable(randInt)) {
                randInt = (int) (Math.random() * 5) + 1;
            }
            cardNums.add(randInt);
        }
    }

    private boolean isAvailable(int num) {
        for (int i = 0; i < cardNums.size(); i++) {
            if (cardNums.get(i) == num) {
                return false;
            }
        }
        return true;
    }

}
