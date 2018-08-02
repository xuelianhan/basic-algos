package org.ict.rpc;

import java.util.concurrent.ExecutionException;

import org.ict.thread.QueryProcessHandler;
import org.ict.thread.RequestQuery;
import org.ict.thread.SettableFuture;
import org.ict.thread.ThreadGod;




public class RpcContextTest {
    
    public static void main(String[] args) {
        
        for (int i = 0; i < 3; i++) {
            RequestQuery query = new RequestQuery();
            query.setName("q"+(i+1));
            query.setPageSize(i+1);
            RpcContext context = RpcContext.getContext();
            ThreadGod tg = new ThreadGod("thread-" + (i+1), query, context);
            Thread t = new Thread(tg, tg.getThreadName()); 
            t.start();
        }
        
        SettableFuture<String> f1 = SettableFuture.create();
        RpcContext context1 = RpcContext.getContext();
        context1.setFuture(f1);
        RequestQuery q1 = new RequestQuery();
        q1.setName("q1");
        q1.setPageSize(1);
        //QueryProcessHandler handler1 = new QueryProcessHandler(context1, q1);
        QueryProcessHandler handler1 = new QueryProcessHandler(f1, q1);
        
        SettableFuture<String> f2 = SettableFuture.create();
        RpcContext context2 = RpcContext.getContext();
        context2.setFuture(f2);
        RequestQuery q2 = new RequestQuery();
        q2.setName("q2");
        q2.setPageSize(2);
        //QueryProcessHandler handler2 = new QueryProcessHandler(context2, q2);
        QueryProcessHandler handler2 = new QueryProcessHandler(f2, q2);
        
        SettableFuture<String> f3 = SettableFuture.create();
        RpcContext context3 = RpcContext.getContext();
        context3.setFuture(f3);
        RequestQuery q3 = new RequestQuery();
        q3.setName("q3");
        q3.setPageSize(3);
        //QueryProcessHandler handler3 = new QueryProcessHandler(context3, q3);
        QueryProcessHandler handler3 = new QueryProcessHandler(f3, q3);
        
        try {
           /* String rs1 = handler1.handle(context1, q1);
            String rs2 = handler2.handle(context2, q2);
            String rs3 = handler3.handle(context3, q3);*/
            
            String rs1 = handler1.handle(f1, q1);
            String rs2 = handler2.handle(f2, q2);
            String rs3 = handler3.handle(f3, q3);
            System.out.println("rs1:" + rs1 + ", rs2:" + rs2 + ", rs3:" + rs3);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
 