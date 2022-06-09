package com.asclepio.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.asclepio.db.sql.SqlQuery;
import com.asclepio.gui.VLogin;
import com.asclepio.gui.VPrincipal;
import com.asclepio.model.Usuario;

public class AppControl implements ActionListener {
	
	private static final int TOTAL_INTENTOS = 3;
	VPrincipal vp;
	VLogin vl;
	private int contAcces;
	SqlQuery sql;
	
	

	public AppControl(VPrincipal vp, VLogin vl) {
		this.vp = vp;
		this.vl = vl;
		this.sql = new SqlQuery();
		contAcces = 0;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() instanceof JButton) {
			
			if (e.getActionCommand().equals(VLogin.BTN_LOGIN) || e.getSource().equals(vl.getTxtPwd())) {
				obtenerUsuario();
			}else if (e.getActionCommand().equals(VPrincipal.BTN_SEE_STOCK)) {
				
			}else if (e.getActionCommand().equals(VPrincipal.BTN_REGISTRAR_C)) {
				
			}else if (e.getActionCommand().equals(VPrincipal.BTN_HISTORIAL_C)) {
				
			}
		}else if(e.getSource() instanceof JMenuItem){
			
			if (e.getActionCommand().equals(VPrincipal.ITEM_MENU_LOGOUT)) {
				
			}else if (e.getActionCommand().equals(VPrincipal.ITEM_MENU_EXIT)) {
				
			}
		}
	}



	private void obtenerUsuario() {
		boolean acceso = true;
		Usuario user = vl.comprobarDatos();
		
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
				vl.dispose();
				vp.hacerVisible();
			}
			
			if (acceso) {
				if (contAcces < 3) {
					error += "Te qudan " + (TOTAL_INTENTOS - contAcces) + " intentos";
					vl.setError(error);
					
				}else {
					error += "\nSe han agotado los tres intentos la aplicación se va a cerrar";
					vl.setError(error); 
					System.exit(0);
				}
				
			}
			
			
		}
		
		
	}

}
