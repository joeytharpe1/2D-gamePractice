package GoblinVsHumanPracticeProject;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Goblin vs Human");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        //sized to fit the preferred size and layouts of subcomponents(GamePanel)
        window.pack();
        //window center of screen
        window.setLocationRelativeTo(null);
        //be able to see window
        window.setVisible(true);
    }
}
