package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class WorkerRowCount {
	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	
public int workerRowCount() {
		
		int rowNumber=1;
			
			try  {
				con = DriverManager.getConnection("jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls", "admin", "Szemes1!");
				St = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				Rs = St.executeQuery("Select workerID from pls.worker");
			 
				while(Rs.next()) {
					
					rowNumber=Rs.getInt(1)+1;
				}
			    
			}catch(Exception e ) {
				e.printStackTrace();
			}
			
		  	return rowNumber;

			
		}

}
