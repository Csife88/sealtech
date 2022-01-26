package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;

public class ArrivedHeatQuantitys {

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;


	public ArrayList<Integer> getListQuntity(JComboBox comboBox) {  // darabszám kivetéle copy tablebõl 

		
		ArrayList<Integer> tomb = new ArrayList<>();

		try {
			String productNumber = comboBox.getModel().getSelectedItem().toString();
			con = DriverManager.getConnection("jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls", "admin", "Szemes1!");
			St = con.createStatement();
			Rs = St.executeQuery("Select quantity from pls.heattreatment where productNumber='" + productNumber+"'");

			while (Rs.next()) {

				tomb.add(Rs.getInt(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return tomb;
	}
}