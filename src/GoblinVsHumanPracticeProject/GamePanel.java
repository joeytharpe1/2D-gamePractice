package GoblinVsHumanPracticeProject;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;
    final int tileSize = originalTileSize * scale; //48*48 tile
    final int maxScreenCol = 16; //16 tiles horizontal
    final int maxScreenRow = 12; //12 tiles vertical
    final int screenWidth = tileSize * maxScreenCol; //768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //setting fps
    int FPS = 60;

    //instantiate KeyHandler
    KeyHandler keyH = new KeyHandler();

    //auto calls the run method. start and stop a time/game
    Thread gameThread;

    //set players default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4; //means 4 pixels

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
        //chane player position
        if(keyH.upPressed)
            playerY -= playerSpeed;
        else if(keyH.downPressed)
            playerY += playerSpeed;
        else if(keyH.leftPressed)
            playerX -= playerSpeed;
        else if(keyH.rightPressed)
            playerX += playerSpeed;
    }
    //standard method to draw in java panel
    //Graphic is the pencil/paintbrush
    public void paintComponent(Graphics g){
        super.paintComponent(g); //super - jPanel

        Graphics2D g2 = (Graphics2D) g; //convert Graphics to a 2d for more functions
        //set color
        g2.setColor(Color.WHITE);
        //draw rect and fill w/ specified color
        g2.fillRect(playerX,playerY,tileSize,tileSize);
        g2.dispose(); //dispose of this graphics context & any system resources that it's using

    }
}






