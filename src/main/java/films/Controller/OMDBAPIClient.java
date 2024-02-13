package films.Controller;

import films.Persistence.APIPersistence;
import films.Service.APIService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Class which request to the API
 * @author Johan Garcia
 */

public class OMDBAPIClient {

    private static APIService as;
    private static OMDBAPIClient instance = null;
    private static final String omdbapiurl = "http://www.omdbapi.com/?apikey=d6f2cc0d";

    /**
     * Constructor 
     * @param as APIService isntance
     */
    public OMDBAPIClient(APIService as){
        OMDBAPIClient.as = as;
    }

    /**
     * Singleton patron method
     * OMDBAPIClient
     * @return Class instance
     */

    public static OMDBAPIClient getInstance(){
        if (instance == null){
            instance = new OMDBAPIClient(APIService.getInstance());
        }
        return instance;
    }

    /**
     * Method who brings the cache information
     * @return cache's movie information
     */
    public static String getMovie(String movie) throws IOException {
        if (as.storedInCache(movie)){
            return as.getMovie(movie);
        }
        URL urlmovie = new URL(omdbapiurl+"&t="+movie);
        String info = requestGetMovie(urlmovie);
        as.addMovie(movie, info);
        return info;
    }

    /**
     * Methodo who request the movie's information
     * @param url URL to request
     * @return Movie's Information from the API
     * @throws IOException In and Out Exception
     */

    private static String requestGetMovie(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = connection.getResponseCode();
        System.out.println("Code: "+responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                System.out.println("Response :" + inputLine);
            }
            in.close();
            return response.toString();
        } else{
            return "GET did not work";
        }
    }
}