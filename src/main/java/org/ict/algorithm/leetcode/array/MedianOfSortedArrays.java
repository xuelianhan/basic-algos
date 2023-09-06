package org.ict.algorithm.leetcode.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * p4
 * tag: array
 *
 * $ javac org/ict/algorithm/leetcode/MedianSortedArrays.java 
 * $ java org/ict/algorithm/leetcode/MedianSortedArrays
 * nums1:[1, 2], nums2:[3, 4]
 * median:2.5
 *
 * To solve this problem, we need to understand “What is the use of median” 
 * In statistics, the median is used for dividing a set into two equal length subsets, 
 * that one subset is always greater than the other
 * If we understand the use of median for dividing, we are very close to the answer
 * First let’s cut A into two parts at a random position i:
 * 
 *       left_A             |        right_A
 * A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
 * Since A has m elements, so there are m+1 kinds of cutting( i = 0 ~ m ) 
 * And we know: len(left_A) = i, len(right_A) = m - i 
 * Note: when i = 0 , left_A is empty, and when i = m , right_A is empty
 * 
 * With the same way, cut B into two parts at a random position j:
 * 
 *       left_B             |        right_B
 * B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
 * Put left_A and left_B into one set, and put right_A and right_B into another set
 * Let’s name them left_part and right_part :
 * 
 *       left_part          |        right_part
 * A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
 * B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
 * If we can ensure:
 * 
 * 1) len(left_part) == len(right_part)
 * 2) max(left_part) <= min(right_part)
 * then we divide all elements in {A, B} into two parts with equal length, and one part is always greater than the other
 * Then median = (max(left_part) + min(right_part))/2.
 * 
 * To ensure these two conditions, we just need to ensure:
 * 
 * (1) i + j == m - i + n - j (or: m - i + n - j + 1)
 *     if n >= m, we just need to set: i = 0 ~ m, j = (m + n + 1)/2 - i
 * (2) B[j-1] <= A[i] and A[i-1] <= B[j]
 * ps.1 For simplicity, I presume A[i-1],B[j-1],A[i],B[j] are always valid even if i=0/i=m/j=0/j=n . 
 * I will talk about how to deal with these edge values at last.
 * 
 * ps.2 Why n >= m? Because I have to make sure j is non-nagative since 0 <= i <= m and j = (m + n + 1)/2 - i. 
 * If n < m , then j may be nagative, that will lead to wrong result.
 * 
 * So, all we need to do is:
 * 
 * Searching i in [0, m], to find an object `i` that:
 *     B[j-1] <= A[i] and A[i-1] <= B[j], ( where j = (m + n + 1)/2 - i )
 * And we can do a binary search following steps described below:
 * 
 * <1> Set imin = 0, imax = m, then start searching in [imin, imax]
 * 
 * <2> Set i = (imin + imax)/2, j = (m + n + 1)/2 - i
 * 
 * <3> Now we have len(left_part)==len(right_part). And there are only 3 situations
 *      that we may encounter:
 *     <a> B[j-1] <= A[i] and A[i-1] <= B[j]
 *         Means we have found the object `i`, so stop searching.
 *     <b> B[j-1] > A[i]
 *         Means A[i] is too small. We must `ajust` i to get `B[j-1] <= A[i]`.
 *         Can we `increase` i?
 *             Yes. Because when i is increased, j will be decreased.
 *             So B[j-1] is decreased and A[i] is increased, and `B[j-1] <= A[i]` may
 *             be satisfied.
 *         Can we `decrease` i?
 *             `No!` Because when i is decreased, j will be increased.
 *             So B[j-1] is increased and A[i] is decreased, and B[j-1] <= A[i] will
 *             be never satisfied.
 *         So we must `increase` i. That is, we must ajust the searching range to
 *         [i+1, imax]. So, set imin = i+1, and goto <2>.
 *     <c> A[i-1] > B[j]
 *         Means A[i-1] is too big. And we must `decrease` i to get `A[i-1]<=B[j]`.
 *         That is, we must ajust the searching range to [imin, i-1].
 *         So, set imax = i-1, and goto <2>.
 * When the object i is found, the median is:
 * 
 * max(A[i-1], B[j-1]) (when m + n is odd)
 * or (max(A[i-1], B[j-1]) + min(A[i], B[j]))/2 (when m + n is even)
 * Now let’s consider the edges values i=0,i=m,j=0,j=n where A[i-1],B[j-1],A[i],B[j] may not exist. 
 * Actually this situation is easier than you think.
 * 
 * What we need to do is ensuring that max(left_part) <= min(right_part). 
 * So, if i and j are not edges values(means A[i-1],B[j-1],A[i],B[j] all exist), 
 * then we must check both B[j-1] <= A[i] and A[i-1] <= B[j].
 * But if some of A[i-1],B[j-1],A[i],B[j] don’t exist, then we don’t need to check one(or both) of these two conditions. 
 * For example, if i=0, then A[i-1] doesn’t exist, then we don’t need to check A[i-1] <= B[j]. So, what we need to do is:
 * 
 * Searching i in [0, m], to find an object `i` that:
 *     (j == 0 or i == m or B[j-1] <= A[i]) and
 *     (i == 0 or j == n or A[i-1] <= B[j])
 *     where j = (m + n + 1)/2 - i
 * And in a searching loop, we will encounter only three situations:
 * 
 * <a> (j == 0 or i == m or B[j-1] <= A[i]) and
 *     (i == 0 or j = n or A[i-1] <= B[j])
 *     Means i is perfect, we can stop searching.
 * 
 * <b> j > 0 and i < m and B[j - 1] > A[i]
 *     Means i is too small, we must increase it.
 * 
 * <c> i > 0 and j < n and A[i - 1] > B[j]
 *     Means i is too big, we must decrease it.
 * Thank @Quentin.chen , him pointed out that: i < m ==> j > 0 and i > 0 ==> j < n . Because:
 * 
 * m <= n, i < m ==> j = (m+n+1)/2 - i > (m+n+1)/2 - m >= (2*m+1)/2 - m >= 0    
 * m <= n, i > 0 ==> j = (m+n+1)/2 - i < (m+n+1)/2 <= (2*n+1)/2 <= n
 * So in situation <b> and <c>, we don’t need to check whether j > 0 and whether j < n.
 *
 *-------------------------------------------------------------------------------------
 *
