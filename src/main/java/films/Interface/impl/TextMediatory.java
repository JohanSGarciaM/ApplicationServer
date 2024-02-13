package films.Interface.impl;

import films.Interface.Mediatory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;


public class TextMediatory implements Mediatory {

    String path;
    Socket client;
    String outputLine = "HTTP/1.1 200 OK \r\n";

    /**
     * Class Constructor
     * @param path File path
     * @param client client socket
     */
    public TextMediatory(String path, Socket client){
        this.path = path;
        this.client = client;
    }

    /**
     * File content response
     * @throws IOException In and Out exception
     */

    @Override
    public void reply() throws IOException {
        Path p = Paths.get("target/classes/public"+path);
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        Charset charset = Charset.forName("US-ASCII");
        String line;
        String response = "";
        try (BufferedReader reader = Files.newBufferedReader(p, charset)) {
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                response += line;
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        String type = typeFile();
        if (Objects.equals(type, "js")){
            outputLine += "Content-Type: text/javascript \r\n"
                    + "\r\n";
        } else {
            outputLine += "Content-Type: text/"+type+" \r\n"
                    + "\r\n";
        }

        System.out.println("TYpe: "+ type);
        outputLine += response;

        out.println(outputLine);
        out.close();
        client.close();
    }

    /**
     * Type of file method
     * @return file extention
     */
    @Override
    public String typeFile() {
        return path.split("\\.")[1];
    }
}