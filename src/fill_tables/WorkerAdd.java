package fill_tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.DatabaseCon;
import controller.RowCount;

public class WorkerAdd {
	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	public void workerAdd(JTextField newWorkerTxt) {

		RowCount rowCount = new RowCount();
		int id = rowCount.getCount("worker");

		try {
			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			PreparedStatement add = con.prepareStatement("insert into pls.worker values(?,?)");

			add.setInt(1, id);
			add.setString(2, newWorkerTxt.getText());
			add.executeUpdate();
			JOptionPane.showMessageDialog(null, "Hozzáadás sikeres");
			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
