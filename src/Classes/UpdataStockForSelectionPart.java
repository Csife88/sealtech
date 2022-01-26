package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class UpdataStockForSelectionPart { // késztermék feltöltése és frissitése srock táblában
	
QantityCount qc = new QantityCount();
	

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;



	public void UpdataSentQuantity(JComboBox comboBox) { // itt tartok 
		
		String number = comboBox.getModel().getSelectedItem().toString();
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls", "admin", "Szemes1!");
			String Query = "Update pls.stock set Quantity='" + qc.selectionQuntity(comboBox) + "'" + "where ProductNumber='"
					+ number+ "'" + "AND Status=" + "'Késztermék'";
			Statement Add = con.createStatement();
			Add.executeUpdate(Query);

		} catch (Exception el) {

			el.printStackTrace();
		}

	}
}
