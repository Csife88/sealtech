package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Statements extends JFrame {
	
	private static final long serialVersionUID = 1L;


	private JPanel contentPane;
	
	public Statements() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1366, 768);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1366, 768);
		contentPane.add(panel);
		panel.setLayout(null);
		new Menu(panel, this);
		
	}
}
