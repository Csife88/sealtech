package heat.treatment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;

import supplier.DatabaseCon;

public class ArrivedHeatQuantitys {

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;


	public ArrayList<Integer> getListQuntity(JComboBox<String> comboBox) { 

		
		ArrayList<Integer> arrivedHeatQuantity = new ArrayList<>();

		try {
			String productNumber = comboBox.getModel().getSelectedItem().toString();
			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			St = con.createStatement();
			Rs = St.executeQuery("Select quantity from pls.heattreatment where productNumber='" + productNumber+"'");

			while (Rs.next()) {

				arrivedHeatQuantity.add(Rs.getInt(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return arrivedHeatQuantity;
	}
}