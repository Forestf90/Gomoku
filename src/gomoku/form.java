package gomoku;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class form extends JFrame implements MouseListener{

	JPanel mapa= new plansza();
	JPanel panel_boczny = new JPanel();
	
	JLabel Lab_ip = new JLabel("Podaj ip: ");
	JTextField text_ip = new JTextField("localhost");
	JLabel Lab_port = new JLabel("Podaj port: ");
	JTextField text_port =new JTextField("2222");
	JButton but_lacz = new JButton("Polacz");
	JTextArea konsola = new JTextArea();
	//Font czcionka = new Font(Font.getDefault(), 24);
	
	Thread odbior;
	
	private Socket socket ;
	private DataOutputStream dos;
	private DataInputStream dis;
	private ServerSocket serverSocket;
	
	public form() {
		super("Gomoku");
		//Insets insets = this.getInsets();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(15*30+150 ,15*30 +50);

		panel_boczny.setBounds(15*30+20,0,100,5*24);

		panel_boczny.setLayout(new BoxLayout(panel_boczny , BoxLayout.Y_AXIS));
		this.setLayout(null);

		text_ip.setSize( new Dimension( 100, 24 ) );
		
		but_lacz.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		        polacz();
		    }
	  });
		konsola.setEditable(false);
		konsola.setBounds(15*30+20,300,100,100);
		konsola.setSize(100, 100);
		konsola.setWrapStyleWord(true);
		konsola.setLineWrap(true);
		
		panel_boczny.add(Lab_ip);
		panel_boczny.add(text_ip);
		panel_boczny.add(Lab_port);
		panel_boczny.add(text_port);
		panel_boczny.add(but_lacz);
		
		mapa.addMouseListener(this);
		this.add(konsola);
		this.add(panel_boczny);
		this.add(mapa);
		
		setVisible(true);
	}
	
	public void polacz() {
		try {
			String ip =text_ip.getText();
			int port =Integer.parseInt(text_port.getText());
			socket = new Socket(ip, port);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			konsola.setText("Polaczono z serwerem");
			((plansza) mapa).ruch=false;
			//odbierz();
			konsola.setText("Ruch przeciwnika");
			tworz_watek();
			
			//accepted = true;
		} catch (IOException e) {
			konsola.setText("Twoj ruch");
			start_serwer();
		}
		
		
	}
	
	public void start_serwer() {
		//String ip =text_ip.getText();
		int port =Integer.parseInt(text_port.getText());
		socket =null;
		try {
			serverSocket = new ServerSocket(port, 8);
			((plansza) mapa).ruch=true;
			czekaj_na_gracza();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//konsola.setText("Oczekiwanie na graczy");
		((plansza) mapa).pierwszy = true;
		
	}
	
	
	public void czekaj_na_gracza() {
		Socket socket = null;
		try {
			socket = serverSocket.accept();
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			tworz_watek();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void wyslij(int x , int y) {
		konsola.setText("Ruch przeciwnika");
		try {
			dos.writeInt(x);
			dos.flush();
			dos.writeInt(y);
			dos.flush();
			revalidate();
			mapa.setFocusable(false);
			((plansza) mapa).ruch=false;
			//odbierz();
		} catch (IOException e1) {
			//errors++;
			e1.printStackTrace();
		}
		
	}
	/*
	public void odbierz() {
		try {
			super.repaint();
			super.revalidate();
			super.notifyAll();
			int x = dis.readInt();
			int y = dis.readInt();
			
			if(((plansza)mapa).pierwszy) {
				((plansza) mapa).grid[x][y]=2;
			}else {
				((plansza) mapa).grid[x][y]=1;
			}
			mapa.setFocusable(true);
			((plansza) mapa).ruch=true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mapa.repaint();
	}
	
	*/
	public void tworz_watek() {
		odbior = new Thread() {
			
			public synchronized void run() {
				while (true) {
					try {
					int x = dis.readInt();
					int y = dis.readInt();
					
					if(((plansza)mapa).pierwszy) {
						((plansza) mapa).grid[x][y]=2;
					}else {
						((plansza) mapa).grid[x][y]=1;
					}
					mapa.repaint();
					mapa.setFocusable(true);
					((plansza) mapa).ruch=true;
					konsola.setText("Twoj ruch");
					if(((plansza) mapa).sprawdz_przegrana(x ,y)) konsola.setText("Przegrales");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					konsola.setText("Utracono polaczenie - gra zostaje zakonczona");
					e.printStackTrace();
					break;
				}
					
				}
			}
		};
		odbior.start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int temp=0;
		if(((plansza) mapa).pierwszy) temp=1;
		else temp=2;
		int tempx =e.getX();
		int tempy =e.getY();
		
		int x = tempx/30;
		int y = tempy/30;
		if(((plansza) mapa).grid[x][y]!=0 || !((plansza) mapa).ruch) return;
		else {
			((plansza) mapa).grid[x][y]=temp;
			mapa.repaint();
			((plansza) mapa).ruch=false;
			//mapa.paintImmediately(0,0,mapa.HEIGHT , mapa.WIDTH);
			//super.notify();
			super.validate();
		}

		
		wyslij(x ,y);
		if(((plansza) mapa).sprawdz_zwyciestwo(x,y)) konsola.setText("Wygrales");

		//getParent().wyslij();
		//gomoku.form.wyslij(x ,y);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
