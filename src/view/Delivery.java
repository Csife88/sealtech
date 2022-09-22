package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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

import controller.CalculateSelectionQuantityMinuszDeliveredQuantity;
import controller.NextWeek;
import controller.PreviousWeek;
import controller.QantityCount;
import controller.SelectProduct;
import fill_tables.FillDeliveryNoteTable;
import read_tables.ReadDeliveryNoteTable;
import read_tables.ReadSelectionTable;
import updata_tables.UpdataSelectionTable;
import updata_tables.UpdataStockForSelectionPart;

public class Delivery extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> partNumberBox = new JComboBox<>();
	private final JLabel lblDarabszm = new JLabel("Darabszám");
	private JTextField dbText;
	private JTextField dátumTxt;
	private JTable tabledWeeklyDelirvery;
	private JLabel lblNewLabel = new JLabel("Cikkszám");

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date(System.currentTimeMillis());

	private int week = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
	private String week2 = String.valueOf(week);
	private final JLabel lblRendelsSzm = new JLabel("Rendelés szám");
	private final JTextField deliveryTxt = new JTextField();
	private final JLabel lblHt_1 = new JLabel("Hét");
	private final JTextField weekChangeTxt = new JTextField();
	private final JLabel prevLbl = new JLabel("<");
	private final JLabel nextLbl = new JLabel(">");
	private final JTable tableOnStock = new JTable();
	private final JLabel lblszallit = new JLabel("Kiszállítás");
	private final JLabel rendelkez = new JLabel("Rendelkezésre áll");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JScrollPane scrollPane_1 = new JScrollPane();

	SelectProduct selectProduct = new SelectProduct();
	FillDeliveryNoteTable fillDeliveryNoteTable = new FillDeliveryNoteTable();
	ReadDeliveryNoteTable readDeliveryNoteTable = new ReadDeliveryNoteTable();
	PreviousWeek pw = new PreviousWeek();
	NextWeek nw = new NextWeek();
	ReadSelectionTable readSelectionTable = new ReadSelectionTable();
	UpdataSelectionTable updataSelectionTable = new UpdataSelectionTable();
	CalculateSelectionQuantityMinuszDeliveredQuantity calculateSelectionQuantityMinuszDeliveredQuantity = new CalculateSelectionQuantityMinuszDeliveredQuantity();
	UpdataStockForSelectionPart updataStockForSelectionPart = new UpdataStockForSelectionPart();
	QantityCount qantityCount = new QantityCount();

	public Delivery() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1366, 768);
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
		lblNewLabel.setBounds(154, 464, 114, 29);
		lblNewLabel.setFont(new Font("Century", Font.BOLD, 17));
		lblNewLabel.setForeground(Color.DARK_GRAY);

		panel.add(lblNewLabel);

		partNumberBox.setForeground(Color.DARK_GRAY);
		partNumberBox.setFont(new Font("Century", Font.BOLD, 17));
		partNumberBox.setBounds(263, 463, 170, 30);
		panel.add(partNumberBox);

		lblDarabszm.setHorizontalAlignment(SwingConstants.CENTER);
		lblDarabszm.setForeground(Color.DARK_GRAY);
		lblDarabszm.setFont(new Font("Century", Font.BOLD, 17));
		lblDarabszm.setBounds(443, 464, 114, 29);

		panel.add(lblDarabszm);

		dbText = new JTextField();
		dbText.setHorizontalAlignment(SwingConstants.CENTER);
		dbText.setFont(new Font("Tahoma", Font.BOLD, 14));
		dbText.setColumns(10);
		dbText.setBounds(560, 463, 120, 30);
		panel.add(dbText);

		JLabel lblDtum = new JLabel("Dátum");
		lblDtum.setHorizontalAlignment(SwingConstants.CENTER);
		lblDtum.setForeground(Color.DARK_GRAY);
		lblDtum.setFont(new Font("Century", Font.BOLD, 17));
		lblDtum.setBounds(666, 464, 114, 29);
		panel.add(lblDtum);

		dátumTxt = new JTextField();
		dátumTxt.setFont(new Font("Tahoma", Font.BOLD, 14));
		dátumTxt.setHorizontalAlignment(SwingConstants.CENTER);
		dátumTxt.setToolTipText("1999-12-31");
		dátumTxt.setColumns(10);
		dátumTxt.setBounds(761, 463, 130, 30);
		dátumTxt.setText(formatter.format(date));
		panel.add(dátumTxt);

		JButton btnNewButton = new JButton("Hozzáad");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// hibakezelés
				int sendDb = Integer.parseInt(dbText.getText());
				if (sendDb > qantityCount.selectionQuntity(partNumberBox)) {
					JOptionPane.showMessageDialog(null, "Nincs elegendő mennyiség");
				} else {

					readDeliveryNoteTable.getDatabase(tabledWeeklyDelirvery, weekChangeTxt);
					fillDeliveryNoteTable.deliveryAdd(weekChangeTxt, partNumberBox, dátumTxt, dbText, deliveryTxt);
					readDeliveryNoteTable.getDatabase(tabledWeeklyDelirvery, weekChangeTxt);
					updataSelectionTable.UpdateColumnToArray(calculateSelectionQuantityMinuszDeliveredQuantity
							.ProdQuntityMinuszSendHeatTreatment(partNumberBox,dbText), partNumberBox);
					readSelectionTable.getSelectionTable(tableOnStock);
					qantityCount.sendHeatQuntityCount(partNumberBox);
					updataStockForSelectionPart.UpdataSentQuantity(partNumberBox);
				}

			}
		});
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(1228, 464, 100, 30);
		panel.add(btnNewButton);
		selectProduct.selectProduct(partNumberBox);
		scrollPane.setBounds(2, 550, 1350, 180);

		panel.add(scrollPane);

		tabledWeeklyDelirvery = new JTable();
		scrollPane.setViewportView(tabledWeeklyDelirvery);
		tabledWeeklyDelirvery.setFont(new Font("Tahoma", Font.BOLD, 14));
		tabledWeeklyDelirvery.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Cikkszám", "Dátum", "Darabszám", "Rendelés száma" }));
		readDeliveryNoteTable.getDatabase(tabledWeeklyDelirvery, weekChangeTxt);

		lblRendelsSzm.setHorizontalAlignment(SwingConstants.CENTER);
		lblRendelsSzm.setForeground(Color.DARK_GRAY);
		lblRendelsSzm.setFont(new Font("Century", Font.BOLD, 17));
		lblRendelsSzm.setBounds(901, 464, 135, 29);

		panel.add(lblRendelsSzm);
		deliveryTxt.setHorizontalAlignment(SwingConstants.CENTER);
		deliveryTxt.setFont(new Font("Tahoma", Font.BOLD, 14));
		deliveryTxt.setColumns(10);
		deliveryTxt.setBounds(1043, 463, 150, 30);

		panel.add(deliveryTxt);

		lblHt_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblHt_1.setForeground(Color.DARK_GRAY);
		lblHt_1.setFont(new Font("Century", Font.BOLD, 17));
		lblHt_1.setBounds(105, 464, 39, 29);

		panel.add(lblHt_1);
		weekChangeTxt.setText(week2);
		weekChangeTxt.setHorizontalAlignment(SwingConstants.CENTER);
		weekChangeTxt.setFont(new Font("Tahoma", Font.BOLD, 14));
		weekChangeTxt.setColumns(10);
		weekChangeTxt.setBounds(35, 464, 30, 30);

		panel.add(weekChangeTxt);
		prevLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int condition = Integer.parseInt(weekChangeTxt.getText());

				if (condition > 1) {
					pw.prewWeek(weekChangeTxt);
					readDeliveryNoteTable.getDatabase(tabledWeeklyDelirvery, weekChangeTxt);
				}

			}
		});
		prevLbl.setHorizontalAlignment(SwingConstants.LEFT);
		prevLbl.setForeground(Color.DARK_GRAY);
		prevLbl.setFont(new Font("Century", Font.BOLD, 23));
		prevLbl.setBounds(14, 465, 21, 29);

		panel.add(prevLbl);
		nextLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int condition = Integer.parseInt(weekChangeTxt.getText());
				if (condition < 52) {
					nw.nextWeek(weekChangeTxt);
					readDeliveryNoteTable.getDatabase(tabledWeeklyDelirvery, weekChangeTxt);
				}
			}
		});
		nextLbl.setHorizontalAlignment(SwingConstants.LEFT);
		nextLbl.setForeground(Color.DARK_GRAY);
		nextLbl.setFont(new Font("Century", Font.BOLD, 23));
		nextLbl.setBounds(69, 465, 21, 29);

		panel.add(nextLbl);
		scrollPane_1.setBounds(2, 190, 1350, 180);

		panel.add(scrollPane_1);
		scrollPane_1.setViewportView(tableOnStock);
		tableOnStock.setFont(new Font("Tahoma", Font.BOLD, 14));
		tableOnStock.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Cikkszám", "Dátum", "Jó termék", "Rossz termék", "Hőkezelés száma" }));

		readSelectionTable.getSelectionTable(tableOnStock);
		readDeliveryNoteTable.getDatabase(tabledWeeklyDelirvery, weekChangeTxt);

		lblszallit.setHorizontalAlignment(SwingConstants.CENTER);
		lblszallit.setForeground(Color.DARK_GRAY);
		lblszallit.setFont(new Font("Century", Font.BOLD, 20));
		lblszallit.setBounds(535, 399, 228, 43);

		panel.add(lblszallit);
		rendelkez.setHorizontalAlignment(SwingConstants.CENTER);
		rendelkez.setForeground(Color.DARK_GRAY);
		rendelkez.setFont(new Font("Century", Font.BOLD, 20));
		rendelkez.setBounds(535, 111, 228, 43);

		panel.add(rendelkez);

	}
}
