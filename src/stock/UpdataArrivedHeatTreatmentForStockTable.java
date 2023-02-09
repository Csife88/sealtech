package stock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;

import supplier.DatabaseCon;
import supplier.QantityCount;

public class UpdataArrivedHeatTreatmentForStockTable {
		

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;


	public void UpdataArrived(JComboBox<String> combo) { 

		
		String number = combo.getModel().getSelectedItem().toString();
		
		try {
			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			String Query = "Update pls.stock set Quantity='" +new QantityCount().arrivedHeatQuntityCount(combo) + "'" + "where ProductNumber='"
					+ number+ "'" + "AND Status=" + "'Hőkezelt'";
			Statement Add = con.createStatement();
			Add.executeUpdate(Query);

		} catch (Exception el) {

			el.printStackTrace();  
		}

	}
}
