package cn.edu.pku.pattern.singleton;

public class SingletonOne extends Singleton {

    //保证内存的可见性
    private static volatile Singleton singleton = null;
    
    private SingletonOne() {}
    
    public static Singleton getSingleton() {
        if (singleton == null) {
            synchronized(SingletonOne.class) {
                if (null == singleton) {
                    singleton = new SingletonOne();
                }
            }
        }
        return singleton;
    }
}
