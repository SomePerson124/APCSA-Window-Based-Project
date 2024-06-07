import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainTower {

    private BufferedImage image;
    private Image healthBar;
    private int towerHealth;
    private int towerDamage;
    private int attackSpeed;
    private int attackRange;
    private int originalHealth;
    private int xCoord;
    private int yCoord;

    public MainTower(int x, int y, String type) {
        try {
            if (type.equals("enemy")) {
                image = ImageIO.read(new File("src/Assets/enemymaintower.png"));
            } else {
                image = ImageIO.read(new File("src/Assets/maintower.png"));
            }
            healthBar = ImageIO.read(new File("src/Assets/healthbar.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        towerHealth = 6000;
        towerDamage = 150;
        attackSpeed = 1000;
        originalHealth = 6000;
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

    public Image getHealthBar() {
        return healthBar;
    }

    public int getTowerHealth() {
        return towerHealth;
    }

    public void loseTowerHealth(int hp) {
        towerHealth -= hp;
        setHealthBar(hp);
    }

    public void setHealthBar(int healthLost) {
        int scale = originalHealth / 30;
        int healthLostScaled = healthLost / scale;
        int width = healthBar.getWidth(null) - healthLostScaled;
        if (width <= 0) {
            width = 1;
        }
        Image healthNew = healthBar.getScaledInstance(width, healthBar.getHeight(null), Image.SCALE_DEFAULT);
        healthBar = healthNew;
    }

    public Rectangle mainTowerRect() {
        int imageWidth = getImage().getWidth();
        int imageHeight = getImage().getHeight();
        Rectangle rect = new Rectangle(xCoord, yCoord, imageWidth, imageHeight);
        return rect;
    }

}