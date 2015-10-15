package javax.openAPI.pruebas;

import javax.openAPI.propiedades.Propiedades;

public class PruebaPropiedades {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String rutaArchivo = null;
		
		rutaArchivo = "archivos/prueba.properties";
		
		Propiedades.iniciarPropiedades(rutaArchivo);
		
		System.out.println("Propiedades del Archivo --> " + Propiedades.propiedades);
		System.out.println("Propiedad propiedad1 (Si esta) --> " + Propiedades.obtenerPropiedad("propiedad1"));
		System.out.println("Propiedad ipServidor (No esta) --> " + Propiedades.obtenerPropiedad("ipServidor"));
		
		System.exit(0);
	}

}
