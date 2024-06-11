import java.awt.*;

public class Orange extends Card {

    public Orange(String gameFile, int x, int y) {
        super("Orange", gameFile, "src/Assets/orange.png", x, y, 2000, 1050, 4, 0.2);
    }

    @Override
    public Rectangle rangeRect() {
        int rangeWidth = 30;
        int rangeHeight = 30;
        Rectangle rect = new Rectangle(getxCoord() - ((rangeWidth - getGameImage().getWidth()) / 2), getyCoord() - ((rangeWidth - getGameImage().getWidth()) / 2), rangeWidth, rangeHeight);
        return rect;
    }

}