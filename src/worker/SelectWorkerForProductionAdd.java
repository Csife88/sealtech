package worker;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;

import supplier.DatabaseCon;

public class SelectWorkerForProductionAdd {

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	public void selectWorker( JComboBox<String> workerCombo) {

		try {

			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
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
