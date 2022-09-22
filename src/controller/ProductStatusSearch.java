package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class ProductStatusSearch {
	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	
	public void keres(JComboBox<String> cikkBox, JTable table) {
	
			String cikkSzam = cikkBox.getModel().getSelectedItem().toString();
		
			try  {
				
				con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
				St = con.createStatement();
				Rs = St.executeQuery("Select ProductNumber,Status,Quantity from pls.stock where ProductNumber='" + cikkSzam+"'");
				table.setModel(DbUtils.resultSetToTableModel(Rs));
				
				
			}catch(Exception ex ) {
				ex.printStackTrace();
			}
			
		}

}
