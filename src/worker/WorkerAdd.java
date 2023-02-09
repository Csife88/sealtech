package worker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import supplier.DatabaseCon;

public class WorkerAdd {
	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	public void workerAdd(JTextField newWorkerTxt) {


		try {
			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			PreparedStatement add = con.prepareStatement("insert into pls.worker values(next value for pls.worker_seq,?)");

			add.setString(1, newWorkerTxt.getText());
			add.executeUpdate();
			JOptionPane.showMessageDialog(null, "Hozzáadás sikeres");
			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
