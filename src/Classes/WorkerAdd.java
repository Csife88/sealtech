package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class WorkerAdd {
	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	public void workerAdd(JTextField newWorkerTxt) {

		WorkerRowCount wrc = new WorkerRowCount();
		int id = wrc.workerRowCount();

		try {
			con = DriverManager.getConnection("jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls",
					"admin", "Szemes1!");
			PreparedStatement add = con.prepareStatement("insert into worker values(?,?)");

			add.setInt(1, id);
			add.setString(2, newWorkerTxt.getText());

			int row = add.executeUpdate();
			JOptionPane.showMessageDialog(null, "Hozzáadás sikeres");
			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
