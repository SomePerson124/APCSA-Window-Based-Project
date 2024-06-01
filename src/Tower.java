import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tower {

    private BufferedImage image;
    private int towerHealth;
    private int towerDamage;
    private int attackSpeed;
    private int attackRange;
    private int xCoord;
    private int yCoord;

    public Tower(int x, int y, String type) {
        try {
            if (type.equals("enemy")) {
                image = ImageIO.read(new File("src/Assets/enemytower.png"));
            } else {
                image = ImageIO.read(new File("src/Assets/tower.png"));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        towerHealth = 4000;
        towerDamage = 150;
        attackSpeed = 800;
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

    public Rectangle towerRect() {
        int imageWidth = getImage().getWidth();
        int imageHeight = getImage().getHeight();
        Rectangle rect = new Rectangle(xCoord, yCoord, imageWidth, imageHeight);
        return rect;
    }

}