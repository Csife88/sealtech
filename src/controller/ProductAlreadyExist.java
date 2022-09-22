package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextField;

public class ProductAlreadyExist {
	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	
public boolean isExist(String  tableName,JTextField txtNewPartNumber) {
     
	
	try  {
		con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
		St = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		Rs = St.executeQuery("Select Cikkszam from pls."+tableName);
	   
		while(Rs.next()) {
			
			if(Rs.getNString("Cikkszam").equals(txtNewPartNumber.getText())) {
				return true;
			}
		}
	    	
	}catch(Exception e ) {
		e.printStackTrace();
	}
	
	
  	return false;

 }

public boolean isExistProductAndStatus(String  tableName,JTextField txtNewPartNumber,JTextField textSatusz) {
     
	
	try  {
		con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
		St = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		Rs = St.executeQuery("Select ProductNumber,Status from pls."+tableName);
	   
		while(Rs.next()) {
			
			
			if(Rs.getNString("ProductNumber").equals(txtNewPartNumber.getText()) && Rs.getNString("Status").equals(textSatusz.getText())) {
				return true;
			}
		}
	    	
	}catch(Exception e ) {
		e.printStackTrace();
	}
	
	
  	return false;

 }


}
