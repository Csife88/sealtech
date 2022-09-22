package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import controller.DatabaseCon;

public class LoginPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image img_logo = new ImageIcon(LoginPage.class.getResource("img/main.png")).getImage()
			.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
	private Image img_lakat = new ImageIcon(LoginPage.class.getResource("img/lakat.png")).getImage()
			.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

	private JPanel contentPane;
	private JTextField UID;
	private JPasswordField uPass;
	JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("");
	JLabel userName = DefaultComponentFactory.getInstance().createLabel("");
	JLabel password = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lakat = DefaultComponentFactory.getInstance().createLabel("");
	JButton loginBtn = new JButton("");

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	

	/**
	 * Launch the application. i
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public LoginPage() throws IOException {
		
		DatabaseCon db = new DatabaseCon();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 684, 361);
		contentPane.add(panel);
		panel.setLayout(null);

		lblNewJgoodiesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewJgoodiesLabel.setBounds(56, 90, 163, 180);
		panel.add(lblNewJgoodiesLabel);
		lblNewJgoodiesLabel.setIcon(new ImageIcon(img_logo));

		userName.setFont(new Font("Gadugi", Font.BOLD, 16));
		userName.setForeground(new Color(75, 0, 130));
		userName.setBounds(290, 150, 140, 30);
		userName.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(userName);
		userName.setText("Felhasználó név");

		password.setFont(new Font("Gadugi", Font.BOLD, 16));
		password.setForeground(new Color(75, 0, 130));
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setBounds(252, 200, 140, 30);
		panel.add(password);
		password.setText("Jelszó");

		UID = new JTextField();
		UID.setBounds(459, 153, 160, 30);
		UID.setFont(new Font("Gadugi", Font.BOLD, 16));
		UID.setForeground(new Color(75, 0, 130));
		panel.add(UID);
		UID.setColumns(10);

		uPass = new JPasswordField();
		uPass.setBounds(459, 203, 160, 30);
		panel.add(uPass);

		lakat.setHorizontalAlignment(SwingConstants.CENTER);
		lakat.setBounds(483, 61, 113, 58);
		panel.add(lakat);
		lakat.setIcon(new ImageIcon(img_lakat));

		JButton loginBtn = new JButton("");
		loginBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String Query = "select * from pls.admin where AdminName='" + UID.getText() + "' and AdminPass='"
						+ uPass.getText() + "'";
				try {

					try {
						con = DriverManager.getConnection(DatabaseCon.getUrl(), DatabaseCon.getName(),
								DatabaseCon.getPassword());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					St = con.createStatement();
					Rs = St.executeQuery(Query);
					if (Rs.next()) {
						new Stock().setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Helyeten felhasználói név vagy jelszó");
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		loginBtn.setBounds(459, 262, 160, 30);
		loginBtn.setFont(new Font("Gadugi", Font.BOLD, 16));
		loginBtn.setForeground(new Color(75, 0, 130));
		panel.add(loginBtn);
		loginBtn.setText("Bejelentkezés");
		System.out.println("hello");
	}
}
