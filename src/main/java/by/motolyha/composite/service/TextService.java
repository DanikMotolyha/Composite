package by.motolyha.composite.service;

import by.motolyha.composite.entity.AbstractTextComponent;
import by.motolyha.composite.exception.CompositeException;

import java.util.List;
import java.util.Map;

public interface TextService {

    List<AbstractTextComponent> sortParagraphsBySentenceCount(AbstractTextComponent text) throws CompositeException;

    List<AbstractTextComponent> sentenceWithLongestWord(AbstractTextComponent text) throws CompositeException;

    AbstractTextComponent removeSentenceWithWordCountLessThan(AbstractTextComponent text, int wordCount)
            throws CompositeException;


    Map<String, Integer> sameWords(AbstractTextComponent text) throws CompositeException;


    long countVowelsOrConsonants(AbstractTextComponent sentence, boolean toggle) throws CompositeException;
}
