package cn.edu.pku.pattern.parser;

public class ParserFactory {

    public static Parser getParser() {
        return new MyParser();
    }
    
    public static void main(String[] args) {
        String userInput = "a";
        ParserFactory.getParser().findAction(userInput).doSomething();
    }
}
