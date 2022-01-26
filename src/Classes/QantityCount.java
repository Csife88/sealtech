package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class QantityCount {

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;


	public int madeQuntityCount(JComboBox comboBox) { // param�terz�s tesztel�se 

		String box = comboBox.getModel().getSelectedItem().toString();
		int productionQuntity = 0;
		try {

			con = DriverManager.getConnection("jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls", "admin", "Szemes1!");
			St = con.createStatement();
			Rs = St.executeQuery("Select db from pls.copyproduction where status='" + "Gy�rtott" + "'"
					+ "AND PartNumber='" + box + "'" + "AND used='" + "false" + "'");

			for (; Rs.next();) {

				productionQuntity = productionQuntity + (Integer) Rs.getObject(1);

				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return productionQuntity;
	}
	public int sendHeatQuntityCount(JComboBox combo) { //  h�kezelben l�v� mennyis�gek sz�ml�l�sa 

		String box = combo.getModel().getSelectedItem().toString();
		int sentQuntity = 0;
		try {

			con = DriverManager.getConnection("jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls", "admin", "Szemes1!");
			St = con.createStatement();
			Rs = St.executeQuery("Select quantity from pls.heattreatment where Status='" + "Sent" + "'"
					+ "AND productNumber='" + box + "'");

			for (; Rs.next();) {

				sentQuntity = sentQuntity + (Integer) Rs.getObject(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sentQuntity;
	}
	public int arrivedHeatQuntityCount(JComboBox combo) { //  h�kezelb�l vissza�rkezett mennyis�gek sz�ml�l�sa 

		String box = combo.getModel().getSelectedItem().toString();
		int sentQuntity = 0;
		try {

			con = DriverManager.getConnection("jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls", "admin", "Szemes1!");
			St = con.createStatement();
			Rs = St.executeQuery("Select quantity from pls.heattreatment where Status='" + "Arrived" + "'"
					+ "AND productNumber='" + box + "'");

			for (; Rs.next();) {

				sentQuntity = sentQuntity + (Integer) Rs.getObject(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sentQuntity;
	}	
	public int selectionQuntity(JComboBox comboBox) { // k�szterm�k sz�ml�l�sa  

		String box = comboBox.getModel().getSelectedItem().toString() ;
		int selectionQuntity = 0;
		try {

			con = DriverManager.getConnection("jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls", "admin", "Szemes1!");
			St = con.createStatement();
			Rs = St.executeQuery("Select GoodPart from pls.selectionparts where PartNumber='" + box + "'");

			for (; Rs.next();) {

				selectionQuntity = selectionQuntity + (Integer) Rs.getObject(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return selectionQuntity;
	}
	
	public int badPartQuntity() { // selejt sz�ml�l�sa

		GetPartNumber gpn = new GetPartNumber();
		String part = GetPartNumber.GetPartNumber();
		
		int selectionQuntity = 0;
		try {

			con = DriverManager.getConnection("jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls", "admin", "Szemes1!");
			St = con.createStatement();
			Rs = St.executeQuery("Select BadPart from pls.selectionparts where PartNumber='" + part + "'");

			for (; Rs.next();) {

				selectionQuntity = selectionQuntity + (Integer) Rs.getObject(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return selectionQuntity;
	}
	
	
}
