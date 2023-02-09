package delivery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import supplier.DatabaseCon;

public class FillDeliveryTable {
	
	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	public void deliveryAdd(JTextField weekTxt,JComboBox<String> comboBox_1 , 
			JTextField dátumTxt, JTextField dbText,JTextField  deliveryTxt,JComboBox<String> yearCombo ) {
		
		
		try {
			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			PreparedStatement add = con.prepareStatement("insert into pls.deliverynote values(next value for pls.deliverynote_seq,?,?,?,?,?,?)");
			
			add.setString(1, yearCombo.getModel().getSelectedItem().toString());
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
