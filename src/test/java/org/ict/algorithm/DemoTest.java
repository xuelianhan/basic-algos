package org.ict.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DemoTest {
	
	/**
	 * https://www.geeksforgeeks.org/gcd-two-array-numbers/
	 * @param a
	 * @param b
	 * @return
	 */
	static int gcd(int a, int b) 
    { 
        if (a == 0) 
            return b; 
        return gcd(b % a, a); 
    } 
  
    // Function to find gcd of array of 
    // numbers 
    public int findGCD(int arr[], int n) 
    { 
        int result = arr[0]; 
        for (int i = 1; i < n; i++) 
            result = gcd(arr[i], result); 
  
        return result; 
    } 

	public List<Integer> cellCompete(int[] states, int days)
    {
        ArrayList<Integer> result = new ArrayList<Integer>();
        backtrack(states, days, result);
        return result;
    }
    
    private void backtrack(int[] states, int days, ArrayList<Integer> result) {
        if (days < 0) {
            return;
        } else if (days == 0) {
            result.addAll(Arrays.stream(states).boxed().collect(Collectors.toList()));
        } else {
            int[] temp = new int[states.length];
            for (int j = 0; j < states.length; j++) {
                if (j == 0) {
                    temp[j] =  0 ^ states[j+1];
                } else if (j == states.length - 1) {
                    temp[j] =  0 ^ states[j-1];
                } else {
                    temp[j] = states[j-1] ^ states[j+1];
                }
            }
            backtrack(temp, days - 1, result);
        }
    }
    
	
	public static void main(String[] args) {
		
	}
}
