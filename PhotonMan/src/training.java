import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class training {

	character player;
	// player character
	ArrayList<character> enemies;
	// enemy characters
	ArrayList<crate> crates;
	// crates
	ArrayList<laser> lasers;
	// lasers

	public training(sprites sp) {
		player = new character(sp, 20, 140, "red");
		// creates new player at 20, 200
		enemies = new ArrayList<character>();
		crates = new ArrayList<crate>();
		lasers = new ArrayList<laser>();
		for (int x = 0; x != 20; x++) {
			character e = new character(sp, 600 + x * 300, 140, "scientist");
			enemies.add(e);
		}
		// creates and instantiates enemies
		crates.add(new crate(sp, 400, 140));
		lasers.add(new laser(sp, 500, 176));
		// test laser
		// test crate
	}

	public void draw(Graphics g) {
		player.draw(g);
		// draws player
		for (character e : enemies) {
			e.draw(g);
		}
		// draw crates
		for (crate c : crates) {
			c.draw(g);
		}
		for (laser l : lasers) {
			l.crateA.draw(g);
			l.crateB.draw(g);
			l.b.draw(g);
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
		check();
		// checks collision and out of bounds
		player.move();
		// moves player
		for (character e : enemies) {
			e.move();
		}
		// moves enemies
		for (crate c : crates) {
			c.move();
		}
		// moves crates
		for (laser l : lasers) {
			l.crateA.move();
			l.crateB.move();
			l.b.move();
		}
		// moves lasers
	}

	public void check() {
		for (int x = 0; x != enemies.size(); x++) {
			if (enemies.get(x).x < -40) {
				enemies.remove(x);
				x--;
			}
		}
		// checks out of bounds
		character e;
		player.blockedSide = false;
		player.blockedUp = false;
		player.blockedDown = false;
		for (int x = 0; x != enemies.size(); x++) {

			e = enemies.get(x);
			player.checkHits(e);
			if (e.health <= 0) {
				e.isDead = true;
				e.currsp = e.mysp[4];
			}
			player.collide(e);
		}
		for (crate c : crates) {
			player.collide(c);
		}
		for (laser l : lasers) {
			player.collide(l.crateA);
			player.collide(l.crateB);
		}
		// checks collision
	}
}