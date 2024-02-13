package films.Controller;

import films.Interface.*;
import films.Interface.impl.*;

import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import org.json.*;

import static films.Controller.SparkHandler.buscar;

/**
 * Http Server Class
 * @author Johan Garcia
 */

public class HttpServer {

    static Map<String, StringService> servicios = new HashMap<>();

    private static Mediatory mediatory;

    /**
     * Class Main Method
     * @throws IOException In and Out exception
     */

    public static void start() throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Ready to receive ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;

            boolean firstLine = true;
            String path = null;


            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if (firstLine) {
                    firstLine = false;
                    path = inputLine.split(" ")[1];
                }
                if (!in.ready()) {
                    break;
                }
            }


            if(path.contains("?")) {
                buscar("/movie", "GET").response(path.split("=")[1], clientSocket);
            }else if (path.endsWith(".html") || path.endsWith(".css") || path.endsWith(".js")) {
                mediatory = new TextMediatory(path, clientSocket);
                mediatory.reply();
            } else if (path.endsWith(".jpeg") || (path.endsWith(".jpg")) || path.endsWith(".gif")){
                mediatory = new ImageMediatory(path, clientSocket);
                mediatory.reply();
            } else {
                System.out.println("Extention invalid");
            }
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    } // Cierre del metodo


} // Cierre de la clase