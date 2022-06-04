package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import graphic.GamePanel;
import graphic.MenuPanel;

import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;

public class GUI {
	private JFrame frame;
	private JPanel runningPanel;
	private MenuPanel manuPanel;
	private GamePanel gamePanel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GUI() {
		initialize();
	}

	private void initialize() {
		manuPanel = new MenuPanel();
		frame = new JFrame();
		frame.setBounds(100, 100, 1440, 1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		runningPanel = (JPanel) frame.getContentPane();
		runningPanel.add(manuPanel);

		manuPanel.start_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamePanel = new GamePanel();
				runningPanel.removeAll();
				runningPanel.add(gamePanel);
				runningPanel.revalidate();
				runningPanel.repaint();
			}
		});

	}

}