stellari
2580
Last Edit: October 23, 2018 1:26 AM

128.0K VIEWS

This problem is notoriously hard to implement due to all the corner cases. Most implementations consider odd-lengthed and even-lengthed arrays as two different cases and treat them separately. As a matter of fact, with a little mind twist. These two cases can be combined as one, leading to a very simple solution where (almost) no special treatment is needed.

First, let's see the concept of 'MEDIAN' in a slightly unconventional way. That is:

"if we cut the sorted array to two halves of EQUAL LENGTHS, then
median is the AVERAGE OF Max(lower_half) and Min(upper_half), i.e. the
two numbers immediately next to the cut".

For example, for [2 3 5 7], we make the cut between 3 and 5:

[2 3 / 5 7]
then the median = (3+5)/2. Note that I'll use '/' to represent a cut, and (number / number) to represent a cut made through a number in this article.

for [2 3 4 5 6], we make the cut right through 4 like this:

[2 3 (4/4) 5 7]

Since we split 4 into two halves, we say now both the lower and upper subarray contain 4. This notion also leads to the correct answer: (4 + 4) / 2 = 4;

For convenience, let's use L to represent the number immediately left to the cut, and R the right counterpart. In [2 3 5 7], for instance, we have L = 3 and R = 5, respectively.

We observe the index of L and R have the following relationship with the length of the array N:

N        Index of L / R
1               0 / 0
2               0 / 1
3               1 / 1  
4               1 / 2      
5               2 / 2
6               2 / 3
7               3 / 3
8               3 / 4
It is not hard to conclude that index of L = (N-1)/2, and R is at N/2. Thus, the median can be represented as

(L + R)/2 = (A[(N-1)/2] + A[N/2])/2
To get ready for the two array situation, let's add a few imaginary 'positions' (represented as #'s) in between numbers, and treat numbers as 'positions' as well.

