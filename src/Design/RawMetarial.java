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

import Classes.ProductionRowCount;
import Classes.RawMetarialRowCount;
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

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class RawMetarial extends JFrame {
	
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
	private final JLabel lblTipus = new JLabel("T\u00EDpus");
	private final JComboBox tipusCombo = new JComboBox();
	private final JLabel lblSlyKg = new JLabel("S\u00FAly kg");
	private final JTextField sulyTxt = new JTextField();
	private final JLabel lblBeszllt = new JLabel("Besz\u00E1ll\u00EDt\u00F3");
	private final JComboBox supplierCombo = new JComboBox();
	private final JLabel lblSzlltlevelSzma = new JLabel("Sz\u00E1ll\u00EDt\u00F3level sz\u00E1ma");
	private final JTextField deliveryTxt = new JTextField();
	private final JButton btnMetarialAdd = new JButton("Hozz\u00E1ad");
	private final JTable table = new JTable();
	private final JTable table_1 = new JTable();
	private final JScrollPane scrollPane = new JScrollPane();

	/**
	 * Launch the application.
	 */

public void getDatabase() {
		
		try  {
			
			con = DriverManager.getConnection("jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls", "admin", "Szemes1!");
			St = con.createStatement();
			Rs = St.executeQuery("Select ID,type,quantity,supplier,deliveryNumber,onStock from pls.rawmetarial");
			table.setModel(DbUtils.resultSetToTableModel(Rs));
			
		}catch(Exception e ) {
			e.printStackTrace();
		}
		
}
public void metarialAdd() {
	
	RawMetarialRowCount rmrc = new RawMetarialRowCount();	 
    int id =rmrc.workerRowCount();
	
	try {
		con = DriverManager.getConnection("jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls", "admin", "Szemes1!");
		PreparedStatement add = con.prepareStatement("insert into rawmetarial values(?,?,?,?,?,?)");
		
		
		
	 	add.setInt(1, id);
		add.setString(2,tipusCombo.getModel().getSelectedItem().toString());
		add.setInt(3, Integer.valueOf(sulyTxt.getText()));
		add.setString(4,supplierCombo.getModel().getSelectedItem().toString());
		add.setString(5, deliveryTxt.getText());
		add.setInt(6,Integer.valueOf(sulyTxt.getText()));
		
		int row = add.executeUpdate();
		JOptionPane.showMessageDialog(null, "Hozzáadás sikeres");
		con.close();
		getDatabase();
		
	}catch (Exception ex){
		ex.printStackTrace();
	}
	
}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
					RawMetarial frame = new RawMetarial();
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
	public RawMetarial() {
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
		
		lblTipus.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipus.setForeground(Color.DARK_GRAY);
		lblTipus.setFont(new Font("Century", Font.BOLD, 20));
		lblTipus.setBounds(-11, 155, 114, 29);
		
		panel.add(lblTipus);
		tipusCombo.setModel(new DefaultComboBoxModel(new String[] {"1 x 53mm ECU-58","1,5 x 45mm ECU-58","1,85 x 45mm ECU-58","2 x 45mm ECU-58"}));
		tipusCombo.setForeground(Color.DARK_GRAY);
		tipusCombo.setFont(new Font("Century", Font.BOLD, 18));
		tipusCombo.setBounds(88, 155, 200, 30);
		
		panel.add(tipusCombo);
		lblSlyKg.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlyKg.setForeground(Color.DARK_GRAY);
		lblSlyKg.setFont(new Font("Century", Font.BOLD, 20));
		lblSlyKg.setBounds(298, 155, 114, 29);
		
		panel.add(lblSlyKg);
		sulyTxt.setToolTipText("1999-12-31");
		sulyTxt.setColumns(10);
		sulyTxt.setBounds(398, 155, 50, 30);
		
		panel.add(sulyTxt);
		lblBeszllt.setHorizontalAlignment(SwingConstants.CENTER);
		lblBeszllt.setForeground(Color.DARK_GRAY);
		lblBeszllt.setFont(new Font("Century", Font.BOLD, 20));
		lblBeszllt.setBounds(455, 155, 114, 29);
		
		panel.add(lblBeszllt);
		supplierCombo.setModel(new DefaultComboBoxModel(new String[] {"Majer Kft","Vinco"}));
		supplierCombo.setForeground(Color.DARK_GRAY);
		supplierCombo.setFont(new Font("Century", Font.BOLD, 18));
		supplierCombo.setBounds(579, 155, 200, 30);
		
		panel.add(supplierCombo);
		lblSzlltlevelSzma.setHorizontalAlignment(SwingConstants.CENTER);
		lblSzlltlevelSzma.setForeground(Color.DARK_GRAY);
		lblSzlltlevelSzma.setFont(new Font("Century", Font.BOLD, 20));
		lblSzlltlevelSzma.setBounds(789, 156, 197, 29);
		
		panel.add(lblSzlltlevelSzma);
		deliveryTxt.setToolTipText("1999-12-31");
		deliveryTxt.setColumns(10);
		deliveryTxt.setBounds(996, 155, 200, 30);
		
		panel.add(deliveryTxt);
		btnMetarialAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				metarialAdd();
			}
		});
		btnMetarialAdd.setBounds(1206, 155, 142, 29);
		btnMetarialAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMetarialAdd.setForeground(Color.DARK_GRAY);
		
		panel.add(btnMetarialAdd);
		table.setBounds(2, 230, 1350, 500);
		table.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		panel.add(table);
		scrollPane.setBounds(2, 200, 1350, 30);
		
		panel.add(scrollPane);
		scrollPane.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sorszám","Típus","Súly KG","Beszállító","Szállítólevel száma","Készleten"
			}
		));
		
		getDatabase();
		
	}
}
