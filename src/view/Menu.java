package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;

public class Menu {

	JLabel lblgyártás = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lblKészletek = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lblAlapanyagok = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lblHőkezelés = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lblVálogatás = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lblKiszállítás = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lblCimke = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lblDenso = DefaultComponentFactory.getInstance().createLabel("");

	public Menu(JPanel panel,JFrame frame) {
		
		
		lblgyártás.setHorizontalAlignment(SwingConstants.CENTER);
		lblgyártás.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblgyártás.setText("Gyártás");
		lblgyártás.setFont(new Font("Century", Font.BOLD, 17));
		lblgyártás.setForeground(new Color(75, 0, 130));
		lblgyártás.setBounds(0, 0, 193, 70);
		panel.add(lblgyártás);

		lblKészletek.setHorizontalAlignment(SwingConstants.CENTER);
		lblKészletek.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblKészletek.setText("Készletek");
		lblKészletek.setFont(new Font("Century", Font.BOLD, 17));
		lblKészletek.setForeground(new Color(75, 0, 130));
		lblKészletek.setBounds(193, 0, 193, 70);
		panel.add(lblKészletek);

		lblAlapanyagok.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlapanyagok.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblAlapanyagok.setText("Kimutatások");
		lblAlapanyagok.setFont(new Font("Century", Font.BOLD, 17));
		lblAlapanyagok.setForeground(new Color(75, 0, 130));
		lblAlapanyagok.setBounds(386, 0, 193, 70);
		panel.add(lblAlapanyagok);

		lblHőkezelés.setHorizontalAlignment(SwingConstants.CENTER);
		lblHőkezelés.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblHőkezelés.setText("Hőkezelés");
		lblHőkezelés.setFont(new Font("Century", Font.BOLD, 17));
		lblHőkezelés.setForeground(new Color(75, 0, 130));
		lblHőkezelés.setBounds(579, 0, 193, 70);
		panel.add(lblHőkezelés);

		lblVálogatás.setHorizontalAlignment(SwingConstants.CENTER);
		lblVálogatás.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblVálogatás.setText("Válogatás");
		lblVálogatás.setFont(new Font("Century", Font.BOLD, 17));
		lblVálogatás.setForeground(new Color(75, 0, 130));
		lblVálogatás.setBounds(772, 0, 193, 70);
		panel.add(lblVálogatás);

		lblKiszállítás.setHorizontalAlignment(SwingConstants.CENTER);
		lblKiszállítás.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblKiszállítás.setText("Kiszállítás");
		lblKiszállítás.setFont(new Font("Century", Font.BOLD, 17));
		lblKiszállítás.setForeground(new Color(75, 0, 130));
		lblKiszállítás.setBounds(965, 0, 193, 70);
		panel.add(lblKiszállítás);

		lblCimke.setHorizontalAlignment(SwingConstants.CENTER);
		lblCimke.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblCimke.setText("Címkék szállítólevek");
		lblCimke.setFont(new Font("Century", Font.BOLD, 17));
		lblCimke.setForeground(new Color(75, 0, 130));
		lblCimke.setBounds(1158, 0, 197, 70);
		panel.add(lblCimke);
		
		lblDenso.setHorizontalAlignment(SwingConstants.CENTER);
		lblDenso.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblDenso.setText("Denso");
		lblDenso.setFont(new Font("Century", Font.BOLD, 14));
		lblDenso.setForeground(new Color(75, 0, 130));
		lblDenso.setBounds(965, 70, 193, 30);
		lblDenso.setVisible(false);
		panel.add(lblDenso);

		
		
		// ---------------------------------------- Mouse entered and exited
				// ---------------------------------------
				lblgyártás.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						lblgyártás.setBorder(UIManager.getBorder("CheckBox.border"));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						lblgyártás.setBorder(new LineBorder(new Color(0, 0, 0)));
					}

					@Override
					public void mouseClicked(MouseEvent e) {
						new Production().setVisible(true);
					      frame.dispose();
					}
				});

				lblKészletek.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						lblKészletek.setBorder(UIManager.getBorder("CheckBox.border"));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						lblKészletek.setBorder(new LineBorder(new Color(0, 0, 0)));
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						new Stock().setVisible(true);
						frame.dispose();
					}
				});

				lblAlapanyagok.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						lblAlapanyagok.setBorder(UIManager.getBorder("CheckBox.border"));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						lblAlapanyagok.setBorder(new LineBorder(new Color(0, 0, 0)));
					}

					@Override
					public void mouseClicked(MouseEvent e) {
						new Statements().setVisible(true);
						frame.dispose();
					}
				});

				lblHőkezelés.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						lblHőkezelés.setBorder(UIManager.getBorder("CheckBox.border"));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						lblHőkezelés.setBorder(new LineBorder(new Color(0, 0, 0)));
					}

					@Override
					public void mouseClicked(MouseEvent e) {
						new HeatTreatment().setVisible(true);
						frame.dispose();
					}
				});

				lblVálogatás.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						lblVálogatás.setBorder(UIManager.getBorder("CheckBox.border"));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						lblVálogatás.setBorder(new LineBorder(new Color(0, 0, 0)));
					}

					@Override
					public void mouseClicked(MouseEvent e) {
						new Selection().setVisible(true);
						frame.dispose();
					}
				});

				lblKiszállítás.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						lblKiszállítás.setBorder(UIManager.getBorder("CheckBox.border"));
					//	lblDenso.setVisible(true);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						lblKiszállítás.setBorder(new LineBorder(new Color(0, 0, 0)));

					}
					@Override
					public void mouseClicked(MouseEvent e) {
						new Delivery().setVisible(true);
					 	frame.dispose();
					}
				});
				
//				lblDenso.addMouseListener(new MouseAdapter() {
//					@Override
//					public void mouseClicked(MouseEvent e) {
//						new Delivery().setVisible(true);
//					 	frame.dispose();
//					}
//				});

				lblCimke.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						lblCimke.setBorder(UIManager.getBorder("CheckBox.border"));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						lblCimke.setBorder(new LineBorder(new Color(0, 0, 0)));
					}
				});

				// --------------------------------------------------------------------------------------------------------
		
	}
	
}
