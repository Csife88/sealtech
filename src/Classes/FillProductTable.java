package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FillProductTable {
	
	ProductionRowCount grc = new ProductionRowCount();
	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	public void productionAdd(JComboBox comboBox_1 ,JComboBox workerCombo, JComboBox deliveryCombo, JTextField dátumTxt, JTextField dbText ) {
		
		  ProductionRowCount grc = new ProductionRowCount();
	      int id = grc.getCount();
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls", "admin", "Szemes1!");
			PreparedStatement add = con.prepareStatement("insert into production values(?,?,?,?,?,?,?,?)");
			
			
			
		 	add.setInt(1, id+1);
			add.setString(2,comboBox_1.getModel().getSelectedItem().toString());
			add.setDate(3,java.sql.Date.valueOf(dátumTxt.getText())); // java.sql.Date
			add.setString(4,workerCombo.getModel().getSelectedItem().toString());
			add.setInt(5, Integer.valueOf(dbText.getText()));
			add.setString(6, "Gyártott");
			add.setString(7, deliveryCombo.getModel().getSelectedItem().toString());
			add.setString(8, "false");
			
			int row = add.executeUpdate();
			JOptionPane.showMessageDialog(null, "Hozzáadás sikeres");
			con.close();
			
		}catch (Exception ex){
			ex.printStackTrace();
		}
		
	}	

}
