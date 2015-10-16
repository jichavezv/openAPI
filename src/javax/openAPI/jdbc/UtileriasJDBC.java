package javax.openAPI.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.openAPI.util.Utilerias;

import org.apache.log4j.Logger;

public class UtileriasJDBC {
	private static Logger log = Logger.getLogger(UtileriasJDBC.class);

	public static ResultSet ejecutaQuery(PreparedStatement pstmt) {
		ResultSet res = null;

		try {
			res = pstmt.executeQuery();
		} catch (Exception e) {
			log.error("[Utilerias.ejecutaQuery] Error: " + e);
		}

		return res;
	}

	public static int ejecutaUpdate(Connection cnx, String query) {
		Statement sel = null;
		int       res = -1;

		try {
			sel = obtenStatement(cnx);
			res = sel.executeUpdate(query);
		} catch (Exception e) {
			log.error("[Utilerias.ejecutaUpdate] Error: " + e);
		} finally {
			cierraStatement(sel);

			sel = null;
		}

		return res;
	}

	public static int ejecutaUpdate(PreparedStatement stmnt) {
		int aplico = 0;

		try {
			aplico = stmnt.executeUpdate();
		} catch (Exception e) {
			log.error("[Utilerias.ejecutaUpdate] Error: " + e);
		}
		
		return aplico;
	}

	public static String obtenValorString(String campo, ResultSet res, String _default) {		
		String    valor = "";		

		try {
			valor = res.getString(campo);
			if (valor != null && !valor.trim().equals("")) {
				valor = valor.trim();
			} else {
				valor = _default;
			}
		} catch (Exception e) {
			log.error("[Utilerias.obtenValorString] Error: " + e);
		}
		return valor;
	}

	public static int  obtenValorEntero(String campo, ResultSet res) {		
		int    valor = 0;		

		try {
			valor = res.getInt(campo);		   
		} catch (Exception e) {
			log.error("[Utilerias.obtenValorEntero] Error: " + e);
		}
		return valor;
	}

	public static String obtenValorDateDay(String campo, ResultSet res, String _default) {		
		String    valor = "";		

		try {
			valor = res.getString(campo);	
			//log.error("Utilerias","obtenFechaActual","valor>"+valor+"<");
			if (valor != null & !valor.trim().equals("-") ) {
				valor = valor.trim().substring(0,10);
			} else {
				valor = _default;
			}
		} catch (Exception e) {
			log.error("[Utilerias.obtenValorDateDay] Error: " + e);
		}
		return valor;
	}

	public static String obtenValorDateTime(String campo, ResultSet res, String _default) {		
		String    valor = "";		

		try {
			valor = res.getString(campo);		   
			if (valor != null) {
				valor = valor.trim().substring(0,19);
			} else {
				valor = _default;
			}
		} catch (Exception e) {
			log.error("[Utilerias.obtenValorDateTime] Error: " + e);
		}		
		return valor;
	}

	public static String obtenValorDateTime(int pos, ResultSet res, String _default) {		
		String    valor = "";		

		try {
			valor = res.getString(pos);
			if (valor != null) {
				valor = valor.trim().substring(0,19);
			} else {
				valor = _default;
			}
		} catch (Exception e) {
			log.error("[Utilerias.obtenValorDateTime] Error: " + e);
		}
		return valor;
	}

	public static Statement obtenStatement(Connection cnx) {
		Statement stmt = null;
		try {
			stmt = cnx.createStatement();
		} catch (SQLException e) {
			log.error("[Utilerias.obtenStatement] Error: " + e);
		}
		return stmt;
	}


	public static PreparedStatement obtenPreparedStatement(Connection cnx, String qry) {
		PreparedStatement pst = null;
		try {
			pst = cnx.prepareStatement(qry);
		} catch (SQLException e) {
			log.error("[Utilerias.obtenPreparedStatement] Error: " + e);
		}
		return pst;
	}


	public static void cierraStatement(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (Exception e) {
			log.error("[Utilerias.cierraStatement] Error: " + e);
		} finally {
			stmt = null;
		}
	}

