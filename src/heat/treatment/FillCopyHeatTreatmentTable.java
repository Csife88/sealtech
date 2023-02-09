package heat.treatment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import supplier.DatabaseCon;

public class FillCopyHeatTreatmentTable {
	

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	
	
	public void FillCopyTable(JComboBox<String> comboBox_1,JTextField sendDateTxt,JTextField quantityTxt) {
		
		
		try {
			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			PreparedStatement add = con.prepareStatement("insert into pls.copyHeatTreatment values(next value for pls.copyheattreatment_seq,?,?,?,?)");

			add.setString(1, comboBox_1.getModel().getSelectedItem().toString());
			add.setDate(2, java.sql.Date.valueOf(sendDateTxt.getText())); // java.sql.Date
			add.setInt(3, Integer.valueOf(quantityTxt.getText()));
			add.setDate(4, java.sql.Date.valueOf(sendDateTxt.getText()));
			

			add.executeUpdate();
			con.close();
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
