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
	
	    public static ResultSet ejecutaQuery(PreparedStatement stmnt) {
	  	  ResultSet res = null;

	  	   try {
	  	      res = stmnt.executeQuery();
	  	   } catch (Exception e) {
	  	      log.error("Utilerias","ejecutaQuery","Error:" + e);
	  	   }
	  	   return res;
	  	}
	    
	    public static int ejecutaUpdate(Connection cnx, String query) {
	        Statement sel = null;
	        int       res = -1;

	        try {
	           sel = cnx.createStatement();
	           res = sel.executeUpdate(query);
	           sel.close();
	        } catch (Exception e) {
	           log.error("Utilerias","ejecutaUpdate","cnx<"+cnx+"> query<"+query+"> Error: "+ e);
	        }
	        return res;
	     }
	    
		public static int ejecutaUpdate(PreparedStatement stmnt) {
			int aplico = 0;

			try {
				aplico = stmnt.executeUpdate();
			} catch (Exception e) {
				log.error("Utilerias","ejecutaUpdate","stmnt<"+stmnt+"> Error:" + e);
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
			   log.error("Utilerias","obtenValorString","campo<"+campo+"> res<"+res+"> _default<"+_default+"> Error:" + e);
			}
		    return valor;
	    }
		
		public static int  obtenValorEntero(String campo, ResultSet res) {		
			int    valor = 0;		

			try {
			   valor = res.getInt(campo);		   
			} catch (Exception e) {
			   log.error("Utilerias","obtenValorEntero","campo<"+campo+"> res<"+res+"> Error: "+campo);
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
			   log.error("Utilerias","obtenValorDateDay","campo<"+campo+"> res<"+res+"> _default<"+_default+"> Error: "+e);
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
			   log.error("Utilerias","obtenValorDateTime","campo<"+campo+"> res<"+res+"> _default<"+_default+"> Error:" + e);
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
			   log.error("Utilerias","obtenValorDateTime","pos<"+pos+"> res<"+res+"> _default<"+_default+"> Error:" + e);
			}
		    return valor;
	    }
		
		public static Statement obtenStatement(Connection cnx) {
			Statement stmt = null;
			try {
				stmt = cnx.createStatement();
			} catch (SQLException e) {
				log.error("Utilerias","obtenStatement","cnx<"+cnx+"> Error:"+e);
			}
			return stmt;
		}
		

		public static PreparedStatement obtenPreparedStatement(Connection cnx, String qry) {
			PreparedStatement pst = null;
			try {
				pst = cnx.prepareStatement(qry);
			} catch (SQLException e) {
				log.error("Utilerias","obtenPreparedStatement","cnx<"+cnx+"> qry<"+qry+"> Error:"+e);
			}
			return pst;
		}
		

		public static void cierraStatement(Statement stmt) {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				log.error("Utilerias","cierraStatement","stmt<"+stmt+"> Error:"+e);			
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
				log.error("Utilerias","cierraResultSet","rs<"+rs+"> Error:"+e);
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
				log.error("Utilerias","cierraPreparedStatement","Excepcion pst("+pst+")]:"+e);
			} finally {
				pst = null;
			}
		}
		

	   
	   public static ResultSet ejecutaQuery(Statement sel, String query) {       
	       ResultSet res = null;

	       try {
	          res = sel.executeQuery(query);
	        } catch (Exception e) {
	          log.error("Utilerias","ejecutaQuery","sel<"+sel+"> query<"+query+"> Error:"+e);
	       }
	       return res;
	   }
	   
	   
		
		public static int ejecutaUpdate(Connection cnx, String query, int folio) {
		   Statement sel    = null;	      
		   int       serial = -1;

		   try {
		      sel    = cnx.createStatement();
		      sel.executeUpdate(query);
		      serial = ((IfxStatement)sel).getSerial();
		   } catch (Exception e) {
		      log.error("Utilerias","ejecutaUpdate","cnx<"+cnx+"> query<"+query+"> folio<"+folio+"> Error:" + e);
		   }
		   return serial;
		}
		
		   public static Integer obtieneSerial(Connection cnx, String query) { 
			      Integer   serial = new Integer("-1"); 		      
			      Statement stmt   = null; 
			      try { 
			         stmt = cnx.createStatement(); 		         
			         stmt.execute(query); 
			         serial = new Integer(((IfxStatement)stmt).getSerial()); 
			         stmt.close(); 
			      } catch(java.lang.Exception ex) { 
			    	  log.error("Utilerias","obtieneSerial","cnx<"+cnx+"> query<"+query+"> Error: " + ex); 
			      } finally { 
			         try { 
			        	 stmt.close(); 
			         } catch (SQLException sqle) { 
			        	 log.error("Utilerias","obtieneSerial","stmt<"+stmt+"> Error: " + sqle);
			         } 
			      } 
			      return serial; 
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
