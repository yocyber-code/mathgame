package component;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.JButton;

public class AnwserComponant extends JButton{
	private int value;
	private boolean isCorrect;
	public Image[] numImg;

	public AnwserComponant(int value, boolean isCorrect, int button_width) {
		numImg = null;
		this.value = value;
		this.isCorrect = isCorrect;
		this.setImage(button_width);
	}

	private void setImage(int button_width) {
		numImg = new Image[2];
		System.out.println(value);
		if (value < 10) {
			String path = "";
			path = String.format("./images/numbers/%d.png", value);
			System.out.println(path);
			try {
				numImg[1] = ImageIO.read(new File(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
			numImg[0] = null;
			numImg[1] = numImg[1].getScaledInstance(button_width / 4, button_width / 4,
					java.awt.Image.SCALE_SMOOTH);
		} else {
			String[] path = new String[2];
			path[0] = String.format("./images/numbers/%d.png", value / 10);
			path[1] = String.format("./images/numbers/%d.png", value - ((value / 10) * 10));
			System.out.println(Arrays.toString(path));
			try {
				numImg[0] = ImageIO.read(new File(path[0]));
				numImg[1] = ImageIO.read(new File(path[1]));
			} catch (IOException e) {
				e.printStackTrace();
			}
			numImg[0] = numImg[0].getScaledInstance(button_width / 4, button_width / 4,
					java.awt.Image.SCALE_SMOOTH);
			numImg[1] = numImg[1].getScaledInstance(button_width / 4, button_width / 4,
					java.awt.Image.SCALE_SMOOTH);
		}
	}
	
	public boolean isCorrect() {
		return isCorrect;
	}
}
