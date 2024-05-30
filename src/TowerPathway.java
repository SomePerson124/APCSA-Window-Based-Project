import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TowerPathway {

    private BufferedImage image;
    private int xCoord;
    private int yCoord;

    public TowerPathway(int x, int y) {
        try {
            image = ImageIO.read(new File("src/Assets/towerpathway.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
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

    public BufferedImage getImage() {
        return image;
    }

    public Rectangle towerPathwayRect() {
        int imageWidth = getImage().getWidth();
        int imageHeight = getImage().getHeight();
        Rectangle rect = new Rectangle(xCoord, yCoord, imageWidth, imageHeight);
        return rect;
    }

}
