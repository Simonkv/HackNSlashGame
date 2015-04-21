package HackNSlash;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;

public class HighscoreList {
	
	private GamePanel panel;
	static Scanner scan = new Scanner(System.in);
	
	ImageIcon HighscoreListSkeleton = new ImageIcon ((getClass().getResource( "/Images/HighscoreListSkeleton.png" )));
	
	private int newHighscore;
	public int bufferScore;
	ArrayList<Integer> highscores = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0));
	
	Rectangle2D.Double background = new Rectangle2D.Double(0, 0, panel.SCREEN_WIDTH, panel.SCREEN_HEIGHT);
	Rectangle2D.Double HSWall = new Rectangle2D.Double(300, 50, 600, 570);
	
	public void loadHighscores(){
		Scanner in;
		int counter = 0;
		try{
			in = new Scanner(new FileReader("highscores.txt"));
			while(in.hasNextLine()){
				highscores.add(counter, Integer.parseInt(in.nextLine()));
				counter++;
			}
			in.close();
		}
		catch (FileNotFoundException e){
	        }
	}
	
	public void save(){
		try{
			PrintWriter outFile = new PrintWriter("highscores.txt");
			for (int i=0; i<10; i++){
				outFile.println(Integer.toString(highscores.get(i)));
			}
	        outFile.close();
	        }
		catch (FileNotFoundException e){
	        }
	}
	
	public HighscoreList(GamePanel panel){
		this.panel = panel;
	}
	
	public String getScore(){
		return Integer.toString(bufferScore);
	}
	
	
	public void addPoints(int score){
		this.newHighscore+=score;
	}
	
	public int size(){
		return highscores.size();
	}
	
	public int getElement(int pos){
		return highscores.get(pos);
	}
	
	
	
	public void addResult(){
		
		
		
		if(panel.gameOver.won){
			System.out.println("DID WIN!");
			if(1000-panel.waveTimer.seconds>0){
				newHighscore = newHighscore + ((1000-panel.waveTimer.seconds)*10);
			}
			
		}
		bufferScore = newHighscore;
		if (newHighscore > highscores.get(size()-1)){
			boolean higher = true;
			int counter = -1;
			while (higher){
				counter++;
				if (newHighscore > getElement(counter)){
					highscores.add(counter, newHighscore);
					highscores.remove(size()-1);
					
					higher = false;
				}
			}
		}newHighscore=0;
	}
	
	public void update(){
		
	}
	
	public void paint(Graphics2D g){
		
		g.drawImage(HighscoreListSkeleton.getImage(), 0 ,0, 1200, 700, null);
		
		//g.setColor(Color.BLACK);
		//g.fill(background);
		//g.setColor(Color.GRAY);
		//g.fill(HSWall);
		g.setColor(Color.ORANGE);
		g.setFont(new Font ("Times New Roman", Font.BOLD, 50));
        //g.drawString("Your highscores:", 310, 90);
        g.drawString(""+ getElement(0), 345, 210);
        g.drawString(""+ getElement(1), 345, 300);
        g.drawString(""+ getElement(2), 345, 390);
        g.drawString(""+ getElement(3), 345, 480);
        g.drawString(""+ getElement(4), 345, 570);
        g.drawString(""+ getElement(5), 775, 210);
        g.drawString(""+ getElement(6), 775, 300);
        g.drawString(""+ getElement(7), 775, 390);
        g.drawString(""+ getElement(8), 775, 480);
        g.drawString(""+ getElement(9), 775, 570);
        
		
	}

}
