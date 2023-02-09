package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;

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

import heat.treatment.FillHeatTreatmentTable;
import heat.treatment.ReadAfterHeatTreatment;
import heat.treatment.ReadSendHeatTreatment;
import heat.treatment.UpdataDateArriveHeatTreatment;
import heat.treatment.UpdataDateArriveHeatTreatmentCopy;
import heat.treatment.UpdataHeatTreatmentNumberOnHeatTreatmentTable;
import heat.treatment.UpdateStatusForArrivedOnHeatTreatmentTable;
import product.SelectProduct;
import production.UpdateCopyTable;
import stock.BeforeHeatTreatmentQuntityForStockTable;
import stock.UpdataArrivedHeatTreatmentForStockTable;
import stock.UpdataMade;
import stock.UpdataSentHeatTreatmentForStockTable;
import supplier.CalculateProductQuntityMinuszSendHeatTreatment;

public class HeatTreatment extends JFrame {

	/**
	 * 
	 */

	;

	private static final long serialVersionUID = 1L;

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date(System.currentTimeMillis());

	CalculateProductQuntityMinuszSendHeatTreatment calculateProductQuntityMinuszSendHeatTreatment = new CalculateProductQuntityMinuszSendHeatTreatment();
	UpdateCopyTable updateCopyTable = new UpdateCopyTable();
	UpdataSentHeatTreatmentForStockTable updataSentHeatTreatmentForStockTable = new UpdataSentHeatTreatmentForStockTable();
	UpdataArrivedHeatTreatmentForStockTable updataArrivedHeatTreatmentForStockTable = new UpdataArrivedHeatTreatmentForStockTable();
	ReadAfterHeatTreatment readAfterHeatTreatment = new ReadAfterHeatTreatment();
	SelectProduct selectProduct = new SelectProduct();
	BeforeHeatTreatmentQuntityForStockTable beforeHeatTreatmentQuntityForStockTable = new BeforeHeatTreatmentQuntityForStockTable();
	ReadSendHeatTreatment readSendHeatTreatment = new ReadSendHeatTreatment();
	UpdateStatusForArrivedOnHeatTreatmentTable updateStatusForArrivedOnHeatTreatmentTable = new UpdateStatusForArrivedOnHeatTreatmentTable();
	UpdataHeatTreatmentNumberOnHeatTreatmentTable updataHeatTreatmentNumberOnHeatTreatmentTable = new UpdataHeatTreatmentNumberOnHeatTreatmentTable();
	UpdataDateArriveHeatTreatment updataDateArriveHeatTreatment = new UpdataDateArriveHeatTreatment();
	UpdataDateArriveHeatTreatmentCopy updataDateArriveHeatTreatmentCopy = new UpdataDateArriveHeatTreatmentCopy();
	UpdataMade updataMade = new UpdataMade();
	FillHeatTreatmentTable fillHeatTreatmentTable = new FillHeatTreatmentTable();

	private JPanel contentPane;
	private final JTable tableReadyToHeattreatment = new JTable();
	private final JTable inHeatTreatment = new JTable();
	private final JTable tableAfterHeattreatment = new JTable();
	private final JLabel lblHkezelben = new JLabel("Hőkezelőben");
	private final JLabel lblHkezelt = new JLabel("Hőkezelt");
	private final JLabel lblNewLabel_1 = new JLabel("Cikkszám");
	public JComboBox<String> comboBox_1 = new JComboBox<>();
	private final JLabel lblNewLabel_1_1 = new JLabel("Küldés dátuma");
	private JTextField sendDateTxt;
	private JTextField quantityTxt;
	public String heatId;
	private JTextField HeatNumberText;
	private JTextField arrivText;
	private final JScrollPane scrollPane_1 = new JScrollPane();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JScrollPane scrollPane_2 = new JScrollPane();

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

		new Menu(panel, this);
		scrollPane_1.setBounds(2, 155, 1350, 142);

