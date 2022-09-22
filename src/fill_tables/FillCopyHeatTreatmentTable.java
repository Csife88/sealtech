package fill_tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import controller.DatabaseCon;
import controller.RowCount;

public class FillCopyHeatTreatmentTable {
	

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	
	
	public void FillCopyTable(JComboBox<String> comboBox_1,JTextField sendDateTxt,JTextField quantityTxt) {
		
		RowCount rowCount =  new RowCount();
		int id = rowCount.getCount("heattreatment");
		
		try {
			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			PreparedStatement add = con.prepareStatement("insert into pls.copyHeatTreatment values(?,?,?,?,?)");

			add.setInt(1, id-1 );
			add.setString(2, comboBox_1.getModel().getSelectedItem().toString());
			add.setDate(3, java.sql.Date.valueOf(sendDateTxt.getText())); // java.sql.Date
			add.setInt(4, Integer.valueOf(quantityTxt.getText()));
			add.setDate(5, java.sql.Date.valueOf(sendDateTxt.getText()));
			

			add.executeUpdate();
			con.close();
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