	public static void cierraResultSet(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			log.error("[Utilerias.cierraResultSet] Error: " + e);
		} finally {
			rs = null;
		}
	}

	public static void cierraPreparedStatement(PreparedStatement pst) {
		try {
			if (pst != null) {
				pst.close();
			}
		} catch (Exception e) {
			log.error("[Utilerias.cierraPreparedStatement] Error: " + e);
		} finally {
			pst = null;
		}
	}



	public static ResultSet ejecutaQuery(Statement sel, String query) {       
		ResultSet res = null;

		try {
			res = sel.executeQuery(query);
		} catch (Exception e) {
			log.error("[Utilerias.ejecutaQuery] Error: " + e);
		}
		return res;
	}

	/**
	 * Metodo para convertir un resultSet a un Lista de Mapas
	 * @author Desarrollo Sistemas (pago)
	 * @param rs   ResultSet
	 * @return Lista de Mapa
	 */
	public static ArrayList<Map<String,String>> resultSetAMapa(ResultSet rs){
		ResultSetMetaData              md    = null;
		ArrayList<Map<String,String>>  lista = new ArrayList<Map<String,String>>();
		Map<String,String>             mapa  = null;
		int                cols = 0;

		try {
			md   = rs.getMetaData();
			cols = md.getColumnCount();

			while (rs.next()){
				mapa =  new HashMap<String,String>();
				for(int i=1; i<=cols; i++){
					mapa.put(md.getColumnName(i), obtenValorString(md.getColumnName(i),rs, ""));
				}
				lista.add(mapa);
			}  
		} catch (Exception e){
			log.error("[Utilerias.resultSetAMapa] Error: " + e);
		}
		return lista;
	}

	/**
	 * Metodo donde obtenemos la Sentencia Insert para Ejecutarla en PISA
	 * @param valorNombreTabla Nombre de la Tabla
	 * @param valorBiblioteca Nombre de la Biblioteca
	 * @param valorSeparador Caracter que se utiliza para separar de que Base de Datos sacara la Tabla
	 * @param valorTablaDatos Tabla que como llave tiene el Nombre del Campo y su valor
	 * @return Cadena en formato SQL de Insert
	 * @author juch [Juan Ignacio Chavez Vela]
	 * @since 18/Jun/2014.
	 */
	public static String armarSQLInsert(String valorNombreTabla, String valorBiblioteca, String valorSeparador, Hashtable<String, String> valorTablaDatos) {
		String              insertSQL        = null;
		StringBuilder       constructorInsert = null;
		Enumeration<String> nombresLlaves     = null;

		try {
			constructorInsert = new StringBuilder();

			//msjLogDebug("Utilerias", "armarSQLInsert", " Tabla de Datos para armar el SQL: " + valorTablaDatos);

			constructorInsert.append("INSERT INTO ");

			if(valorBiblioteca != null && valorSeparador != null) {
				constructorInsert.append(valorBiblioteca);
				constructorInsert.append(valorSeparador);
			}

			constructorInsert.append(valorNombreTabla);

			nombresLlaves = valorTablaDatos.keys();
			constructorInsert.append("(" + Utilerias.obtenerValoresEnumeracionString(nombresLlaves, ", ") + ")");
			constructorInsert.append(" VALUES ");

			nombresLlaves = null;
			nombresLlaves = valorTablaDatos.elements();
			constructorInsert.append("('" + Utilerias.obtenerValoresEnumeracionString(nombresLlaves, "', '") + "')");

			insertSQL = constructorInsert.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error("[Utilerias.armarSQLInsert] Error --> " + e);

			insertSQL = "";
		} finally {
			constructorInsert = null;
			nombresLlaves     = null;
		}

		return insertSQL;
	}

	/**
	 * Metodo que nos permite obtener una Sentencia Update
	 * @param valorNombreTabla Nombre de la Tabla
	 * @param valorBiblioteca Nombre de la Biblioteca
	 * @param valorSeparador Caracter que se utiliza para separar de que Base de Datos sacara la Tabla
	 * @param valorTablaDatos Datos de la Tabla a Modificar
	 * @param valorTablaCondicion Datos de la Tabla que se pondran despues del WHERE
	 * @return Cadena en formato SQL de Update
	 * @author juch [Juan Ignacio Chavez Vela]
	 * @since 18/Jun/2014.
	 */
	public static String armarSQLUpdate(String valorNombreTabla, String valorBiblioteca, String valorSeparador, Hashtable<String, String> valorTablaDatos, Hashtable<String, String> valorTablaCondicion) {
		String        updateSQL         = null;
		StringBuilder constructorUpdate = null;

		try {
			constructorUpdate = new StringBuilder();

			//msjLogDebug("Utilerias", "armarSQLUpdate", " Tabla de Datos para armar el SQL: " + valorTablaDatos);

			constructorUpdate.append("UPDATE ");

			if(valorBiblioteca != null && valorSeparador != null) {
				constructorUpdate.append(valorBiblioteca);
				constructorUpdate.append(valorSeparador);
			}

			constructorUpdate.append(valorNombreTabla);
			constructorUpdate.append(" SET ");

			constructorUpdate.append(Utilerias.obtenerValoresHashtableString(valorTablaDatos, ", "));

			if(valorTablaCondicion != null) {
				constructorUpdate.append(" WHERE ");
				constructorUpdate.append(Utilerias.obtenerValoresHashtableString(valorTablaCondicion, " AND "));
			}

			updateSQL = constructorUpdate.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error("[Utilerias.armarSQLInsert] Error --> " + e);

			updateSQL = "";
		} finally {
			constructorUpdate = null;
		}

		return updateSQL;
	}
}
