package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.DeliveryNumberForProductionAdd;
import controller.RowCount;
import controller.SelectProductForProductionAdd;
import controller.SelectWorkerForProductionAdd;
import fill_tables.FillCopyTable;
import fill_tables.FillProductionTable;
import fill_tables.WorkerAdd;
import read_tables.FilterProductionTable;
import read_tables.ReadPorductionDatabase;
import updata_tables.UpdataMade;
import updata_tables.UpdateRawMetrialTable;

public class Production extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> productComboBox = new JComboBox<>();
	private JComboBox<String> workerCombo = new JComboBox<>();
	private JComboBox<String> deliveryCombo = new JComboBox<String>();

	private JTextField dátumTxt;
	private final JLabel lblNewLabel_1_1_1_1 = new JLabel("Darabszám");
	private final JTextField dbText = new JTextField();
	private final JLabel lblAlapanyagSzllLev = new JLabel("Alapanyag szállítólevél száma ");
	private final JLabel lblNewLabel_1_1_2 = new JLabel("Új gyártó hozzáadása");
	private final JTextField newWorkerTxt = new JTextField();
	private JTable table;
	private final JTable table_1 = new JTable();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JScrollPane scrollPane_1 = new JScrollPane();
	private JLabel lblNewLabel = new JLabel("Cikkszám");
	private JLabel lblNewLabelDate = new JLabel("Dátum");
	private JLabel lblFromAt = new JLabel("tól-ig");
	private boolean isFilter = false;
	private final JButton btnFilter = new JButton("Szűrés");
	private JTextField textFieldFrom;
	private JTextField textFieldAt;
	private JTextField piecesSumText;
	private final JTextField partNumberSumText = new JTextField();
	private final JTextField textField = new JTextField();
	private final JTextField textField_1 = new JTextField();
	private final JTextField textField_2 = new JTextField();

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date(System.currentTimeMillis());

    RowCount rowCount = new RowCount();
	FillCopyTable fillCopyTable = new FillCopyTable();
	ReadPorductionDatabase readPorductionDatabase = new ReadPorductionDatabase();
	FillProductionTable fillProductTable = new FillProductionTable();
	DeliveryNumberForProductionAdd deliveryNumberForProductionAdd = new DeliveryNumberForProductionAdd();
	SelectProductForProductionAdd selectProductForProductionAdd = new SelectProductForProductionAdd();
	SelectWorkerForProductionAdd selectWorkerForProductionAdd = new SelectWorkerForProductionAdd();
	WorkerAdd workerAdd = new WorkerAdd();
	UpdataMade updataMade = new UpdataMade();
	FilterProductionTable filterProductionTable = new FilterProductionTable();
	UpdateRawMetrialTable updateRawMetrialTable = new UpdateRawMetrialTable();

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

		new Menu(panel, this);

		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 100, 114, 29);
		lblNewLabel.setFont(new Font("Century", Font.BOLD, 18));
		lblNewLabel.setForeground(Color.DARK_GRAY);

		panel.add(lblNewLabel);
		lblNewLabelDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabelDate.setForeground(Color.DARK_GRAY);
		lblNewLabelDate.setFont(new Font("Century", Font.BOLD, 18));
		lblNewLabelDate.setBounds(311, 100, 140, 29);

		panel.add(lblNewLabelDate);

		productComboBox.setForeground(Color.DARK_GRAY);
		productComboBox.setFont(new Font("Century", Font.BOLD, 18));
		productComboBox.setBounds(119, 100, 220, 30);
		panel.add(productComboBox);

		dátumTxt = new JTextField();
		dátumTxt.setToolTipText("1999-12-31");
		dátumTxt.setBounds(423, 100, 220, 30);
		dátumTxt.setFont(new Font("Century", Font.BOLD, 18));
		panel.add(dátumTxt);
		dátumTxt.setColumns(10);
		dátumTxt.setText(date.toString());

		JLabel lblNewLabel_1_1_1 = new JLabel("Gyártó");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1_1.setFont(new Font("Century", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(626, 100, 140, 29);
		panel.add(lblNewLabel_1_1_1);
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1_1_1.setFont(new Font("Century", Font.BOLD, 18));
		lblNewLabel_1_1_1_1.setBounds(971, 100, 140, 29);

		panel.add(lblNewLabel_1_1_1_1);
		dbText.setToolTipText("1999-12-31");
		dbText.setColumns(10);
		dbText.setBounds(1099, 100, 220, 30);
		dbText.setFont(new Font("Century", Font.BOLD, 18));

		panel.add(dbText);

		workerCombo.setForeground(Color.DARK_GRAY);
		workerCombo.setFont(new Font("Century", Font.BOLD, 20));
		workerCombo.setBounds(741, 100, 220, 30);
		panel.add(workerCombo);
		lblAlapanyagSzllLev.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlapanyagSzllLev.setForeground(Color.DARK_GRAY);
		lblAlapanyagSzllLev.setFont(new Font("Century", Font.BOLD, 18));
		lblAlapanyagSzllLev.setBounds(0, 175, 319, 29);

		panel.add(lblAlapanyagSzllLev);

		JButton btnNewButton = new JButton("Hozzáad");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (newWorkerTxt.getText().isEmpty()) {
					updateRawMetrialTable.UpdateOnStockQuantity(deliveryCombo, productComboBox, dbText);
					if (updateRawMetrialTable.isEnoughtMetarial) {
						fillProductTable.productionAdd(productComboBox, workerCombo, deliveryCombo, dátumTxt, dbText);
						fillCopyTable.CopyproductionAdd(productComboBox, workerCombo, deliveryCombo, dátumTxt, dbText);
						readPorductionDatabase.getDatabase(table, "production");
						updataMade.UpdataDataMade(productComboBox);
					} else {
						JOptionPane.showMessageDialog(null, "Hozzáadás sikertelen!");
					}
				} else {

					workerAdd.workerAdd(newWorkerTxt);
					selectWorkerForProductionAdd.selectWorker(workerCombo);
					newWorkerTxt.setText("");

				}

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setBounds(1112, 175, 220, 30);
		panel.add(btnNewButton);
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1_2.setFont(new Font("Century", Font.BOLD, 18));
		lblNewLabel_1_1_2.setBounds(626, 175, 240, 29);

		panel.add(lblNewLabel_1_1_2);
		newWorkerTxt.setToolTipText("1999-12-31");
		newWorkerTxt.setColumns(10);
		newWorkerTxt.setBounds(851, 175, 220, 30);

		panel.add(newWorkerTxt);

		scrollPane_1.setBounds(2, 270, 1354, 440);
		panel.add(scrollPane_1);
		table = new JTable();

		JTableHeader header = new JTableHeader(); // table header üres
		table.setTableHeader(header);

		scrollPane_1.setViewportView(table);
		scrollPane_1.setColumnHeaderView(table.getTableHeader());

		table.setFont(new Font("Tahoma", Font.BOLD, 15));
		scrollPane.setBounds(2, 245, 1354, 30);

		panel.add(scrollPane);
		scrollPane.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Cikkszám", "Dátum", "Gyartónév", "Darabszám", "Szállítólevál száma" }));
		table_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		deliveryCombo.setForeground(Color.DARK_GRAY);
		deliveryCombo.setFont(new Font("Century", Font.BOLD, 20));
		deliveryCombo.setBounds(299, 175, 341, 30);
		panel.add(deliveryCombo);

		selectProductForProductionAdd.selectProduct(productComboBox);
		selectWorkerForProductionAdd.selectWorker(workerCombo);

		deliveryNumberForProductionAdd.getDeliverNumber(deliveryCombo);
		readPorductionDatabase.getDatabase(table, "production");
		rowCount.getCount("production");

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (!isFilter) {
					isFilter = true;
					newWorkerTxt.setEnabled(false);
					dbText.setEnabled(false);
					btnNewButton.setEnabled(false);
					btnNewButton.setVisible(false);
					btnFilter.setVisible(true);
					dátumTxt.setEnabled(false);
					textFieldFrom.setVisible(true);
					textFieldAt.setVisible(true);
					lblFromAt.setVisible(true);

				} else {
					newWorkerTxt.setEnabled(true);
					dbText.setEnabled(true);
					btnNewButton.setEnabled(true);
					readPorductionDatabase.getDatabase(table, "production");
					btnNewButton.setVisible(true);
					btnFilter.setVisible(false);
					isFilter = false;
					dátumTxt.setEnabled(true);
					textFieldFrom.setVisible(false);
					textFieldAt.setVisible(false);
					lblFromAt.setVisible(false);

				}
			}
		});
		
		lblNewLabel_1.setIcon(new ImageIcon(Production.class.getResource("img/filter.png")));
		lblNewLabel_1.setBounds(10, 210, 31, 24);
		panel.add(lblNewLabel_1);

		btnFilter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				filterProductionTable.getDatabase(table, "production", productComboBox, workerCombo, deliveryCombo,
						textFieldFrom, textFieldAt);
				partNumberSumText.setText(productComboBox.getModel().getSelectedItem().toString());

				// number format
				int total = totalNumberOfPieces();
				DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
				DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

				symbols.setGroupingSeparator(' ');
				formatter.setDecimalFormatSymbols(symbols);
				// ------------------------
				piecesSumText.setText(formatter.format(total));

			}
		});
		btnFilter.setVisible(false);
		btnFilter.setForeground(Color.DARK_GRAY);
		btnFilter.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFilter.setBounds(1112, 211, 220, 30);

		panel.add(btnFilter);

		textFieldFrom = new JTextField();
		textFieldFrom.setVisible(false);
		textFieldFrom.setToolTipText("1999-12-31");
		textFieldFrom.setFont(new Font("Dialog", Font.BOLD, 15));
		textFieldFrom.setColumns(10);
		textFieldFrom.setBounds(459, 141, 87, 30);
		panel.add(textFieldFrom);

		textFieldAt = new JTextField();
		textFieldAt.setVisible(false);
		textFieldAt.setToolTipText("1999-12-31");
		textFieldAt.setFont(new Font("Dialog", Font.BOLD, 15));
		textFieldAt.setColumns(10);
		textFieldAt.setBounds(556, 141, 87, 30);
		panel.add(textFieldAt);
		lblFromAt.setVisible(false);

		lblFromAt.setHorizontalAlignment(SwingConstants.CENTER);
		lblFromAt.setForeground(Color.DARK_GRAY);
		lblFromAt.setFont(new Font("Dialog", Font.BOLD, 18));
		lblFromAt.setBounds(343, 140, 140, 29);
		panel.add(lblFromAt);

		piecesSumText = new JTextField();
		piecesSumText.setFont(new Font("Tahoma", Font.BOLD, 15));
		piecesSumText.setHorizontalAlignment(SwingConstants.CENTER);
		piecesSumText.setBounds(800, 710, 270, 30);
		panel.add(piecesSumText);
		piecesSumText.setColumns(10);
		partNumberSumText.setHorizontalAlignment(SwingConstants.CENTER);
		partNumberSumText.setFont(new Font("Tahoma", Font.BOLD, 15));
		partNumberSumText.setColumns(10);
		partNumberSumText.setBounds(0, 710, 270, 30);

		panel.add(partNumberSumText);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setColumns(10);
		textField.setBounds(1070, 710, 285, 30);

		panel.add(textField);
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_1.setColumns(10);
		textField_1.setBounds(270, 710, 270, 30);

		panel.add(textField_1);
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_2.setColumns(10);
		textField_2.setBounds(535, 710, 270, 30);

		panel.add(textField_2);
	}

	private int totalNumberOfPieces() {
		int sum = 0;
		for (int i = 0; i < table.getModel().getRowCount(); i++) {
			sum = sum + (int) table.getModel().getValueAt(i, 3);
		}
		return sum;
	}
}
