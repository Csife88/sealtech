package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import heat.treatment.ReadAfterHeatTreatment;
import heat.treatment.RefressArrivedHeatTreatmentQuantity;
import product.SelectProduct;
import selection.FillSelectionTable;
import selection.ReadSelectionTable;
import stock.UpdataArrivedHeatTreatmentForStockTable;
import stock.UpdataStockForSelectionPart;
import supplier.QantityCount;

public class Selection extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;

	private JPanel contentPane;
	private JTable tableHeated;
	protected static JComboBox<String> comboBox = new JComboBox<>();
	protected  static JTextField goodPartText;
	protected static JTextField badPartText = new JTextField();

	private final JTextField deliveryText;
	private final JLabel lblNewLabel = new JLabel("Cikkszám");
	private final JLabel lblSzlltlevlSzma = new JLabel("Hőkezelés száma");
	private final JLabel lblJ = new JLabel("Jó");
	private final JLabel lblNewLabel_1_1 = new JLabel("Selejt");
	private final JButton btnKsz = new JButton("Kész");
	private final JTable tableSelectionParts = new JTable();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JScrollPane scrollPane_1 = new JScrollPane();

	public String SelectionId;
	public String Quntity;
	
	public static int goodPart;
	public static int badPart;
	public static String getPartNumber;
	public static int heatedQuantity;
	public static int goodPluszBad;
	public static String dText;

	UpdataStockForSelectionPart updataStockForSelectionPart = new UpdataStockForSelectionPart();
	UpdataArrivedHeatTreatmentForStockTable updataArrivedHeatTreatmentForStockTable = new UpdataArrivedHeatTreatmentForStockTable();
	SelectProduct selectProduct = new SelectProduct();
	ReadSelectionTable readSelectionTable = new ReadSelectionTable();
	QantityCount qantityCount = new QantityCount();
	ReadAfterHeatTreatment readAfterHeatTreatment = new ReadAfterHeatTreatment();
	

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

		new Menu(panel, this);
		scrollPane.setBounds(2, 210, 1350, 180);
		
		panel.add(scrollPane);
		
				tableHeated = new JTable();
				scrollPane.setViewportView(tableHeated);
				tableHeated.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int row = 0;
						DefaultTableModel model = (DefaultTableModel) tableHeated.getModel();
						row = tableHeated.getSelectedRow();
						comboBox.setSelectedItem(model.getValueAt(row, 1));
						deliveryText.setText(model.getValueAt(row, 4).toString());
						SelectionId = model.getValueAt(row, 0).toString();
						Quntity = model.getValueAt(row, 3).toString();

					}
				});
		tableHeated.setFont(new Font("Tahoma", Font.BOLD, 14));
		tableHeated.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Sorszám", "Cikkszám", "Érkezés dátuma", "Darabszám", "Szálltólevél száma" }));

		readAfterHeatTreatment.readAfterHeatTreatment(tableHeated);

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
				getPartNumber = comboBox.getModel().getSelectedItem().toString();
				heatedQuantity = qantityCount.arrivedHeatQuntityCount(comboBox);

				goodPart = Integer.parseInt(goodPartText.getText());
				badPart = Integer.parseInt(badPartText.getText());
				dText = deliveryText.getText();

				if ((qantityCount.arrivedHeatQuntityCount(comboBox)) < goodPluszBad) {
					JOptionPane.showMessageDialog(null, "Kevés a válogatnivaló mennyiség!!");
					new SelectionReworkPopUpQuestion().setVisible(true);

				} else {

					if (SelectionId != null) {
					new FillSelectionTable().fillSelectionTable(goodPart, badPart, getPartNumber, dText);
						updataStockForSelectionPart.UpdataSentQuantity(comboBox);
						RefressArrivedHeatTreatmentQuantity.refressArrivedHeatTreatmentQuantity();
						readSelectionTable.getSelectionTable(tableSelectionParts);
						readAfterHeatTreatment.readAfterHeatTreatment(tableHeated);
						updataArrivedHeatTreatmentForStockTable.UpdataArrived(comboBox);

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
		scrollPane_1.setBounds(2, 520, 1350, 200);
		
		panel.add(scrollPane_1);
		scrollPane_1.setViewportView(tableSelectionParts);
		tableSelectionParts.setFont(new Font("Tahoma", Font.BOLD, 14));
		tableSelectionParts.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Cikkszám", "Dátum", "Jó termék", "Rossz termék", "Hőkezelés száma" }));
		readSelectionTable.getSelectionTable(tableSelectionParts);
		

		comboBox.setBounds(20, 446, 300, 35);
		comboBox.setFont(new Font("Century", Font.BOLD, 16));
		panel.add(comboBox);
		selectProduct.selectProduct(comboBox);

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
