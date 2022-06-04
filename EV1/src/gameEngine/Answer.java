package gameEngine;

import javax.swing.JPanel;

import component.AnwserComponant;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Answer extends JPanel {

	private int correct_value;
	private int[] incorrect_value;
	public AnwserComponant[] allAnswers;
	private Image img;

	public Answer(int question_result) {
		this.setSize(800, 200);
		this.setLayout(null);
		newAnwser(question_result);
	}

	private void newAnwser(int question_result) {
		img = new ImageIcon(this.getClass().getResource("/anwser_button.png")).getImage();
		img = img.getScaledInstance(this.getHeight(), this.getHeight(), java.awt.Image.SCALE_SMOOTH);

		correct_value = question_result;
		incorrect_value = new int[3];
		do {
			for (int i = 0; i < incorrect_value.length; ++i) {
				do {
					incorrect_value[i] = question_result + (int) (Math.random() * 41 - 20);
				} while (incorrect_value[i] == question_result || incorrect_value[i] < 0);
			}
		} while (hasSameValue(incorrect_value));

		allAnswers = new AnwserComponant[4];
		allAnswers[0] = new AnwserComponant(correct_value, true, 200);
		for (int i = 1; i < allAnswers.length; ++i)
			allAnswers[i] = new AnwserComponant(incorrect_value[i - 1], false, 200);
		this.shuffle();

		
		for(int i = 0 ; i < allAnswers.length ; ++i) {
			allAnswers[i].setBounds(200 * i, 0, 200, 200);
			allAnswers[i].setIcon(new ImageIcon(img));
			add(allAnswers[i]);
		}
		
		stayInPosition(allAnswers);
	}

	private void shuffle() {
		AnwserComponant[] clone = this.allAnswers.clone();
		AnwserComponant buffer;
		int rounds = (int) (Math.random() * 16 + 5);
		int index1;
		int index2;
		for (int i = 0; i < rounds; ++i) {
			do {
				index1 = (int) (Math.random() * 4);
				index2 = (int) (Math.random() * 4);
			} while (index1 == index2);
			buffer = clone[index1];
			clone[index1] = clone[index2];
			clone[index2] = buffer;
		}
		this.allAnswers = clone;
	}

	private boolean hasSameValue(int[] incorrectValue) {
		for (int i = 0; i < incorrectValue.length; ++i) {
			for (int j = 0; j < incorrectValue.length; ++j) {
				if (i != j)
					if (incorrectValue[i] == incorrectValue[j])
						return true;
			}
		}
		return false;
	}

	private void stayInPosition(AnwserComponant []answer_button) {
		for(AnwserComponant answerBuffer : allAnswers) {
			answerBuffer.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					repaint();
				}
			});
			answerBuffer.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseExited(MouseEvent e) {
					repaint();
				}
			});
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (allAnswers != null) {
			for (int i = 0; i < allAnswers.length; ++i) {
				if (allAnswers[i] != null) {
					if (allAnswers[i].numImg[0] != null) {
						g.drawImage(allAnswers[i].numImg[0], (200 * i) + 50, 50, this);
						g.drawImage(allAnswers[i].numImg[1], (200 * i) + 100, 50, this);
					} else g.drawImage(allAnswers[i].numImg[1], (200 * i) + 75, 50, this);
				}
			}
		}
	}
}
