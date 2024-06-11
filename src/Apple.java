import java.awt.*;

public class Apple extends Card {

    public Apple(String gameFile, int x, int y) {
        super("Apple", gameFile, "src/Assets/apple.png", x, y, 2400, 280, 1, 3, 0.2);
    }

    @Override
    public Rectangle rangeRect() {
        int rangeWidth = 40;
        int rangeHeight = 40;
        Rectangle rect = new Rectangle(getxCoord() - ((rangeWidth - getGameImage().getWidth()) / 2), getyCoord() - ((rangeHeight - getGameImage().getWidth()) / 2), rangeWidth, rangeHeight);
        return rect;
    }

}