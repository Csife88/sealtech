package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FillDeliveryNoteTable {
	
	ProductionRowCount grc = new ProductionRowCount();
	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	public void productionAdd(JTextField weekTxt,JComboBox comboBox_1 , JTextField dátumTxt, JTextField dbText,JTextField  deliveryTxt) {
		
		  DeliveryRowCount drc = new DeliveryRowCount();
	      int id = drc.getCount();
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls", "admin", "Szemes1!");
			PreparedStatement add = con.prepareStatement("insert into deliveryNote values(?,?,?,?,?,?)");
			
			
			
		 	add.setInt(1, id+1);
		 	add.setString(2, weekTxt.getText());
			add.setString(3,comboBox_1.getModel().getSelectedItem().toString());
			add.setDate(4,java.sql.Date.valueOf(dátumTxt.getText())); // java.sql.Date
			add.setInt(5, Integer.valueOf(dbText.getText()));
			add.setString(6, deliveryTxt.getText());
			
			int row = add.executeUpdate();
			//JOptionPane.showMessageDialog(null, "Hozzáadás sikeres");
			con.close();
			
		}catch (Exception ex){
			ex.printStackTrace();
		}
		
	}	

}
