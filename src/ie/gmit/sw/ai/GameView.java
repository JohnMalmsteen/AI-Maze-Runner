package ie.gmit.sw.ai;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;

import ie.gmit.sw.maze.Cell;
import ie.gmit.sw.maze.ConnectionType;
public class GameView extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_VIEW_SIZE = 800;	
	private int cellspan = 10;	
	private int cellpadding = 5;
	private Cell[][] maze;
	private int enemy_state = 5;
	private Timer timer;
	private int currentRow;
	private int currentCol;
	private boolean zoomOut = false;
	private BufferedImage eastWall;
	private BufferedImage westWall;
	private BufferedImage southWall;
	private BufferedImage northWall;
	
	public GameView(Cell[][] maze) throws Exception{
		init();
		this.maze = maze;
		setBackground(Color.LIGHT_GRAY);
		setDoubleBuffered(true);
		timer = new Timer(300, this);
		timer.start();
	}
	
	public void setCurrentRow(int row) {
		if (row < cellpadding){
			currentRow = cellpadding;
		}else if (row > (maze.length - 1) - cellpadding){
			currentRow = (maze.length - 1) - cellpadding;
		}else{
			currentRow = row;
		}
	}

	public void setCurrentCol(int col) {
		if (col < cellpadding){
			currentCol = cellpadding;
		}else if (col > (maze[currentRow].length - 1) - cellpadding){
			currentCol = (maze[currentRow].length - 1) - cellpadding;
		}else{
			currentCol = col;
		}
	}

	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
              
        cellspan = zoomOut ? maze.length : 5;         
        final int size = DEFAULT_VIEW_SIZE/cellspan;
        g2.drawRect(0, 0, GameView.DEFAULT_VIEW_SIZE, GameView.DEFAULT_VIEW_SIZE);
        
        for(int row = 0; row < cellspan; row++) {
        	for (int col = 0; col < cellspan; col++){  
        		int x1 = col * size;
        		int y1 = row * size;
        		
        		Cell ch = null;
       		
        		if (zoomOut){
        			ch = maze[row][col];
        			if (row == currentRow && col == currentCol){
        				g2.setColor(Color.YELLOW);
        				g2.fillRect(x1, y1, size, size);
        				continue;
        			}
        		}else{
        			ch = maze[currentRow - 1 + row][currentCol - 1 + col];
        		}
        		
        		g2.setColor(Color.LIGHT_GRAY);
    			g2.fillRect(x1, y1, size, size);
    			
        		if(ch.getEast().getType()==ConnectionType.WALL){
        			g2.drawImage(eastWall, x1, y1, null);
        		}
        		
        		if(ch.getWest().getType()==ConnectionType.WALL){
        			g2.drawImage(westWall, x1, y1, null);
        		}
        		
        		if(ch.getNorth().getType()==ConnectionType.WALL){
        			g2.drawImage(northWall, x1, y1, null);
        		}
        		
        		if(ch.getSouth().getType()==ConnectionType.WALL){
        			g2.drawImage(southWall, x1, y1, null);
        		}
        		
        		
    			
        		   		
        	}
        }
	}
	
	public void toggleZoom(){
		zoomOut = !zoomOut;		
	}

	public void actionPerformed(ActionEvent e) {	
		if (enemy_state < 0 || enemy_state == 5){
			enemy_state = 6;
		}else{
			enemy_state = 5;
		}
		this.repaint();
	}
	
	private void init() throws Exception{
		westWall = ImageIO.read(new java.io.File("resources/westwall.png"));
		eastWall = ImageIO.read(new java.io.File("resources/eastwall.png"));
		northWall = ImageIO.read(new java.io.File("resources/northwall.png"));
		southWall = ImageIO.read(new java.io.File("resources/southwall.png"));
	}
}