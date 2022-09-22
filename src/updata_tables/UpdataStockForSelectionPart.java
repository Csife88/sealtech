package updata_tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;

import controller.DatabaseCon;
import controller.QantityCount;

public class UpdataStockForSelectionPart { // k�szterm�k felt�lt�se �s frissit�se srock t�bl�ban
	
QantityCount qc = new QantityCount();
	

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;



	public void UpdataSentQuantity(JComboBox<String> comboBox) { // itt tartok 
		
		String number = comboBox.getModel().getSelectedItem().toString();
		
		try {
			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			String Query = "Update pls.stock set Quantity='" + qc.selectionQuntity(comboBox) + "'" + "where ProductNumber='"
					+ number+ "'" + "AND Status=" + "'Késztermék'";
			Statement Add = con.createStatement();
			Add.executeUpdate(Query);

		} catch (Exception el) {

			el.printStackTrace();
		}

	}
}
