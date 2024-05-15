import javax.swing.*;

public class MainFrame implements Runnable {

    private String title;
    private int windowWidth;
    private int windowHeight;
    private GraphicsPanel mainPanel;

    public MainFrame() {

        title = "Game";
        windowWidth = 500;
        windowHeight = 500;
        JFrame mainFrame = new JFrame();

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle(title);
        mainFrame.setSize(windowWidth, windowHeight);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        mainPanel = new GraphicsPanel();
        mainFrame.add(mainPanel);

        Thread thread = new Thread(this);
        thread.start();

    }

    public void run() {
        while (true) {
            mainPanel.repaint();
        }
    }

}