		panel.add(scrollPane_1);
		scrollPane_1.setViewportView(tableReadyToHeattreatment);
		tableReadyToHeattreatment.setFont(new Font("Tahoma", Font.BOLD, 14));
		tableReadyToHeattreatment
				.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Cikkszám", "Darabszám" }));
		tableReadyToHeattreatment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = 0;
				DefaultTableModel model = (DefaultTableModel) tableReadyToHeattreatment.getModel();
				row = tableReadyToHeattreatment.getSelectedRow();
				heatId = model.getValueAt(row, 0).toString();
				comboBox_1.setSelectedItem(model.getValueAt(row, 0));
			}
		});

		beforeHeatTreatmentQuntityForStockTable.beferoHeatTreatment(tableReadyToHeattreatment);
		scrollPane.setBounds(2, 393, 1350, 130);

		panel.add(scrollPane);
		scrollPane.setViewportView(inHeatTreatment);
		inHeatTreatment.setFont(new Font("Tahoma", Font.BOLD, 14));
		inHeatTreatment.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Sorszám", "Cikkszám", "Küldss dátuma", "Darabszám", "Hőkezelés száma" }));

		inHeatTreatment.addMouseListener(new MouseAdapter() {// Sor számának megállapitása amire kattintottak
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = 0;
				DefaultTableModel model = (DefaultTableModel) inHeatTreatment.getModel();
				row = inHeatTreatment.getSelectedRow();
				heatId = model.getValueAt(row, 0).toString();
				comboBox_1.setSelectedItem(model.getValueAt(row, 1)); // combobox kiválaszott elem átállitása
																		// ArriveHeatTreatmenthez
			}
		});
		readSendHeatTreatment.getSendHeatTreatment(inHeatTreatment);
		scrollPane_2.setBounds(0, 595, 1350, 145);

		panel.add(scrollPane_2);
		scrollPane_2.setViewportView(tableAfterHeattreatment);
		tableAfterHeattreatment.setFont(new Font("Tahoma", Font.BOLD, 14));
		tableAfterHeattreatment.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Sorszám", "Cikkszám", "Érkezés dátuma", "Darabszám", "Hőkezelés száma" }));

		JLabel lblNewLabel = new JLabel("Hőkezelésre vár");
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

		JLabel lblNewLabel_1_1_1 = new JLabel("Darabszám");
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

		JButton btnKlds = new JButton("Küldés");
		btnKlds.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fillHeatTreatmentTable.sendHeatTreatment(quantityTxt, comboBox_1, sendDateTxt,
						tableReadyToHeattreatment);

				if (!fillHeatTreatmentTable.isSend) {
					JOptionPane.showMessageDialog(null, "Nincs elegendő gyártott mennyiség ehhez a küldéshez");
				} else {
					updateCopyTable.UpdateColumnToArray(calculateProductQuntityMinuszSendHeatTreatment
							.ProdQuntityMinuszSendHeatTreatment(comboBox_1), comboBox_1);
					updataMade.UpdataDataMade(comboBox_1);
					beforeHeatTreatmentQuntityForStockTable.beferoHeatTreatment(tableReadyToHeattreatment);
				}
				updataSentHeatTreatmentForStockTable.UpdataSentQuantity(comboBox_1);
				readSendHeatTreatment.getSendHeatTreatment(inHeatTreatment);
			}
		});
		btnKlds.setForeground(Color.DARK_GRAY);
		btnKlds.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnKlds.setBounds(1110, 330, 228, 30);
		panel.add(btnKlds);

		JButton btnBerkeztets = new JButton("Beérkeztetés");
		btnBerkeztets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBerkeztets.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (heatId != null) {
					updateStatusForArrivedOnHeatTreatmentTable.afterHeatTreatment(heatId);
					updataDateArriveHeatTreatment.UpdataData(heatId, arrivText);
					updataDateArriveHeatTreatmentCopy.UpdataDataCopyTable(heatId, arrivText);
					updataHeatTreatmentNumberOnHeatTreatmentTable.heatNumber(heatId, HeatNumberText);
					readSendHeatTreatment.getSendHeatTreatment(inHeatTreatment);
					readAfterHeatTreatment.readAfterHeatTreatment(tableAfterHeattreatment);
					updataArrivedHeatTreatmentForStockTable.UpdataArrived(comboBox_1);
					updataSentHeatTreatmentForStockTable.UpdataSentQuantity(comboBox_1);
				} else {
					JOptionPane.showMessageDialog(null, "Hoppá valami nem jó!");
				}
			}
		});
		btnBerkeztets.setForeground(Color.DARK_GRAY);
		btnBerkeztets.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBerkeztets.setBounds(1110, 550, 228, 30);
		panel.add(btnBerkeztets);

		try {
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Hiba");
		}
		selectProduct.selectProduct(comboBox_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Hőkezelés száma");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1_1_1.setFont(new Font("Century", Font.BOLD, 16));
		lblNewLabel_1_1_1_1.setBounds(2, 555, 186, 29);
		panel.add(lblNewLabel_1_1_1_1);

		HeatNumberText = new JTextField();
		HeatNumberText.setFont(new Font("Century", Font.BOLD, 16));
		HeatNumberText.setBounds(176, 555, 150, 30);
		panel.add(HeatNumberText);

		JLabel lblNewLabel_1_1_2 = new JLabel("Beérkezés Dátuma");
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

		readAfterHeatTreatment.readAfterHeatTreatment(tableAfterHeattreatment);

	}
}
