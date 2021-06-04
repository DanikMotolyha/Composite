package by.motolyha.composite.parser;

import by.motolyha.composite.entity.AbstractTextComponent;
import by.motolyha.composite.entity.Lexem;
import by.motolyha.composite.entity.TextComponentType;
import by.motolyha.composite.entity.TextComposite;
import by.motolyha.composite.exception.CompositeException;

import java.util.regex.Pattern;

public class LexemeParser extends AbstractTextParser {
    private static final String PUNCTUATION_REGEX = "\\.|!|\\?|\\.{3}|,";
    private static final String WORD_REGEX = "[^.!?,]+";
    private static final String LEXEME_SPLIT_REGEX = "\\s";

    @Override
    public void parse(AbstractTextComponent component, String data) throws CompositeException {
        String[] lexemes = data.split(LEXEME_SPLIT_REGEX);
        var wordPattern = Pattern.compile(WORD_REGEX);
        var punctuationPattern = Pattern.compile(PUNCTUATION_REGEX);
        for (String lexeme : lexemes) {
            var wordMatcher = wordPattern.matcher(lexeme);
            var punctuationMatcher = punctuationPattern.matcher(lexeme);
            while (wordMatcher.find()) {
                String word = wordMatcher.group();
                AbstractTextComponent lexemeComponent = new TextComposite(TextComponentType.WORD);
                component.add(lexemeComponent);
                nextParser.parse(lexemeComponent, word);
            }
            while (punctuationMatcher.find()) {
                String punctuation = punctuationMatcher.group();
                AbstractTextComponent lexemeComponent = new Lexem(TextComponentType.LEXEME, punctuation);
                component.add(lexemeComponent);
            }
        }
    }
}
