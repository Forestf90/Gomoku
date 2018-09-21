package gomoku;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class plansza extends JPanel implements MouseListener{
	
	int[][] grid = new int[15][15];
	BufferedImage krzyzyk =new BufferedImage(28, 28 ,BufferedImage.TYPE_INT_ARGB);
	BufferedImage kolko = new BufferedImage(28, 28 ,BufferedImage.TYPE_INT_ARGB);
	
	
 public plansza() {
	 this.setSize(15*30+1 ,15*30 +1);
	 this.addMouseListener(this);
	 rysuj_krz();
 }
 
 
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
	@Override
	public void mouseClicked(MouseEvent e) {
		int tempx =e.getX();
		int tempy =e.getY();
		
		int x = tempx/30;
		int y = tempy/30;
		grid[x][y]=1;
		repaint();
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
