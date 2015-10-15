package javax.openAPI.servicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.openAPI.util.Utilerias;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class Servicios {
	private static Logger log = Logger.getLogger(Servicios.class);
	
	public static JSONObject ejecutarServicioJSONPOST(String valorURL, JSONObject valorParametro) {
		JSONObject respuesta = null;
		String entrada = null;
		String salida = null;
		
		try {
			entrada = valorParametro.toString();
			
			salida = ejecutarServicio(valorURL, entrada, "POST");
			
			if(salida.length() > 0) {
				respuesta = new JSONObject(salida);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error("[Servicios.ejecutarServicioJSONPOST] Error JSON --> " + e);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("[Servicios.ejecutarServicioJSONPOST] Error --> " + e);
		} finally {
			entrada = null;
			salida = null;
		}
		
		log.info("[Servicios.ejecutarServicioJSONPOST] Respuesta --> " + respuesta);
		
		return respuesta;
	}
	
	public static JSONObject ejecutarServicioJSONGET(String valorURL) {
		JSONObject respuesta = null;
		String salida = null;
		
		try {
			salida = ejecutarServicio(valorURL);
			
			if(salida != null) {
				respuesta = new JSONObject(salida);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error("[Servicios.ejecutarServicioJSONGET] Error JSON --> " + e);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("[Servicios.ejecutarServicioJSONGET] Error --> " + e);
		} finally {
			salida = null;
		}
		
		log.info("[Servicios.ejecutarServicioJSONGET] Respuesta --> " + respuesta);
		
		return respuesta;
	}
	
	public static String ejecutarServicio(String valorURL, String valorParametro, String valorPeticion) {
		String respuesta = null;
		URL direccionURL = null;
		HttpURLConnection conexion = null;
		OutputStream flujoSalida = null;
		BufferedReader lectorBuffer = null;
		
		try {
			direccionURL = new URL(valorURL);
			
			conexion = (HttpURLConnection) direccionURL.openConnection();
			conexion.setDoOutput(true);
			conexion.setRequestMethod(valorPeticion);
			conexion.setRequestProperty("Content-Type", "text/xml");
			
			flujoSalida = conexion.getOutputStream();
			flujoSalida.write(valorParametro.getBytes());
			flujoSalida.flush();
			
			lectorBuffer = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
			
			respuesta = lectorBuffer.readLine();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error("[Servicios.ejecutarServicio] Error MalformedURL --> " + e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error("[Servicios.ejecutarServicio] Error IO --> " + e);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("[Servicios.ejecutarServicio] Error --> " + e);
		} finally {
			Utilerias.cerrarFlujoSalida(flujoSalida);
			Utilerias.cerrarLector(lectorBuffer);
			
			conexion.disconnect();
			
			direccionURL = null;
			conexion = null;
			flujoSalida = null;
			lectorBuffer = null;
		}
		
		log.info("[Servicios.ejecutarServicio] Respuesta --> " + respuesta);
		
		return respuesta;
	}
	
	public static String ejecutarServicio(String valorURL) {
		String respuesta = null;
		URL direccionURL = null;
		HttpURLConnection conexion = null;
		OutputStream flujoSalida = null;
		BufferedReader lectorBuffer = null;
		
		try {
			direccionURL = new URL(valorURL);
			
			conexion = (HttpURLConnection) direccionURL.openConnection();
			conexion.setDoOutput(true);
			conexion.setRequestMethod("GET");
			conexion.setRequestProperty("Content-Type", "text/xml");
			conexion.connect();
			
			lectorBuffer = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
			
			respuesta = lectorBuffer.readLine();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error("[Servicios.ejecutarServicio] Error MalformedURL --> " + e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error("[Servicios.ejecutarServicio] Error IO --> " + e);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("[Servicios.ejecutarServicio] Error --> " + e);
		} finally {
			Utilerias.cerrarFlujoSalida(flujoSalida);
			Utilerias.cerrarLector(lectorBuffer);
			
			conexion.disconnect();
			
			direccionURL = null;
			conexion = null;
			flujoSalida = null;
			lectorBuffer = null;
		}
		
		log.info("[Servicios.ejecutarServicio] Respuesta --> " + respuesta);
		
		return respuesta;
	}
}
