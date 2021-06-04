package by.motolyha.composite.parser;

import by.motolyha.composite.entity.AbstractTextComponent;
import by.motolyha.composite.exception.CompositeException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractTextParser {
    private static final Logger LOGGER = LogManager.getLogger();
    protected AbstractTextParser nextParser = EmptyTextParser.getEmptyTextParser();

    public void setNextParser(AbstractTextParser textParser) {
        this.nextParser = textParser;
    }

    public abstract void parse(AbstractTextComponent component, String data) throws CompositeException;

    private static class EmptyTextParser extends AbstractTextParser {
        private static final EmptyTextParser INSTANCE = new EmptyTextParser();

        public static EmptyTextParser getEmptyTextParser() {
            return INSTANCE;
        }

        @Override
        public void parse(AbstractTextComponent component, String data) {
            LOGGER.log(Level.INFO, "empty parser chain/ end of chain");
        }
    }
}
