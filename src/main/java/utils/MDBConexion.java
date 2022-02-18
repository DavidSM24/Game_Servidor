package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MDBConexion {
	
	private static String server="jdbc:mysql://localhost:3307"; //jdbc:mysql://localhost:3307
	private static String database="gamex"; //"musiclick
	private static String username="root"; //"root"
	private static String password="";
	
	private static Connection con = null;

	/**
	 * Debe ir cargado desde un xml externo
	 */

	/**
	 * 
	 * @return una conexión a una bbdd
	 */
	public static Connection getConexion() {
		if (con == null) {
			try {
				con = DriverManager.getConnection(server+"/"+database,username,password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				con = null;
			}
		}
		return con;
	}
	
	/**
	 * Cierra los recursos de una conexión abierta
	 */
	public static void cerrar() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
