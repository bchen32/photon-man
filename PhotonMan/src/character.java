import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class training {

	character player;
	// player character
	ArrayList<character> enemies;
	// enemy characters
//	int attackCount = 0;

	public training(sprites sp) {
		player = new character(sp, 20, 140);
		// creates new player at 20, 200
		player.setLoadOut("blue");
		enemies = new ArrayList<character>();
		for (int x = 0; x != 20; x++) {
			character e = new character(sp, 600 + x * 300, 140);
			e.setLoadOut("scientist");
			enemies.add(e);
		}
		// creates and instantiates enemies
	}

	public void draw(Graphics g) {
		player.draw(g);
		// draws player
		for (character e : enemies) {
			e.draw(g);
		}
		// draws enemies
		drawOverlay(g);
	}
	
	public void drawOverlay(Graphics g) {
		g.fillRect(0, 240, 480, 60);
		g.setColor(Color.WHITE);
		g.fillRect(60, 260, player.energy, 10);
		g.drawImage(player.profile, 20, 255, null);
	}

	public void move() {
		player.move();
		// moves player
//		attackCount++;
		for (character e : enemies) {
//			if (attackCount == 20) {
//				// attacks every 20 frames
//				attackCount = 0;
//				e.attack();
//			}
			e.move();
		}
		// moves enemies
		check();
		// checks collision and out of bounds
	}

	public void check() {
		for (int x = 0; x != enemies.size(); x++) {
			if (enemies.get(x).x < -40) {
				enemies.remove(x);
				x--;
			}
		}
		// checks out of bounds

		for (int x = 0; x != enemies.size(); x++) {
			character e = enemies.get(x);
			player.checkHits(e);
			if (e.health <= 0) {
				e.isDead = true;
				e.currsp = e.mysp[4];
			}
		}
		// checks collision
	}

}
