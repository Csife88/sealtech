package Design;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


import com.jgoodies.forms.factories.DefaultComponentFactory;

import Classes.QantityCount;
import Classes.ReadDeliveryNoteTable;
import Classes.ReadSelectionTable;
import Classes.SelectProduct;
import Classes.UpdataMade;
import Classes.UpdataSelectionTable;
import Classes.UpdataStockForSelectionPart;
import Classes.CalculateSelectionQuantityMinuszDeliveredQuantity;
import Classes.FillDeliveryNoteTable;
import Classes.NextWeek;
import Classes.PreviousWeek;
import Classes.ProductNarrow;
import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.ComponentOrientation;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;



public class Order extends JFrame {

	
	private JFrame window;
	private JPanel contentPane;
	JLabel lbl1 = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lbl2 = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lbl3 = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lbl4 = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lbl5 = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lbl6 = DefaultComponentFactory.getInstance().createLabel("");
	JLabel lbl7 = DefaultComponentFactory.getInstance().createLabel("");
    JLabel lblNewLabel = new JLabel("Cikkszám");
	JComboBox comboBox_1 = new JComboBox();


	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	private JTable table_1;
	private final JLabel lblDarabszm = new JLabel("Darabsz\u00E1m");
	private JTextField dbText;
	private JTextField dátumTxt;
	
	SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date(System.currentTimeMillis());
	
	private JTable table;
	
	int week =Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
	String week2 = String.valueOf(week);
	private final JLabel lblRendelsSzm = new JLabel("Rendel\u00E9s sz\u00E1m");
	private final JTextField deliveryTxt = new JTextField();

	private final JTable table_2 = new JTable();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JLabel lblHt_1 = new JLabel("H\u00E9t");
	private final JTextField weekChangeTxt = new JTextField();
	private final JLabel prevLbl = new JLabel("<");
	private final JLabel nextLbl = new JLabel(">");
	private final JTable table_3 = new JTable();
	private final JTable table_4 = new JTable();
	private final JLabel lblszallit = new JLabel("Kisz\u00E1ll\u00EDt\u00E1s");
	private final JLabel rendelkez = new JLabel("Rendelkez\u00E9sre \u00E1ll");
	private final JScrollPane scrollPane_1 = new JScrollPane();
	
	SelectProduct sp = new SelectProduct();
	FillDeliveryNoteTable fdnt =  new FillDeliveryNoteTable();
	ReadDeliveryNoteTable rdnt = new ReadDeliveryNoteTable();
	PreviousWeek pw = new PreviousWeek();
	NextWeek nw = new NextWeek();
	ReadSelectionTable rst = new ReadSelectionTable();
	UpdataSelectionTable ust = new UpdataSelectionTable();
	CalculateSelectionQuantityMinuszDeliveredQuantity csqmdq= new CalculateSelectionQuantityMinuszDeliveredQuantity();
	UpdataStockForSelectionPart usfs = new UpdataStockForSelectionPart();
	QantityCount qc = new QantityCount();

	

	
public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
					Order frame = new Order();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


public Order() {

	   
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
		
		
	//---------------------------------------- Mouse entered and exited ---------------------------------------
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
	
	
	
	
	
	//--------------------------------------------------------------------------------------------------------
		
		
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
		lblNewLabel.setBounds(154, 464, 114, 29);
		lblNewLabel.setFont(new Font("Century", Font.BOLD, 17));
		lblNewLabel.setForeground(Color.DARK_GRAY);
		
		panel.add(lblNewLabel);
		
		
		comboBox_1.setForeground(Color.DARK_GRAY);
		comboBox_1.setFont(new Font("Century", Font.BOLD, 17));
		comboBox_1.setBounds(263, 463, 170, 30);
		panel.add(comboBox_1);
		
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
		
		JLabel lblDtum = new JLabel("D\u00E1tum");
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
		
		JButton btnNewButton = new JButton("Hozz\u00E1ad");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// hibakezelés
				int sendDb= Integer.parseInt(dbText.getText());
				if(sendDb>qc.selectionQuntity(comboBox_1)) {
					JOptionPane.showMessageDialog(null, "Nincs elegendõ mennyiség");
				}else {
					
					
					rdnt.getDatabase(table, weekChangeTxt);
					fdnt.productionAdd(weekChangeTxt, comboBox_1, dátumTxt, dbText, deliveryTxt);
					rdnt.getDatabase(table, weekChangeTxt);
					ust.UpdateColumnToArray(csqmdq.ProdQuntityMinuszSendHeatTreatment(comboBox_1), comboBox_1);
					rst.getSelectionTable(table_3);
					qc.sendHeatQuntityCount(comboBox_1);
					usfs.UpdataSentQuantity(comboBox_1);
				}
			
				
			}
		});
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(1228, 464, 100, 30);
		panel.add(btnNewButton);
		sp.selectProduct(comboBox_1);
		
		table = new JTable();
		table.setBounds(2, 550, 1350,180 );
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(table);
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
		scrollPane.setBounds(0, 525	, 1350, 25);
		
		panel.add(scrollPane);
		scrollPane.setViewportView(table_2);
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Cikkszám","Dátum","Darabszám","Rendelés száma"
			}
		));
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
				
				if(condition>1) {
					pw.prewWeek(weekChangeTxt);
					rdnt.getDatabase(table, weekChangeTxt);
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
				if(condition<52) {
					nw.nextWeek(weekChangeTxt);
					rdnt.getDatabase(table, weekChangeTxt);
				}
			}
		});
		nextLbl.setHorizontalAlignment(SwingConstants.LEFT);
		nextLbl.setForeground(Color.DARK_GRAY);
		nextLbl.setFont(new Font("Century", Font.BOLD, 23));
		nextLbl.setBounds(69, 465, 21, 29);
		
		panel.add(nextLbl);
		table_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		table_3.setBounds(2, 190, 1350, 180);
		
		panel.add(table_3);
		scrollPane_1.setBounds(2, 165, 1350, 25);
		
		panel.add(scrollPane_1);
		scrollPane_1.setViewportView(table_4);
		table_4.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Cikkszám","Dátum", "Jó termék", "Rossz termék","Hõkezelés száma"
			}
		));
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
		
	
		rdnt.getDatabase(table, weekChangeTxt);
		rst.getSelectionTable(table_3);
	
	}
}
