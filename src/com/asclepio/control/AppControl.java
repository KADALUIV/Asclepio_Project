package com.asclepio.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.asclepio.gui.VLogin;
import com.asclepio.gui.VPrincipal;

public class AppControl implements ActionListener {
	
	VPrincipal vP;
	VLogin vL;
	
	

	public AppControl(VPrincipal vP, VLogin vL) {
		this.vP = vP;
		this.vL = vL;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
