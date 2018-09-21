package gomoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class form extends JFrame {

	JPanel mapa= new plansza();
	JPanel panel_boczny = new JPanel();
	
	JLabel Lab_ip = new JLabel("Podaj ip: ");
	JTextField text_ip = new JTextField();
	JLabel Lab_port = new JLabel("Podaj port: ");
	JTextField text_port =new JTextField();
	JButton but_lacz = new JButton("Polacz");
	//Font czcionka = new Font(Font.getDefault(), 24);
	
	public form() {
		super("Gomoku");
		//Insets insets = this.getInsets();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(15*30+150 ,15*30 +50);
		//panel_boczny.setSize(15*30 ,120);
		panel_boczny.setBounds(15*30+20,0,100,5*24);
		//panel_boczny.setBackground(Color.YELLOW);
		panel_boczny.setLayout(new BoxLayout(panel_boczny , BoxLayout.Y_AXIS));
		this.setLayout(null);
		//mapa.setBackground(Color.WHITE);
		//this.setBackground(Color.GREEN);
		text_ip.setSize( new Dimension( 100, 24 ) );
		
		panel_boczny.add(Lab_ip);
		panel_boczny.add(text_ip);
		panel_boczny.add(Lab_port);
		panel_boczny.add(text_port);
		panel_boczny.add(but_lacz);
		//panel_boczny.setFocusable(true);
		this.add(panel_boczny);
		this.add(mapa);
		
		setVisible(true);
	}
}
