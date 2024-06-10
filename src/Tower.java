import org.w3c.dom.css.Rect;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tower {

    private BufferedImage image;
    private BufferedImage arrow;
    private Image healthBar;
    private int towerHealth;
    private int towerDamage;
    private int attackSpeed;
    private int attackRange;
    private int originalHealth;
    private int xCoord;
    private int yCoord;

    public Tower(int x, int y, String type) {
        try {
            if (type.equals("enemy")) {
                image = ImageIO.read(new File("src/Assets/enemytower.png"));
            } else {
                image = ImageIO.read(new File("src/Assets/tower.png"));
            }
            arrow = ImageIO.read(new File("src/Assets/arrow.png"));
            healthBar = ImageIO.read(new File("src/Assets/healthbar.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        towerHealth = 4000;
        towerDamage = 150;
        attackSpeed = 800;
        originalHealth = 4000;
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
    public BufferedImage getArrowImage() {
        return arrow;
    }

    public Image getHealthBar() {
        return healthBar;
    }

    public int getTowerHealth() {
        return towerHealth;
    }

    public int getTowerDamage() {
        return towerDamage;
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

    public Rectangle towerRect() {
        int imageWidth = getImage().getWidth();
        int imageHeight = getImage().getHeight();
        Rectangle rect = new Rectangle(xCoord, yCoord, imageWidth, imageHeight);
        return rect;
    }

    public Rectangle towerRange(int x, int y) {
        int rangeWidth = 240;
        int rangeHeight = 230;
        Rectangle rect = new Rectangle(x, y, rangeWidth, rangeHeight);
        return rect;
    }

    public Rectangle arrowRect(int x, int y) {
        int imageWidth = getArrowImage().getWidth();
        int imageHeight = getArrowImage().getHeight();
        Rectangle rect = new Rectangle(x, y, imageWidth, imageHeight);
        return rect;
    }

}