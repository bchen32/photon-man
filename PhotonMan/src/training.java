import java.awt.Graphics;
import java.util.ArrayList;

public class training {

	character player;
	// player character
	ArrayList<character> enemies;
	// enemy characters
	int atkCount;

	public training(sprites sp) {
		player = new character(sp, 20, 200, true);
		atkCount = 0;
		// creates new player at 20, 200
		player.blackLoadOut();
		// sets player to gun suit
		enemies = new ArrayList<character>();
		for (int x = 0; x != 20; x++) {
			character e = new character(sp, 600 + x * 300, 200, false);

			e.Soldier();
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
		atkCount++;
		// moves player
		for (character e : enemies) {
			e.move();
			if (atkCount == 20) {
				e.attack();
				atkCount = 0;
			}
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
			if (e.hp <= 0) {
				e.isDead = true;
				e.currsp = e.mysp[4];
			}
		}
		// checks collision
	}

}
