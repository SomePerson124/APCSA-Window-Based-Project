import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel implements KeyListener, MouseListener, ActionListener {

    private boolean[] elixir;
    private int elixirTracker;
    private ArrayList<Placeholder> placeholders;
    private Timer timer;

    public GraphicsPanel() {

        elixirTracker = 0;
        elixir = new boolean[10];
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
        g.drawRect(10, 540, 30, 40);

        for (int i = 50; i <= 350; i += 100) {
            g.drawRect(i, 500, 80, 100);
        }

        for (int i = 40; i < 440; i += 40) {
            g.drawRect(i, 615, 40, 30);
        }

        for (int i = 0; i < elixir.length; i++) {
            if (elixir[i]) {
                g.setColor(Color.MAGENTA);
                g.fillRect((i + 1) * 40, 615, 40, 30);
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
        if (e.getSource() instanceof Timer && elixirTracker < 10) {
            elixir[elixirTracker] = true;
            elixirTracker++;
        }
    }

}