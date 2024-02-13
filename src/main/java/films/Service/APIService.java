package films.Service;

import films.Persistence.APIPersistence;


/**
 * API Service Class
 * @author Johan Garcia
 */
public class APIService {

    private static APIPersistence ap;
    private static APIService instance = null;

    /**
     * APIService Class constructor
     */
    public APIService(APIPersistence ap){
        APIService.ap = ap;
    }

    /**
     * Patron Singleton method
     * @return Class interface
     */
    public static APIService getInstance(){
        if (instance == null){
            instance = new APIService(APIPersistence.getInstance());
        }
        return instance;
    }

    /**
     * Looks for a movie in cache
     * @param movie Movie's name
     * @return Movie's information
     */
    public String getMovie(String movie){
        return ap.getMovie(movie);
    }

    /**
     * Checks if the movie is in cache
     * @param movie Movie's name
     * @return Boolean returns if the movie is in cache
     */
    public boolean storedInCache(String movie){
        return ap.movieStoredCache(movie);
    }

    /**
     * Send the movie to caché
     * @param movie Movie's name
     * @param info Movie's information
     */
    public void addMovie(String movie, String info){
        ap.addMovie(movie, info);
    }
}