import java.awt.*;

public class Watermelon extends Card {

    public Watermelon(String gameFile, int x, int y) {
        super("Watermelon", gameFile, "src/Assets/watermelon.png", x, y, 4500, 370, 2, 5, 0.1);
    }

    @Override
    public Rectangle rangeRect() {
        int rangeWidth = 40;
        int rangeHeight = 40;
        Rectangle rect = new Rectangle(getxCoord() - ((rangeWidth - getGameImage().getWidth()) / 2), getyCoord() - ((rangeWidth - getGameImage().getWidth()) / 2), rangeWidth, rangeHeight);
        return rect;
    }

}