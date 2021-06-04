package by.motolyha.composite.parser;

import by.motolyha.composite.entity.AbstractTextComponent;
import by.motolyha.composite.entity.TextComponentType;
import by.motolyha.composite.entity.TextComposite;
import by.motolyha.composite.exception.CompositeException;


public class ParagraphParser extends AbstractTextParser {
    private static final String PARAGRAPH_SPLIT_REGEX = "[\\t\\n]+";

    @Override
    public void parse(AbstractTextComponent component, String data) throws CompositeException {
        String[] paragraphs = data.split(PARAGRAPH_SPLIT_REGEX);

        for (String paragraph : paragraphs) {
            var paragraphComponent = new TextComposite(TextComponentType.PARAGRAPH);
            component.add(paragraphComponent);
            nextParser.parse(component, paragraph);
        }
    }
}
