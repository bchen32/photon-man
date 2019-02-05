import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class sprites {

	// 0 rest 1-3 run cycle 4 death
	BufferedImage[] red = new BufferedImage[5];
	BufferedImage[] blue = new BufferedImage[5];
	BufferedImage[] green = new BufferedImage[5];
	BufferedImage[] purple = new BufferedImage[5];
	BufferedImage[] orange = new BufferedImage[5];
	BufferedImage[] black = new BufferedImage[5];
	BufferedImage[] scientist = new BufferedImage[5];
	BufferedImage[] soldier = new BufferedImage[5];

	// 0 bullet 1 slice 2 beam 3 yellow orb 4 purple orb
	BufferedImage[] smallobj = new BufferedImage[5];
	// 0 red 1 blue 2 green 3 purple 4 orange 5 black
	BufferedImage[] icons = new BufferedImage[6];
	// 0 metal box 1 wood box 2 laser
	BufferedImage[] largeobj = new BufferedImage[3];

	public sprites() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(this.getClass().getResourceAsStream("/images/photon man ss.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// temp image is linked to the sprite sheet

		for (int x = 0; x != 5; x++) {
			red[x] = img.getSubimage(40 * x, 0, 40, 40);
			blue[x] = img.getSubimage(40 * x, 40, 40, 40);
			green[x] = img.getSubimage(40 * x, 80, 40, 40);
			purple[x] = img.getSubimage(40 * x, 120, 40, 40);
			orange[x] = img.getSubimage(40 * x, 160, 40, 40);
			black[x] = img.getSubimage(40 * x, 200, 40, 40);
			scientist[x] = img.getSubimage(40 * x, 240, 40, 40);
			soldier[x] = img.getSubimage(40 * x, 280, 40, 40);
			smallobj[x] = img.getSubimage(20 * x, 320, 10, 10);
		}
		for(int x = 0; x != 6; x++)
			icons[x] = img.getSubimage(20 * x, 340, 20, 20);
		largeobj[0] = img.getSubimage(120, 320, 40, 40);
		largeobj[1] = img.getSubimage(160, 320, 40, 40);
		largeobj[2] = img.getSubimage(100, 320, 20, 20);
		// chops up the sprite sheet into individual sprites then organizes them
	}

}