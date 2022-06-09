package com.asclepio.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.asclepio.db.sql.SqlQuery;
import com.asclepio.gui.PStock;
import com.asclepio.gui.VLogin;
import com.asclepio.gui.VPrincipal;

import com.asclepio.model.Producto;


public class AppControl implements ActionListener {
	
	private static ArrayList<Producto> listaProd;
	VPrincipal vP;
	VLogin vL;
	PStock pSto;
	SqlQuery sq;
	

	public AppControl(VPrincipal vP, VLogin vL, PStock pSto) {
		this.vP = vP;
		this.vL = vL;
		this.pSto = pSto;
		this.sq = new SqlQuery();
		listaProd = new ArrayList<Producto>();
	}



	@Override
	public void actionPerformed(ActionEvent ev) {
		if(ev.getActionCommand().equals(pSto.BTN_BUSQUEDA)) {
			
			listaProd = sq.verStock();
			
		}
	}

}
