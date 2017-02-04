package org.ict.algorithm.util;

import org.ict.algorithm.fundamentals.Bag;

public class Stats {
	
    public static void main(String[] args) {
    	//1.input
        Bag<Double> bag = new Bag<Double>();
        while(!StdIn.isEmpty()) {
        	bag.add(StdIn.readDouble());
        }
        
        int N = bag.size();
        
        //2.compute sample mean
        Double sum = 0d;
        for (Double d : bag) {
        	sum += d;
        }
        Double mean = sum / N;
        
        //3.compute sample standard deviation
        sum = 0.0;
        for (Double d : bag) {
        	sum += (d - mean) * (d - mean);
        }
        double std = Math.sqrt(sum);
        
        //4.output result
        StdOut.printf("mean: %.2f\n", mean);
        StdOut.printf("std:  %.2f\n", std);
        StdOut.println();
    }

}
