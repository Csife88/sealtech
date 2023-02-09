package product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;

import supplier.DatabaseCon;


public class SelectProductForProductionAdd {

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	

	public void selectProduct(JComboBox<String> comboBox_1 ) {

		try {

			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			St = con.createStatement();
			Rs = St.executeQuery("Select Cikkszam from pls.product");
			// table.setModel(DbUtils.resultSetToTableModel(Rs));

			comboBox_1.removeAllItems();
			while (Rs.next()) {

				comboBox_1.addItem(Rs.getString("Cikkszam"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
}
