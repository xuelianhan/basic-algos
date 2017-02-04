package org.ict.algorithm.fundamentals;
import org.ict.algorithm.util.StdOut;

/***********************************************************
 * Compilation: javac org/ict/algorithm/util/Counter.java
 * Execution: java org/ict/algorithm/util/Counter  6 600000
 * Dependencies: StdOut.java StdRandom.java
 * 
 * A mutable data type for an integer counter.
 *
 * The test clients create N counters and performs T increment
 * operations on random conters.
 *
 * 99890 counter0
 * 99988 counter1
 * 99986 counter2
 * 99826 counter3
 * 100129 counter4
 * 100181 counter5
 *
 *
 *********************************************************/
public class Counter implements Comparable<Counter> {

  //counter name
  private final String name;

  //current value
  private int count = 0;

  public Counter(String id) {
    this.name = id;
  }
  
  /**
   * Compare this counter to the special counter.
   * @param that the other counter
   * @return <tt>0</tt> if the value of this counter
   * equals the value of that counter; a negative integer if
   * the value of this counter is less than the value of that
   * counter; and a positive integer if the value of this counter
   * is greater than the value of that counter.
   *
   */
  public int compareTo(Counter that) {
    if ( this.count > that.count) {
      return +1;
    } else if (this.count < that.count) {
      return -1;
    } else {
      return 0;
    }
  }

  /**
   * increments the counter by 1
   */
  public void increment() {
     this.count++;
  }
  
  /**
   * Returns the current value of this count
   */
  public int tally() {
    return this.count;
  }

  public String toString() {
    return count + " " + name;
  }

  public static void main(String[] args) {
    int N = Integer.valueOf(args[0]);
    int T = Integer.valueOf(args[1]);
    
    //create N counters
    Counter[] hits = new Counter[N];
    for (int i = 0; i < N; i++) {
      hits[i] = new Counter("counter" + i);
    }

    //increate T conters an random
    for (int t = 0; t < T; t++) {
      hits[StdRandom.uniform(N)].increment();
    }
    
    //print results
    for (int i = 0; i < N; i++) {
      StdOut.println(hits[i]); 
    }
  }

}
