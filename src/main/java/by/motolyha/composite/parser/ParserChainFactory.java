package by.motolyha.composite.parser;

public class ParserChainFactory {

    public AbstractTextParser createParserChain(){
        var paragraphParser = new ParagraphParser();
        var sentenceParser = new SentenceParser();
        var wordParser = new WordParser();
        var lexemeParser = new LexemeParser();

        paragraphParser.setNextParser(sentenceParser);
        sentenceParser.setNextParser(lexemeParser);
        lexemeParser.setNextParser(wordParser);
        return paragraphParser;
    }
}
