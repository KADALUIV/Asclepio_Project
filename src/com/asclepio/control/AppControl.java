package com.asclepio.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.asclepio.gui.PHistorial;
import com.asclepio.gui.VLogin;
import com.asclepio.gui.VPrincipal;

public class AppControl implements ActionListener {
	
	VPrincipal vP;
	VLogin vL;
	PHistorial pHist;
	
	

	public AppControl(VPrincipal vP, VLogin vL, PHistorial pHist) {
		this.vP = vP;
		this.vL = vL;
		this.pHist = pHist;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			if (e.getActionCommand().equals(PHistorial.BTN_CONSULTAR)) {
				pHist.consultarProductos();
			}	
		}
	}
}
