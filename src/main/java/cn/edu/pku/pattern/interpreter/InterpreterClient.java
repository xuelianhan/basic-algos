package cn.edu.pku.pattern.interpreter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InterpreterClient {
    
    public InterpreterContext context;
    
    private Map<String, Expression> dispatcher;
    
    public InterpreterClient(InterpreterContext context) {
        this.context = context;
        
        dispatcher = new HashMap<String, Expression>();
        dispatcher.put("hexadecimal", new IntToHexExpression());
        dispatcher.put("binary", new IntToBinaryExpression());
        dispatcher.put("octal", new IntToOctalExpression());
    }
    
    public String interpret(String input) {
        Expression exp = null;
        Set<String> keySet = dispatcher.keySet();
        for (String key : keySet) {
            if (input.contains(key)) {
                exp = dispatcher.get(key);
                String number = extractNumberFromStr(input);
                System.out.println("number is:" + number);
                context.setCount(Integer.valueOf(number));
                return exp.interpret(context);
            }
        }
        return null;
    }
    
    /**
     * @see https://stackoverflow.com/questions/2367381/how-to-extract-numbers-from-a-string-and-get-an-array-of-ints
     * @param s
     * @return
     */
    public String extractNumberFromStr(String input) {
        Pattern p = Pattern.compile("-?\\d+");
        Matcher m = p.matcher(input);
        while (m.find()) {
            return m.group();
        }
        return "";
    }
    
    public static void main(String[] args) {
        String s1 = "28 in binary";
        String s2 = "28 in hexadecimal";
        String s3 = "28 in octal";
        Set<String> input = new HashSet<String>();
        input.add(s1);
        input.add(s2);
        input.add(s3);
        
        InterpreterClient ic = new InterpreterClient(new InterpreterContext());
        for (String s : input) {
            System.out.println(s + " interpret result:" + ic.interpret(s));
        }
    }
    
}
