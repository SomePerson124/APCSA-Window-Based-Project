import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Card {

    private String troop;
    private BufferedImage gameImage;
    private BufferedImage cardImage;
    private int health;
    private int damage;
    private int range;
    private int elixirCost;
    private double xCoord;
    private double yCoord;

    public Card(String troop, String gameFile, String cardFile, int x, int y, int health, int damage, int range, int elixirCost) {
        this.troop = troop;
        try {
            gameImage = ImageIO.read(new File(gameFile));
            cardImage = ImageIO.read(new File(cardFile));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.health = health;
        this.damage = damage;
        this.range = range;
        this.elixirCost = elixirCost;
        xCoord = x;
        yCoord = y;
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
    }

}