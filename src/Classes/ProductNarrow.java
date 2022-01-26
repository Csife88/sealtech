package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class ProductNarrow {
	
	JTable table ;
	JComboBox cikkBox;
	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	public void keres(JComboBox cikkBox, JComboBox statusBox, JTable table) {
	
			String s = statusBox.getModel().getSelectedItem().toString();
			String cikkSzam = cikkBox.getModel().getSelectedItem().toString();
		
			try  {
				
				
				con = DriverManager.getConnection("jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls", "admin", "Szemes1!");
				St = con.createStatement();
				Rs = St.executeQuery("Select ProductNumber,Status,Quantity from pls.stock where ProductNumber='" + cikkSzam+"'" +"AND Status='"+s+"'");
				table.setModel(DbUtils.resultSetToTableModel(Rs));
				
				
			}catch(Exception ex ) {
				ex.printStackTrace();
			}
			
		}

}
