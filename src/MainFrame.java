import javax.swing.*;

public class MainFrame implements Runnable {

    private String title;
    private int windowWidth;
    private int windowHeight;
    private GraphicsPanel mainPanel;

    public MainFrame() {

        title = "Game";
        windowWidth = 500;
        windowHeight = 700;
        JFrame mainFrame = new JFrame(title);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(windowWidth, windowHeight);
        mainFrame.setLocationRelativeTo(null);

        mainPanel = new GraphicsPanel();
        mainFrame.add(mainPanel);

        mainFrame.setVisible(true);

        Thread thread = new Thread(this);
        thread.start();

    }

    public void run() {
        while (true) {
            mainPanel.repaint();
        }
    }

}