package com.asclepio.main;

import java.awt.EventQueue;

import com.asclepio.control.AppControl;
import com.asclepio.gui.PStock;
import com.asclepio.gui.VLogin;
import com.asclepio.gui.VPrincipal;



public class RunApp {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				VPrincipal vP = new VPrincipal();
				VLogin vL = new VLogin();
				PStock pStock = new PStock();
				
				AppControl control = new AppControl(vP, vL, pStock);
				
				vL.setControlador(control);
				vP.setControlador(control);
				pStock.setControlador(control);
				
				
				vL.hacerVisible();
				
			}
		});

	}

}
