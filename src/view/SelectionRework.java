package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.GetPartNumber;
import controller.ModifyBadPartQuantityAfterRework;
import controller.QantityCount;
import controller.RefressArrivedQuntityToZero;
import fill_tables.FillSelectionTable;
import read_tables.ReadSelectionTable;

public class SelectionRework extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPanel = new JPanel();
	private JTextField reWorkTxt;

	QantityCount qantityCount = new QantityCount();
	RefressArrivedQuntityToZero refressArrivedQuntityToZero = new RefressArrivedQuntityToZero();
	ReadSelectionTable readSelectionTable = new ReadSelectionTable();
	ModifyBadPartQuantityAfterRework modifyBad = new ModifyBadPartQuantityAfterRework();


	public SelectionRework() {

		setBounds(600, 300, 480, 180);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel partNumberLabel = new JLabel("");
			partNumberLabel.setBounds(30, 15, 130, 25);
			partNumberLabel.setText(GetPartNumber.getPartNumber());
			partNumberLabel.setFont(new Font("Century", Font.BOLD, 14));
			contentPanel.add(partNumberLabel);
		}
		{
			JLabel lblNewLabel = new JLabel("Újra válogatható mennyiség: ");
			lblNewLabel.setBounds(148, 15, 240, 25);
			lblNewLabel.setFont(new Font("Century", Font.BOLD, 14));
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel quantityLabel = new JLabel("");
			quantityLabel.setBounds(386, 15, 68, 29);
			quantityLabel.setText(String.valueOf(qantityCount.badPartQuntity()));
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
						if (Selection.getHeatedQuantity() + Integer.parseInt(reWorkTxt.getText()) >= Selection
								.getGoodPluszBad()) {
						new FillSelectionTable().fillSelectionTable(Selection.goodPart, Selection.badPart, Selection.getNumber,
									Selection.dText);
							refressArrivedQuntityToZero.refressToZero(GetPartNumber.getPartNumber());
							modifyBad.modifyBadPartQuantity(GetPartNumber.getPartNumber(), reWorkTxt);

							System.out.println(Selection.goodPart + "   " + Selection.badPart);

							dispose();

						} else {

							JOptionPane.showMessageDialog(null, "Nincs elegend� darab");
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
				cancelButton.setActionCommand("M�gse");
				buttonPane.add(cancelButton);
			}
		}

	}
}
