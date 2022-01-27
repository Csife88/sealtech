package Design;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.CellConstraints;

import Classes.AfterHeatTreatment;
import Classes.ArrivedHeatQuantitys;
import Classes.BadPartQuantitys;
import Classes.GetPartNumber;
import Classes.HeatTreatmentIDs;
import Classes.QantityCount;
import Classes.ReadSelectionTable;
import Classes.SelectProduct;
import Classes.SelectionpartsRowCount;
import Classes.UpdataArrivedHeatTreatment;
import Classes.UpdataStockForSelectionPart;
import net.proteanit.sql.DbUtils;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Calendar;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class Selection extends JFrame {

	SelectionpartsRowCount src = new SelectionpartsRowCount();
	UpdataStockForSelectionPart usfs = new UpdataStockForSelectionPart();
	UpdataArrivedHeatTreatment uah = new UpdataArrivedHeatTreatment();
	SelectProduct sp = new SelectProduct();
	ReadSelectionTable rst = new ReadSelectionTable();
	QantityCount qc = new QantityCount();
	AfterHeatTreatment aht = new AfterHeatTreatment();

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;

	private JPanel contentPane;
	JLabel lbl1 = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lbl2 = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lbl3 = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lbl4 = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lbl5 = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lbl6 = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lbl7 = DefaultComponentFactory.getInstance().createLabel("");
	JComboBox comboBox = new JComboBox();
	private JTable table;
	private JTable table_1;
	private final JScrollPane scrollPane = new JScrollPane();

	private JTextField goodPartText;
	private JTextField deliveryText;
	private final JTextField badPartText = new JTextField();
	private final JLabel lblNewLabel = new JLabel("Cikkszám");
	private final JLabel lblSzlltlevlSzma = new JLabel("H\u0151kezel\u00E9s sz\u00E1ma");
	private final JLabel lblJ = new JLabel("J\u00F3");
	private final JLabel lblNewLabel_1_1 = new JLabel("Selejt");
	private final JButton btnKsz = new JButton("K\u00E9sz");
	private final JTable table_2 = new JTable();
	private final JTable table_3 = new JTable();
	private final JScrollPane scrollPane_1 = new JScrollPane();
	public String SelectionId;
	public String Quntity;
	
	public static int goodPart;
	public static int badPart;

	public static String getNumber;
	public static int heatedQuantity;
	public static int goodPluszBad;
	public static String dText;

	
	private int remaining;

	public void fillSelectionTable(int goodPart, int badPart, String getNumber,String dText) { 
		try {
			con = DriverManager.getConnection("jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls",
					"admin", "Szemes1!");
			PreparedStatement add = con.prepareStatement("insert into selectionparts values(?,?,?,?,?,?)");

			int id = src.SelectionRowCount();

			long millis = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis); // aktuális dátum date objektumba

			add.setInt(1, id);
			add.setString(2,getNumber);
			add.setDate(3, date);
			add.setInt(4, goodPart );
			add.setInt(5, badPart);
			add.setString(6, dText);

			int row = add.executeUpdate();
			JOptionPane.showMessageDialog(null, "Küldés sikeres");
			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void refressArrivedHeatTreatmentQuantity() {

		HeatTreatmentIDs hti = new HeatTreatmentIDs();
		ArrivedHeatQuantitys arrive = new ArrivedHeatQuantitys();
		
		goodPluszBad = Integer.parseInt(goodPartText.getText()) + Integer.parseInt(badPartText.getText());

		int remaining = 0;

		for (int i = 0; i < hti.getListID(comboBox).size(); i++) {

			int id = hti.getListID(comboBox).get(i);

			if (goodPluszBad >= arrive.getListQuntity(comboBox).get(i)) {

				remaining = 0;
				goodPluszBad = goodPluszBad - arrive.getListQuntity(comboBox).get(i);

			} else {

				remaining = arrive.getListQuntity(comboBox).get(i) - goodPluszBad;
				goodPluszBad = 0;
			}

			try {
				con = DriverManager.getConnection(
						"jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls", "admin", "Szemes1!");
				String Query = "Update pls.heattreatment set quantity='" + remaining + "'" + "where ID='" + id + "'"+"AND Status='"+"Arrived"+"'";
				Statement Add = con.createStatement();
				Add.executeUpdate(Query);

			} catch (Exception el) {

				el.printStackTrace();

			}

		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
					Selection frame = new Selection();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Selection() {

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

			@Override
			public void mouseClicked(MouseEvent e) {
				new HeatTreatment().setVisible(true);
				dispose();
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

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = 0;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				row = table.getSelectedRow();
				comboBox.setSelectedItem(model.getValueAt(row, 1));
				deliveryText.setText(model.getValueAt(row, 4).toString());
				SelectionId = model.getValueAt(row, 0).toString();
				Quntity = model.getValueAt(row, 3).toString();

			}
		});
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setBounds(2, 223, 1350, 175);
		panel.add(table);
		scrollPane.setBounds(2, 198, 1350, 25);

		panel.add(scrollPane);

		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Sorszám", "Cikkszám", "Érkezés dátuma", "Darabszám", "Szállítólevél száma" }));

		aht.getafterHeatTreatment(table);

		JLabel lblHkezelt = new JLabel("H\u0151kezelt");
		lblHkezelt.setHorizontalAlignment(SwingConstants.CENTER);
		lblHkezelt.setForeground(Color.DARK_GRAY);
		lblHkezelt.setFont(new Font("Century", Font.BOLD, 20));
		lblHkezelt.setBounds(530, 144, 228, 43);
		panel.add(lblHkezelt);

		goodPartText = new JTextField();
		goodPartText.setBounds(677, 444, 150, 35);
		goodPartText.setFont(new Font("Century", Font.BOLD, 16));
		goodPartText.setHorizontalAlignment(SwingConstants.CENTER);
		goodPartText.setText("0");
		panel.add(goodPartText);

		deliveryText = new JTextField();
		deliveryText.setColumns(10);
		deliveryText.setBounds(351, 444, 300, 35);
		deliveryText.setFont(new Font("Century", Font.BOLD, 16));
		deliveryText.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(deliveryText);

		badPartText.setHorizontalAlignment(SwingConstants.CENTER);
		badPartText.setFont(new Font("Century", Font.BOLD, 16));
		badPartText.setBounds(851, 444, 150, 35);
		badPartText.setText("0");
		panel.add(badPartText);
		lblNewLabel.setBounds(33, 409, 259, 24);
		lblNewLabel.setFont(new Font("Century", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lblNewLabel);
		lblSzlltlevlSzma.setHorizontalAlignment(SwingConstants.CENTER);
		lblSzlltlevlSzma.setFont(new Font("Century", Font.BOLD, 16));
		lblSzlltlevlSzma.setBounds(366, 409, 259, 24);

		panel.add(lblSzlltlevlSzma);
		lblJ.setHorizontalAlignment(SwingConstants.CENTER);
		lblJ.setFont(new Font("Century", Font.BOLD, 16));
		lblJ.setBounds(677, 409, 150, 24);

		panel.add(lblJ);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Century", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(851, 409, 150, 24);
		panel.add(lblNewLabel_1_1);

		btnKsz.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				goodPluszBad = Integer.parseInt(goodPartText.getText()) + Integer.parseInt(badPartText.getText());
				getNumber = comboBox.getModel().getSelectedItem().toString();
				heatedQuantity = qc.arrivedHeatQuntityCount(comboBox);
				
				goodPart = Integer.parseInt(goodPartText.getText());
				badPart = Integer.parseInt(badPartText.getText());
				dText = deliveryText.getText();

				if ((qc.arrivedHeatQuntityCount(comboBox)) < goodPluszBad) {
					JOptionPane.showMessageDialog(null, "Kevés a válogatnivaló mennyiség!!");
					new SelectionReworkPopUpQuestion().setVisible(true);
					// System.out.println(qc.arrivedHeatQuntityCount(comboBox));
					

				} else {

					if (SelectionId != null) {
						fillSelectionTable(goodPart, badPart, getNumber,dText);
						usfs.UpdataSentQuantity(comboBox);
						refressArrivedHeatTreatmentQuantity();
						rst.getSelectionTable(table_2);
						aht.getafterHeatTreatment(table);
						uah.UpdataArrived(comboBox);

					} else {
						JOptionPane.showMessageDialog(null, "Hoppá valami nem jó!!");
					}

				}

			}
		});
		btnKsz.setForeground(Color.DARK_GRAY);
		btnKsz.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnKsz.setBounds(1082, 446, 228, 30);

		panel.add(btnKsz);
		table_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		table_2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_2.setBounds(2, 535, 1350, 175);

		panel.add(table_2);
		scrollPane_1.setBounds(2, 510, 1350, 25);

		panel.add(scrollPane_1);
		scrollPane_1.setViewportView(table_3);
		table_3.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Cikkszám", "Dátum", "Jó termék", "Rossz termék", "Hõkezelés száma" }));

		comboBox.setBounds(20, 446, 300, 35);
		comboBox.setFont(new Font("Century", Font.BOLD, 16));
		panel.add(comboBox);
		sp.selectProduct(comboBox);
		aht.getafterHeatTreatment(table);
		rst.getSelectionTable(table_2);

		
	}

	public static String getNumb() {

		String a = getNumber;

		return a;
	}
	public static int getHeatedQuantity() {

		int a = heatedQuantity;
		
		return a;	
	}
	public static int getGoodPluszBad() {
		
		int a = goodPluszBad;
		
		return a;
	}
	public static int getGoodPart() {

		int a = goodPart;
		
		return a;
	}
	public static int getBadPart() {
		
		int a = badPart;
		
		return a;
		
	}
	public static String getDText() {
	
		String a = dText;
		
		return a;
	}
	

}
