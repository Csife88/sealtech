package com.csife.designepattern.demo1;

import javax.swing.SwingUtilities;

import com.csife.designepattern.Controller.Controller;
import com.csife.designepattern.model.Model;
import com.csife.designepattern.view.View;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				runApp();
			}
			  
		});
	}

	public static void runApp() {
		
		Model model = new Model();
		View view  = new View(model);
		
		Controller controller = new Controller(model, view);
	}
}
