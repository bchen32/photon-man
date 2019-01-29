import java.awt.Graphics;
import java.util.ArrayList;

public class training {

	character player;
	// player character
	ArrayList<character> enemies;
	// enemy characters

	public training(sprites sp) {
		player = new character(sp, 20, 200);
		// creates new player at 20, 200
		player.setLoadOut("black");
		enemies = new ArrayList<character>();
		for (int x = 0; x != 20; x++) {
			character e = new character(sp, 600 + x * 300, 200);
			e.setLoadOut("soldier");
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

	}

	public void move() {
		player.move();
		// moves player
		for (character e : enemies)
			e.move();
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
