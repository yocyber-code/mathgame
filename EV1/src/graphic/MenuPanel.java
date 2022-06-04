package graphic;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {

	private Image image;
	public JButton start_button;

	public MenuPanel() {
		this.setSize(1440, 1080);
		setLayout(null);
		JLabel title_lebel = new JLabel("Quick Mat(c)h");
		title_lebel.setForeground(Color.GRAY);
		title_lebel.setFont(new Font("Sitka Heading", Font.BOLD | Font.ITALIC, 91));
		title_lebel.setBounds(194, 186, 571, 114);
		this.add(title_lebel);

		JLabel cartoon_1 = new JLabel("");
		cartoon_1.setBounds(208, 440, 476, 476);
		image = new ImageIcon(this.getClass().getResource("/cartoon.png")).getImage();
		image = image.getScaledInstance(cartoon_1.getWidth(), cartoon_1.getHeight(), java.awt.Image.SCALE_SMOOTH);
		cartoon_1.setIcon(new ImageIcon(image));
		this.add(cartoon_1);
		
		start_button = new JButton("");
		start_button.setBounds(598, 534, 453, 185);
		image = new ImageIcon(this.getClass().getResource("/start_button.png")).getImage();
		image = image.getScaledInstance(start_button.getWidth(), start_button.getHeight(),
				java.awt.Image.SCALE_SMOOTH);
		start_button.setIcon(new ImageIcon(image));
		this.add(start_button);
		
		JLabel cartoon_2 = new JLabel("");
		cartoon_2.setBounds(814, 45, 476, 562);
		image = new ImageIcon(this.getClass().getResource("/cartoon2.png")).getImage();
		image = image.getScaledInstance(cartoon_2.getWidth(), cartoon_2.getHeight(), java.awt.Image.SCALE_SMOOTH);
		cartoon_2.setIcon(new ImageIcon(image));
		this.add(cartoon_2);
	}
}
