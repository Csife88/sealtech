package controller;

import java.util.ArrayList;

import javax.swing.JComboBox;

public class CalculateProductQuntityMinuszSendHeatTreatment {

	SendHeatTreatmentQuantitys shtq = new SendHeatTreatmentQuantitys();
	ProductQuntitys pq = new ProductQuntitys();
	

	public ArrayList<Integer> ProdQuntityMinuszSendHeatTreatment(JComboBox<String> combo)  
	{ 
		String prodNumber = combo.getModel().getSelectedItem().toString();
	
		ArrayList<Integer> calculate = new ArrayList<>();

		int heatTreatmentQuantity = shtq.SendHeatTreatmentQuantity(prodNumber);
		
		calculate = pq.getListQuntity(prodNumber);
		int helper = 0;

		for (int i = 0; i < pq.getListQuntity(prodNumber).size(); i++) {

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
