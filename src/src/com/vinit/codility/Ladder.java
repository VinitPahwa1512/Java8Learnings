package com.vinit.codility;


/*You have to climb up a ladder. The ladder has exactly N rungs, numbered from 1 to N. With each step, you can ascend by one or two rungs. More precisely:

with your first step you can stand on rung 1 or 2,
if you are on rung K, you can move to rungs K + 1 or K + 2,
finally you have to stand on rung N.
Your task is to count the number of different ways of climbing to the top of the ladder.

For example, given N = 4, you have five different ways of climbing, ascending by:

1, 1, 1 and 1 rung,
1, 1 and 2 rungs,
1, 2 and 1 rung,
2, 1 and 1 rungs, and
2 and 2 rungs.
Given N = 5, you have eight different ways of climbing, ascending by:

1, 1, 1, 1 and 1 rung,
1, 1, 1 and 2 rungs,
1, 1, 2 and 1 rung,
1, 2, 1 and 1 rung,
1, 2 and 2 rungs,
2, 1, 1 and 1 rungs,
2, 1 and 2 rungs, and
2, 2 and 1 rung.
The number of different ways can be very large, so it is sufficient to return the result modulo 2P, for a given integer P.

Write a function:

class Solution { public int[] solution(int[] A, int[] B); }

that, given two non-empty arrays A and B of L integers, returns an array consisting of L integers specifying the consecutive answers; position I should contain the number of different ways of climbing the ladder with A[I] rungs modulo 2B[I].

For example, given L = 5 and:

    A[0] = 4   B[0] = 3
    A[1] = 4   B[1] = 2
    A[2] = 5   B[2] = 4
    A[3] = 5   B[3] = 3
    A[4] = 1   B[4] = 1
the function should return the sequence [5, 1, 8, 0, 1], as explained above.

Write an efficient algorithm for the following assumptions:

L is an integer within the range [1..50,000];
each element of array A is an integer within the range [1..L];
each element of array B is an integer within the range [1..30].*/

public class Ladder {

	public Ladder() {
		// TODO Auto-generated constructor stub
	}

	 public int[] solution(int[] A, int[] B) {

	        // The task is to find out the number of ways 
	        // someone can climb up a ladder of N rungs 
	        // by ascending one or two rungs at a time.
	        // It is not very hard to see that 
	        // this number is just the "Fibonacci number of order N"
	        
	        // we implemented an easy dynamic programming approach 
	        // to compute Fibonacci numbers, this will take complexity O(n)
	        
	        // I use binary operators to keep track of "N modulo 2^{30}" 
	        // otherwise. the Fibonacci numbers will cause a memory overflow (be careful~!!)
	        // and we are also asked to return "numbers modulo some power of 2"
	        
	        int L = A.length;
	       
	        // determine the "max" for Fibonacci
	        int max = 0;
	        for (int i = 0; i < L; i++) {
	            max = Math.max(A[i], max);
	        }
	        //max += 2; // for Fibonacci
	        
	        int[] fibonacci = new int[max+1]; // plus one for "0"
	        
	        // initial setting of Fibonacci (importnat)
	        fibonacci[0] =1;
	        fibonacci[1] =1;

	        for(int i=2; i<= max; i++){
	            fibonacci[i] = (fibonacci[i-1] + fibonacci[i-2]) % (1 << 30);
	            // we want to find the result of "a number modulo 2^P"
	            // if we first let the number modulo 2^Q (Q > P) 
	            // then, modulo 2^P, the esult is the same.
	            // So, "we first modulo 2^30" to avoid overflow
	            // where, 2^30 == 1 << 30 
	        }
	        
	        // to find "results"
	        int[] results = new int[L];
	        
	        for(int i=0; i<L; i++){
	            results[i] = fibonacci[A[i]] % (1 << B[i]); // where, "1 << B[i]" means 2^B[i]
	        }
	        
	        return results;
	    }
}
