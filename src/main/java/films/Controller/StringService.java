package films.Controller;

import java.io.IOException;
import java.net.Socket;

/**
 * Services's interfaces
 * @author Johan Garcia
 * @version 12/02/2024/1
 */
public interface StringService {

    /**
     * Interface's response method
     * @param str Param's name
     * @param client client socket
     * @throws IOException
     */
    public void response(String str, Socket client) throws IOException;
}