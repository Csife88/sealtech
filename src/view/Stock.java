package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
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
import javax.swing.table.DefaultTableModel;

import controller.ProductStatusSearch;
import controller.SelectProduct;
import fill_tables.FillProductTable;
import fill_tables.FillRawMetarialTable;
import fill_tables.FillStockTableForNewProduct;
import read_tables.ReadRawMetarialTable;

public class Stock extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date(System.currentTimeMillis());

	private JPanel contentPane;

	private JLabel lblNewLabel = new JLabel("Cikkszám");
	private JComboBox<String> comboBox_1 = new JComboBox<>();
    private	JTable stockTable = new JTable();
	private final JLabel lblTipus = new JLabel("Típus");
	private final JComboBox<String> tipusCombo = new JComboBox<String>();
	private final JTextField incomingWeight = new JTextField();
	private final JLabel lblSlyKg = new JLabel("Súly kg");
	private final JLabel lblBeszllt = new JLabel("Beszállító");
	private final JComboBox<String> supplierCombo = new JComboBox<String>();
	private final JLabel lblSzlltlevelSzma = new JLabel("Szállítólevel száma");
	private final JTextField textFieldDeliveryNote = new JTextField();
	private final JButton btnMetarialAdd = new JButton("Hozzáad");
	private final JTable tableRawMetarial = new JTable();
	private final JButton btnNewPartAdd = new JButton("Hozzáad");
	private final JButton btnNewPart = new JButton("Új termék");
	
	
	private final JScrollPane scrollPane_1 = new JScrollPane();
	private JTextField textFieldArriveDate;
	private JTextField txtCompany;
	private JTextField txtNewPartNumber;
	private JTextField textSatusz;

	
	SelectProduct selectProduct = new SelectProduct();
	ProductStatusSearch pn = new ProductStatusSearch();
	ReadRawMetarialTable readRawMetarailTable = new ReadRawMetarialTable();
	FillRawMetarialTable fillRawMetarialTable = new FillRawMetarialTable();
	FillStockTableForNewProduct fillStockTableForNewProduct = new FillStockTableForNewProduct();
	FillProductTable fillProductTable = new FillProductTable();
	private final JScrollPane scrollPane = new JScrollPane();
	

	public Stock() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(3, 3, 3, 3));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1366, 732);
		contentPane.add(panel);
		panel.setLayout(null);

		new Menu(panel, this);
	
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 120, 114, 29);
		lblNewLabel.setFont(new Font("Century", Font.BOLD, 17));
		lblNewLabel.setForeground(Color.DARK_GRAY);

		panel.add(lblNewLabel);
		scrollPane.setBounds(2, 200, 1350, 140);
		
		panel.add(scrollPane);
		scrollPane.setViewportView(stockTable);
		stockTable.setFont(new Font("Tahoma", Font.BOLD, 16));
		stockTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Cikkszám", "Státusz", "Darabszám" }));

		comboBox_1.setForeground(Color.DARK_GRAY);
		comboBox_1.setFont(new Font("Century", Font.BOLD, 20));
		comboBox_1.setBounds(120, 120, 220, 30);
		panel.add(comboBox_1);

		JButton btnNewButton = new JButton("Keres");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				pn.keres(comboBox_1, stockTable);
			}
		});
		btnNewButton.setFont(new Font("Century", Font.BOLD, 20));
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setBounds(350, 120, 220, 30);
		panel.add(btnNewButton);

		selectProduct.selectProduct(comboBox_1);
		lblTipus.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipus.setForeground(Color.DARK_GRAY);
		lblTipus.setFont(new Font("Dialog", Font.BOLD, 17));
		lblTipus.setBounds(-20, 400, 114, 29);
		
		panel.add(lblTipus);
		tipusCombo.setModel(new DefaultComboBoxModel<String>(new String[] {"1 x 53mm ECU-58","1,5 x 45mm ECU-58","1,85 x 45mm ECU-58","2 x 45mm ECU-58"}));
		tipusCombo.setForeground(Color.DARK_GRAY);
		tipusCombo.setFont(new Font("Dialog", Font.BOLD, 17));
		tipusCombo.setBounds(70, 400, 160, 30);
		
		panel.add(tipusCombo);
		incomingWeight.setFont(new Font("Dialog", Font.BOLD, 16));
		incomingWeight.setToolTipText("");
		incomingWeight.setColumns(10);
		incomingWeight.setBounds(305, 400, 50, 30);
		
		panel.add(incomingWeight);
		lblSlyKg.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlyKg.setForeground(Color.DARK_GRAY);
		lblSlyKg.setFont(new Font("Dialog", Font.BOLD, 17));
		lblSlyKg.setBounds(210, 400, 114, 29);
		
		panel.add(lblSlyKg);
		lblBeszllt.setHorizontalAlignment(SwingConstants.CENTER);
		lblBeszllt.setForeground(Color.DARK_GRAY);
		lblBeszllt.setFont(new Font("Dialog", Font.BOLD, 17));
		lblBeszllt.setBounds(345, 400, 114, 29);
		
		panel.add(lblBeszllt);
		supplierCombo.setModel(new DefaultComboBoxModel<String>(new String[] {"Majer Kft","Vinco","Wieland"}));
		supplierCombo.setForeground(Color.DARK_GRAY);
		supplierCombo.setFont(new Font("Dialog", Font.BOLD, 16));
		supplierCombo.setBounds(445, 400, 150, 30);
		
		panel.add(supplierCombo);
		lblSzlltlevelSzma.setHorizontalAlignment(SwingConstants.CENTER);
		lblSzlltlevelSzma.setForeground(Color.DARK_GRAY);
		lblSzlltlevelSzma.setFont(new Font("Dialog", Font.BOLD, 17));
		lblSzlltlevelSzma.setBounds(575, 400, 197, 29);
		
		panel.add(lblSzlltlevelSzma);
		textFieldDeliveryNote.setFont(new Font("Dialog", Font.BOLD, 16));
		textFieldDeliveryNote.setColumns(10);
		textFieldDeliveryNote.setBounds(755, 400, 160, 30);
		
		panel.add(textFieldDeliveryNote);
		btnMetarialAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fillRawMetarialTable.metarialAdd(tipusCombo, incomingWeight, supplierCombo, textFieldDeliveryNote, textFieldArriveDate);
				readRawMetarailTable.getDatabase(tableRawMetarial);
			}
		});
		btnMetarialAdd.setForeground(Color.DARK_GRAY);
		btnMetarialAdd.setFont(new Font("Dialog", Font.BOLD, 16));
		btnMetarialAdd.setBounds(1210, 400, 140, 30);
		
		panel.add(btnMetarialAdd);
		scrollPane_1.setBounds(2, 453, 1350, 282);
		
		panel.add(scrollPane_1);
		scrollPane_1.setViewportView(tableRawMetarial);
		tableRawMetarial.setFont(new Font("Tahoma", Font.BOLD, 15));
		tableRawMetarial.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Sorszám","Típus","Súly KG","Beszállító","Szállítólevel száma","Beérkezés dátuma","Készleten"
				}
			));
		readRawMetarailTable.getDatabase(tableRawMetarial);
		
		JLabel lblBerkezsDtuma = new JLabel("Beérkezés dátuma");
		lblBerkezsDtuma.setHorizontalAlignment(SwingConstants.CENTER);
		lblBerkezsDtuma.setForeground(Color.DARK_GRAY);
		lblBerkezsDtuma.setFont(new Font("Dialog", Font.BOLD, 17));
		lblBerkezsDtuma.setBounds(900, 400, 197, 29);
		panel.add(lblBerkezsDtuma);
		
		textFieldArriveDate = new JTextField();
		textFieldArriveDate.setToolTipText("1999-12-31");
		textFieldArriveDate.setText(date.toString());
		textFieldArriveDate.setFont(new Font("Dialog", Font.BOLD, 16));
		textFieldArriveDate.setColumns(10);
		textFieldArriveDate.setBounds(1080, 400, 110, 30);
		panel.add(textFieldArriveDate);
		
		JLabel lblMetarial = new JLabel("Alapanyagok");
		lblMetarial.setHorizontalAlignment(SwingConstants.CENTER);
		lblMetarial.setForeground(Color.BLACK);
		lblMetarial.setFont(new Font("Dialog", Font.BOLD, 21));
		lblMetarial.setBounds(608, 350, 174, 29);
		panel.add(lblMetarial);
		
		JLabel lblParts = new JLabel("Termékek");
		lblParts.setHorizontalAlignment(SwingConstants.CENTER);
		lblParts.setForeground(Color.BLACK);
		lblParts.setFont(new Font("Dialog", Font.BOLD, 21));
		lblParts.setBounds(598, 78, 174, 29);
		panel.add(lblParts);
		
		
		btnNewPart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtNewPartNumber.setVisible(true);
			//	txtCompany.setVisible(true);
				textSatusz.setVisible(true);
				btnNewPartAdd.setVisible(true);
				btnNewPart.setVisible(false);
			
			}
		});
		btnNewPart.setForeground(Color.DARK_GRAY);
		btnNewPart.setFont(new Font("Dialog", Font.BOLD, 16));
		btnNewPart.setBounds(1116, 139, 220, 30);
		panel.add(btnNewPart);
		
		txtCompany = new JTextField();
		txtCompany.setVisible(false);
		txtCompany.setText("Vevő");
		txtCompany.setHorizontalAlignment(SwingConstants.CENTER);
		txtCompany.setFont(new Font("Dialog", Font.BOLD, 16));
		txtCompany.setColumns(10);
		txtCompany.setBounds(937, 139, 160, 30);
		panel.add(txtCompany);
		
		txtNewPartNumber = new JTextField();
		txtNewPartNumber.setVisible(false);
		txtNewPartNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtNewPartNumber.setText("Cikkszám");
		txtNewPartNumber.setFont(new Font("Dialog", Font.BOLD, 16));
		txtNewPartNumber.setColumns(10);
		txtNewPartNumber.setBounds(598, 139, 160, 30);
		panel.add(txtNewPartNumber);
		
		textSatusz = new JTextField();
		textSatusz.setVisible(false);
		textSatusz.setText("Státusz");
		textSatusz.setHorizontalAlignment(SwingConstants.CENTER);
		textSatusz.setFont(new Font("Dialog", Font.BOLD, 16));
		textSatusz.setColumns(10);
		textSatusz.setBounds(767, 139, 160, 30);
		panel.add(textSatusz);
		btnNewPartAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fillStockTableForNewProduct.productAdd(txtNewPartNumber, textSatusz);
				fillProductTable.productAdd(txtNewPartNumber);
				
			}
		});
		
		
		btnNewPartAdd.setVisible(false);
		btnNewPartAdd.setForeground(Color.DARK_GRAY);
		btnNewPartAdd.setFont(new Font("Dialog", Font.BOLD, 16));
		btnNewPartAdd.setBounds(1116, 98, 220, 30);
		panel.add(btnNewPartAdd);

		//add matarial to table
		
	}
}
