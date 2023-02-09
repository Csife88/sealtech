package product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;

import supplier.DatabaseCon;

public class SelectProduct { //combo box felt�lt�se term�kkel
	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;

	
	public void selectProduct(JComboBox<String> comboBox) {

		try {
			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			St = con.createStatement();
			Rs = St.executeQuery("Select Cikkszam from pls.product");
			// table.setModel(DbUtils.resultSetToTableModel(Rs));

			comboBox.removeAllItems();
			while (Rs.next()) {

				comboBox.addItem(Rs.getString("Cikkszam"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
