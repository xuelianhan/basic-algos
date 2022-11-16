package org.ict.algorithm.leetcode.binarysearch;

/**
 * We are playing the Guess Game. The game is as follows:
 *
 * I pick a number from 1 to n. You have to guess which number I picked.
 *
 * Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.
 *
 * You call a pre-defined API int guess(int num), which returns three possible results:
 *
 * -1: Your guess is higher than the number I picked (i.e. num > pick).
 * 1: Your guess is lower than the number I picked (i.e. num < pick).
 * 0: your guess is equal to the number I picked (i.e. num == pick).
 * Return the number that I picked.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 10, pick = 6
 * Output: 6
 * Example 2:
 *
 * Input: n = 1, pick = 1
 * Output: 1
 * Example 3:
 *
 * Input: n = 2, pick = 1
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2^31 - 1
 * 1 <= pick <= n
 * @author sniper
 * @date 16 Nov, 2022
 * LC374
 */
public class GuessNumberHigherOrLower {

    /**
     * Forward declaration of guess API.
     * @param num your guess
     * @return -1 if num is higher than the picked number,
     * 1 if num is lower than the picked number,
     * otherwise return 0
     * int guess(int num);
     */
    public int guess(int num) {
        return -1;
        //return 1;
        //return 0;
    }

    /**
     * Right Answer.
     * To do binary search, sometimes I see people use
     *
     * mid = (low + high) / 2;
     * Sometimes I see
     *
     * mid = low + (high - low) / 2;
     * mid will at most diff 1. What is the difference between these two approaches?
     *
     * Here is my code, it works with "low + (high - low)" but TLE with (low + high) / 2.
     * @author reggiezhou
     * @param n
     * @return
     */
    public int guessNumberV3(int n) {
        int low = 1, high = n;
        int mid = low + (high - low) / 2;
        int guess = 0;
        while ((guess = (guess(mid))) != 0) {
            if (guess > 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
            mid = low + (high - low) / 2;
        }
        return mid;
    }

    /**
     * Wrong answer.
     * @param n
     * @return
     */
    public int guessNumberV2(int n) {
        int low = 1, high = n;
        while (low < high) {
            int mid = low + (high - low)/2;
            if (guess(mid) == 0)  {
                return mid;
            } else if (guess(mid) == 1) {
                high = mid - 1;
            } else {
                low = mid;
            }
        }
        return low;
    }


    /**
     * Time Limit Exceed.
     * @param n
     * @return
     */
    public int guessNumberV1(int n) {
        int low = 1, high = n;
        while (low < high) {
            int mid = (high + low)/2;
            if (guess(mid) == 0)  {
                return mid;
            } else if (guess(mid) == 1) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    /**
     * It's OK
     * @param n
     * @return
     */
    public int guessNumber(int n) {
        int low = 1, high = n;
        while (low < high) {
            int mid = low + (high - low)/2;
            if (guess(mid) == 0)  {
                return mid;
            } else if (guess(mid) == 1) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

}
