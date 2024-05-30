import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel implements KeyListener, MouseListener, ActionListener {

    private Elixir elixir;
    private River river;
    private Bridge bridgeLeft;
    private Bridge bridgeRight;
    private TowerPathway towerPathway1;
    private TowerPathway towerPathway2;
    private TowerPathway enemyTowerPathway1;
    private TowerPathway enemyTowerPathway2;
    private ArrayList<Placeholder> placeholders;
    private Timer timer;

    public GraphicsPanel() {

        elixir = new Elixir();

        river = new River(0, 230);

        bridgeLeft = new Bridge(60, 230);
        bridgeRight = new Bridge(392, 230);

        towerPathway1 = new TowerPathway(60, 260);
        towerPathway2 = new TowerPathway(392, 260);
        enemyTowerPathway1 = new TowerPathway(60, 120);
        enemyTowerPathway2 = new TowerPathway(392, 120);

        placeholders = new ArrayList<>();
        timer = new Timer(2800, this);
        timer.start();

        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        requestFocusInWindow();

    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawString("Next: ", 10, 530);
        g.drawRect(10, 540, 30, 40); //draws border for next card
        g.drawRect(0, 490, 500, 0); //draws divisor for cards selection and main game

        g.drawRect(209, 20, 65, 65); //draws enemy main tower
        g.drawRect(50, 70, 50, 50); //draws enemy tower 1
        g.drawRect(382, 70, 50, 50); //draws enemy tower 2
        g.drawRect(209, 405, 65, 65); //draw main tower
        g.drawRect(50, 370, 50, 50); //draws tower 1
        g.drawRect(382, 370, 50, 50); //draws tower 2

        g.drawRect(90, 43, 119, 20); //draws enemy main tower pathway (left)
        g.drawRect(274, 43, 118, 20); //draws enemy main tower pathway (right)
        g.drawRect(60, 43, 30, 27); //draws enemy main tower pathway (down 1)
        g.drawRect(392, 43, 30, 27); //draws enemy main tower pathway (down 2)

        g.drawRect(60, 120, 30, 110); //draws enemy tower pathway 1
        g.drawRect(392, 120, 30, 110); //draws enemy tower pathway 2

        g.drawRect(90, 427, 119, 20); //draws main tower pathway (left)
        g.drawRect(274, 427, 118, 20); //draws main tower pathway (right)
        g.drawRect(60, 420, 30, 27); //draws main tower pathway (down 1)
        g.drawRect(392, 420, 30, 27); //draws main tower pathway (down 2)

        g.drawRect(60, 260, 30, 110); //draws tower pathway 1
        g.drawRect(392, 260, 30, 110); //draws tower pathway 2

        g.drawImage(towerPathway1.getImage(), towerPathway1.getxCoord(), towerPathway1.getyCoord(), null); //draws tower pathway 1
        g.drawImage(towerPathway2.getImage(), towerPathway2.getxCoord(), towerPathway2.getyCoord(), null); //draws tower pathway 2
        g.drawImage(enemyTowerPathway1.getImage(), enemyTowerPathway1.getxCoord(), enemyTowerPathway1.getyCoord(), null); //draws enemy tower pathway 1
        g.drawImage(enemyTowerPathway2.getImage(), enemyTowerPathway2.getxCoord(), enemyTowerPathway2.getyCoord(), null); //draws enemy tower pathway 2

        g.drawRect(0, 230,500, 30); //draws river
        g.drawRect(60, 230, 30, 30); //draws bridge (left)
        g.drawRect(392, 230, 30, 30); //draw bridge (right)

        g.drawImage(river.getImage(), river.getxCoord(), river.getyCoord(), null); //draws river
        g.drawImage(bridgeLeft.getImage(), bridgeLeft.getxCoord(), bridgeLeft.getyCoord(), null); //draws left bridge
        g.drawImage(bridgeRight.getImage(), bridgeRight.getxCoord(), bridgeRight.getyCoord(), null); //draws right bridge

        for (int i = 50; i <= 350; i += 100) {
            g.drawRect(i, 500, 80, 100); //draws card borders
        }

        for (int i = 40; i < 440; i += 40) {
            g.drawRect(i, 615, 40, 30); //draws elixir bar
        }

        for (int i = 0; i < elixir.getElixirBar().length; i++) {
            if (elixir.getElixirBar()[i]) {
                g.setColor(Color.MAGENTA);
                g.fillRect((i + 1) * 40, 615, 40, 30); //draws elixir
                g.setColor(Color.BLACK);
            }
        }

        for (int i = 0; i < placeholders.size(); i++) {
            Placeholder placeholder = placeholders.get(i);
            g.drawImage(placeholder.getImage(), placeholder.getxCoord(), placeholder.getyCoord(), null);
        }

    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}

    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            Point mouseClickLocation = e.getPoint();
            Placeholder placeholder = new Placeholder(mouseClickLocation.x, mouseClickLocation.y);
            placeholders.add(placeholder);
        }
    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Timer && elixir.getElixirAmt() < 10) {
            elixir.getElixirBar()[elixir.getElixirAmt()] = true; //generates elixir
            elixir.addElixir();
        }
    }

}