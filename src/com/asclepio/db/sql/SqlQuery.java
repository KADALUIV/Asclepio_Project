package com.asclepio.db.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.asclepio.db.AccesoDB;

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
				} {
				
			}
			
		}
		
		return pwd;
	}

}
