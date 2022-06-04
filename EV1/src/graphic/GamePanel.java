package graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import component.AnwserComponant;
import gameEngine.Answer;
import gameEngine.GameTimer;
import gameEngine.Question;
import gameEngine.ScoreBoard;

public class GamePanel extends JPanel {
	private Question question;
	private Answer answer;
	private GameTimer timer;
	private ScoreBoard scores;
	private Timer t;
	private static final int full_time = 10;
	private int rounds;

	public GamePanel() {
		this.setSize(1440, 1080);
		setLayout(null);
		rounds = 0;
		setNewQuestion();
	}

	private void getAnswer(AnwserComponant[] allAnswers) {
		for(AnwserComponant answerBuffer : allAnswers) {
			answerBuffer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					t.cancel();
					t = null;
					scores.updateScore(answerBuffer.isCorrect());
					setNewQuestion();
				}
			});
		}
	}

	private void setNewQuestion() {
		question = null;
		answer = null;
		timer = null;
		removeAll();

		rounds += 1;
		if(rounds > 30) {
			scores = new ScoreBoard(scores.getScores());
			scores.setLocation((this.getWidth() / 2) - (scores.getWidth() / 2), 300);
			add(scores);
			revalidate();
			repaint();
			return;
		}

		else {
			if(scores == null) {
				scores = new ScoreBoard();
				scores.setLocation((this.getWidth() / 2) - (scores.getWidth() / 2), 200);
			}
			add(scores);

			question = new Question();
			question.setLocation((this.getWidth() / 2) - (question.getWidth() / 2),
					scores.getY() + scores.getHeight() + 133);
			add(question);

			answer = new Answer(question.getResult());
			answer.setLocation((question.getX() + question.getWidth() / 2) - (answer.getWidth() / 2),
					question.getY() + question.getHeight() + 30);
			add(answer);

			timer = new GameTimer(full_time - (int) (rounds / 3.5));
			timer.setLocation(0, 0);
			add(timer);

			getAnswer(answer.allAnswers);

			revalidate();
			repaint();

			countdown();
		}

	}

	private void countdown() {
		t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				timer.timeleft -= 1;
				timer.dots[timer.timeleft].setDot(false);
				if(timer.timeleft == 0) {
					t.cancel();
					scores.updateScore(false);
					setNewQuestion();
				}
				repaint();
			}
		}, 1000, 1000);
	}
}
