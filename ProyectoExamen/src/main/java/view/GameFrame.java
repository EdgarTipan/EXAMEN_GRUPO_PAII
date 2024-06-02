package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import controller.Container;

public class GameFrame extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;
	private Container con;
	private JPanel contPanel;

	public GameFrame(String title) {
		super(title);

		con = new Container();
		contPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				con.draw(g);
			}
		};

		contPanel.setBackground(Color.BLACK);
		setContentPane(contPanel);
		addKeyListener(this);

		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Timer timer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				con.moveDown(1);
				contPanel.repaint();
			}
		});
		timer.start();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// No se necesita implementación
	}

	@Override
	public void keyPressed(KeyEvent e) {
		setFocusable(true);
		switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
				con.moveLeft(10);
				break;
			case KeyEvent.VK_D:
				con.moveRight(10);
				break;
			case KeyEvent.VK_SPACE:
				con.drawShoot(contPanel.getGraphics());
				break;
			default:
				break;
		}
		contPanel.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// No se necesita implementación
	}
}
