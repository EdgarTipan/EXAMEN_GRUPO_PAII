package view_package;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import controller_package.Container;

public class GameFrame extends JFrame implements KeyListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Container con; 
	private JPanel contPanel;

	public GameFrame(String title){
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800,600);
		
		con = new Container();
		contPanel = new JPanel();
		contPanel.setBackground(Color.BLACK);
		setContentPane(contPanel);
		addKeyListener(this);
		
		Timer timer = new Timer(100, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				con.moveDown(1);
				repaint();
			}
		});
		timer.start();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		con.draw(g);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		setFocusable(true);
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A: {
			con.moveLeft(10);
			break;
		}
		case KeyEvent.VK_D: {
			con.moveRight(10);
			break;
		}
		
		case KeyEvent.VK_SPACE: {
			con.drawShoot(getGraphics());
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + e.getKeyCode());
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
