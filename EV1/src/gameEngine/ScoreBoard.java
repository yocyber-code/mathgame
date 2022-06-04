package gameEngine;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ScoreBoard extends JPanel {
	private int scores;
	private Image scoresImg[];
	private Image labelImg;

	public ScoreBoard() {
		this.setSize(300, 100);
		setLayout(null);
		scores = 0;
		labelImg = new ImageIcon(this.getClass().getResource("/scores.png")).getImage();
		labelImg = labelImg.getScaledInstance((this.getWidth() / 3) * 2, this.getHeight(), java.awt.Image.SCALE_SMOOTH);
		newScore();
	}
	
	public ScoreBoard(int total_score) {
		this.setSize(750, 250);
		setLayout(null);
		scores = total_score;
		labelImg = new ImageIcon(this.getClass().getResource("/scores.png")).getImage();
		labelImg = labelImg.getScaledInstance((this.getWidth() / 3) * 2, this.getHeight(), java.awt.Image.SCALE_SMOOTH);
		newScore();
	}

	private void newScore() {
		scoresImg = new Image[2];

		if(scores < 10) {
			String path = "";
			path = String.format("./images/numbers/%d.png", scores);
			System.out.println(path);
			try {
				scoresImg[1] = ImageIO.read(new File(path));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			scoresImg[0] = null;
			scoresImg[1] = scoresImg[1].getScaledInstance(this.getHeight() / 2, this.getHeight() / 2,
					java.awt.Image.SCALE_SMOOTH);
		}
		else {
			String[] path = new String[2];
			path[0] = String.format("./images/numbers/%d.png", scores / 10);
			path[1] = String.format("./images/numbers/%d.png", scores - ((scores / 10) * 10));
			System.out.println(Arrays.toString(path));
			try {
				scoresImg[0] = ImageIO.read(new File(path[0]));
				scoresImg[1] = ImageIO.read(new File(path[1]));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			scoresImg[0] = scoresImg[0].getScaledInstance(this.getHeight() / 2, this.getHeight() / 2,
					java.awt.Image.SCALE_SMOOTH);
			scoresImg[1] = scoresImg[1].getScaledInstance(this.getHeight() / 2, this.getHeight() / 2,
					java.awt.Image.SCALE_SMOOTH);
		}
	}

	public void updateScore(boolean correct) {
		if(correct)
			scores += 3;
		else
			scores -= 1;
		if(scores < 0) scores = 0;
		System.out.println("");
		System.out.println("score is " + getScores());
		System.out.println("");
		newScore();
	}

	public void paint(Graphics g) {
		g.drawImage(labelImg, 0, 0, this);

		if(scoresImg != null) {
			if(scoresImg != null) {
				if(scoresImg[0] != null) {
					g.drawImage(scoresImg[0], labelImg.getWidth(null), (int)(25 * this.getWidth()/300), this);
					g.drawImage(scoresImg[1], labelImg.getWidth(null) + (int)(50 * this.getWidth()/300) ,(int)(25 * this.getWidth()/300), this);
				}
				else
					g.drawImage(scoresImg[1], labelImg.getWidth(null) + 25,(int)(25 * this.getWidth()/300), this);
			}
		}
	}
	
	public int getScores() {
		return scores;
	}
}
