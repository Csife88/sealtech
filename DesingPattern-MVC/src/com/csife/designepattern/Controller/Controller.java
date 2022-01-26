package com.csife.designepattern.Controller;

import com.csife.designepattern.model.Model;
import com.csife.designepattern.view.View;

public class Controller {
	
	private Model model;
	private View view;
	
	public Controller(Model model, View view) {
		super();
		this.model = model;
		this.view = view;
	}
	
	

}
