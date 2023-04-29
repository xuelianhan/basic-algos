package org.ict.algorithm;


import java.util.ArrayList;
import java.util.List;

public class Permutation {

	    private char initial;
	    private char last;
	    List<Character> chars = new ArrayList<Character>();
		        
	    public static void main(String[] args) {
		new Permutation('a','d').start();
	    }

		public Permutation(char initial,char last) {
			this.initial=initial;
			this.last=last;
			for (char c = this.initial; c <= this.last; c++) {
				chars.add(c);
			}
	    }

	    public void start(){
	        next(chars,new ArrayList<Character>());
	    }

		private void next(List<Character> unused,List<Character> used){
			if (unused.isEmpty()) {
					System.out.println(used);
			} else {
				for (int i = 0;i < unused.size(); i++) {
					List<Character> cur=new ArrayList<>(unused);
					List<Character> curUsed=new ArrayList<>(used);
					curUsed.add(cur.remove(i));
					next(cur,curUsed);
				}
			}
		}

} 
