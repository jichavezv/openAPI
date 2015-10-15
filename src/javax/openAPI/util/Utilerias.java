package javax.openAPI.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import org.apache.log4j.Logger;

public class Utilerias {
	private static Logger log = Logger.getLogger(Utilerias.class);
	
	public static boolean esNumero(String valorCadena) {
		boolean esNumero = true;
		
		try {
			Integer.valueOf(valorCadena);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.warn("[Utilerias.esNumero] Error --> " + e);
			esNumero = false;
		} catch (Exception e) {
			// TODO: handle exception
			log.warn("[Utilerias.esNumero] Error --> " + e);
			esNumero = false;
		}
		
		return esNumero;
	}
	
	public static void cerrarArchivo(InputStream valorArchivo) {
		try {
			if(valorArchivo != null){
				valorArchivo.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.warn("[Utilerias.cerrarArchivo] Error IO --> " + e);
		} catch (Exception e) {
			// TODO: handle exception
			log.warn("[Utilerias.cerrarArchivo] Error --> " + e);
		}
	}
	
	public static String obtenerFechaFormateada(String valorFormato) {
		SimpleDateFormat formateador = null;
		String fecha = null;
		
		try {
			formateador = new SimpleDateFormat(valorFormato);
			fecha = formateador.format(new Date());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.warn("[Utilerias.obtenerFechaFormateada] Error --> " + e);
		} finally {
			formateador = null;
			fecha = null;
		}
		
		return fecha;
	}
	
	public static void cerrarFlujoSalida(OutputStream valorFlujo) {
		try {
			if(valorFlujo != null) {
				valorFlujo.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.warn("[Utilerias.cerrarFlujoSalida] Error IO --> " + e);
		} catch (Exception e) {
			// TODO: handle exception
			log.warn("[Utilerias.cerrarFlujoSalida] Error --> " + e);
		}
	}
	
	public static void cerrarLector(Reader valorLector) {
		try {
			if(valorLector != null) {
				valorLector.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.warn("[Utilerias.cerrarLector] Error IO --> " + e);
		} catch (Exception e) {
			// TODO: handle exception
			log.warn("[Utilerias.cerrarLector] Error --> " + e);
		}
	}
	
	/**
	 * Metodo que nos permite obtener una cadena con los campos y sus respectivos valores
	 * @param valorTabla Tabla con sus nombres y valores 
	 * @param valorCaracterSeparacion Caracter de separacion
	 * @return Cadena con el nombre del Campo y su Valor
	 * @author juch [Juan Ignacio Chavez Vela]
	 * @since 18/Jun/2014.
	 */
	public static String obtenerValoresHashtableString(Hashtable<String, String> valorTabla, String valorCaracterSeparacion) {
		StringBuilder       constructorCadena = null;
		Enumeration<String> nombresLLaves     = null;
		String              llave             = null;
		String              valoresTabla      = null;
		
		try {
			constructorCadena = new StringBuilder();
			nombresLLaves     = valorTabla.keys();
			
			while(nombresLLaves.hasMoreElements()) {
				llave = nombresLLaves.nextElement();
				constructorCadena.append(llave + " = '" + valorTabla.get(llave) + "'" + valorCaracterSeparacion);
			}
			
			valoresTabla = constructorCadena.toString();
			
			if(valoresTabla.length() > 0) {
				valoresTabla = valoresTabla.substring(0, valoresTabla.length()-valorCaracterSeparacion.length());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error("[Utilerias.obtenerValoresHashtableString] Error --> " + e);
			
			valoresTabla = "";
		} finally {
			//msjLogDebug("Utilerias", "obtenerValoresHashtableString", " Valores de la Enumeracion: |" + valoresTabla + "|");
			
			constructorCadena = null;
			nombresLLaves     = null;
			llave             = null;
		}
		
		return valoresTabla;
	}
	
	/**
	 * Metodo donde obtenemos los valores de un Objeto de Numeracion de Tipo String
	 * @param valorEnumeracion Objeto Enumeracion
	 * @param valorCaracterSeparacion Caracter que le pondremos de separacion entre 
	 * cada uno de los elementos de la Enumeracion
	 * @return Cadena con los Elementos de la Enumeracion separados por el Caracter especifico
	 * @author juch [Juan Ignacio Chavez Vela]
	 * @since 18/Jun/2014.
	 */
	public static String obtenerValoresEnumeracionString(Enumeration<String> valorEnumeracion, String valorCaracterSeparacion) {
		StringBuilder constructorCadena  = null;
		String        nombreEnumeracion  = null;
		String        valoresEnumeracion = null;
		
		try {
			constructorCadena = new StringBuilder();
			
			while(valorEnumeracion.hasMoreElements()) {
				nombreEnumeracion = valorEnumeracion.nextElement();
				constructorCadena.append(nombreEnumeracion + valorCaracterSeparacion);
			}
			
			valoresEnumeracion = constructorCadena.toString();
			
			if(valoresEnumeracion.length() > 0) {
				valoresEnumeracion = valoresEnumeracion.substring(0, valoresEnumeracion.length()-valorCaracterSeparacion.length());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error("[Utilerias.obtenerValoresEnumeracionString] Error --> " + e);
			
			valoresEnumeracion = "";
		} finally {
			//msjLogDebug(ORIGEN, "obtenerValoresEnumeracionString", " Valores de la Enumeracion: |" + valoresEnumeracion + "|");
			
			constructorCadena = null;
			nombreEnumeracion = null;
		}
		
		return valoresEnumeracion;
	}
}
