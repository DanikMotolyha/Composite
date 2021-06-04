package by.motolyha.composite.service.impl;

import by.motolyha.composite.entity.AbstractTextComponent;
import by.motolyha.composite.entity.TextComponentType;
import by.motolyha.composite.entity.TextComposite;
import by.motolyha.composite.exception.CompositeException;
import by.motolyha.composite.service.TextService;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {
    private static final String VOWEL_REGEX = "[aAeEiIoOuU]";

    @Override
    public List<AbstractTextComponent> sortParagraphsBySentenceCount(AbstractTextComponent text) throws CompositeException {
        if (!text.getComponentType().equals(TextComponentType.TEXT)) {
            throw new CompositeException("incorrect text type :" + text.getComponentType());
        }
        return text.getChildren()
                .stream()
                .sorted(new ParagraphComparator())
                .collect(Collectors.toList());
    }

    @Override
    public List<AbstractTextComponent> sentenceWithLongestWord(AbstractTextComponent text) throws CompositeException {
        if (!text.getComponentType().equals(TextComponentType.TEXT)) {
            throw new CompositeException("incorrect text type :" + text.getComponentType());
        }
        List<AbstractTextComponent> allSentences = text.getChildren()
                .stream()
                .flatMap(paragraph -> paragraph.getChildren().stream())
                .collect(Collectors.toList());
        int maxLength = allSentences.stream()
                .map(this::maxWordLength)
                .max(Integer::compareTo)
                .orElseThrow(() -> new CompositeException("no words"));
        return allSentences.stream()
                .filter(sentence -> maxWordLength(sentence) == maxLength)
                .collect(Collectors.toList());
    }

    @Override
    public AbstractTextComponent removeSentenceWithWordCountLessThan(AbstractTextComponent text, int wordCount) throws CompositeException {
        if (!text.getComponentType().equals(TextComponentType.TEXT)) {
            throw new CompositeException("incorrect text type :" + text.getComponentType());
        }
        AbstractTextComponent newText = new TextComposite(TextComponentType.TEXT);
        text.getChildren().forEach(paragraph -> {
            Collection<AbstractTextComponent> newSentences = paragraph.getChildren();
            newSentences.removeIf(sentence -> sentence.getChildren().stream().filter(component ->
                    component.getComponentType().equals(TextComponentType.WORD)).count() < wordCount);
            AbstractTextComponent newParagraph = new TextComposite(TextComponentType.PARAGRAPH);
            newParagraph.addAll(newSentences);
            newText.add(newParagraph);
        });
        return newText;
    }

    @Override
    public Map<String, Integer> sameWords(AbstractTextComponent text) throws CompositeException {
        if (!text.getComponentType().equals(TextComponentType.TEXT)) {
            throw new CompositeException("incorrect text type :" + text.getComponentType());
        }
        Map<String, Integer> map = new HashMap<>();
        text.getChildren()
                .stream()
                .flatMap(component -> component.getChildren().stream())
                .flatMap(component -> component.getChildren().stream())
                .filter(component -> component.getComponentType().equals(TextComponentType.WORD))
                .forEach(word -> {
                    var key = word.toString();
                    int occurrences = map.getOrDefault(key, 0);
                    map.put(key, ++occurrences);
                });
        return map;
    }

    @Override
    public long countVowelsOrConsonants(AbstractTextComponent sentence, boolean toggle) throws CompositeException {
        if (!sentence.getComponentType().equals(TextComponentType.SENTENCE)) {
            throw new CompositeException("incorrect text type :" + sentence.getComponentType());
        }

        return sentence.getChildren()
                .stream() // stream of lexemes
                .filter(component -> component.getComponentType().equals(TextComponentType.WORD)) // stream of words
                .flatMap(component -> component.getChildren().stream()) // stream of letters
                .filter(letter -> {
                    if(toggle){
                        return Pattern.matches(VOWEL_REGEX, letter.toString());
                    } else {
                        return !Pattern.matches(VOWEL_REGEX, letter.toString());
                    }
                })
                .count();
    }

    private int maxWordLength(AbstractTextComponent sentence) {
        return sentence.getChildren()
                .stream()
                .filter(lexeme -> lexeme.getComponentType().equals(TextComponentType.WORD))
                .map(word -> word.getChildren().size())
                .max(Integer::compareTo)
                .orElse(0);
    }

    private static class ParagraphComparator implements Comparator<AbstractTextComponent> {
        @Override
        public int compare(AbstractTextComponent first, AbstractTextComponent second) {
            return Long.compare(
                    first.getChildren().size(),
                    second.getChildren().size()
            );
        }
    }
}
