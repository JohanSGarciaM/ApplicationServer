package films.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * ths class stores the lambda services
 * @author Johan Garcia
 * @version 12/02/2024/1
 */
public class SparkHandler {

    // Map used to store the services
    private static final Map<String, StringService> serviciosGET = new HashMap<>();
    private static final Map<String, StringService> serviciosPOST = new HashMap<>();

    /**
     * Method used to look for created services
     * @param nombre service's name
     * @return Service's interface
     */
    public static StringService buscar(String nombre, String verbo) {
        if (verbo.equals("GET")){
            return serviciosGET.get(nombre);
        } else{
            return serviciosPOST.get(nombre);
        }
    }

    /**
     * Method used to create services based on lambda functions
     * @param str service's name
     * @param service service's interface
     */
    public static void get(String str, StringService service){
        serviciosGET.put(str, service);
    }

    public static void post(String str, StringService service){
        serviciosPOST.put(str, service);
    }
}