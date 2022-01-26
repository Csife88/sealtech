package Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import Design.HeatTreatment;

public class CalculateSelectionQuantityMinuszDeliveredQuantity {

	SendDeliveryQuantity sdq = new SendDeliveryQuantity();
	//SendHeatTreatmentQuantitys shtq = new SendHeatTreatmentQuantitys();
	SelectionProductQuntitys spq = new SelectionProductQuntitys();
	//ProductQuntitys pq = new ProductQuntitys();
	

	public ArrayList<Integer> ProdQuntityMinuszSendHeatTreatment(JComboBox combo)  //teszt
	{ 
		String prodNumber = combo.getModel().getSelectedItem().toString();
	
		ArrayList<Integer> calculate = new ArrayList<>();

		int heatTreatmentQuantity = sdq.SendDeliveryQuantity(prodNumber);
		
		calculate = spq.getListQuntity(prodNumber);
		int helper = 0;

		for (int i = 0; i < spq.getListQuntity(prodNumber).size(); i++) {

			if (heatTreatmentQuantity >= calculate.get(i)) 
			{
				helper = calculate.get(i);
				calculate.set(i, calculate.get(i) - calculate.get(i));

				heatTreatmentQuantity = heatTreatmentQuantity - helper;

			} else {

				calculate.set(i, (calculate.get(i) - heatTreatmentQuantity));
				heatTreatmentQuantity = 0;
			}
		}
		return calculate;

	}

}
