package films.Interface;

import java.io.IOException;


/**
 *  Response Interface
 * @author Johan Garcia
 */
public interface Mediatory {

    /**
     * Response method
     * @throws IOException In and out Exception
     */
    void reply() throws IOException;

    /**
     * Type of file method
     * @return File extension
     */
    String typeFile();
}