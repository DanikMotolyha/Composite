package by.motolyha.composite.reader;

import by.motolyha.composite.exception.CompositeException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextReader {
    private static final Logger LOGGER = LogManager.getLogger();

    public String readAllLines(String absolutePath) throws CompositeException {
        try {
            Path path = Path.of( absolutePath);
            return Files.readString(path);
        } catch (IOException e) {
            String message = "exception was occurred, details: " +  e.getMessage();
            LOGGER.log(Level.ERROR, message);
            throw new CompositeException(message);
        }
    }

}
