package javax.openAPI.propiedades;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import javax.openAPI.util.Utilerias;

public class Propiedades {
	public static Hashtable<String, String> propiedades;
	
	public static void iniciarPropiedades(String valorArchivo) {
		propiedades = new Hashtable<String, String>();
		
		leerPropiedades(valorArchivo);
	}
	
	public static void leerPropiedades(String valorRutaArchivo) {
		Properties props = null;
		FileInputStream archivo = null;
		Enumeration<Object> llaves = null;
		String nombreLlave = null;
		
		try {
			props = new Properties();
			archivo = new FileInputStream(valorRutaArchivo);
			props.load(archivo);
			llaves = props.keys();
			
			while(llaves.hasMoreElements()) {
				nombreLlave = (String) llaves.nextElement();
				propiedades.put(nombreLlave, props.getProperty(nombreLlave));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("[Propiedades.leerPropiedades] Error --> " + e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("[Propiedades.leerPropiedades] Error --> " + e);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("[Propiedades.leerPropiedades] Error --> " + e);
		} finally {
			props.clear();
			
			Utilerias.cerrarArchivo(archivo);
			
			props = null;
			archivo = null;
			llaves = null;
			nombreLlave = null;
		}
		
		System.out.println("[Propiedades.leerPropiedades] Propiedades --> " + propiedades);
	}
	
	public static String obtenerPropiedad(String valorNombre) {
		String propiedad = null;
		
		try {
			if(propiedades.containsKey(valorNombre)) {
				propiedad = propiedades.get(valorNombre);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("[Propiedades.obtenerPropiedad] Error --> " + e);
		}
		
		return propiedad;
	}
}
