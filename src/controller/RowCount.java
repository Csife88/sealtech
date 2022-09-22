package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class RowCount {
	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	
public int getCount(String  tableName) {
     

	int count=1;
	
	try  {
		con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
		St = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		Rs = St.executeQuery("Select ID from pls."+tableName);
	   
		while(Rs.next()) {
			
			count=Rs.getInt(1)+1;
		}
	    	
	}catch(Exception e ) {
		e.printStackTrace();
	}
	
	
  	return count;

 }

	

}
