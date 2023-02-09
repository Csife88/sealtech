package stock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;

import supplier.DatabaseCon;
import supplier.QantityCount;

public class UpdataSentHeatTreatmentForStockTable {
	
QantityCount qantityCount = new QantityCount();
	

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;



	public void UpdataSentQuantity(JComboBox<String> combo) { // 
		
		String number = combo.getModel().getSelectedItem().toString();
		
		try {
			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			String Query = "Update pls.stock set Quantity='" + qantityCount.sendHeatQuntityCount(combo) + "'" + "where ProductNumber='"
					+ number+ "'" + "AND Status=" + "'Hőkezelőben'";
			Statement Add = con.createStatement();
			Add.executeUpdate(Query);

		} catch (Exception el) {

			el.printStackTrace();
		}

	}
}
