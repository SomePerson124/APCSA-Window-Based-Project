import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class River {

    private BufferedImage image;
    private int xCoord;
    private int yCoord;

    public River(int x, int y) {
        try {
            image = ImageIO.read(new File("src/Assets/river.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        xCoord = x;
        yCoord = y;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public Rectangle riverRect() {
        int imageWidth = getImage().getWidth();
        int imageHeight = getImage().getHeight();
        Rectangle rect = new Rectangle(xCoord, yCoord, imageWidth, imageHeight);
        return rect;
    }

}