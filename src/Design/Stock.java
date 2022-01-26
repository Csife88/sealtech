package Design;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
import Classes.ReadSelectionTable;
import Classes.ReadStockTable;
import Classes.SelectProduct;
import Classes.UpdataMade;
import Classes.UpdataStockForSelectionPart;
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



public class Stock extends JFrame {

	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
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
	JLabel lblNewLabel_1_1 = new JLabel("St\u00E1tusz");
	JComboBox comboBox = new JComboBox();
	JComboBox comboBox_1 = new JComboBox();
    JTable table = new JTable();

	private JTable table_1;
	private final JTable table_2 = new JTable();
	private final JScrollPane scrollPane = new JScrollPane();
	
	QantityCount qc = new QantityCount();
	UpdataStockForSelectionPart usfsp = new UpdataStockForSelectionPart();
	SelectProduct sp = new SelectProduct();
	ReadStockTable rst = new ReadStockTable();
	
public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
					Stock frame = new Stock();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


public Stock() {

	   
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
		lblNewLabel.setBounds(50, 150, 114, 29);
		lblNewLabel.setFont(new Font("Century", Font.BOLD, 17));
		lblNewLabel.setForeground(Color.DARK_GRAY);
		
		panel.add(lblNewLabel);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1.setFont(new Font("Century", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(450, 150, 114, 29);
		
		panel.add(lblNewLabel_1_1);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Késztermék", "Hõkezelt","Hõkezelõben","Gyártott"}));
		comboBox.setBounds(600, 150, 220, 30);
		panel.add(comboBox);
		comboBox.setForeground(Color.DARK_GRAY);
		comboBox.setFont(new Font("Century", Font.BOLD, 20));
		
		
		table.setBounds(2, 245, 1350, 500);
		panel.add(table);
		table.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		
		comboBox_1.setForeground(Color.DARK_GRAY);
		comboBox_1.setFont(new Font("Century", Font.BOLD, 20));
		comboBox_1.setBounds(213, 150, 220, 30);
		panel.add(comboBox_1);
		
		JButton btnNewButton = new JButton("Keres");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ProductNarrow pn = new ProductNarrow();
				pn.keres(comboBox_1, comboBox, table);
				qc.sendHeatQuntityCount(comboBox_1);
				usfsp.UpdataSentQuantity(comboBox_1);
			}
		});
		btnNewButton.setFont(new Font("Century", Font.BOLD, 20));
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setBounds(980, 150, 220, 30);
		panel.add(btnNewButton);
		scrollPane.setBounds(2, 214, 1350, 30);
		
		panel.add(scrollPane);
		scrollPane.setViewportView(table_2);
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Cikkszám","Státusz","Darabszám"
			}
		));
		sp.selectProduct(comboBox_1);
		rst.ReadDatabase(table);
	
	}
}
