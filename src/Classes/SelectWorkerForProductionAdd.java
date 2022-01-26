package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;

public class SelectWorkerForProductionAdd {

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	public void selectWorker( JComboBox workerCombo) {

		try {

			con = DriverManager.getConnection("jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls",
					"admin", "Szemes1!");
			St = con.createStatement();
			Rs = St.executeQuery("Select Name from pls.worker");
			// table.setModel(DbUtils.resultSetToTableModel(Rs));

			workerCombo.removeAllItems();
			while (Rs.next()) {

				workerCombo.addItem(Rs.getString("Name"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
