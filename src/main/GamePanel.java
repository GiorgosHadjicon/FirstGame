package main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTING
    final int ORIGINAL_TILE_SIZE = 16;
    final int SCALE = 3;
    final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;

    final int MAX_SCREEN_COLUMN = 16;
    final int MAX_SCREEN_ROW = 12;
    final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMN;
    final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;

    Thread gameThread;

    public GamePanel() {

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        
    }
}
