package cn.edu.pku.pattern.singleton;

public class SingletonTwo extends Singleton {

    private SingletonTwo(){}
    
    public static Singleton getSingleton() {
        return Holder.singleton;
    }
    
    private static class Holder {
        private static Singleton singleton = new SingletonTwo();
    }
}
