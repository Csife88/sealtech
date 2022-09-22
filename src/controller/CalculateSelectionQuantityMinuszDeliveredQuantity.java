package controller;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class CalculateSelectionQuantityMinuszDeliveredQuantity {

	SelectionProductQuntitys selectionProductQuntitys = new SelectionProductQuntitys();
	

	public ArrayList<Integer> ProdQuntityMinuszSendHeatTreatment(JComboBox<String> combo, JTextField quantity)  //teszt
	{ 
		String prodNumber = combo.getModel().getSelectedItem().toString();
	
		ArrayList<Integer> calculate = new ArrayList<>();

		 int deliveredQuantity = Integer.parseInt(quantity.getText());
		 System.out.println(deliveredQuantity);
		
		calculate = selectionProductQuntitys.getListQuntity(prodNumber);
		int helper = 0;

		for (int i = 0; i < selectionProductQuntitys.getListQuntity(prodNumber).size(); i++) {

			if (deliveredQuantity >= calculate.get(i)) 
			{
				helper = calculate.get(i);
				calculate.set(i, calculate.get(i) - calculate.get(i));

				deliveredQuantity = deliveredQuantity - helper;

			} else {

				calculate.set(i, (calculate.get(i) - deliveredQuantity));
				deliveredQuantity = 0;
			}
		}
		return calculate;

	}

}
