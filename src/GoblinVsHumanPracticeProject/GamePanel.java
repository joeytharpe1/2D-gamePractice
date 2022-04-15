package GoblinVsHumanPracticeProject;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;
    final int tileSize = originalTileSize * scale; //48*48 tile
    final int maxScreenCol = 16; //16 tiles horizontal
    final int maxScreenRow = 12; //12 tiles vertical
    final int screenWidth = tileSize * maxScreenCol; //768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); //better rendering performance
    }
}
