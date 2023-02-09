package selection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import supplier.DatabaseCon;

public class FillSelectionTable {

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	

	public void fillSelectionTable(int goodPart, int badPart, String getNumber, String dText) {
		try {
			con = DriverManager.getConnection(DatabaseCon.getUrl(), DatabaseCon.getName(), DatabaseCon.getPassword());
			PreparedStatement add = con.prepareStatement("insert into pls.selectionparts values(next value for pls.selection_seq,?,?,?,?,?)");


			long millis = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis); // aktuális dátum date objektumba

			add.setString(1, getNumber);
			add.setDate(2, date);
			add.setInt(3, goodPart);
			add.setInt(4, badPart);
			add.setString(5, dText);

			add.executeUpdate();
			JOptionPane.showMessageDialog(null, "Küldés sikeres");
			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
}
