package com.asclepio.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.asclepio.db.sql.SqlQuery;
import com.asclepio.gui.PCompra;
import com.asclepio.gui.VLogin;
import com.asclepio.gui.VPrincipal;

public class AppControl implements ActionListener {
	
	VPrincipal vP;
	VLogin vL;
	PCompra pC;
	SqlQuery p;
	
	

	public AppControl(VPrincipal vP, VLogin vL, PCompra pC) {
		this.vP = vP;
		this.vL = vL;
		this.pC = pC;
		
		p = new SqlQuery();
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			if(e.getActionCommand().equals(PCompra.BTN_BUSQ)) {
				searchProd();
			}else if(e.getActionCommand().equals(PCompra.BTN_CARRITO)) {
				addCarrito();
			}
		}
	}



	private void addCarrito() {
		if(pC.getList().getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(pC, "Debe seleccionar el elemento a añadir", "Error de selección",
					JOptionPane.ERROR_MESSAGE);
		}else {
			String id = pC.getSelectedRow();
			
		//	p.getProduct();
		}
		
	}



	private void searchProd() {
		
		//pC.showList(p.getProducts());
		
		if(pC.getTxtBusq().isEmpty()) {
			pC.showList(p.getProducts());
		}else {
			String busq = pC.getTxtBusq();
			pC.showList(p.getSearchedProd(busq));
			
			
		}
		
	}

}
