package org.ict.rpc;

import org.ict.thread.SettableFuture;
import org.ict.thread.ThreadGod;




public class RpcContextTest {
    
    public static void main(String[] args) {
        
        for (int i = 0; i < 5; i++) {
            ThreadGod tg = new ThreadGod("thread-" + i);
            Thread t = new Thread(tg, tg.getThreadName()); 
            t.start();
        }
        
        SettableFuture<String> f1 = SettableFuture.create();
        RpcContext.getContext().setFuture(f1);
        
        SettableFuture<String> f2 = SettableFuture.create();
        RpcContext.getContext().setFuture(f2);
        
        SettableFuture<String> f3 = SettableFuture.create();
        RpcContext.getContext().setFuture(f3);
    }
}
 