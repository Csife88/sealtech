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

public class FillDeliveryNoteTable {
	
	RowCount rowCount = new RowCount();
	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	public void deliveryAdd(JTextField weekTxt,JComboBox<String> comboBox_1 , JTextField dátumTxt, JTextField dbText,JTextField  deliveryTxt) {
		
	      int id = rowCount.getCount("deliveryNote");
		
		try {
			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			PreparedStatement add = con.prepareStatement("insert into pls.deliverynote values(?,?,?,?,?,?)");
			
		 	add.setInt(1, id+1);
		 	add.setString(2, weekTxt.getText());
			add.setString(3,comboBox_1.getModel().getSelectedItem().toString());
			add.setDate(4,java.sql.Date.valueOf(dátumTxt.getText())); // java.sql.Date
			add.setInt(5, Integer.valueOf(dbText.getText()));
			add.setString(6, deliveryTxt.getText());
			
			add.executeUpdate();
			con.close();
			
		}catch (Exception ex){
			ex.printStackTrace();
		}
		
	}	

}
