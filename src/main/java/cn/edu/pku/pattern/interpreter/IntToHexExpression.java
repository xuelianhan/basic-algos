package cn.edu.pku.pattern.interpreter;

public class IntToHexExpression implements Expression {

    private int count;
    
    public IntToHexExpression(){}
    
    public IntToHexExpression(int count) {
        this.count = count;
    }
    
    @Override
    public String interpret(InterpreterContext context) {
        return context.getHexadecimalFormat();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
