package cn.edu.pku.pattern;

public abstract class Pizza {
    
    //encapluate action
    RemA rmFn = new RemA();
    TopAwC topFn = new TopAwC();
    SubAbC subFn = new SubAbC();
    
    //define action functions
    abstract Pizza remA();
    abstract Pizza topAwC();
    abstract Pizza subAbC();
}
