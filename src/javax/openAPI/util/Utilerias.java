package javax.openAPI.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
