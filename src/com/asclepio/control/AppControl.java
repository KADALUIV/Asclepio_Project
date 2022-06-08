package com.asclepio.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.asclepio.db.sql.SqlQuery;
import com.asclepio.gui.VLogin;
import com.asclepio.gui.VPrincipal;
import com.asclepio.model.Usuario;

public class AppControl implements ActionListener {
	
	private static final int TOTAL_INTENTOS = 3;
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
			
			if (e.getActionCommand().equals(VLogin.BTN_LOGIN)) {
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
			String error = "";
			
			if (pwd == null) {
				error = "El id usuario no existe";
			}else if (!pwd.equals(user.getPwd())) {
				error = "La password introducida no es correcta.";
			}else {
				acceso = false;
				vL.dispose();
				vP.hacerVisible();
			}
			
			if (acceso) {
				if (contAcces < 3) {
					error += "Te qudan " + (TOTAL_INTENTOS - contAcces) + " intentos";
				}else {
					error += "\nSe han agotado los tres intentos la aplicaci�n se va a cerrar";
					vL.setError(error); 
					System.exit(0);
				}
				
			}
			
			
		}
		
		
	}

}
