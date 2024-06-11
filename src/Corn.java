import java.awt.*;

public class Corn extends Card {

    public Corn(String gameFile, int x, int y) {
        super("Corn", gameFile, "src/Assets/corn.png", x, y, 780, 200, 7, 3, 0.2);
    }

    @Override
    public Rectangle rangeRect() {
        int rangeWidth = 100;
        int rangeHeight = 100;
        Rectangle rect = new Rectangle(getxCoord() - ((rangeWidth - getGameImage().getWidth()) / 2), getyCoord() - ((rangeWidth - getGameImage().getWidth()) / 2), rangeWidth, rangeHeight);
        return rect;
    }

}