package films.Persistence;


import java.util.concurrent.ConcurrentHashMap;

/**
 * Cache class 
 * @author Johan Garcia
 */

public class APIPersistence{


    private ConcurrentHashMap<String, String> moviesCache;
    private static APIPersistence instance = null;

    /**
     * APIPersistence class constructor
     */
    public APIPersistence(){
        moviesCache = new ConcurrentHashMap<String, String>();
    }

    /**
     * Patron singleton method
     * @return Instancia de la clase
     */
    public static APIPersistence getInstance(){
        if (instance == null){
            instance = new APIPersistence();
        }
        return instance;
    }

    /**
     * Receive the name of the movie and if it exists give back the movie's information
     * @param movie Movie's name
     * @return Movie's information
     */
    public boolean movieStoredCache(String movie){
        return moviesCache.contains(movie);
    }

    /**
     * Check if the movie is stored in cache
     * @param movie Movie's name
     * @return Boolean that indicates if the movie exists
     */
    public String getMovie(String movie){
        return moviesCache.get(movie);
    }

    /**
     * Stores a movie in caché
     * @param movie Movie's name
     * @param info Movie's information
     */

    public void addMovie(String movie, String info){
        moviesCache.put(movie, info);
    }
}