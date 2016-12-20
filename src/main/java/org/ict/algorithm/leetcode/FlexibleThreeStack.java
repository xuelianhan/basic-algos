package org.ict.algorithm.leetcode;

public class FlexibleThreeStack {
   private static int NUMBER_OF_STACKS = 3;
   
   private static int DEFAULT_SIZE = 4;

   private static int TOTAL_SIZE = DEFAULT_SIZE * NUMBER_OF_STACKS;

   private static StackData[] stacks = {new StackData(0, DEFAULT_SIZE), new StackData(DEFAULT_SIZE, DEFAULT_SIZE), 
   new StackData(DEFAULT_SIZE * 2, DEFAULT_SIZE)};
   
   private static int[] buffer = new int[TOTAL_SIZE];

   public static void main(String[] args) {
        push(0, 10);
        push(1, 20);
        push(2, 30);
        int v = pop(0);
        System.out.println(v);
   }

   public static int numberOfElements() {
        return stacks[0].size + stacks[1].size + stacks[2].size;
   }
   

   public static void push(int stackNum, int number) {
	   
   }
   
   public static int pop(int  stackNum) {
	   return 0;
   }
}
