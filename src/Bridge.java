import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bridge {

    private BufferedImage image;
    private int xCoord;
    private int yCoord;

    public Bridge(int x, int y) {
        try {
            image = ImageIO.read(new File("src/Assets/bridge.png"));
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

}
