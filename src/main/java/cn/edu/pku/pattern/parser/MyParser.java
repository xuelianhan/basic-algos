package cn.edu.pku.pattern.parser;

/**
 * @see https://stackoverflow.com/questions/271526/avoiding-null-statements
 * @see https://stackoverflow.com/questions/8039504/how-to-convert-a-possible-null-value-to-a-default-value-using-guava
 */
public class MyParser implements Parser {
    
    private static Action DO_SOTHING = new Action() {
        public void doSomething(){
            System.out.println("my parser is working");
        }
    };

    @Override
    public Action findAction(String userInput) {
        /* Code to return requested Action if found */
        return DO_SOTHING;
    }

}
