package Design;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
 
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import Classes.DeliveryNumberForProductionAdd;
import Classes.FillProductTable;
import Classes.ProductionRowCount;
import Classes.ReadPorductionDatabase;
import Classes.SelectProductForProductionAdd;
import Classes.SelectWorkerForProductionAdd;
import Classes.UpdataMade;
import Classes.WorkerAdd;
import Classes.fillCopyTable;

public class Production extends JFrame {

	private JPanel contentPane;
	JLabel lbl1 = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lbl2 = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lbl3 = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lbl4 = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lbl5 = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lbl6 = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lbl7 = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lblNewLabel = new JLabel("Cikkszám");
	JLabel lblNewLabel_1_1 = new JLabel("D\u00E1tum");
	JComboBox comboBox_1 = new JComboBox();
	JComboBox workerCombo = new JComboBox();

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	private JTextField dátumTxt;
	private final JLabel lblNewLabel_1_1_1_1 = new JLabel("Darabsz\u00E1m");
	private final JTextField dbText = new JTextField();
	private final JLabel lblAlapanyagSzllLev = new JLabel("Alapanyag sz\u00E1ll\u00EDt\u00F3lev\u00E9l sz\u00E1ma");
	private final JLabel lblNewLabel_1_1_2 = new JLabel("\u00DAj gy\u00E1rt\u00F3 hozz\u00E1ad\u00E1sa");
	private final JTextField newWorkerTxt = new JTextField();
	private JTable table;
	private final JTable table_1 = new JTable();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JComboBox deliveryCombo = new JComboBox();
	
	SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date(System.currentTimeMillis());

	ProductionRowCount grc = new ProductionRowCount();
	fillCopyTable fcp = new fillCopyTable();
	ReadPorductionDatabase rpd = new ReadPorductionDatabase();
	FillProductTable fpt = new FillProductTable();
	DeliveryNumberForProductionAdd dnumberadd = new DeliveryNumberForProductionAdd();
	SelectProductForProductionAdd spadd= new SelectProductForProductionAdd();
	SelectWorkerForProductionAdd swadd = new SelectWorkerForProductionAdd();
    WorkerAdd wa = new WorkerAdd();
    UpdataMade um = new UpdataMade();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
					Production frame = new Production();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public Production() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1366, 768);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(3, 3, 3, 3));
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

		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 150, 114, 29);
		lblNewLabel.setFont(new Font("Century", Font.BOLD, 18));
		lblNewLabel.setForeground(Color.DARK_GRAY);

		panel.add(lblNewLabel);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1.setFont(new Font("Century", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(311, 150, 140, 29);

		panel.add(lblNewLabel_1_1);

		comboBox_1.setForeground(Color.DARK_GRAY);
		comboBox_1.setFont(new Font("Century", Font.BOLD, 18));
		comboBox_1.setBounds(119, 148, 220, 30);
		panel.add(comboBox_1);

		dátumTxt = new JTextField();
		dátumTxt.setToolTipText("1999-12-31");
		dátumTxt.setBounds(423, 150, 220, 30);
		dátumTxt.setFont(new Font("Century", Font.BOLD, 18));
		panel.add(dátumTxt);
		dátumTxt.setColumns(10);
		dátumTxt.setText(date.toString());

		JLabel lblNewLabel_1_1_1 = new JLabel("Gy\u00E1rt\u00F3");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1_1.setFont(new Font("Century", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(626, 150, 140, 29);
		panel.add(lblNewLabel_1_1_1);
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1_1_1.setFont(new Font("Century", Font.BOLD, 18));
		lblNewLabel_1_1_1_1.setBounds(971, 150, 140, 29);

		panel.add(lblNewLabel_1_1_1_1);
		dbText.setToolTipText("1999-12-31");
		dbText.setColumns(10);
		dbText.setBounds(1099, 150, 220, 30);
		dbText.setFont(new Font("Century", Font.BOLD, 18));

		panel.add(dbText);

		workerCombo.setForeground(Color.DARK_GRAY);
		workerCombo.setFont(new Font("Century", Font.BOLD, 20));
		workerCombo.setBounds(741, 150, 220, 30);
		panel.add(workerCombo);
		lblAlapanyagSzllLev.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlapanyagSzllLev.setForeground(Color.DARK_GRAY);
		lblAlapanyagSzllLev.setFont(new Font("Century", Font.BOLD, 18));
		lblAlapanyagSzllLev.setBounds(0, 230, 319, 29);

		panel.add(lblAlapanyagSzllLev);

		JButton btnNewButton = new JButton("Hozzáad");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (newWorkerTxt.getText().isEmpty()) {
					fpt.productionAdd(comboBox_1, workerCombo, deliveryCombo, dátumTxt, dbText);
					fcp.CopyproductionAdd(comboBox_1, workerCombo, deliveryCombo, dátumTxt, dbText);
					rpd.getDatabase(table);
					um.UpdataDataMade(comboBox_1);
				} else {

					wa.workerAdd(newWorkerTxt);
					swadd.selectWorker(workerCombo);
					newWorkerTxt.setText("");
					
				}

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setBounds(1112, 228, 220, 30);
		panel.add(btnNewButton);
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1_2.setFont(new Font("Century", Font.BOLD, 18));
		lblNewLabel_1_1_2.setBounds(626, 230, 240, 29);

		panel.add(lblNewLabel_1_1_2);
		newWorkerTxt.setToolTipText("1999-12-31");
		newWorkerTxt.setColumns(10);
		newWorkerTxt.setBounds(851, 230, 220, 30);

		panel.add(newWorkerTxt);

		table = new JTable();
		table.setBounds(2, 320, 1354, 425);
		table.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(table);
		scrollPane.setBounds(2, 291, 1354, 30);

		panel.add(scrollPane);
		scrollPane.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Cikkszám", "Dátum", "Gyartónév", "Darabszám", "Szállítólevél száma" }));
		table_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		deliveryCombo.setForeground(Color.DARK_GRAY);
		deliveryCombo.setFont(new Font("Century", Font.BOLD, 20));
		deliveryCombo.setBounds(299, 230, 341, 30);

		panel.add(deliveryCombo);

		rpd.getDatabase(table);
		spadd.selectProduct(comboBox_1);
		swadd.selectWorker(workerCombo);
		dnumberadd.getDeliverNumber(deliveryCombo);

		grc.getCount();

	}
}
