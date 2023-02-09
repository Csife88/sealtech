package production;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import supplier.DatabaseCon;

public class FillProductionTable {
	
	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	public void productionAdd(JComboBox<String> comboBox_1 ,JComboBox<String> workerCombo, JComboBox<String> deliveryCombo, JTextField dátumTxt, JTextField dbText ) {
		
		
		try {
			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			PreparedStatement add = con.prepareStatement("insert into pls.production values(next value for pls.production_seq,?,?,?,?,?,?,?)");
			
			
			add.setString(1,comboBox_1.getModel().getSelectedItem().toString());
			add.setDate(2,java.sql.Date.valueOf(dátumTxt.getText())); // java.sql.Date
			add.setString(3,workerCombo.getModel().getSelectedItem().toString());
			add.setInt(4, Integer.valueOf(dbText.getText()));
			add.setString(5, "Gyártott");
			add.setString(6, deliveryCombo.getModel().getSelectedItem().toString());
			add.setString(7, "false");
			
			add.executeUpdate();
			JOptionPane.showMessageDialog(null, "Hozzáadás sikeres");
			con.close();
			
		}catch (Exception ex){
			ex.printStackTrace();
		}
		
	}	

}
