import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class main extends JPanel implements ActionListener {

	private static final long serialVersionUID = 7278894226467689035L;
	private Timer timer;
	// timer that triggers update at specific intervals
	private final int DELAY = 10;
	// specific interval is every 10 milliseconds

	sprites sp = new sprites();
	// main spritesheet

	String state;
	// current game state
	training tr;
	// training mode game state
	loadOut lo;

	// loadout select game state
	public main() {

		initboard();
	}

	private void initboard() {
		addKeyListener(new KAdapter());
		// adds key input
		addMouseListener(new MAdapter());
		// adds mouse input
		setFocusable(true);
		setBackground(Color.GRAY);
		// sets background color to gray
		timer = new Timer(DELAY, this);
		timer.start();
		// creates and starts the timer
		state = "loadout";
		// sets the current state to training
		tr = new training(sp);
		// instantiates training
		lo = new loadOut(sp, tr.player);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
		// draws the game
		Toolkit.getDefaultToolkit().sync();
	}

	private void doDrawing(Graphics g) {
		if (state.equals("training"))
			tr.draw(g);
		if (state.equals("loadout"))
			lo.draw(g);
		// if the game state is training, draw training
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		if (state.equals("training"))
			tr.move();
		if (state.equals("loadout"))
			lo.move();
		// if the game state is training, move training
	}

	private class KAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();

			if (state.equals("loadout")) {
				// if game state is loadout...
				if (key == KeyEvent.VK_D) {
					tr.player.right = true;
					lo.selectRight();
				}
				if (key == KeyEvent.VK_A) {
					tr.player.left = true;
					lo.selectLeft();
				}
				if (key == KeyEvent.VK_W) {
					tr.player.up = true;
				}
				if (key == KeyEvent.VK_S) {
					tr.player.down = true;
				}
			} else if (state.equals("training")) {
				// if the game state is training.
				if (key == KeyEvent.VK_W) {
					tr.player.up = true;
				}
				if (key == KeyEvent.VK_S) {
					tr.player.down = true;
				}
				if (key == KeyEvent.VK_D) {
					tr.player.right = true;
				}
				if (key == KeyEvent.VK_A) {
					tr.player.left = true;
				}
				if (key == KeyEvent.VK_E) {
					state = "loadout";
				}
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			if (state.equals("loadout")) {
				if (key == KeyEvent.VK_SPACE) {
					tr.player.setLoadOut(lo.select());
					state = "training";
				}
				if (key == KeyEvent.VK_W) {
					tr.player.up = false;
				}
				if (key == KeyEvent.VK_S) {
					tr.player.down = false;
				}
				if (key == KeyEvent.VK_D) {
					tr.player.right = false;
				}
				if (key == KeyEvent.VK_A) {
					tr.player.left = false;
				}
			} else if (state.equals("training")) {
				// if the game state is training...
				if (key == KeyEvent.VK_SPACE)
					tr.player.attack();
				// space triggers player attack
				if (key == KeyEvent.VK_W) {
					tr.player.up = false;
				}
				if (key == KeyEvent.VK_S) {
					tr.player.down = false;
				}
				if (key == KeyEvent.VK_D) {
					tr.player.right = false;
				}
				if (key == KeyEvent.VK_A) {
					tr.player.left = false;
				}
			}
		}
	}

	private class MAdapter extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {

		}
	}
}