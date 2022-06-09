package com.asclepio.db.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.asclepio.db.AccesoDB;
import com.asclepio.gui.PStock;
import com.asclepio.model.Producto;



public class SqlQuery {

	private AccesoDB acceso;
	
	public SqlQuery() {
		acceso = new AccesoDB();
	}
	
	
	
	
	
	
	
	public ArrayList<Producto> verStock() {
		ArrayList<Producto> listaProd = new ArrayList<Producto>();
		
		String query = "SELECT * " + " FROM " + ProductContract.NOMBRE_TABLA;
		
		if(PStock.obtenerTexto() != null) {
			query = query + " WHERE " + ProductContract.COLUMN_NOM 
					+ " IS LIKE %" + PStock.obtenerTexto() + "%";
		}
		
		Connection con = null;
		Statement stml = null;
		ResultSet rslt = null;
		
		try {
			con = acceso.getConnection();
			
			stml = con.createStatement();
			
			rslt = stml.executeQuery(query);
			
		Producto prod;
		
		String id_producto;
		String nombre;
		String tipo;
		double precio;
		int stock;
		
		while (rslt.next()) {
			
			id_producto = rslt.getString(1);
			nombre = rslt.getString(2);
			tipo = rslt.getString(3);
			precio = rslt.getDouble(4);
			stock = rslt.getInt(5);

			prod = new Producto(id_producto, nombre, tipo, precio, stock);
			
			listaProd.add(prod);
		}
		
		
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos");
			e.printStackTrace();
		}finally {
			try {
				if (rslt != null) rslt.close(); 
				if (stml != null) stml.close();	
				if (con != null) con.close();
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listaProd;
	}
	
}
