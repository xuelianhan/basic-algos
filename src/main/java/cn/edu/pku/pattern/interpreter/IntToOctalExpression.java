package cn.edu.pku.pattern.interpreter;

public class IntToOctalExpression implements Expression {

    private int count;
    
    public IntToOctalExpression(){}
    
    public IntToOctalExpression(int count) {
        this.count = count;
    }
    
    @Override
    public String interpret(InterpreterContext context) {
        return context.getOctalFormat();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
