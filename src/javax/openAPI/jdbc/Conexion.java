package javax.openAPI.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;


public class Conexion {
	private Connection conexionBD;
	private String     driver;
	private String     url;
	private String     usuario;
	private String     password;
	
	private Logger log = Logger.getLogger(Conexion.class);
	
	public Conexion(String valorDriver, String valorURL, String valorUsuario, String valorPassword) {
		// TODO Auto-generated constructor stub
		this.driver = valorDriver;
		this.url    = valorURL;
		this.usuario = valorUsuario;
		this.password = valorPassword;
	}
	
	private void crearConexion() {
		try {
			Class.forName(this.driver);
			
			this.conexionBD = DriverManager.getConnection(this.url, this.usuario, this.password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error("[Utilerias.crearConexion] Error: " + e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error("[Utilerias.crearConexion] Error: " + e);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("[Utilerias.crearConexion] Error: " + e);
		}
	}
	
	public void verificarConexion() {
		try {
			if(this.conexionBD != null) {
				if(this.conexionBD.isClosed()) {
					this.crearConexion();
				}
			} else {
				this.crearConexion();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error("[Utilerias.verificarConexion] Error: " + e);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("[Utilerias.verificarConexion] Error: " + e);
		}
	}

	public void cierraConexion() {
		try {
			if (this.conexionBD != null) {
				this.conexionBD.close();				
			}
		} catch (Exception e) {
			log.error("[Utilerias.cierraConexion] Error: " + e);
		} finally {
			this.conexionBD = null;
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String cadena = null;
		
		cadena = "Driver[" + this.driver + "], ";
		cadena = cadena + "URL[" + this.url + "], ";
		cadena = cadena + "Usuario[" + this.usuario + "], ";
		cadena = cadena + "Password[" + this.password + "]";
		
		return cadena;
	}
}

