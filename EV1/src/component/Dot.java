package component;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Dot {
	private static Image[] dotbufffer;
	public Image current_dot;
	private static final int width = 80;
	private static final int heigth = 80;

	public Dot(boolean isActivated) {
		try {
			dotbufffer = new Image[2];
			dotbufffer[0] = ImageIO.read(new File("./images/dots/inactivated.png"));
			dotbufffer[1] = ImageIO.read(new File("./images/dots/activated.png"));
			dotbufffer[0] = dotbufffer[0].getScaledInstance(width, heigth, java.awt.Image.SCALE_SMOOTH);
			dotbufffer[1] = dotbufffer[1].getScaledInstance(width, heigth, java.awt.Image.SCALE_SMOOTH);
			current_dot = dotbufffer[isActivated ? 1:0];
		}
		catch(IOException e) {
		}
	}

	public void setDot(boolean status) {
		current_dot = dotbufffer[status ? 1 : 0];
	}

	public static int getWidth() {
		return width;
	}

	public static int getHeigth() {
		return heigth;
	}

}
