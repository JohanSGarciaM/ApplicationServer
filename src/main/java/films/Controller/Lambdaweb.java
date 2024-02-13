package films.Controller;

import films.Interface.MovieResponse;
import java.io.IOException;
import static films.Controller.SparkHandler.*;
import static films.Controller.HttpServer.*;

/**
 * Invokes the Lamda functions
 * @author Johan Garcia
 */
public class Lambdaweb {

    /**
     * Main method of the Lambdaweb class
     * @param args string array
     * @throws IOException In / Out exception
     */
    public static void main(String[] args) throws IOException {
        get("/movie", (str, cli) -> {
            MovieResponse.getMovie(cli, str);
        });

        post("/movie", (str, cli) ->{
            MovieResponse.getMovie(cli, str);
        });

        start();
    }
}