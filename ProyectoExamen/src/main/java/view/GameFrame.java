package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

import controller.Container;
import util.GameStateSerializer;

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
					con.draw(g, getWidth(), getHeight());
					if (isPaused) {
						con.drawPauseScreen(g, getWidth(), getHeight());
					}
				} else {
					con.drawStartScreen(g, getWidth(), getHeight());
				}
			}
		};
		contPanel.setBackground(Color.BLACK);
		add(contPanel);
		addKeyListener(this);
		showLoadGameDialog();
	}

	private void showLoadGameDialog() {
		int response = JOptionPane.showConfirmDialog(this, "¿Desea cargar una partida jugada anteriormente?", "Cargar Partida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.YES_OPTION) {
			String input = JOptionPane.showInputDialog(this, "Ingrese el ID de la partida que desea cargar:", "Cargar Partida", JOptionPane.QUESTION_MESSAGE);
			if (input != null && !input.trim().isEmpty()) {
				try {
					Long gameId = Long.parseLong(input.trim());
					loadGameState(gameId);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(this, "ID inválido. Comenzando un nuevo juego.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			// Llevar al usuario a la pantalla de inicio
			showStartScreen();
		}
	}

	private void showStartScreen() {
		isGameRunning = false;
		contPanel.repaint();
	}

	public void startGame() {
		isGameRunning = true;
		con.startEnemyMovementTimer();
		con.startEnemyShootingTimer();
		Timer timer = new Timer(20, e -> {
			if (!isPaused && !con.isGameOver() && !con.isGameWon()) {
				con.update();
				con.updateBackground();
				if (con.isLevelCompleted()) {
					con.advanceToNextLevel();
				}
			}
			contPanel.repaint();
		});
		timer.start();
	}

	private void saveGameState() {
		GameStateSerializer serializer = new GameStateSerializer();
		serializer.saveGameState(con);
	}

	private void loadGameState(Long id) {
		GameStateSerializer serializer = new GameStateSerializer();
		GameStateSerializer.GameState gameState = serializer.loadGameStateFromApi(id);
		if (gameState != null) {
			con.loadGameState(gameState);
			startGame();  // Empezar el juego después de cargar el estado
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (isGameRunning) {
			if (!con.isGameOver() && !con.isGameWon()) {
				if (key == KeyEvent.VK_A && !isPaused) {
					con.heroMove("LEFT", 10);
				} else if (key == KeyEvent.VK_D && !isPaused) {
					con.heroMove("RIGHT", 10);
				} else if (key == KeyEvent.VK_SPACE && !isPaused) {
					con.heroShootBullet();
				} else if (key == KeyEvent.VK_L && !isPaused) {
					String input = JOptionPane.showInputDialog(this, "Ingrese el ID de la partida que desea cargar:", "Cargar Partida", JOptionPane.QUESTION_MESSAGE);
					if (input != null && !input.trim().isEmpty()) {
						try {
							Long gameId = Long.parseLong(input.trim());
							loadGameState(gameId);
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(this, "ID inválido.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				} else if (key == KeyEvent.VK_S && !isPaused) {
					saveGameState();
				} else if (key == KeyEvent.VK_ESCAPE) {
					togglePause();
				}
			}
		} else {
			startGame();
		}
	}

	private void togglePause() {
		if (!con.isGameOver() && !con.isGameWon()) {
			isPaused = !isPaused;
			if (isPaused) {
				con.stopEnemyMovementTimer();
				con.stopEnemyShootingTimer();
			} else {
				con.startEnemyMovementTimer();
				con.startEnemyShootingTimer();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// No se usa pero es necesario para implementar KeyListener
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// No se usa pero es necesario para implementar KeyListener
	}
}
