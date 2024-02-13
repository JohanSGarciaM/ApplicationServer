package films.Interface;

import films.Controller.OMDBAPIClient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MovieResponse {

    public static void getMovie(Socket client, String path) throws IOException {
        OMDBAPIClient omdbapi = OMDBAPIClient.getInstance();
        System.out.println("PASA POR GET MOVIE");
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        String outputLine = "HTTP/1.1 200 OK \r\n" +
                "Content-Type: application/json \r\n" +
                "\r\n"+ omdbapi.getMovie(path);
        out.println(outputLine);
        out.close();
        client.close();
    }
}