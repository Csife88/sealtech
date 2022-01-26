package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FillCopyHeatTreatmentTable {
	

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	
	
	public void FillCopyTable(JComboBox comboBox_1,JTextField sendDateTxt,JTextField quantityTxt) {
		
		HeatTreatmentRowCount htrc = new HeatTreatmentRowCount();
		int id = htrc.getRowCount();
		
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls", "admin", "Szemes1!");
			PreparedStatement add = con.prepareStatement("insert into copyHeatTreatment values(?,?,?,?,?)");

			add.setInt(1, id-1 );
			add.setString(2, comboBox_1.getModel().getSelectedItem().toString());
			add.setDate(3, java.sql.Date.valueOf(sendDateTxt.getText())); // java.sql.Date
			add.setInt(4, Integer.valueOf(quantityTxt.getText()));
			add.setDate(5, java.sql.Date.valueOf(sendDateTxt.getText()));
			

			int row = add.executeUpdate();
			con.close();
			
			

		
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
