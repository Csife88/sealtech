package Design;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.GetPartNumber;
import Classes.ModifyBadPartQuantityAfterRework;
import Classes.QantityCount;
import Classes.ReadSelectionTable;
import Classes.RefressArrivedQuntityToZero;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import java.awt.Rectangle;
import java.time.LocalDate;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.JTree;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectionRework extends JDialog {

	private final JPanel contentPanel = new JPanel();

	Selection s = new Selection();
	QantityCount qc = new QantityCount();
	RefressArrivedQuntityToZero raqtz = new RefressArrivedQuntityToZero();
	ReadSelectionTable rst = new ReadSelectionTable();
	private JTextField reWorkTxt;
	ModifyBadPartQuantityAfterRework modifyBad =  new ModifyBadPartQuantityAfterRework();

	public static void main(String[] args) {
		try {
			SelectionRework dialog = new SelectionRework();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SelectionRework() {

		setBounds(600, 300, 480, 180);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel partNumberLabel = new JLabel("");
			partNumberLabel.setBounds(30, 15, 130, 25);
			partNumberLabel.setText(GetPartNumber.GetPartNumber());
			partNumberLabel.setFont(new Font("Century", Font.BOLD, 14));
			contentPanel.add(partNumberLabel);
		}
		{
			JLabel lblNewLabel = new JLabel("Újra válogatható mennyiség: ");
			lblNewLabel.setBounds(150, 15, 240, 25);
			lblNewLabel.setFont(new Font("Century", Font.BOLD, 14));
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel quantityLabel = new JLabel("");
			quantityLabel.setBounds(360, 15, 40, 25);
			quantityLabel.setText(String.valueOf(qc.badPartQuntity()));
			quantityLabel.setFont(new Font("Century", Font.BOLD, 14));
			contentPanel.add(quantityLabel);
		}

		JLabel lblEbbljraVlogatva = new JLabel("Ebb\u0151l \u00FAjra v\u00E1logatva:");
		lblEbbljraVlogatva.setFont(new Font("Century", Font.BOLD, 14));
		lblEbbljraVlogatva.setBounds(116, 50, 240, 25);
		contentPanel.add(lblEbbljraVlogatva);

		reWorkTxt = new JTextField();
		reWorkTxt.setHorizontalAlignment(SwingConstants.CENTER);
		reWorkTxt.setBounds(290, 51, 86, 25);
		reWorkTxt.setFont(new Font("Century", Font.BOLD, 14));
		contentPanel.add(reWorkTxt);
		reWorkTxt.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						//
					 if(s.getHeatedQuantity()+ Integer.parseInt(reWorkTxt.getText()) >= s.getGoodPluszBad() ) {
						   s.fillSelectionTable(s.goodPart, s.badPart);
						   raqtz.refressToZero(GetPartNumber.GetPartNumber());
						   modifyBad.modifyBadPartQuantity(GetPartNumber.GetPartNumber(), reWorkTxt);
						   
						   
						 //  rst.getSelectionTable(table_2);
						   dispose();
					 
					 }else {
						 JOptionPane.showMessageDialog(null, "Nincs elegendõ darab");
					 }
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Mégse");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Mégse");
				buttonPane.add(cancelButton);
			}
		}
		

	}
}
