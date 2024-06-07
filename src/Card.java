import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Card {

    private String troop;
    private BufferedImage gameImage;
    private BufferedImage cardImage;
    private int originalHealth;
    private int health;
    private int damage;
    private int range;
    private int elixirCost;
    private double xCoord;
    private double yCoord;
    private double movementSpeed;
    private Image healthBar;

    public Card(String troop, String gameFile, String cardFile, int x, int y, int health, int damage, int range, int elixirCost, double speed) {
        this.troop = troop;
        try {
            gameImage = ImageIO.read(new File(gameFile));
            cardImage = ImageIO.read(new File(cardFile));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        originalHealth = health;
        this.health = health;
        this.damage = damage;
        this.range = range;
        this.elixirCost = elixirCost;
        xCoord = x;
        yCoord = y;
        movementSpeed = speed;
        try {
            this.healthBar = ImageIO.read(new File("src/Assets/healthbar.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void moveUp() {
        yCoord -= movementSpeed;
    }

    public void moveDown() {
        yCoord += movementSpeed;
    }

    public void moveLeft() {
        xCoord -= movementSpeed;
    }

    public void moveRight() {
        xCoord += movementSpeed;
    }

    public String getTroop() {
        return troop;
    }

    public BufferedImage getGameImage() {
        return gameImage;
    }

    public BufferedImage getCardImage() {
        return cardImage;
    }

    public Image getHealthBar() {
        return healthBar;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }

    public int getElixirCost() {
        return elixirCost;
    }

    public int getxCoord() {
        return (int) xCoord;
    }

    public int getyCoord() {
        return (int) yCoord;
    }

    public void loseHealth(int hp) {
        health -= hp;
        setHealthBar(hp);
    }

    private void setHealthBar(int healthLost) {
        int scale = originalHealth / 30;
        int healthLostScaled = healthLost / scale;
        int width = healthBar.getWidth(null) - healthLostScaled;
        if (width <= 0) {
            width = 1;
        }
        Image healthNew = healthBar.getScaledInstance(width, healthBar.getHeight(null), Image.SCALE_DEFAULT);
        healthBar = healthNew;
    }

}