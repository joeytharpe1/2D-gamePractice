package GoblinVsHumanPracticeProject;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; //48*48 tile
    public final int maxScreenCol = 16; //16 tiles horizontal
    public final int maxScreenRow = 12; //12 tiles vertical
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //FPS SETTINGS
    int FPS = 60;

    //instantiate tile manager
    TileManager tileM = new TileManager(this);

    //instantiate KeyHandler
    KeyHandler keyH = new KeyHandler();

    //auto calls the run method. start and stop a time/game
    Thread gameThread;

    //instantiate collision checker
    public CollisionChecker cChecker = new CollisionChecker(this);

    //instantiate human class
    public Player player = new Player(this, keyH);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); //better rendering performance
        //add KeyHandler
        this.addKeyListener(keyH);
        this.setFocusable(true);//focused to receive key input
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start(); //start the thread
    }

    @Override
    public void run() {
        //dividing 1s by 60fps
        double drawInterval = 1000000000 / FPS; //0.16666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        //create a game loop which is the core
        //as long as this thread exists then repeat process in while loop
        while(gameThread != null){
            // 1 UPDATE: update info such as a character positions
            update();
            // 2 DRAW: draw the screen w/ updated info
            repaint(); //calling the paintComponent method

            try {
                //how much time left untill next draw time
                double remainingTime = nextDrawTime - System.nanoTime(); //current time - draw time
                //convert remaining time to milliseconds
                remainingTime = remainingTime / 1000000;

                if(remainingTime < 0)
                    remainingTime = 0;

                Thread.sleep((long) remainingTime);

                //if awaken
                nextDrawTime += drawInterval;

            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public void update(){
        player.update();
    }
    //standard method to draw in java panel
    //Graphic is the pencil/paintbrush
    public void paintComponent(Graphics g){
        super.paintComponent(g); //super - jPanel

        Graphics2D g2 = (Graphics2D) g; //convert Graphics to a 2d for more functions
        //tiles before the player
        tileM.draw(g2);
        player.draw(g2);

        g2.dispose(); //dispose of this graphics context & any system resources that it's using

    }
}






