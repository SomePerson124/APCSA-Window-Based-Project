import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Card {

    private BufferedImage image;
    private BufferedImage[] cards;
    private ArrayList<Integer> used;
    private int xCoord;
    private int yCoord;

    public Card(int x, int y, String png) {
        cards = new BufferedImage[5];
        used = new ArrayList<Integer>();
        int random = (int) (Math.random() * 5) + 1;
        for (int i = 0; i < cards.length; i++) {
            used.add(random);
            try {
                if (random == 1) {
                    cards[i] = ImageIO.read(new File("src/Assets/log.png"));
                    System.out.println("log");
                } else if (random == 2) {
                    cards[i] = ImageIO.read(new File("src/Assets/corn.png"));
                    System.out.println("corn");
                } else if (random == 3) {
                    cards[i] = ImageIO.read(new File("src/Assets/apple.png"));
                    System.out.println("apple");
                } else if (random == 4) {
                    cards[i] = ImageIO.read(new File("src/Assets/watermelon.png"));
                    System.out.println("watermelon");
                } else {
                    cards[i] = ImageIO.read(new File("src/Assets/orange.png"));
                    System.out.println("orange");
                }
                random = (int) (Math.random() * 5) + 1;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        xCoord = x;
        yCoord = y;
    }



}
