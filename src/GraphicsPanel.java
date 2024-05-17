import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel implements KeyListener, MouseListener, ActionListener {

    private int elixir;
    private ArrayList<Placeholder> placeholders;
    private Timer timer;

    public GraphicsPanel() {

        elixir = 0;

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
        if (e.getSource() instanceof Timer) {
            if (elixir < 10) {
                elixir++;
            }
            System.out.println(elixir);
        }
    }

}
