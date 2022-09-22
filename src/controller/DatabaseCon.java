package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DatabaseCon  {
	
	private static String url;
	private static String userName;
	private static String password;

	public static void Run() throws IOException {
		String file ="C:/Sealtech2/login";
		
		
		 ArrayList<String> lines = new ArrayList<String>();
		 
		 BufferedReader reader = new BufferedReader(new FileReader(file));
		
		String line = reader.readLine();
		
		 while (line != null) {
			 	lines.add(line);
	           line = reader.readLine();
	       }
		 
		 reader.close();
		 DatabaseCon.url = lines.get(0);
		 DatabaseCon.userName =lines.get(1);
		 DatabaseCon.password =lines.get(2);
	}
	
	
//amazon	
//private static String url = "jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls";
//private static String userName = "admin";
//private static String password = "Szemes1!";

//local home
//private static String url = "jdbc:mysql://127.0.0.1/pls";
//private static String userName = "root";
//private static String password = "Basill1988";
	
//ms sql 192.168.1.121
	//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//private static String url = "jdbc:sqlserver://192.168.1.121:1433;DatabaseName=sealtech;instance=SQLEXPRESS;encrypt=true;TrustServerCertificate=true;";



	
public static String getUrl() throws IOException {
	Run();
	return url;
}
public static String getName() throws IOException {
	return userName;
}
public static String getPassword() throws IOException {
	return password;
}
	
}
