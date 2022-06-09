package com.asclepio.main;

import java.awt.EventQueue;

import com.asclepio.control.AppControl;
import com.asclepio.gui.PHistorial;
import com.asclepio.gui.VLogin;
import com.asclepio.gui.VPrincipal;

public class RunApp {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				VPrincipal vP = new VPrincipal();
				VLogin vL = new VLogin();
				PHistorial pHist = new PHistorial();
				
				AppControl control = new AppControl(vP, vL, pHist);
				
				vL.setControlador(control);
				vP.setControlador(control);
				pHist.setControlador(control);
				
				vL.hacerVisible();
				
			}
		});

	}

}
