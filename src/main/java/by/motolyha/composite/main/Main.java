package by.motolyha.composite.main;

import by.motolyha.composite.entity.TextComponentType;
import by.motolyha.composite.entity.TextComposite;
import by.motolyha.composite.parser.ParserChainFactory;
import by.motolyha.composite.reader.TextReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        TextReader reader = new TextReader();
        try {
            var text = reader.readAllLines("src/main/resources/data/text.txt");
            var composite = new TextComposite(TextComponentType.TEXT);
            var factory = new ParserChainFactory();
            var parser = factory.createParserChain();

            parser.parse(composite, text);
        } catch (Exception e){
            LOGGER.log(Level.ERROR, e.getMessage());
        }
    }
}
