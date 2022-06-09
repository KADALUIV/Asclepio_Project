package com.asclepio.main;

import java.awt.EventQueue;

import com.asclepio.control.AppControl;
import com.asclepio.gui.PStock;
import com.asclepio.gui.PCompra;
import com.asclepio.gui.VLogin;
import com.asclepio.gui.VPrincipal;



public class RunApp {

	public static void main(String[] args) {
		
		/* UTILIZAR MAVEN Y LAS LIBRERIAS FLATLIGHTLAF
		try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }*/
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				VPrincipal vP = new VPrincipal();
				VLogin vL = new VLogin();
				PCompra pC = new PCompra();
				PStock pStock = new PStock();
				
				AppControl control = new AppControl(vP, vL, pC, pStock);
				
				vL.setControlador(control);
				vP.setControlador(control);
				pC.setControlador(control);
				pStock.setControlador(control);
				
				vL.hacerVisible();
				
			}
		});

	}

}
