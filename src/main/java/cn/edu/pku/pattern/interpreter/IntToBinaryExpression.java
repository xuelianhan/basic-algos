package cn.edu.pku.pattern.interpreter;

public class IntToBinaryExpression implements Expression {

    private int count;
    
    public IntToBinaryExpression(){}
    
    public IntToBinaryExpression(int count) {
        this.count = count;
    }
    
    @Override
    public String interpret(InterpreterContext context) {
        
        return context.getBinaryFormat();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
