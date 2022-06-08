package com.asclepio.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.asclepio.db.sql.SqlQuery;
import com.asclepio.gui.VLogin;
import com.asclepio.gui.VPrincipal;
import com.asclepio.model.Usuario;

public class AppControl implements ActionListener {
	
	VPrincipal vP;
	VLogin vL;
	private int contAcces;
	SqlQuery sql;
	
	

	public AppControl(VPrincipal vP, VLogin vL) {
		this.vP = vP;
		this.vL = vL;
		this.sql = new SqlQuery();
		contAcces = 0;
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
		boolean acceso = true;
		Usuario user = vL.comprobarDatos();
		
		if (user != null) {
			contAcces++;
			
			String pwd = sql.consultarPwdxUser(user.getIdUsuario());
			
		}
		
		
	}

}
