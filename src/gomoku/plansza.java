package gomoku;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import gomoku.form;

public class plansza extends JPanel {
	
	int[][] grid = new int[15][15];
	BufferedImage krzyzyk =new BufferedImage(28, 28 ,BufferedImage.TYPE_INT_ARGB);
	BufferedImage kolko = new BufferedImage(28, 28 ,BufferedImage.TYPE_INT_ARGB);
	public boolean pierwszy = false;
	public boolean ruch;
	
 public plansza() {
	 this.setSize(15*30+1 ,15*30 +1);
	 //this.addMouseListener(this);
	 rysuj_krz();
 }
 
 	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			for(int i=0 ;i<16 ;i++) {
				g.drawLine(i*30, 0, i*30, 15*30);
				g.drawLine(0, i*30, 15*30, i*30);
			}
			
			for(int i=0 ;i<15 ;i++) {
				for(int j=0 ; j<15 ; j++) {
					if(grid[i][j]==1) {
						g.drawImage(krzyzyk, 30*i+2, j*30+2, 28, 28, null);
					}
					else if(grid[i][j]==2) {
						g.drawImage(kolko, 30*i+2, j*30+2, 28, 28, null);
					}
				}
			}
			
		}

	public void rysuj_krz() {
		
		Graphics2D g = (Graphics2D) kolko.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.BLUE);
		g.drawOval(0, 0, 26, 26);
		
		Graphics2D g2 = (Graphics2D) krzyzyk.getGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.RED);
		g2.drawLine(1, 1 ,26 ,26);
		g2.drawLine(26, 1, 1, 26);
		
		
	}
	
	public boolean sprawdz_zwyciestwo(int x , int y) {
		int in_row=1;
		boolean lewo=true , prawo=true;
		int temp;
		if(pierwszy) temp=1;
		else temp=2;
		
		
		for(int i=1; i<=4 ; i++) {
			if(lewo && grid[x-i][y]==temp) in_row++;
			else lewo =false;
			if(prawo && grid[x+i][y]==temp) in_row++;
			else prawo =false;
			
		}
		if(in_row>=5)return true;
		else in_row=1;
		
		lewo =true;
		prawo=true;
		
		
		for(int i=1; i<=4 ; i++) {
			if(lewo && grid[x-i][y-i]==temp) in_row++;
			else lewo =false;
			if(prawo && grid[x+i][y+i]==temp) in_row++;
			else prawo =false;
			
		}
		if(in_row>=5)return true;
		else in_row=1;
		
		lewo =true;
		prawo=true;
		
		
		for(int i=1; i<=4 ; i++) {
			if(lewo && grid[x+i][y-i]==temp) in_row++;
			else lewo =false;
			if(prawo && grid[x-i][y+i]==temp) in_row++;
			else prawo =false;
			
		}
		if(in_row>=5)return true;
		else in_row=1;
		
		lewo =true;
		prawo=true;
		
		
		
		for(int i=1; i<=4 ; i++) {
			if(lewo && grid[x][y-i]==temp) in_row++;
			else lewo =false;
			if(prawo && grid[x][y+i]==temp) in_row++;
			else prawo =false;
			
		}
		if(in_row>=5)return true;
		else in_row=1;
		

		
		return false;
	}
	
	public boolean sprawdz_przegrana(int x , int y) {
		
		ruch=false;
		int in_row=1;
		boolean lewo=true , prawo=true;
		int temp;
		if(pierwszy) temp=2;
		else temp=1;
		
		
		for(int i=1; i<=4 ; i++) {
			if(lewo && grid[x-i][y]==temp) in_row++;
			else lewo =false;
			if(prawo && grid[x+i][y]==temp) in_row++;
			else prawo =false;
			
		}
		if(in_row>=5)return true;
		else in_row=1;
		
		lewo =true;
		prawo=true;
		
		
		for(int i=1; i<=4 ; i++) {
			if(lewo && grid[x-i][y-i]==temp) in_row++;
			else lewo =false;
			if(prawo && grid[x+i][y+i]==temp) in_row++;
			else prawo =false;
			
		}
		if(in_row>=5)return true;
		else in_row=1;
		
		lewo =true;
		prawo=true;
		
		
		for(int i=1; i<=4 ; i++) {
			if(lewo && grid[x+i][y-i]==temp) in_row++;
			else lewo =false;
			if(prawo && grid[x-i][y+i]==temp) in_row++;
			else prawo =false;
			
		}
		if(in_row>=5)return true;
		else in_row=1;
		
		lewo =true;
		prawo=true;
		
		
		
		for(int i=1; i<=4 ; i++) {
			if(lewo && grid[x][y-i]==temp) in_row++;
			else lewo =false;
			if(prawo && grid[x][y+i]==temp) in_row++;
			else prawo =false;
			
		}
		if(in_row>=5)return true;
		else in_row=1;
		

		ruch=true;
		return false;
	}
	
	public void reset() {
		for(int i=0 ; i<grid.length ; i++) {
			for(int j=0 ; j<grid[i].length ;j++) {
				grid[i][j]=0;
			}
		}
		
		if(pierwszy) ruch=true;
		else ruch=false;
		this.repaint();
	}
	
	
}
