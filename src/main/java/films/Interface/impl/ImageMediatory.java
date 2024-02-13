package films.Interface.impl;

import films.Interface.Mediatory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Image type files
 * @author Johan garcia
 */
public class ImageMediatory implements Mediatory {


    String path;
    Socket client;
    String outputLine = "HTTP/1.1 200 OK \r\n";

    /**
     * Class constructor
     * @param path File path
     * @param client Client socket
     */
    public ImageMediatory(String path, Socket client){
        this.path = path;
        this.client = client;
    }

    /**
     * response method
     * @throws IOException In and out Exception
     */
    @Override
    public void reply() throws IOException {
        String type = typeFile();
        Path p = Paths.get("target/classes/public"+path);
        BufferedImage bImage = ImageIO.read(new File(p.toUri()));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, type, bos );

        OutputStream clientout = client.getOutputStream();
        String contentType = outputLine + "Content-Type: image/"+type+" \r\n" + "\r\n";
        clientout.write(contentType.getBytes());
        clientout.write(bos.toByteArray());
        clientout.close();

        client.close();
    }

    /**
     * Type of file method
     * @return Extension del archivo
     */
    @Override
    public String typeFile() {
        if (path.contains(".")) {
            return path.split("\\.")[1];
        } else{
            return "";
        }
    }
}