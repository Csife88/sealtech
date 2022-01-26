package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;

public class SelectProduct { //combo box feltöltése termékkel
	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;

	
	public void selectProduct(JComboBox comboBox) {

		try {

			con = DriverManager.getConnection("jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls", "admin", "Szemes1!");
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
