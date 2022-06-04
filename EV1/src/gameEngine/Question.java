package gameEngine;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Question extends JPanel {
	private int first_num;
	private int second_num;
	private int operants;
	private Image buffer;
	private Image firstNumImg;
	private Image secondNumImg;
	private Image operantImg;

	public Question() {
		this.setSize(600, 200);
		this.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
		this.setLayout(null);
		newQuestion();
	}

	private void newQuestion() {
		String[] path = new String[3];
		Arrays.fill(path, "");
		first_num = (int) (Math.random() * 9 + 1);
		second_num = (int) (Math.random() * 9 + 1);
		operants = (int) (Math.random() * 4);
		System.out.println("Anwser is " + this.getResult());
		if (this.getResult() >= 100 || this.getResult() < 0) {
			newQuestion();
			return;
		}
		path[0] = String.format("./images/numbers/%d.png", first_num);
		path[1] = String.format("./images/numbers/%d.png", second_num);
		path[2] = String.format("./images/operants/%d.png", operants);
		System.out.println(Arrays.toString(path));

		firstNumImg = null;
		secondNumImg = null;
		operantImg = null;

		try {
			buffer = ImageIO.read(new File(path[0]));
			firstNumImg = buffer.getScaledInstance(this.getWidth() / 3, this.getHeight(), java.awt.Image.SCALE_SMOOTH);
			buffer = ImageIO.read(new File(path[1]));
			secondNumImg = buffer.getScaledInstance(this.getWidth() / 3, this.getHeight(), java.awt.Image.SCALE_SMOOTH);
			buffer = ImageIO.read(new File(path[2]));
			operantImg = buffer.getScaledInstance(this.getWidth() / 3, this.getHeight(), java.awt.Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//repaint();
	}

	public int getResult() {
		switch (operants) {
		case 0:
			return first_num + second_num;
		case 1:
			return first_num - second_num;
		case 2:
			return first_num * second_num;
		case 3:
			if (second_num == 0)
				return Integer.MIN_VALUE;
			double result = (double)first_num / second_num;
			if(result % 1 == 0)
				return (int)result;
			return Integer.MIN_VALUE;
		default:
			return Integer.MIN_VALUE;
		}

	}

	public void paint(Graphics g) {
		if (firstNumImg != null)
			g.drawImage(firstNumImg, 0, 0, this);
		if (operantImg != null)
			g.drawImage(operantImg, firstNumImg.getWidth(null), 0, this);
		if (secondNumImg != null)
			g.drawImage(secondNumImg, firstNumImg.getWidth(null) * 2, 0, this);
	}

}
