package cn.edu.pku.pattern.interpreter;

public interface Expression {

    String interpret(InterpreterContext context);
    
}
