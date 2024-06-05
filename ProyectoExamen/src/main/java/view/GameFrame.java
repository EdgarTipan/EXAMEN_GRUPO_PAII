package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serial;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import controller.Container;

public class GameFrame extends JFrame implements KeyListener {

	@Serial
	private static final long serialVersionUID = 1L;
	private final Container con;
	private final JPanel contPanel;
	private final int width = 800;
	private final int height = 600;
	private boolean isPaused = false;
	private boolean isStartScreen = true;

	public GameFrame(String title) {
		super(title);

		con = new Container();
		contPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (isStartScreen) {
					con.drawStartScreen(g, width, height);
				} else {
					con.drawScreenElements(g, width, height,50);
					if (isPaused) {
						con.drawPauseScreen(g, width, height);
					}
				}
			}
		};

		contPanel.setBackground(Color.BLACK);
		setContentPane(contPanel);
		addKeyListener(this);

		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);


		Timer timer = new Timer(30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isPaused && !isStartScreen) {
					con.updateBackground();
					con.enemyMove("DOWN",1);
					con.updateGame();
					contPanel.repaint();
				}
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
		if (isStartScreen) {
			isStartScreen = false;
			contPanel.repaint();
			return;
		}
		switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
				con.heroMove("LEFT", 10);
				break;
			case KeyEvent.VK_D:
				con.heroMove("RIGHT",10);
				break;
			case KeyEvent.VK_SPACE:
				con.heroShootBullet();
				break;
			case KeyEvent.VK_ESCAPE: // Tecla para pausar/despausar
				togglePause();
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

	private void togglePause() {
		isPaused = !isPaused;
	}
}
