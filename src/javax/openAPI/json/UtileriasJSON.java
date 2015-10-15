package javax.openAPI.json;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UtileriasJSON {
	private static Logger log = Logger.getLogger(UtileriasJSON.class);
	
	public static JSONObject obtenerObjetoJSONDeString(String valorJSON) {
		JSONObject objetoJSON = null;
		
		try {
			if(valorJSON != null && !valorJSON.equalsIgnoreCase("")) {
				objetoJSON = new JSONObject(valorJSON);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.warn("[UtileriasJSON.obtenerObjetoJSONDeString] Error JSON --> " + e);
		} catch (Exception e) {
			// TODO: handle exception
			log.warn("[UtileriasJSON.obtenerObjetoJSONDeString] Error --> " + e);
		}
		
		return objetoJSON;
	}
	
	public static String obtenerValorString(String valorNombre, JSONObject valorJSON, String valorDefault) {
		String cadena = null;
		
		try {
			if(valorJSON.has(valorNombre)) {
				cadena = valorJSON.getString(valorNombre);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.warn("[UtileriasJSON.obtenerValorString] Error JSON --> " + e);
		} catch (Exception e) {
			// TODO: handle exception
			log.warn("[UtileriasJSON.obtenerValorString] Error --> " + e);
		} finally {
			if(cadena == null) {
				cadena = valorDefault; 
			} else {
				cadena = cadena.trim();
			}
		}
		
		return cadena;
	}
	
	public static void ponerAtributoStringJSON(JSONObject valorJSON, String valorNombre, String valorDato) {
		try {
			valorJSON.put(valorNombre, valorDato);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.warn("[UtileriasJSON.obtenerValorString] Error JSON --> " + e);
		} catch (Exception e) {
			// TODO: handle exception
			log.warn("[UtileriasJSON.obtenerValorString] Error --> " + e);
		}
	}
	
	public static void ponerAtributoBooleanJSON(JSONObject valorJSON, String valorNombre, Boolean valorDato) {
		try {
			valorJSON.put(valorNombre, valorDato);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.warn("[UtileriasJSON.obtenerValorString] Error JSON --> " + e);
		} catch (Exception e) {
			// TODO: handle exception
			log.warn("[UtileriasJSON.obtenerValorString] Error --> " + e);
		}
	}
	
	public static List<String> obtenerListaString(JSONObject valorJSON, String valorNombreLista) {
		List<String> lista = null;
		JSONArray arregloJSON = null;
		String elemento = null;
		
		try {
			lista = new ArrayList<String>();
			
			if(valorJSON.has(valorNombreLista)){
				arregloJSON = valorJSON.getJSONArray(valorNombreLista);
				
				for(int i=0; i<arregloJSON.length(); i++) {
					elemento = obtenerElementoStringArregloJSON(arregloJSON, i);
					
					if(elemento != null) {
						lista.add(elemento);
					}
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.warn("[UtileriasJSON.obtenerListaString] Error --> " + e);
		} catch (Exception e) {
			// TODO: handle exception
			log.warn("[UtileriasJSON.obtenerListaString] Error --> " + e);
		}
		
		return lista;
	}
	
	private static String obtenerElementoStringArregloJSON(JSONArray valorArreglo, Integer valorIndice) {
		String elementoArreglo = null;
		
		try {
			elementoArreglo = valorArreglo.getString(valorIndice);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.warn("[UtileriasJSON.obtenerElementoStringArregloJSON] Error --> " + e);
		} catch (Exception e) {
			// TODO: handle exception
			log.warn("[UtileriasJSON.obtenerElementoStringArregloJSON] Error --> " + e);
		}
		
		return elementoArreglo;
	}
	
	public static void ponerAtributoArregloJSON(JSONObject valorJSON, String valorNombre, JSONArray valorDato) {
		try {
			valorJSON.put(valorNombre, valorDato);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.warn("[UtileriasJSON.ponerAtributoArregloJSON] Error JSON --> " + e);
		} catch (Exception e) {
			// TODO: handle exception
			log.warn("[UtileriasJSON.ponerAtributoArregloJSON] Error --> " + e);
		}
	}
	
	public static List<Integer> obtenerListaInteger(JSONObject valorJSON, String valorNombreLista) {
		List<Integer> lista = null;
		JSONArray arregloJSON = null;
		Integer elemento = null;
		
		try {
			lista = new ArrayList<Integer>();
			
			if(valorJSON.has(valorNombreLista)){
				arregloJSON = valorJSON.getJSONArray(valorNombreLista);
				
				for(int i=0; i<arregloJSON.length(); i++) {
					elemento = obtenerElementoIntegerArregloJSON(arregloJSON, i);
					
					if(elemento != null) {
						lista.add(elemento);
					}
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.warn("[UtileriasJSON.obtenerListaInteger] Error --> " + e);
		} catch (Exception e) {
			// TODO: handle exception
			log.warn("[UtileriasJSON.obtenerListaInteger] Error --> " + e);
		}
		
		return lista;
	}
	
	private static Integer obtenerElementoIntegerArregloJSON(JSONArray valorArreglo, Integer valorIndice) {
		Integer elementoArreglo = null;
		
		try {
			elementoArreglo = valorArreglo.getInt(valorIndice);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.warn("[UtileriasJSON.obtenerElementoIntegerArregloJSON] Error --> " + e);
		} catch (Exception e) {
			// TODO: handle exception
			log.warn("[UtileriasJSON.obtenerElementoIntegerArregloJSON] Error --> " + e);
		}
		
		return elementoArreglo;
	}

}
