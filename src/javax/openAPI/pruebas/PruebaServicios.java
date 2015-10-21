package javax.openAPI.pruebas;

import javax.openAPI.servicios.Servicios;

import org.apache.log4j.BasicConfigurator;

public class PruebaServicios {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String respuesta = null;
		
		BasicConfigurator.configure();
		
		respuesta = Servicios.ejecutarServicio("http://www.google.com.mx");
		System.out.println("[PruebaServicios.main] Respuesta Servicio --> " + respuesta);
		
		System.exit(0);
	}

}
