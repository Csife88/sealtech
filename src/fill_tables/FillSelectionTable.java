package fill_tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import controller.DatabaseCon;
import controller.RowCount;

public class FillSelectionTable {

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	RowCount rowCount = new RowCount();

	public void fillSelectionTable(int goodPart, int badPart, String getNumber, String dText) {
		try {
			con = DriverManager.getConnection(DatabaseCon.getUrl(), DatabaseCon.getName(), DatabaseCon.getPassword());
			PreparedStatement add = con.prepareStatement("insert into pls.selectionparts values(?,?,?,?,?,?)");

			int id = rowCount.getCount("selectionparts");

			long millis = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis); // aktuális dátum date objektumba

			add.setInt(1, id);
			add.setString(2, getNumber);
			add.setDate(3, date);
			add.setInt(4, goodPart);
			add.setInt(5, badPart);
			add.setString(6, dText);

			add.executeUpdate();
			JOptionPane.showMessageDialog(null, "Küldés sikeres");
			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
}
