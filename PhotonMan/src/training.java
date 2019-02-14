import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class training {

	character player;
	// player character
	ArrayList<character> enemies;
	// enemy characters

	// int attackCount = 0;

	public training(sprites sp) {
		player = new character(sp, 20, 140, "red");
		// creates new player at 20, 200
		enemies = new ArrayList<character>();
		for (int x = 0; x != 20; x++) {
			character e = new character(sp, 600 + x * 300, 140, "scientist");
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
		player.draw(g);
	}

	public void move() {
		player.move();
		// moves player
		// attackCount++;
		for (character e : enemies) {
			// if (attackCount == 20) {
			// // attacks every 20 frames
			// attackCount = 0;
			// e.attack();
			// }
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
		player.stuckRight = false;
		character e;
		for (int x = 0; x != enemies.size(); x++) {

			e = enemies.get(x);
			player.checkHits(e);
			if (e.health <= 0) {
				e.isDead = true;
				e.currsp = e.mysp[4];
			}

			playerCollide(e);

		}

		// checks collision
	}

	public void playerCollide(object a) {
		if (a instanceof character) {
			character e = ((character) a);
			if (!e.isDead)
				if (!(player.y + player.h < e.y || player.y > e.y + e.h))
					if (player.x + player.w >= e.x - player.dx && player.x + player.w <= e.x + player.dx) {
						player.stuckRight = true;
						player.x += e.dx;
					}
		} else {
			if (!(player.y + player.h < a.y || player.y > a.y + a.h))
				if (player.x + player.w >= a.x - player.dx && player.x + player.w <= a.x + player.dx) {
					player.stuckRight = true;
					player.x += a.dx;
				}
		}
	}
}