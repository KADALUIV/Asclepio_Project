package com.asclepio.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.asclepio.gui.VLogin;
import com.asclepio.gui.VPrincipal;
import com.asclepio.model.Usuario;

public class AppControl implements ActionListener {
	
	VPrincipal vP;
	VLogin vL;
	
	

	public AppControl(VPrincipal vP, VLogin vL) {
		this.vP = vP;
		this.vL = vL;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() instanceof JButton) {
			JButton btn = (JButton)e.getSource();
			if (btn == vL.getBtnLogin()) {
				obtenerUsuario();
			}
		}
	}



	private void obtenerUsuario() {
		Usuario user = vL.comprobarDatos();
		
		//TODO
		
	}

}
