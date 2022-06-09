package com.asclepio.db.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.asclepio.db.AccesoDB;
import com.asclepio.model.ProductoCompra;

public class SqlQuery {
	public static final String NOMBRE_TABLA = "PRODUCTO";
	public static final String COLUMN_IDPRODUCTO = "NOMBRE";
	public static final String COLUMN_PRECIO = "PRECIO";
	public static final String COLUMN_STOCK = "STOCK";
	
	private AccesoDB acceso;
	
	public SqlQuery() {
		acceso = new AccesoDB();
	}
	
	public ArrayList<ProductoCompra> consultarProductos(String fecha) {
		ArrayList<ProductoCompra> productos = new ArrayList<ProductoCompra>();
		
		String query = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		
		try {
			con = this.acceso.getConnection();
			
			if(fecha == null || fecha.equals("")) {
				query = "SELECT (P.NOMBRE || ' (' || P.ID_PRODUCTO || ')') AS NOMBRE, P.PRECIO, SUM(C.CANTIDAD) AS CANTIDAD_TOTAL"
						+ " FROM PRODUCTO P, COMPRA C"
						+ " WHERE P.ID_PRODUCTO = C.ID_STOCK"
						+ " GROUP BY P.ID_PRODUCTO, P.PRECIO";
				
				pstmt = con.prepareStatement(query);
			} else {
				query = "SELECT (P.NOMBRE || ' (' || P.ID_PRODUCTO || ')') AS NOMBRE, P.PRECIO, SUM(C.CANTIDAD) AS CANTIDAD_TOTAL"
						+ " FROM PRODUCTO P, COMPRA C"
						+ " WHERE P.ID_PRODUCTO = C.ID_STOCK"
						+ " AND FECHA_COMPRA = ?"
						+ " GROUP BY P.ID_PRODUCTO, P.PRECIO";
				
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, fecha);
			}
			
			rslt = pstmt.executeQuery();
			
			while (rslt.next()) {
				String nombre = rslt.getString("NOMBRE");
				double precio = rslt.getDouble("PRECIO");
				int cantidad = rslt.getInt("CANTIDAD_TOTAL");
				
				ProductoCompra producto = new ProductoCompra(nombre, precio, cantidad);
				productos.add(producto);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rslt != null) rslt.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	 	return productos;
		
	}
}
