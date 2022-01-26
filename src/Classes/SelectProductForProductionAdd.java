package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;


public class SelectProductForProductionAdd {

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	

	public void selectProduct(JComboBox comboBox_1 ) {

		try {

			con = DriverManager.getConnection("jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls",
					"admin", "Szemes1!");
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
