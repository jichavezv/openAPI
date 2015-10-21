package javax.openAPI.pruebas;

import java.util.List;

import javax.openAPI.json.UtileriasJSON;

import org.apache.log4j.BasicConfigurator;
import org.json.JSONObject;

public class PruebaJSON {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String json = "{\"id\": 1,\"name\": \"A green door\",\"price\": 12.50,\"tags\": [\"home\", \"green\"]}";
		JSONObject objetoJSON = null;
		List<String> listaString = null;
		
		BasicConfigurator.configure();
		
		objetoJSON = UtileriasJSON.obtenerObjetoJSONDeString(json);
		System.out.println("[PruebaJSON.main] Objeto JSON --> " + objetoJSON);
		
		System.out.println("[PruebaJSON.main] Atributo String name --> " + UtileriasJSON.obtenerValorString("name", objetoJSON, null));
		
		listaString = UtileriasJSON.obtenerListaString(objetoJSON, "tags");
		System.out.println("[PruebaJSON.main] Arreglo JSON en Lista de String --> " + listaString);
		
		System.exit(0);
	}

}
