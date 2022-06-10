package com.asclepio.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import com.asclepio.db.sql.SqlQuery;
import com.asclepio.gui.PStock;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.asclepio.gui.PCompra;
import com.asclepio.gui.PHistorial;
import com.asclepio.gui.VLogin;
import com.asclepio.gui.VPrincipal;
import com.asclepio.model.Usuario;

import com.asclepio.model.Producto;

public class AppControl implements ActionListener {

	private static final int TOTAL_INTENTOS = 3;
	private static ArrayList<Producto> listaProd;
	VPrincipal vp;
	VLogin vl;
	PCompra pC;
	private int contAcces;
	SqlQuery sql;
	PStock pSto;
	PHistorial pHist;

	public AppControl(VPrincipal vp, VLogin vl, PCompra pC, PStock pSto, PHistorial pHist) {
		this.vp = vp;
		this.vl = vl;
		this.pC = pC;
		this.pSto = pSto;
		this.sql = new SqlQuery();
		this.pHist = pHist;
		contAcces = 0;
		listaProd = new ArrayList<Producto>();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			if (e.getActionCommand().equals(PCompra.BTN_BUSQ)) {
				searchProd();
			} else if (e.getActionCommand().equals(PCompra.BTN_CARRITO)) {
				addCarrito();
			} else if (e.getActionCommand().equals(PCompra.BTN_COMPRAR)) {
				buyProducts();
			} else if (e.getActionCommand().equals(PCompra.BTN_ELIMINAR)) {
				deleteProd();
			} else if (e.getActionCommand().equals(PStock.BTN_BUSQUEDA)) {
				listaProd = sql.verStock();
			} else if (e.getActionCommand().equals(VLogin.BTN_LOGIN) || e.getSource().equals(vl.getTxtPwd())) {
				obtenerUsuario();
			} else if (e.getActionCommand().equals(VPrincipal.BTN_SEE_STOCK)) {

			} else if (e.getActionCommand().equals(VPrincipal.BTN_REGISTRAR_C)) {

			} else if (e.getActionCommand().equals(VPrincipal.BTN_HISTORIAL_C)) {

			} else if (e.getActionCommand().equals(PHistorial.BTN_CONSULTAR)) {
				pHist.consultarProductos();
			}
		} else if (e.getSource() instanceof JMenuItem) {

			if (e.getActionCommand().equals(VPrincipal.ITEM_MENU_LOGOUT)) {

			} else if (e.getActionCommand().equals(VPrincipal.ITEM_MENU_EXIT)) {

			}
		}

	}

	private void deleteProd() {
		// TODO Auto-generated method stub
		
	}

	private void buyProducts() {
		// TODO Auto-generated method stub
		
	}

	private void addCarrito() {
		if (pC.getList().getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(pC, "Debe seleccionar el elemento a a�adir", "Error de selecci�n",
					JOptionPane.ERROR_MESSAGE);
		} else {
			
			pC.habilitarCompList(true);

			Producto prod = pC.getSelectedProd();

			int cant = pC.getSpn();
			//String error = "";
			
			if(cant <= 0 && cant > prod.getStock()) {
				pC.setError("La cantidad debe ser mayor que 0 y menor que la de Stock");
			}else {
				int result = sql.updateStock(cant, prod.getIdProducto());
				
				if(result < 0 ) {
					pC.setError("Error en la base de datos");
				}else {
					pC.rellenarTabla(prod, cant);
				}
				
				
			}

			
		}

	}

	private void searchProd() {

		// pC.showList(p.getProducts());

		if (pC.getTxtBusq().isEmpty()) {
			pC.showList(sql.getProducts());
		} else {
			String busq = pC.getTxtBusq();
			pC.showList(sql.getSearchedProd(busq));

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

			} else if (!pwd.equals(user.getPwd())) {
				error = "La password introducida no es correcta.";

			} else {
				acceso = false;
				vl.dispose();
				vp.hacerVisible();
			}

			if (acceso) {
				if (contAcces < 3) {
					error += "Te qudan " + (TOTAL_INTENTOS - contAcces) + " intentos";
					vl.setError(error);

				} else {
					error += "\nSe han agotado los tres intentos la aplicación se va a cerrar";
					vl.setError(error);
					System.exit(0);
				}

			}

		}

	}
}
