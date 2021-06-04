package by.motolyha.composite.parser;

import by.motolyha.composite.entity.AbstractTextComponent;
import by.motolyha.composite.entity.Lexem;
import by.motolyha.composite.entity.TextComponentType;

public class WordParser extends  AbstractTextParser{
    @Override
    public void parse(AbstractTextComponent component, String data) {
        for (char symbolValue : data.toCharArray()) {
            var lexeme = new Lexem(TextComponentType.LEXEME, String.valueOf(symbolValue));
            component.add(lexeme);
        }
    }
}
