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

	public static ResultSet ejecutarQuery(PreparedStatement pstmt) {
		ResultSet res = null;

		try {
			res = pstmt.executeQuery();
		} catch (Exception e) {
			log.error("[Utilerias.ejecutaQuery] Error: " + e);
		}

		return res;
	}

	public static int ejecutarUpdate(Connection cnx, String query) {
		Statement sel = null;
		int       res = -1;

		try {
			sel = obtenerStatement(cnx);
			res = sel.executeUpdate(query);
		} catch (Exception e) {
			log.error("[Utilerias.ejecutaUpdate] Error: " + e);
		} finally {
			cerrarStatement(sel);

			sel = null;
		}

		return res;
	}

	public static int ejecutarUpdate(PreparedStatement stmnt) {
		int aplico = 0;

		try {
			aplico = stmnt.executeUpdate();
		} catch (Exception e) {
			log.error("[Utilerias.ejecutaUpdate] Error: " + e);
		}
		
		return aplico;
	}

	public static String obtenerValorString(String campo, ResultSet res, String _default) {		
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

	public static int  obtenerValorEntero(String campo, ResultSet res) {		
		int    valor = 0;		

		try {
			valor = res.getInt(campo);		   
		} catch (Exception e) {
			log.error("[Utilerias.obtenValorEntero] Error: " + e);
		}
		return valor;
	}

	public static String obtenerValorDateDay(String campo, ResultSet res, String _default) {		
		String    valor = "";		

		try {
			valor = res.getString(campo);	
			
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

	public static String obtenerValorDateTime(String campo, ResultSet res, String _default) {		
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

	public static String obtenerValorDateTime(int pos, ResultSet res, String _default) {		
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

	public static Statement obtenerStatement(Connection cnx) {
		Statement stmt = null;
		try {
			stmt = cnx.createStatement();
		} catch (SQLException e) {
			log.error("[Utilerias.obtenStatement] Error: " + e);
		}
		return stmt;
	}


	public static PreparedStatement obtenerPreparedStatement(Connection cnx, String qry) {
		PreparedStatement pst = null;
		try {
			pst = cnx.prepareStatement(qry);
		} catch (SQLException e) {
			log.error("[Utilerias.obtenPreparedStatement] Error: " + e);
		}
		return pst;
	}


	public static void cerrarStatement(Statement stmt) {
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

	public static void cerrarResultSet(ResultSet rs) {
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

	public static void cerrarPreparedStatement(PreparedStatement pst) {
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

	public static ResultSet ejecutarQuery(Statement sel, String query) {       
		ResultSet res = null;

		try {
			res = sel.executeQuery(query);
		} catch (Exception e) {
			log.error("[Utilerias.ejecutaQuery] Error: " + e);
		}
		return res;
	}

	public static ArrayList<Map<String,String>> obtenerMapaPorResultSet(ResultSet rs){
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
					mapa.put(md.getColumnName(i), obtenerValorString(md.getColumnName(i),rs, ""));
				}
				lista.add(mapa);
			}  
		} catch (Exception e){
			log.error("[Utilerias.obtenerMapaPorResultSet] Error: " + e);
		}
		return lista;
	}

	public static String armarSQLInsert(String valorNombreTabla, Hashtable<String, String> valorTablaDatos) {
		String              insertSQL        = null;
		StringBuilder       constructorInsert = null;
		Enumeration<String> nombresLlaves     = null;

		try {
			constructorInsert = new StringBuilder();

			constructorInsert.append("INSERT INTO ");

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

	public static String armarSQLUpdate(String valorNombreTabla, Hashtable<String, String> valorTablaDatos, Hashtable<String, String> valorTablaCondicion) {
		String        updateSQL         = null;
		StringBuilder constructorUpdate = null;

		try {
			constructorUpdate = new StringBuilder();

			constructorUpdate.append("UPDATE ");

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
