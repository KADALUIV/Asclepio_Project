package com.asclepio.db.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.asclepio.db.AccesoDB;
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

	public String consultarPwdxUser(int idUsuario) {
		String pwd = null;
		
		
		String query = "SELECT " + UserContract.COLUMN_PASSWD + " FROM " + UserContract.NOMBRE_TABLA + 
						" WHERE " + UserContract.COLUMN_IDUSUARIO + " = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;

						try {
							con = acceso.getConnection();
							pstmt = con.prepareStatement(query);
							
							pstmt.setInt(1, idUsuario);
							
							rslt = pstmt.executeQuery();
							
							if (rslt.next()) {
								
								pwd = rslt.getString(1);
							}
							
						} catch (ClassNotFoundException e) {
							System.out.println("El driver indicado no es correcto");
							e.printStackTrace();
						} catch (SQLException e) {
							System.out.println("Error, sentencia incorrecta");
							e.printStackTrace();
						}finally {
							
							
								try {
									if (rslt != null)rslt.close();
									if (pstmt != null)pstmt.close();
									if (con != null)con.close();
									
								} catch (SQLException e) {
								
									e.printStackTrace();
								} 
							
						}
						
						return pwd;
					}

	
	public ArrayList<Producto> getSearchedProd(String nombre) {
		ArrayList<Producto> listaProdBusq = new ArrayList<Producto>();
		
		String query = "SELECT * FROM " + ProductContract.NOMBRE_TABLA + " WHERE UPPER ( " + ProductContract.COLUMN_NOM + ") LIKE ? ";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;

		try {
			con = acceso.getConnection();

			pstmt = con.prepareStatement(query);
			pstmt.setString(2,"%" +  nombre.toUpperCase() + "%");
			rslt = pstmt.executeQuery();
			
			Producto prod;
			String id;
			String nom;
			String tipo;
			double precio;
			int stock;

			while (rslt.next()) {
				id = rslt.getString(1);
				nom = rslt.getString(2);
				tipo = rslt.getString(3);
				precio = rslt.getDouble(4);
				stock = rslt.getInt(5);

				prod = new Producto(id, nom, tipo, precio, stock);
				listaProdBusq.add(prod);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rslt != null)
					rslt.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		return listaProdBusq;

	}
	
	
	public ArrayList<Producto> getProducts() {
		ArrayList<Producto> listaProductos = new ArrayList<Producto>();

		String query = "SELECT * FROM " + ProductContract.NOMBRE_TABLA;

		Connection con = null;
		Statement stmt = null;
		ResultSet rslt = null;

		try {
			con = acceso.getConnection();
			stmt = con.createStatement();
			rslt = stmt.executeQuery(query);

			Producto prod;
			String id;
			String nombre;
			String tipo;
			double precio;
			int stock;

			while (rslt.next()) {
				id = rslt.getString(1);
				nombre = rslt.getString(2);
				tipo = rslt.getString(3);
				precio = rslt.getDouble(4);
				stock = rslt.getInt(5);

				prod = new Producto(id, nombre, tipo, precio, stock);
				listaProductos.add(prod);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rslt != null)
					rslt.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listaProductos;
	}

	public void getProduct() {
		
		
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
