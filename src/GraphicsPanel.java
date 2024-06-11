import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel implements KeyListener, MouseListener, ActionListener {

    private BufferedImage background;
    private BufferedImage cardTable;
    private Elixir elixir;
    private River river;
    private Bridge bridgeLeft;
    private Bridge bridgeRight;
    private TowerPathway towerPathway1;
    private TowerPathway towerPathway2;
    private TowerPathway enemyTowerPathway1;
    private TowerPathway enemyTowerPathway2;
    private TowerPathway mainTowerPathwayDown1;
    private TowerPathway mainTowerPathwayDown2;
    private TowerPathway enemyMainTowerPathwayDown1;
    private TowerPathway enemyMainTowerPathwayDown2;
    private TowerPathway mainTowerPathwayLeft;
    private TowerPathway mainTowerPathwayRight;
    private TowerPathway enemyMainTowerPathwayLeft;
    private TowerPathway enemyMainTowerPathwayRight;
    private Tower towerLeft;
    private Tower towerRight;
    private Tower enemyTowerLeft;
    private Tower enemyTowerRight;
    private MainTower mainTower;
    private MainTower enemyMainTower;
    private ArrayList<Card> cards;
    private ArrayList<Card> cardsInUse;
    private boolean[] pressedKeys;
    private Point mouseClickLocation;
    private Timer timer;
    private int logMoves;
    private int logHits;
    private double arrowYLeftE;
    private double arrowYRightE;
    private int appleAttack;
    private int cornAttack;
    private int watermelonAttack;
    private int orangeAttack;

    public GraphicsPanel() {

        try {
            background = ImageIO.read(new File("src/Assets/background.png"));
            cardTable = ImageIO.read(new File("src/Assets/cardTable.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        elixir = new Elixir();

        river = new River(0, 230);

        bridgeLeft = new Bridge(60, 230);
        bridgeRight = new Bridge(392, 230);

        towerPathway1 = new TowerPathway(60, 260, "tower");
        towerPathway2 = new TowerPathway(392, 260, "tower");
        enemyTowerPathway1 = new TowerPathway(60, 120, "tower");
        enemyTowerPathway2 = new TowerPathway(392, 120, "tower");

        mainTowerPathwayDown1 = new TowerPathway(60, 420, "down");
        mainTowerPathwayDown2 = new TowerPathway(392, 420, "down");
        enemyMainTowerPathwayDown1 = new TowerPathway(60, 43, "down");
        enemyMainTowerPathwayDown2 = new TowerPathway(392, 43, "down");

        mainTowerPathwayLeft = new TowerPathway(90, 427, "side");
        mainTowerPathwayRight = new TowerPathway(274, 427, "side");
        enemyMainTowerPathwayLeft = new TowerPathway(90, 43, "side");
        enemyMainTowerPathwayRight = new TowerPathway(274, 43, "side");

        towerLeft = new Tower(50, 370, "player");
        towerRight = new Tower(382, 370, "player");
        enemyTowerLeft = new Tower(50, 70, "enemy");
        enemyTowerRight = new Tower(382, 70, "enemy");

        mainTower = new MainTower(209, 405, "player");
        enemyMainTower = new MainTower(209, 20, "enemy");

        cards = randomStart();
        cardsInUse = new ArrayList<Card>();
        pressedKeys = new boolean[128];
        mouseClickLocation = new Point(0, 0);
        timer = new Timer(100, this);
        timer.start();

        logMoves = 0;
        logHits = 0;

        arrowYLeftE = 90;
        arrowYRightE = 90;

        appleAttack = 0;
        cornAttack = 0;
        watermelonAttack = 0;
        orangeAttack = 0;

        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        requestFocusInWindow();

    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.drawImage(background, 0, 0, null);
        g.drawRect(240, 0, 0, 700); // draws halfway divisor
        g.drawImage(cardTable, 0, 490, null);
        g.drawString("Next: ", 10, 530);

        g.drawRect(10, 540, 30, 40); //draws border for next card
        Image next = cards.get(4).getCardImage().getScaledInstance(30, 40, Image.SCALE_DEFAULT);
        g.drawImage(next, 10, 540, null);
        g.drawRect(0, 490, 500, 0); //draws divisor for cards selection and main game

        g.drawRect(90, 43, 119, 20); //draws enemy main tower pathway (left)
        g.drawRect(274, 43, 118, 20); //draws enemy main tower pathway (right)
        g.drawRect(60, 43, 30, 27); //draws enemy main tower pathway (down 1)
        g.drawRect(392, 43, 30, 27); //draws enemy main tower pathway (down 2)

        g.drawImage(enemyMainTowerPathwayLeft.getImage(), enemyMainTowerPathwayLeft.getxCoord(), enemyMainTowerPathwayLeft.getyCoord(), null); //draws enemy main tower pathway left
        g.drawImage(enemyMainTowerPathwayRight.getImage(), enemyMainTowerPathwayRight.getxCoord(), enemyMainTowerPathwayRight.getyCoord(), null); //draws enemy main tower pathway right
        g.drawImage(enemyMainTowerPathwayDown1.getImage(), enemyMainTowerPathwayDown1.getxCoord(), enemyMainTowerPathwayDown1.getyCoord(), null); //draws enemy main tower pathway down 1
        g.drawImage(enemyMainTowerPathwayDown2.getImage(), enemyMainTowerPathwayDown2.getxCoord(), enemyMainTowerPathwayDown2.getyCoord(), null); //draws enemy main tower pathway down 2

        g.drawRect(90, 427, 119, 20); //draws main tower pathway (left)
        g.drawRect(274, 427, 118, 20); //draws main tower pathway (right)
        g.drawRect(60, 420, 30, 27); //draws main tower pathway (down 1)
        g.drawRect(392, 420, 30, 27); //draws main tower pathway (down 2)

        g.drawImage(mainTowerPathwayLeft.getImage(), mainTowerPathwayLeft.getxCoord(), mainTowerPathwayLeft.getyCoord(), null); //draws main tower pathway left
        g.drawImage(mainTowerPathwayRight.getImage(), mainTowerPathwayRight.getxCoord(), mainTowerPathwayRight.getyCoord(), null); //draws main tower pathway right
        g.drawImage(mainTowerPathwayDown1.getImage(), mainTowerPathwayDown1.getxCoord(), mainTowerPathwayDown1.getyCoord(), null); //draws main tower pathway down 1
        g.drawImage(mainTowerPathwayDown2.getImage(), mainTowerPathwayDown2.getxCoord(), mainTowerPathwayDown2.getyCoord(), null); //draws main tower pathway down 2

        g.drawRect(60, 260, 30, 110); //draws tower pathway 1
        g.drawRect(392, 260, 30, 110); //draws tower pathway 2
        g.drawRect(60, 120, 30, 110); //draws enemy tower pathway 1
        g.drawRect(392, 120, 30, 110); //draws enemy tower pathway 2

        g.drawImage(towerPathway1.getImage(), towerPathway1.getxCoord(), towerPathway1.getyCoord(), null); //draws tower pathway 1
        g.drawImage(towerPathway2.getImage(), towerPathway2.getxCoord(), towerPathway2.getyCoord(), null); //draws tower pathway 2
        g.drawImage(enemyTowerPathway1.getImage(), enemyTowerPathway1.getxCoord(), enemyTowerPathway1.getyCoord(), null); //draws enemy tower pathway 1
        g.drawImage(enemyTowerPathway2.getImage(), enemyTowerPathway2.getxCoord(), enemyTowerPathway2.getyCoord(), null); //draws enemy tower pathway 2

        g.drawRect(209, 20, 65, 65); //draws enemy main tower
        g.drawRect(50, 70, 50, 50); //draws enemy tower 1
        g.drawRect(382, 70, 50, 50); //draws enemy tower 2

        if (enemyMainTower.getTowerHealth() > 0) {
            g.drawImage(enemyMainTower.getImage(), enemyMainTower.getxCoord(), enemyMainTower.getyCoord(), null); //draws enemy main tower
            g.drawImage(enemyMainTower.getHealthBar(), enemyMainTower.getxCoord() + 17, enemyMainTower.getyCoord() - 10, null);
        }
        if (enemyTowerLeft.getTowerHealth() > 0) {
            g.drawImage(enemyTowerLeft.getImage(), enemyTowerLeft.getxCoord(), enemyTowerLeft.getyCoord(), null); //draws enemy tower 1
            g.drawImage(enemyTowerLeft.getHealthBar(), enemyTowerLeft.getxCoord() + 10, enemyTowerLeft.getyCoord() - 10, null);
        }
        if (enemyTowerRight.getTowerHealth() > 0) {
            g.drawImage(enemyTowerRight.getImage(), enemyTowerRight.getxCoord(), enemyTowerRight.getyCoord(), null); //draws enemy tower 2
            g.drawImage(enemyTowerRight.getHealthBar(), enemyTowerRight.getxCoord() + 10, enemyTowerRight.getyCoord() - 10, null);
        }

        g.drawRect(209, 405, 65, 65); //draw main tower
        g.drawRect(50, 370, 50, 50); //draws tower 1
        g.drawRect(382, 370, 50, 50); //draws tower 2

        if (mainTower.getTowerHealth() > 0) {
            g.drawImage(mainTower.getImage(), mainTower.getxCoord(), mainTower.getyCoord(), null); //draws main tower
            g.drawImage(mainTower.getHealthBar(), mainTower.getxCoord() + 17, mainTower.getyCoord() - 10, null);
        }
        if (towerLeft.getTowerHealth() > 0) {
            g.drawImage(towerLeft.getImage(), towerLeft.getxCoord(), towerLeft.getyCoord(), null); //draws tower 1
            g.drawImage(towerLeft.getHealthBar(), towerLeft.getxCoord() + 10, towerLeft.getyCoord() - 10, null);
        }
        if (towerRight.getTowerHealth() > 0) {
            g.drawImage(towerRight.getImage(), towerRight.getxCoord(), towerRight.getyCoord(), null); //draws tower 2
            g.drawImage(towerRight.getHealthBar(), towerRight.getxCoord() + 10, towerRight.getyCoord() - 10, null);

        }

        g.drawRect(0, 230,500, 30); //draws river
        g.drawRect(60, 230, 30, 30); //draws bridge (left)
        g.drawRect(392, 230, 30, 30); //draw bridge (right)

        g.drawImage(river.getImage(), river.getxCoord(), river.getyCoord(), null); //draws river
        g.drawImage(bridgeLeft.getImage(), bridgeLeft.getxCoord(), bridgeLeft.getyCoord(), null); //draws left bridge
        g.drawImage(bridgeRight.getImage(), bridgeRight.getxCoord(), bridgeRight.getyCoord(), null); //draws right bridge

        for (int i = 50; i <= 350; i += 100) {
            g.drawRect(i, 500, 80, 100); //draws card borders
            g.drawImage(cards.get(i / 100).getCardImage(), i, 500, null); //draws cards
        }

        for (int i = 0; i < elixir.getElixirBar().length; i++) {
            if (elixir.getElixirBar()[i]) {
                g.setColor(Color.MAGENTA);
                g.fillRect((i + 1) * 40, 615, 40, 30); //draws elixir
                g.setColor(Color.BLACK);
            }
        }

        for (int i = 40; i < 440; i += 40) {
            g.drawRect(i, 615, 40, 30); //draws elixir bar
        }

        if (mouseClickLocation.y > 260 && mouseClickLocation.y < 460 && !isTouchingTower()) {
            Card card = null;
            int cardNum = 0;
            if (pressedKeys[49]) {
                cardNum = 0;
                card = cards.get(0);
            } else if (pressedKeys[50]) {
                cardNum = 1;
                card = cards.get(1);
            } else if (pressedKeys[51]) {
                cardNum = 2;
                card = cards.get(2);
            } else if (pressedKeys[52]) {
                cardNum = 3;
                card = cards.get(3);
            }
            if (card != null) {
                if (card.getTroop().equals("Apple")) {
                    card = new Apple("src/Assets/appleplayer.png", mouseClickLocation.x, mouseClickLocation.y);
                } else if (card.getTroop().equals("Corn")) {
                    card = new Corn("src/Assets/cornplayer.png", mouseClickLocation.x, mouseClickLocation.y);
                } else if (card.getTroop().equals("Log")) {
                    card = new Log("src/Assets/logplayer.png", mouseClickLocation.x, mouseClickLocation.y);
                } else if (card.getTroop().equals("Watermelon")) {
                    card = new Watermelon("src/Assets/watermelonplayer.png", mouseClickLocation.x, mouseClickLocation.y);
                } else if (card.getTroop().equals("Orange")) {
                    card = new Orange("src/Assets/orangeplayer.png", mouseClickLocation.x, mouseClickLocation.y);
                }
                if (elixir.getElixirAmt() >= card.getElixirCost()) {
                    elixir.useElixir(card.getElixirCost());
                    cardsInUse.add(card);
                    Card removed = cards.remove(cardNum);
                    cards.add(cardNum, cards.get(3));
                    cards.add(4, removed);
                }
            }
        }

        for (int i = 0; i < cardsInUse.size(); i++) {
            Card card = cardsInUse.get(i);
            if (card.getHealth() > 0) {
                g.drawImage(card.getGameImage(), card.getxCoord(), card.getyCoord(), null);
                if (!(card instanceof Log)) {
                    g.drawImage(cardsInUse.get(i).getHealthBar(), card.getxCoord(), card.getyCoord() - 10, null);
                    if (card.getyCoord() < 490) {
                        if (card.getxCoord() > 240) {
                            if (card.getxCoord() < 392) {
                                card.moveRight();
                            } else if (card.getxCoord() > 392) {
                                card.moveLeft();
                            } else {
                                if (!card.rangeRect().intersects(enemyTowerRight.towerRect())) {
                                    card.moveUp();
                                }
                            }
                        } else {
                            if (card.getxCoord() < 60) {
                                card.moveRight();
                            } else if (card.getxCoord() > 60) {
                                card.moveLeft();
                            } else {
                                if (!card.rangeRect().intersects(enemyTowerLeft.towerRect())) {
                                    card.moveUp();
                                }
                            }
                        }
                    }
                    if (enemyTowerLeft.getTowerHealth() > 0 && card.cardRect().intersects(enemyTowerLeft.towerRange(0, 0))) {
                        if (card.getHealth() >= card.getHealth() - enemyTowerLeft.getTowerDamage()) {
                            g.drawImage(enemyTowerLeft.getArrowImage(), 72, (int) arrowYLeftE, null);
                            if (enemyTowerLeft.arrowRect(72, (int) arrowYLeftE).intersects(card.cardRect())) {
                                card.loseHealth(enemyTowerLeft.getTowerDamage());
                                arrowYLeftE = 90;
                            } else {
                                arrowYLeftE += 0.1;
                            }
                        }
                    }
                    if (enemyTowerRight.getTowerHealth() > 0 && card.cardRect().intersects(enemyTowerRight.towerRange(260, 0))) {
                        if (card.getHealth() >= card.getHealth() - enemyTowerRight.getTowerDamage()) {
                            g.drawImage(enemyTowerRight.getArrowImage(), 403, (int) arrowYRightE, null);
                            if (enemyTowerRight.arrowRect(403, (int) arrowYRightE).intersects(card.cardRect())) {
                                card.loseHealth(enemyTowerRight.getTowerDamage());
                                arrowYRightE = 90;
                            } else {
                                arrowYRightE += 0.1;
                            }
                        }
                    }
                    if (enemyTowerLeft.getTowerHealth() > 0 && card.rangeRect().intersects(enemyTowerLeft.towerRect())) {
                        if (card instanceof Apple) {
                            appleAttack++;
                            if (appleAttack == 800) {
                                enemyTowerLeft.loseTowerHealth(card.getDamage());
                                appleAttack = 0;
                            }
                        }
                        if (card instanceof Corn) {
                            cornAttack++;
                            if (cornAttack == 500) {
                                enemyTowerLeft.loseTowerHealth(card.getDamage());
                                cornAttack = 0;
                            }
                        }
                        if (card instanceof Watermelon) {
                            watermelonAttack++;
                            if (watermelonAttack == 1000) {
                                enemyTowerLeft.loseTowerHealth(card.getDamage());
                                watermelonAttack = 0;
                            }
                        }
                        if (card instanceof Orange) {
                            orangeAttack++;
                            if (orangeAttack == 1200) {
                                enemyTowerLeft.loseTowerHealth(card.getDamage());
                                orangeAttack = 0;
                            }
                        }
                    }
                    if (enemyTowerRight.getTowerHealth() > 0 && card.rangeRect().intersects(enemyTowerRight.towerRect())) {
                        if (card instanceof Apple) {
                            appleAttack++;
                            if (appleAttack == 800) {
                                enemyTowerRight.loseTowerHealth(card.getDamage());
                                appleAttack = 0;
                            }
                        } else if (card instanceof Corn) {
                            cornAttack++;
                            if (cornAttack == 500) {
                                enemyTowerRight.loseTowerHealth(card.getDamage());
                                cornAttack = 0;
                            }
                        } else if (card instanceof Watermelon) {
                            watermelonAttack++;
                            if (watermelonAttack == 1000) {
                                enemyTowerRight.loseTowerHealth(card.getDamage());
                                watermelonAttack = 0;
                            }
                        } else if (card instanceof Orange) {
                            orangeAttack++;
                            if (orangeAttack == 1200) {
                                enemyTowerRight.loseTowerHealth(card.getDamage());
                                orangeAttack = 0;
                            }
                        }
                    }
                    if (enemyTowerLeft.getTowerHealth() <= 0 && card.getyCoord() <= 230 && card.getyCoord() >= 45) {
                        if (card.getxCoord() < 240) {
                            card.moveUp();
                        }
                    }
                    if (enemyTowerRight.getTowerHealth() <= 0 && card.getyCoord() <= 230 && card.getyCoord() >= 45) {
                        if (card.getxCoord() > 240) {
                            card.moveUp();
                        }
                    }
                    if (card.getyCoord() < 45 && !card.rangeRect().intersects(enemyMainTower.mainTowerRect())) {
                        card.moveRight();
                        card.moveRight();
                    }
                    if (card.getyCoord() < 45 && !card.rangeRect().intersects(enemyMainTower.mainTowerRect())) {
                        card.moveLeft();
                        card.moveLeft();
                    }
                } else {
                    if (logMoves < 400) {
                        card.moveUp();
                        logMoves++;
                        if (card.cardRect().intersects(enemyTowerLeft.towerRect()) && logHits != 1) {
                            enemyTowerLeft.loseTowerHealth(card.getDamage());
                            logHits++;
                        }
                        if (card.cardRect().intersects(enemyTowerRight.towerRect()) && logHits != 1) {
                            enemyTowerRight.loseTowerHealth(card.getDamage());
                            logHits++;
                        }
                    } else {
                        card.loseHealth(card.getHealth());
                        logMoves = 0;
                        logHits = 0;
                    }
                }
            }
            if (card.getHealth() <= 0) {
                cardsInUse.remove(i);
                i--;
            }
        }

        mouseClickLocation = new Point(0, 0);

    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        //key codes found here
        //https://stackoverflow.com/questions/15313469/java-keyboard-keycodes-list
        int key = e.getKeyCode();
        pressedKeys[key] = true;
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        pressedKeys[key] = false;
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseClickLocation = e.getPoint();
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Timer && elixir.getElixirAmt() < 10) {
            elixir.getElixirBar()[elixir.getElixirAmt()] = true; //generates elixir
            elixir.addElixir();
        }
    }

    private ArrayList<Card> randomStart() {
        ArrayList<Card> cards = new ArrayList<Card>();
        ArrayList<Integer> numsUsed = new ArrayList<>();
        int randomNum = (int) (Math.random() * 5) + 1;
        for (int i = 0; i < 5; i++) {
            while (!isAvailable(numsUsed, randomNum)) {
                randomNum = (int) (Math.random() * 5) + 1;
            }
            if (randomNum == 1) {
                cards.add(new Apple("src/Assets/appleplayer.png", 0, 0));
            } else if (randomNum == 2) {
                cards.add(new Corn("src/Assets/cornplayer.png", 0, 0));
            } else if (randomNum == 3) {
                cards.add(new Log("src/Assets/logplayer.png", 0, 0));
            } else if (randomNum == 4) {
                cards.add(new Watermelon("src/Assets/watermelonplayer.png", 0, 0));
            } else {
                cards.add(new Orange("src/Assets/orangeplayer.png", 0, 0));
            }
            useNum(numsUsed, randomNum);
        }
        return cards;
    }

    private boolean isAvailable(ArrayList<Integer> numsUsed, int numToUse) {
        for (int i = 0; i  < numsUsed.size(); i++) {
            if (numsUsed.get(i) == numToUse) {
                return false;
            }
        }
        return true;
    }

    private void useNum(ArrayList<Integer> numsUsed, int num) {
        numsUsed.add(num);
    }

    private boolean isTouchingTower() {
        if (mouseClickLocation.x > 20 && mouseClickLocation.x < 100 && mouseClickLocation.y > 355 && mouseClickLocation.y < 420) {
            return true;
        }
        if (mouseClickLocation.x > 352 && mouseClickLocation.x < 432 && mouseClickLocation.y > 355 && mouseClickLocation.y < 420) {
            return true;
        }
        if (mouseClickLocation.x > 179 && mouseClickLocation.x < 274 && mouseClickLocation.y > 385 && mouseClickLocation.y < 470) {
            return true;
        }
        return false;
    }

}