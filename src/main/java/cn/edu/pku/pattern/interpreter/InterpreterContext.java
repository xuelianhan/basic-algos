package cn.edu.pku.pattern.interpreter;

public class InterpreterContext {
    
    private Integer count;
    
    public InterpreterContext(){}
    
    public String getBinaryFormat() {
        return Integer.toBinaryString(this.count);
    }
    
    public String getHexadecimalFormat() {
        return Integer.toHexString(this.count);
    }
    
    public String getOctalFormat() {
        return Integer.toOctalString(this.count);
    }
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
}
