package rawmetarial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import supplier.DatabaseCon;
import supplier.MyTableModel;

public class UpdateRawMetrialTable {

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;

	MyTableModel myTableModel = new MyTableModel();

	private double remaining = 0;
	public boolean isEnoughtMetarial = false;

	private double ReadOnstockQuantity(JComboBox<String> deliveryCombo) {

		double onStockquantity = 0;

		try {

			con = DriverManager.getConnection(DatabaseCon.getUrl(), DatabaseCon.getName(), DatabaseCon.getPassword());
			St = con.createStatement();
			Rs = St.executeQuery("Select onStock from pls.rawmetarial where deliveryNumber='"
					+ deliveryCombo.getModel().getSelectedItem().toString() + "'");
			while (Rs.next()) {
				onStockquantity = Rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Math.round(onStockquantity);
	}

	private double calculateRequiredQuantity(JTextField dbText, JComboBox<String> productComboBox) {

		double requiredQuantity = 0;

		switch (productComboBox.getModel().getSelectedItem().toString()) {
		case "HU093133-0960":
			requiredQuantity = Double.parseDouble(dbText.getText()) * 2.99 / 1000;
			break;
		case "HU093133-0890":
			requiredQuantity = Double.parseDouble(dbText.getText()) * 4.30 / 1000;
			break;
		case "HU093133-0820":
			requiredQuantity = Double.parseDouble(dbText.getText()) * 4.05 / 1000;
			break;
		case "HU093133-0970":
			requiredQuantity = Double.parseDouble(dbText.getText()) * 2.99 / 1000;
			break;
		case "HU098065-0020":
			requiredQuantity = Double.parseDouble(dbText.getText()) * 1.85 / 1000;
			break;
		case "HU098065-0030":
			requiredQuantity = Double.parseDouble(dbText.getText()) * 3.40 / 1000;
			break;

		}

		return Math.round(requiredQuantity);
	}

	private double calculateRemainingQuntity(JComboBox<String> deliveryCombo, JComboBox<String> productComboBox,
			JTextField dbText) {

		if (ReadOnstockQuantity(deliveryCombo) < calculateRequiredQuantity(dbText, productComboBox)) {

			double onePeace = calculateRequiredQuantity(dbText, productComboBox) * 1000
					/ Double.parseDouble(dbText.getText());
			double enough = Math.round(ReadOnstockQuantity(deliveryCombo) * 1000 / onePeace);

			isEnoughtMetarial = false;
			JOptionPane.showMessageDialog(null,
					"Ez a szállítólevé számu nyersanyag már csak " + enough + " darab termék legyártására elegendő");

		} else {
			remaining = ReadOnstockQuantity(deliveryCombo) - calculateRequiredQuantity(dbText, productComboBox);
			isEnoughtMetarial = true;
		}

		return Math.round(remaining);
	}

	public void UpdateOnStockQuantity(JComboBox<String> deliveryCombo, JComboBox<String> productComboBox,
			JTextField dbText) {

		calculateRemainingQuntity(deliveryCombo, productComboBox, dbText);

		if (isEnoughtMetarial) {
			try {
				con = DriverManager.getConnection(DatabaseCon.getUrl(), DatabaseCon.getName(),
						DatabaseCon.getPassword());
				String Query = "Update pls.rawmetarial set onStock='" + remaining + "'" + "where deliveryNumber='"
						+ deliveryCombo.getModel().getSelectedItem().toString() + "'";
				Statement Add = con.createStatement();
				Add.executeUpdate(Query);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isEnoughtMetarial() {
		return isEnoughtMetarial;
	}

}
