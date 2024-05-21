import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel implements KeyListener, MouseListener, ActionListener {

    private Elixir elixir;
    private ArrayList<Placeholder> placeholders;
    private Timer timer;

    public GraphicsPanel() {

        elixir = new Elixir();
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

        g.drawRect(250, 30, 65, 65);
        g.drawRect(50, 70, 50, 50); //draws enemy tower 1
        g.drawRect(382, 70, 50, 50); //draws enemy tower 2
        g.drawRect(50, 370, 50, 50); //draws tower 1
        g.drawRect(382, 370, 50, 50); //draws tower 2

        g.drawRect(0, 10, 50, 0); //measure to check for even distance from sides
        g.drawRect(375, 420, 0, 70); //measure to check for even distance from top & bottom sides

        g.drawRect(0, 230,500, 30); //draws bridge

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