package org.ict.util;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.google.common.base.MoreObjects;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
/**
 * 
 * @see https://www.tutorialspoint.com/guava/guava_caching_utilities.htm
 * @see https://www.concretepage.com/google-api/google-guava-cache-example-using-loadingcache-cachebuilder-and-cacheloader
 */
public class GuavaCacheTester {
    
    private static ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    
    //create a cache for employees based on their employee id
    private static LoadingCache<String, Employee> employeeCache = CacheBuilder.newBuilder()
            .refreshAfterWrite(1, TimeUnit.MINUTES)
            .maximumSize(100)                             // maximum 100 records can be cached
            //.expireAfterAccess(1, TimeUnit.MINUTES)      // cache will expire after 1 minutes of access
            .build(new CacheLoader<String, Employee>() {  // build the cacheloader
          
                  @Override
                  public Employee load(String key) throws Exception {
                     //make the expensive call
                     return getFromDatabase(key);
                  }
          
                  public ListenableFuture<Employee> reload(final String key, Employee prevValue) {
                    
                        // asynchronous!
                        ListenableFutureTask<Employee> task = ListenableFutureTask.create(new Callable<Employee>() {
                          public Employee call() {
                            return refreshDatabaseValue(key);
                          }
                        });
                        executor.execute(task);
                        return task;
                  }
            });
    
    public static Employee getValueByKey(String key) throws ExecutionException {
        return employeeCache.get(key);
    }
    
    public static void enforceReloadAllCache() {
        System.out.println("At " + new Date() + ", reload all cache datas!");
        employeeCache.invalidateAll();
        employeeCache.putAll(reloadDatabaseValues());
    }
    
    public static void enforeReloadCache(String key) {
        System.out.println("At " + new Date() + ", reload cache key:" + key);
        employeeCache.invalidate(key);
        employeeCache.put(key, reloadDatabaseValues(key));
    }
    
    public static void printKeyValue(String key) {
        try {
            System.out.println("At " + new Date() + ", item:" + key + " after refresh value:" + getValueByKey(key));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    
    public static void printKeyList(List<String> keyList) {
        keyList.forEach(item->{
            printKeyValue(item);
        });
    }
    
    /**
     * @see https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ScheduledExecutorService.html
     * Here is a class with a method that sets up a ScheduledExecutorService to beep refresh cache every 20 seconds for 1 minutes:
 
     */
    public static void beepForAnHour(List<String> keyList) {
        
        final Runnable beeper = new Runnable() {
            
            public void run() { 
                
                keyList.forEach(item->{
                    System.out.println("At " + new Date() + ", beep refresh " + item + " in cache start..."); 
                    employeeCache.refresh(item);
                    System.out.println("At " + new Date() + ", beep refresh " + item + " in cache end...");
                    printKeyValue(item);
                });
                
            }
        };
        
        final ScheduledFuture<?> beeperHandle = executor.scheduleAtFixedRate(beeper, 20, 20, TimeUnit.SECONDS);
        
        executor.schedule(new Runnable() {
            public void run() { 
                beeperHandle.cancel(true); 
            }
        }, 1 * 60, TimeUnit.SECONDS);
    }

    public static void main(String args[]) {
      try {
          
         //on first invocation, cache will be populated with corresponding
         //employee record
         System.out.println("Invocation #1==================");
         System.out.println(getValueByKey("100"));
         System.out.println(getValueByKey("103"));
         System.out.println(getValueByKey("110"));
         
         //second invocation, data will be returned from cache
         System.out.println("Invocation #2==================");
         System.out.println(getValueByKey("100"));
         System.out.println(getValueByKey("103"));
         System.out.println(getValueByKey("110"));
         
         
         
         //refresh one key
         System.out.println("Invocation #3==================");
         System.out.println("before refresh:" + getValueByKey("100"));
         //refresh will trigger the invoking of reload method in CacheLoader
         employeeCache.refresh("100");
         System.out.println("after refresh:" + getValueByKey("100"));
         
         //scheduled thread refresh all keys in cache
         List<String> keyList = new ArrayList<String>();
         keyList.add("100");
         keyList.add("103");
         keyList.add("110");
         System.out.println("Invocation #4=================");
         beepForAnHour(keyList);
         
         
        
         Thread.sleep(60000);
         System.out.println("Invocation #5=================");
         enforceReloadAllCache();
         printKeyList(keyList);
      } catch (ExecutionException | InterruptedException e) {
         e.printStackTrace();
      }
    }

    private static Employee getFromDatabase(String empId) {
        Employee e1 = new Employee("Mahesh", "Finance", "100");
        Employee e2 = new Employee("Rohan", "IT", "103");
        Employee e3 = new Employee("Sohan", "Admin", "110");

        Map<String, Employee> database = new HashMap<String, Employee>();
        database.put("100", e1);
        database.put("103", e2);
        database.put("110", e3);
  
        System.out.println("At " + new Date() + "Database load hit for " + empId);
        return database.get(empId);       
    }
    
    
    private static Employee refreshDatabaseValue(String empId) {
        Employee e1 = new Employee("xuelian.han", "IT", "100");
        Employee e2 = new Employee("haizhen.ni", "IT", "103");
        Employee e3 = new Employee("li.han", "Admin", "110");

        Map<String, Employee> database = new HashMap<String, Employee>();
        database.put("100", e1);
        database.put("103", e2);
        database.put("110", e3);
  
        System.out.println("At " + new Date() + " Database refresh hit for " + empId);
        return database.get(empId);     
    }
    
    
    private static Map<String, Employee> reloadDatabaseValues() {
        Employee e1 = new Employee("Google", "IT", "100");
        Employee e2 = new Employee("Amazon", "IT", "103");
        Employee e3 = new Employee("Airbnb", "IT", "110");

        Map<String, Employee> database = new HashMap<String, Employee>();
        database.put("100", e1);
        database.put("103", e2);
        database.put("110", e3);
  
        System.out.println("At " + new Date() + " Database reload all!");
        return database;     
    }
    
    private static Employee reloadDatabaseValues(String key) {
        Employee e1 = new Employee("Google", "IT", "100");
        Employee e2 = new Employee("Amazon", "IT", "103");
        Employee e3 = new Employee("Airbnb", "IT", "110");

        Map<String, Employee> database = new HashMap<String, Employee>();
        database.put("100", e1);
        database.put("103", e2);
        database.put("110", e3);
  
        System.out.println("At " + new Date() + " Database reload all!");
        return database.get(key);     
    }
}

class Employee {
    String name;
    String dept;
    String emplD;

    public Employee(String name, String dept, String empID) {
        this.name = name;
        this.dept = dept;
        this.emplD = empID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getEmplD() {
        return emplD;
    }

    public void setEmplD(String emplD) {
        this.emplD = emplD;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(Employee.class).add("Name", name)
                .add("Department", dept).add("Emp Id", emplD).toString();
    }

}
