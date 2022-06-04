package gameEngine;

import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import component.Dot;

public class GameTimer extends JPanel {
	public Dot[] dots;
	public int timeleft;

	public GameTimer(int second) {
		this.setSize(Dot.getWidth() * 10, Dot.getHeigth());
		this.setLayout(null);
		newTimer(second);
	}

	private void newTimer(int second) {
		dots = new Dot[10];
		timeleft = second;
		for(int i = 0; i < dots.length; ++i) {
			if(i < second)
				dots[i] = new Dot(true);
			else
				dots[i] = new Dot(false);
		}
	}

	public void paint(Graphics g) {
		if(dots != null) {
			for(int i = 0; i < dots.length; ++i) {
				g.drawImage(dots[i].current_dot, Dot.getWidth() * i, 0, this);
			}
		}
	}
}
