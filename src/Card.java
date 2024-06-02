import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Card {

    private BufferedImage image;
    private int xCoord;
    private int yCoord;

    public Card(int x, int y, String png) {
        try {
            image = ImageIO.read(new File(png));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        xCoord = x;
        yCoord = y;
    }



}
