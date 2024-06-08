package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import controller.Container;

public class GameFrame extends JFrame implements KeyListener {

	private final Container con;
	private final JPanel contPanel;
    private boolean isPaused = false;
	private boolean isGameRunning = false;

	public GameFrame(String title) {
		con = new Container();
        int width = 800;
        int height = 600;
        setSize(width, height);
		setTitle(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		contPanel = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (isGameRunning) {
					con.updateBackground();
					if (isPaused) {
						con.drawPauseScreen(g, getWidth(), getHeight());
					} else {
						con.draw(g, getWidth(), getHeight());
					}
				} else {
					con.drawStartScreen(g, getWidth(), getHeight());
				}
			}
		};
		contPanel.setBackground(Color.BLACK);
		add(contPanel);
		addKeyListener(this);
	}

	public void startGame() {
		isGameRunning = true;
        Timer timer = new Timer(20, e -> {
            if (!isPaused) {
                con.update();
                if (con.isLevelCompleted()) {
                    con.advanceToNextLevel();
                }
            }
            contPanel.repaint();
        });
		timer.start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
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
				if (!isGameRunning) {
					startGame();
				}
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// No implementación
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// No implementación
	}

	private void togglePause() {
		isPaused = !isPaused;
		if (isPaused) {
			con.stopEnemyMovementTimer();
		} else {
			con.startEnemyMovementTimerAgain();
		}
	}
}
