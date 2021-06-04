package by.motolyha.composite.parser;

import by.motolyha.composite.entity.AbstractTextComponent;
import by.motolyha.composite.entity.TextComponentType;
import by.motolyha.composite.entity.TextComposite;
import by.motolyha.composite.exception.CompositeException;

public class SentenceParser extends AbstractTextParser{
    private static final String SENTENCE_SPLIT_REGEX = "(?<=\\.{3}|\\.|\\?|!)\\s";

    @Override
    public void parse(AbstractTextComponent component, String data) throws CompositeException {
        String[] sentences = data.split(SENTENCE_SPLIT_REGEX);

        for (String sentence : sentences) {
            var sentenceComponent = new TextComposite(TextComponentType.SENTENCE);
            component.add(sentenceComponent);

                nextParser.parse(sentenceComponent, sentence);
        }
    }
}
