package Design;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import Classes.ProductionRowCount;
import Classes.QantityCount;
import Classes.ReadSendHeatTreatment;
import Classes.SelectProduct;
import Classes.UpdateCopyTable;
import Classes.UpdateStatusForArrivedOnHeatTreatmentTable;
import Classes.SendHeatTreatmentQuantitys;
import Classes.UpdataArrivedHeatTreatment;
import Classes.UpdataDateArriveHeatTreatment;
import Classes.UpdataDateArriveHeatTreatmentCopy;
import Classes.UpdataHeatTreatmentNumberOnHeatTreatmentTable;
import Classes.UpdataMade;
import Classes.UpdataSentHeatTreatment;
import Classes.AfterHeatTreatment;
import Classes.BeforeHeatTreatmentQuntityForStockTable;
import Classes.CalculateProductQuntityMinuszSendHeatTreatment;
import Classes.FillCopyHeatTreatmentTable;
import Classes.HeatTreatmentRowCount;
import net.proteanit.sql.DbUtils;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HeatTreatment extends JFrame {

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;

	QantityCount qc = new QantityCount();
	UpdataMade um = new UpdataMade();
	SendHeatTreatmentQuantitys shtq = new SendHeatTreatmentQuantitys();
	CalculateProductQuntityMinuszSendHeatTreatment calc = new CalculateProductQuntityMinuszSendHeatTreatment();
	UpdateCopyTable uct = new UpdateCopyTable();
	UpdataSentHeatTreatment ush = new UpdataSentHeatTreatment();
	UpdataArrivedHeatTreatment uah = new UpdataArrivedHeatTreatment();
	AfterHeatTreatment aht = new AfterHeatTreatment();
	SelectProduct sp = new SelectProduct();
	BeforeHeatTreatmentQuntityForStockTable bhtqfst = new BeforeHeatTreatmentQuntityForStockTable();
	ReadSendHeatTreatment rsht = new ReadSendHeatTreatment();
	UpdateStatusForArrivedOnHeatTreatmentTable usfaoht = new UpdateStatusForArrivedOnHeatTreatmentTable();
	UpdataHeatTreatmentNumberOnHeatTreatmentTable uhtnohtt = new UpdataHeatTreatmentNumberOnHeatTreatmentTable();
	UpdataDateArriveHeatTreatment  udaht = new  UpdataDateArriveHeatTreatment(); 
	FillCopyHeatTreatmentTable fchtt = new  FillCopyHeatTreatmentTable();
	UpdataDateArriveHeatTreatmentCopy udahtc = new UpdataDateArriveHeatTreatmentCopy();
	
	SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date(System.currentTimeMillis());

	private JPanel contentPane;
	JLabel lbl1 = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lbl2 = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lbl3 = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lbl4 = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lbl5 = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lbl6 = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lbl7 = DefaultComponentFactory.getInstance().createLabel("");
	private final JTable table = new JTable();
	private final JTable table_1 = new JTable();
	private final JTable table_2 = new JTable();
	private final JTable table_3 = new JTable();
	private final JTable table_4 = new JTable();
	private final JTable table_5 = new JTable();
	private final JLabel lblHkezelben = new JLabel("H\u0151kezel\u0151ben");
	private final JLabel lblHkezelt = new JLabel("H\u0151kezelt");
	private final JLabel lblNewLabel_1 = new JLabel("Cikksz\u00E1m");
	public JComboBox comboBox_1 = new JComboBox();
	private final JLabel lblNewLabel_1_1 = new JLabel("K\u00FCld\u00E9s d\u00E1tuma");
	private JTextField sendDateTxt;
	private JTextField quantityTxt;
	private final JScrollPane scrollPane = new JScrollPane();
	private final JScrollPane scrollPane_1 = new JScrollPane();
	private final JScrollPane scrollPane_2 = new JScrollPane();
	boolean isSend = true;
	public String heatId;
	private JTextField HeatNumberText;
	private JTextField arrivText;


	public void sendHeatTreatment() {

		int year = Calendar.getInstance().get(Calendar.YEAR);

		HeatTreatmentRowCount htrc = new HeatTreatmentRowCount();
		int id = htrc.getRowCount();
		int number = htrc.getRowCount();

		if (Integer.valueOf(quantityTxt.getText()) > qc.madeQuntityCount(comboBox_1)) {
			JOptionPane.showMessageDialog(null, "Küldés sikertelen");
			isSend = false;

		} else {

			try {
				con = DriverManager.getConnection("jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls", "admin", "Szemes1!");
				PreparedStatement add = con.prepareStatement("insert into heattreatment values(?,?,?,?,?,?,?)");

				String heatnumber = Integer.toString(year) + "/" + number;

				add.setInt(1, id );
				add.setString(2, comboBox_1.getModel().getSelectedItem().toString());
				add.setDate(3, java.sql.Date.valueOf(sendDateTxt.getText())); // java.sql.Date
				add.setInt(4, Integer.valueOf(quantityTxt.getText()));
				add.setDate(5, java.sql.Date.valueOf(sendDateTxt.getText()));
				add.setString(6, heatnumber);
				add.setString(7, "Sent");
				

				int row = add.executeUpdate();
				JOptionPane.showMessageDialog(null, "Küldés sikeres");
				con.close();

		     	bhtqfst.beferoHeatTreatment(table);
				isSend = true;
				

			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			fchtt.FillCopyTable(comboBox_1, sendDateTxt, quantityTxt);
		}

	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
					HeatTreatment frame = new HeatTreatment();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public HeatTreatment() {
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

		// ---------------------------------------- Mouse entered and exited
		// ---------------------------------------
		lbl1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lbl1.setBorder(UIManager.getBorder("CheckBox.border"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lbl1.setBorder(new LineBorder(new Color(0, 0, 0)));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				new Production().setVisible(true);
				dispose();
			}
		});

		lbl2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lbl2.setBorder(UIManager.getBorder("CheckBox.border"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lbl2.setBorder(new LineBorder(new Color(0, 0, 0)));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				new Stock().setVisible(true);
				dispose();
			}
		});

		lbl3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lbl3.setBorder(UIManager.getBorder("CheckBox.border"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lbl3.setBorder(new LineBorder(new Color(0, 0, 0)));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				new RawMetarial().setVisible(true);
				dispose();
			}
		});

		lbl4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lbl4.setBorder(UIManager.getBorder("CheckBox.border"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lbl4.setBorder(new LineBorder(new Color(0, 0, 0)));
			}
		});

		lbl5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lbl5.setBorder(UIManager.getBorder("CheckBox.border"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lbl5.setBorder(new LineBorder(new Color(0, 0, 0)));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				new Selection().setVisible(true);
				dispose();
			}
		});

		lbl6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lbl6.setBorder(UIManager.getBorder("CheckBox.border"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lbl6.setBorder(new LineBorder(new Color(0, 0, 0)));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				new Order().setVisible(true);
				dispose();
			}
		});

		lbl7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lbl7.setBorder(UIManager.getBorder("CheckBox.border"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lbl7.setBorder(new LineBorder(new Color(0, 0, 0)));
			}
		});

		// --------------------------------------------------------------------------------------------------------

		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl1.setText("Gyártás");
		lbl1.setFont(new Font("Century", Font.BOLD, 17));
		lbl1.setForeground(new Color(75, 0, 130));
		lbl1.setBounds(0, 0, 193, 100);
		panel.add(lbl1);
		
		lbl2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl2.setText("Készletek");
		lbl2.setFont(new Font("Century", Font.BOLD, 17));
		lbl2.setForeground(new Color(75, 0, 130));
		lbl2.setBounds(193, 0, 193, 100);
		panel.add(lbl2);
		
		lbl3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl3.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl3.setText("Alapanyagok");
		lbl3.setFont(new Font("Century", Font.BOLD, 17));
		lbl3.setForeground(new Color(75, 0, 130));
		lbl3.setBounds(386, 0, 193, 100);
		panel.add(lbl3);
		
		lbl4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl4.setText("Hõkezelés");
		lbl4.setFont(new Font("Century", Font.BOLD, 17));
		lbl4.setForeground(new Color(75, 0, 130));
		lbl4.setBounds(579, 0, 193, 100);
		panel.add(lbl4);
		
		lbl5.setHorizontalAlignment(SwingConstants.CENTER);
		lbl5.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl5.setText("V\u00E1logat\u00E1s");
		lbl5.setFont(new Font("Century", Font.BOLD, 17));
		lbl5.setForeground(new Color(75, 0, 130));
		lbl5.setBounds(772, 0, 193, 100);
		panel.add(lbl5);
		
		lbl6.setHorizontalAlignment(SwingConstants.CENTER);
		lbl6.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl6.setText("Kiszállítás");
		lbl6.setFont(new Font("Century", Font.BOLD, 17));
		lbl6.setForeground(new Color(75, 0, 130));
		lbl6.setBounds(965, 0, 193, 100);
		panel.add(lbl6);
		
		lbl7.setHorizontalAlignment(SwingConstants.CENTER);
		lbl7.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl7.setText("Címkék szállítólevek");
		lbl7.setFont(new Font("Century", Font.BOLD, 17));
		lbl7.setForeground(new Color(75, 0, 130));
		lbl7.setBounds(1158, 0, 197, 100);
		panel.add(lbl7);
		
		table.setBounds(2, 165, 1350, 130);
		table.setFont(new Font("Tahoma", Font.BOLD, 14));

		panel.add(table);
		table_1.setBounds(2, 393, 1350, 130);
		table_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		panel.add(table_1);
		table_2.setBounds(0, 611, 1350, 130);
		table_2.setFont(new Font("Tahoma", Font.BOLD, 14));

		panel.add(table_2);
		scrollPane_1.setBounds(2, 145, 1350, 20);

		panel.add(scrollPane_1);
		scrollPane_1.setViewportView(table_3);
		table_3.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Cikkszám", "Darabszám" }));
		scrollPane.setBounds(2, 373, 1350, 20);

		panel.add(scrollPane);
		scrollPane.setViewportView(table_4);
		table_4.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Sorszám", "Cikkszám", "Küldés dátuma", "Darabszám", "Hõkezelés száma" }));
		scrollPane_2.setBounds(2, 590, 1350, 20);

		panel.add(scrollPane_2);
		scrollPane_2.setViewportView(table_5);
		table_5.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Sorszám","Cikkszám", "Érkezés dátuma", "Darabszám", "Hõkezelés száma" }));

		JLabel lblNewLabel = new JLabel("Hõkezelésre vár");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(530, 105, 228, 43);
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Century", Font.BOLD, 18));
		panel.add(lblNewLabel);
		lblHkezelben.setHorizontalAlignment(SwingConstants.CENTER);
		lblHkezelben.setForeground(Color.DARK_GRAY);
		lblHkezelben.setFont(new Font("Century", Font.BOLD, 18));
		lblHkezelben.setBounds(530, 290, 228, 43);

		panel.add(lblHkezelben);
		lblHkezelt.setHorizontalAlignment(SwingConstants.CENTER);
		lblHkezelt.setForeground(Color.DARK_GRAY);
		lblHkezelt.setFont(new Font("Century", Font.BOLD, 18));
		lblHkezelt.setBounds(530, 520, 228, 43);

		panel.add(lblHkezelt);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setFont(new Font("Century", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 330, 114, 29);

		panel.add(lblNewLabel_1);

		comboBox_1.setForeground(Color.DARK_GRAY);
		comboBox_1.setFont(new Font("Century", Font.BOLD, 16));
		comboBox_1.setBounds(120, 330, 220, 30);
		panel.add(comboBox_1);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1.setFont(new Font("Century", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(350, 330, 186, 29);

		panel.add(lblNewLabel_1_1);

		sendDateTxt = new JTextField();
		sendDateTxt.setToolTipText("1999-12-31");
		sendDateTxt.setColumns(10);
		sendDateTxt.setBounds(513, 330, 220, 30);
		sendDateTxt.setFont(new Font("Century", Font.BOLD, 16));
		sendDateTxt.setText(date.toString());
		panel.add(sendDateTxt);

		JLabel lblNewLabel_1_1_1 = new JLabel("Darabsz\u00E1m");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1_1.setFont(new Font("Century", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(705, 330, 186, 29);
		panel.add(lblNewLabel_1_1_1);

		quantityTxt = new JTextField();
		quantityTxt.setToolTipText("1999-12-31");
		quantityTxt.setColumns(10);
		quantityTxt.setBounds(847, 330, 220, 30);
		quantityTxt.setFont(new Font("Century", Font.BOLD, 16));
		panel.add(quantityTxt);

		JButton btnKlds = new JButton("K\u00FCld\u00E9s");
		btnKlds.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sendHeatTreatment();

				if (!isSend) {
					JOptionPane.showMessageDialog(null, "Nincs elegendõ gyártott mennyiség ehhez a küldéshez");
				} else {

					uct.UpdateColumnToArray(calc.ProdQuntityMinuszSendHeatTreatment(comboBox_1), comboBox_1);

					/*
					 * régi megoldás if
					 * (comboBox_1.getModel().getSelectedItem().toString().equals("HU093133-0820"))
					 * {
					 * //uct.UpdateColumnToArray0820(calc.ProdQuntityMinuszSendHeatTreatment0820());
					 * } else if
					 * (comboBox_1.getModel().getSelectedItem().toString().equals("HU093133-0890"))
					 * {
					 * //uct.UpdateColumnToArray0890(calc.ProdQuntityMinuszSendHeatTreatment0890());
					 * } else if
					 * (comboBox_1.getModel().getSelectedItem().toString().equals("HU093133-0960"))
					 * {
					 * //uct.UpdateColumnToArray0960(calc.ProdQuntityMinuszSendHeatTreatment0960());
					 * } else if
					 * (comboBox_1.getModel().getSelectedItem().toString().equals("HU093133-0970"))
					 * { uct.UpdateColumnToArray0970(calc.ProdQuntityMinuszSendHeatTreatment0970());
					 * } else if
					 * (comboBox_1.getModel().getSelectedItem().toString().equals("HU098065-0020"))
					 * {
					 * //uct.UpdateColumnToArray0020(calc.ProdQuntityMinuszSendHeatTreatment0020());
					 * } else if
					 * (comboBox_1.getModel().getSelectedItem().toString().equals("HU098065-0030"))
					 * {
					 * //uct.UpdateColumnToArray0030(calc.ProdQuntityMinuszSendHeatTreatment0030());
					 * }
					 */
				}
				ush.UpdataSentQuantity(comboBox_1);
				um.UpdataDataMade(comboBox_1);
			    bhtqfst.beferoHeatTreatment(table);
				rsht.getSendHeatTreatment(table_1);

			}
		});
		btnKlds.setForeground(Color.DARK_GRAY);
		btnKlds.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnKlds.setBounds(1110, 330, 228, 30);
		panel.add(btnKlds);

		JButton btnBerkeztets = new JButton("Be\u00E9rkeztet\u00E9s");
		btnBerkeztets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBerkeztets.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(heatId != null) {
					usfaoht.afterHeatTreatment(heatId);
					udaht.UpdataData(heatId, arrivText);
					udahtc.UpdataDataCopyTable(heatId, arrivText);
					uhtnohtt.heatNumber(heatId, HeatNumberText);
					rsht.getSendHeatTreatment(table_1);
					aht.getafterHeatTreatment(table_2);
					uah.UpdataArrived(comboBox_1);
					ush.UpdataSentQuantity(comboBox_1);
				}else			
				{
				JOptionPane.showMessageDialog(null, "Hoppá valami nem jó!");
				}
			}
		});
		btnBerkeztets.setForeground(Color.DARK_GRAY);
		btnBerkeztets.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBerkeztets.setBounds(1110, 550, 228, 30);
		panel.add(btnBerkeztets);
		
	
		

		table_1.addMouseListener(new MouseAdapter() {// Sor számának megállapitása amire kattintottak
			@Override
			public void mouseClicked(MouseEvent e) {
				int row=0;
				DefaultTableModel model = (DefaultTableModel) table_1.getModel();
				row = table_1.getSelectedRow();
				heatId = model.getValueAt(row, 0).toString();
				comboBox_1.setSelectedItem(model.getValueAt(row, 1)); // combobox kiválaszott elem átálltása ArriveHeatTreatmenthez
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row=0;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				row = table.getSelectedRow();
				heatId = model.getValueAt(row, 0).toString();
				comboBox_1.setSelectedItem(model.getValueAt(row, 0)); // combobox kiválaszott elem átálltása ArriveHeatTreatmenthez
			}
		});

		bhtqfst.beferoHeatTreatment(table);
		sp.selectProduct(comboBox_1);
		rsht.getSendHeatTreatment(table_1);
		aht.getafterHeatTreatment(table_2);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("H\u0151kezel\u00E9s sz\u00E1ma");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1_1_1.setFont(new Font("Century", Font.BOLD, 16));
		lblNewLabel_1_1_1_1.setBounds(2, 555, 186, 29);
		panel.add(lblNewLabel_1_1_1_1);
		
		HeatNumberText = new JTextField();
		HeatNumberText.setFont(new Font("Century", Font.BOLD, 16));
		HeatNumberText.setBounds(176, 555, 150, 30);
		panel.add(HeatNumberText);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("\u00C9rkez\u00E9s d\u00E1tuma");
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1_2.setFont(new Font("Century", Font.BOLD, 16));
		lblNewLabel_1_1_2.setBounds(336, 555, 186, 29);
		panel.add(lblNewLabel_1_1_2);
		
		arrivText = new JTextField();
		arrivText.setToolTipText("1999-12-31");
		arrivText.setText(date.toString());
		arrivText.setFont(new Font("Century", Font.BOLD, 16));
		arrivText.setColumns(10);
		arrivText.setBounds(513, 555, 220, 30);
		panel.add(arrivText);
		

	}
}