[6 9 13 18]  ->   [# 6 # 9 # 13 # 18 #]    (N = 4)
position index     0 1 2 3 4 5  6 7  8     (N_Position = 9)
		  
[6 9 11 13 18]->   [# 6 # 9 # 11 # 13 # 18 #]   (N = 5)
position index      0 1 2 3 4 5  6 7  8 9 10    (N_Position = 11)
As you can see, there are always exactly 2*N+1 'positions' regardless of length N. Therefore, the middle cut should always be made on the Nth position (0-based). Since index(L) = (N-1)/2 and index(R) = N/2 in this situation, we can infer that index(L) = (CutPosition-1)/2, index(R) = (CutPosition)/2.

Now for the two-array case:

A1: [# 1 # 2 # 3 # 4 # 5 #]    (N1 = 5, N1_positions = 11)

A2: [# 1 # 1 # 1 # 1 #]     (N2 = 4, N2_positions = 9)
Similar to the one-array problem, we need to find a cut that divides the two arrays each into two halves such that

"any number in the two left halves" <= "any number in the two right
halves".

We can also make the following observations：

There are 2N1 + 2N2 + 2 position altogether. Therefore, there must be exactly N1 + N2 positions on each side of the cut, and 2 positions directly on the cut.

Therefore, when we cut at position C2 = K in A2, then the cut position in A1 must be C1 = N1 + N2 - k. For instance, if C2 = 2, then we must have C1 = 4 + 5 - C2 = 7.

 [# 1 # 2 # 3 # (4/4) # 5 #]    

 [# 1 / 1 # 1 # 1 #]   
When the cuts are made, we'd have two L's and two R's. They are

 L1 = A1[(C1-1)/2]; R1 = A1[C1/2];
 L2 = A2[(C2-1)/2]; R2 = A2[C2/2];
In the above example,

    L1 = A1[(7-1)/2] = A1[3] = 4; R1 = A1[7/2] = A1[3] = 4;
    L2 = A2[(2-1)/2] = A2[0] = 1; R2 = A1[2/2] = A1[1] = 1;
Now how do we decide if this cut is the cut we want? Because L1, L2 are the greatest numbers on the left halves and R1, R2 are the smallest numbers on the right, we only need

L1 <= R1 && L1 <= R2 && L2 <= R1 && L2 <= R2
to make sure that any number in lower halves <= any number in upper halves. As a matter of fact, since
L1 <= R1 and L2 <= R2 are naturally guaranteed because A1 and A2 are sorted, we only need to make sure:

L1 <= R2 and L2 <= R1.

Now we can use simple binary search to find out the result.

If we have L1 > R2, it means there are too many large numbers on the left half of A1, then we must move C1 to the left (i.e. move C2 to the right); 
If L2 > R1, then there are too many large numbers on the left half of A2, and we must move C2 to the left.
Otherwise, this cut is the right one. 
After we find the cut, the medium can be computed as (max(L1, L2) + min(R1, R2)) / 2;
Two side notes:

A. Since C1 and C2 can be mutually determined from each other, we can just move one of them first, then calculate the other accordingly. However, it is much more practical to move C2 (the one on the shorter array) first. The reason is that on the shorter array, all positions are possible cut locations for median, but on the longer array, the positions that are too far left or right are simply impossible for a legitimate cut. For instance, [1], [2 3 4 5 6 7 8]. Clearly the cut between 2 and 3 is impossible, because the shorter array does not have that many elements to balance out the [3 4 5 6 7 8] part if you make the cut this way. Therefore, for the longer array to be used as the basis for the first cut, a range check must be performed. It would be just easier to do it on the shorter array, which requires no checks whatsoever. Also, moving only on the shorter array gives a run-time complexity of O(log(min(N1, N2))) (edited as suggested by @baselRus)

B. The only edge case is when a cut falls on the 0th(first) or the 2Nth(last) position. For instance, if C2 = 2N2, then R2 = A2[2*N2/2] = A2[N2], which exceeds the boundary of the array. To solve this problem, we can imagine that both A1 and A2 actually have two extra elements, INT_MAX at A[-1] and INT_MAX at A[N]. These additions don't change the result, but make the implementation easier: If any L falls out of the left boundary of the array, then L = INT_MIN, and if any R falls out of the right boundary, then R = INT_MAX.

I know that was not very easy to understand, but all the above reasoning eventually boils down to the following concise code:

 double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
    int N1 = nums1.size();
    int N2 = nums2.size();
    if (N1 < N2) return findMedianSortedArrays(nums2, nums1);	// Make sure A2 is the shorter one.
    
    int lo = 0, hi = N2 * 2;
    while (lo <= hi) {
        int mid2 = (lo + hi) / 2;   // Try Cut 2 
        int mid1 = N1 + N2 - mid2;  // Calculate Cut 1 accordingly
        
        double L1 = (mid1 == 0) ? INT_MIN : nums1[(mid1-1)/2];	// Get L1, R1, L2, R2 respectively
        double L2 = (mid2 == 0) ? INT_MIN : nums2[(mid2-1)/2];
        double R1 = (mid1 == N1 * 2) ? INT_MAX : nums1[(mid1)/2];
        double R2 = (mid2 == N2 * 2) ? INT_MAX : nums2[(mid2)/2];
        
        if (L1 > R2) lo = mid2 + 1;		// A1's lower half is too big; need to move C1 left (C2 right)
        else if (L2 > R1) hi = mid2 - 1;	// A2's lower half too big; need to move C2 left.
        else return (max(L1,L2) + min(R1, R2)) / 2;	// Otherwise, that's the right cut.
    }
    return -1;
}
 If you have any suggestions to make the logic and implementation even more cleaner. Please do let me know!
 *
 *
 * LC4, Hard, frequency=105
 *
 */
public class MedianOfSortedArrays {

    /**
     * Time Cost 2ms
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArraysV3(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] aux = new int[m + n];
        int i = 0;
        int j = 0;
        int k = 0;
        for (; i < m && j < n; k++) {
            if (nums1[i] <= nums2[j]) {
                aux[k] = nums1[i++];
            } else {
                aux[k] = nums2[j++];
            }
        }

        while (i < m) {
            aux[k++] = nums1[i++];
        }
        while (j < n) {
            aux[k++] = nums2[j++];
        }

        int lo = 0;
        int hi = m + n - 1;
        int mid = lo + (hi - lo) / 2;
        double res = 0;
        if ((m + n) % 2 == 0) {
            res = (aux[mid] + aux[mid + 1]) / 2.0;
        } else {
            res = aux[mid];
        }
        return res;
    }

    /**
     * Max-heap small has the smaller half of the numbers.
     */
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

    /**
     * Min-heap large has the larger half of the numbers.
     */
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    private int size = 0;

    public void addNum(int num) {
        if (size % 2 == 0) {
            minHeap.add(num);
            maxHeap.add(minHeap.poll());
        } else {
            maxHeap.add(num);
            minHeap.add(maxHeap.poll());
        }
        size++;
    }

    public double findMedian() {
        double res;
        if (size % 2 == 0) {
            res = (minHeap.peek() + maxHeap.peek()) / 2.0;
        } else {
            res = maxHeap.peek();
        }
        return res;
    }

    /**
     * The Easiest Solution
     * @author xueliansniper
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArraysV2(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int i = 0;
        int j = 0;
        for (; i < m && j < n;) {
            if (nums1[i] <= nums2[j]) {
                addNum(nums1[i]);
                i++;
            } else {
                addNum(nums2[j]);
                j++;
            }
        }
        while (i < m) {
            addNum(nums1[i]);
            i++;
        }
        while (j < n) {
            addNum(nums2[j]);
            j++;
        }
        return findMedian();
    }
	
	public double findMedianSortedArraysV1(int[] nums1, int[] nums2) {
		int N1 = nums1.length;
		int N2 = nums2.length;
		if (N1 < N2) {
			return findMedianSortedArraysV1(nums2, nums1);	// Make sure A2 is the shorter one.
		}
		int lo = 0, hi = N2 * 2;
	    while (lo <= hi) {
	        int mid2 = (lo + hi) / 2;   // Try Cut 2 
	        int mid1 = N1 + N2 - mid2;  // Calculate Cut 1 accordingly
	        
	        double L1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[(mid1-1)/2];	// Get L1, R1, L2, R2 respectively
	        double L2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[(mid2-1)/2];
	        double R1 = (mid1 == N1 * 2) ? Integer.MAX_VALUE : nums1[(mid1)/2];
	        double R2 = (mid2 == N2 * 2) ? Integer.MAX_VALUE : nums2[(mid2)/2];
	        
	        if (L1 > R2) lo = mid2 + 1;		// A1's lower half is too big; need to move C1 left (C2 right)
	        else if (L2 > R1) hi = mid2 - 1;	// A2's lower half too big; need to move C2 left.
	        else return (Math.max(L1,L2) + Math.min(R1, R2)) / 2;	// Otherwise, that's the right cut.
	    }
	    return -1;
	}

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }
        if (n == 0) {
            throw new IllegalArgumentException("nums2 must not be empty!");
        }
        int i = 0, j = 0, imin = 0, imax = m, halfLen = (m + n + 1)/2;
        double maxOfLeft = 0.0d;
        double minOfRight = 0.0d;
        
        while (imin <= imax) {
            i = (imin + imax) / 2;    
            j = halfLen - i;
            if ((j > 0) && (i < m) && (nums2[j-1] > nums1[i])) {
                // Notice: i is too small, must increase it
                // Don't write as imax
                imin = i + 1;
            } else if ((i > 0) && (j < n) && (nums1[i-1] > nums2[j])) {
                // Notice: i is too big, must decrease it
                // Don't write as imin
                imax = i - 1;
            } else {
                // i is perfect
                if (i == 0) {
                    maxOfLeft = nums2[j-1];    
                } else if (j == 0) {
                    maxOfLeft = nums1[i-1];
                } else {
                    maxOfLeft = Math.max(nums1[i-1], nums2[j-1]);
                }
                // find the i, should break here
                // Don't forget this break!
                break;
            }
        }//end while-loop

        if ((m + n) % 2 == 1) {
            return maxOfLeft;
        }

        if (i == m) {
            minOfRight = nums2[j];
        } else if (j == n) {
            minOfRight = nums1[i];
        } else {
            minOfRight = Math.min(nums1[i], nums2[j]);
        }
        return (maxOfLeft + minOfRight) / 2.0d;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        System.out.println("nums1:" + Arrays.toString(nums1) + ", nums2:" + Arrays.toString(nums2));
        double median = findMedianSortedArrays(nums1, nums2); 
        System.out.println("median:" + median);
    }
}

