package org.ict.algorithm.leetcode.design;

import java.util.ArrayList;
import java.util.List;

/**
 * Design an iterator to flatten a 2D vector.
 * It should support the next and hasNext operations.
 *
 * Implement the Vector2D class:
 *
 * Vector2D(int[][] vec) initializes the object with the 2D vector vec.
 * next() returns the next element from the 2D vector and moves the pointer one step forward.
 * You may assume that all the calls to next are valid.
 * hasNext() returns true if there are still some elements in the vector, and false otherwise.
 *
 *
 * Example 1:
 *
 * Input
 * ["Vector2D", "next", "next", "next", "hasNext", "hasNext", "next", "hasNext"]
 * [[[[1, 2], [3], [4]]], [], [], [], [], [], [], []]
 * Output
 * [null, 1, 2, 3, true, true, 4, false]
 *
 * Explanation
 * Vector2D vector2D = new Vector2D([[1, 2], [3], [4]]);
 * vector2D.next();    // return 1
 * vector2D.next();    // return 2
 * vector2D.next();    // return 3
 * vector2D.hasNext(); // return True
 * vector2D.hasNext(); // return True
 * vector2D.next();    // return 4
 * vector2D.hasNext(); // return False
 *
 *
 * Constraints:
 *
 * 0 <= vec.length <= 200
 * 0 <= vec[i].length <= 500
 * -500 <= vec[i][j] <= 500
 * At most 10^5 calls will be made to next and hasNext.
 *
 *
 * Follow up: As an added challenge, try to code it using only iterators in C++ or iterators in Java.
 * @author sniper
 * @date 20 Jun 2023
 * LC251, Medium, frequency=9
 */
public class Flatten2DVector {

    /**
     * Iterator solution
     * --------------------------
     * class Vector2D {
     *  public:
     *   Vector2D(vector<vector<int>>& v) {
     *     i = v.begin();
     *     iEnd = v.end();
     *   }
     *
     *   int next() {
     *     moveIterator();
     *     return (*i)[j++];
     *   }
     *
     *   bool hasNext() {
     *     moveIterator();
     *     return i != iEnd;
     *   }
     *
     *  private:
     *   // (*i)[j] := current pointed value
     *   vector<vector<int>>::iterator i, iEnd;
     *   int j = 0;
     *
     *   void moveIterator() {
     *     while (i != iEnd && j == (*i).size())
     *       ++i, j = 0;
     *   }
     * };
     */
    static class Vector2DV3 {
        public Vector2DV3(int[][] vec) {
            for (int[] arr : vec) {
                for (int a : arr) {
                    this.vec.add(a);
                }
            }
        }

        public int next() {
            return vec.iterator().next();
        }

        public boolean hasNext() {
            return vec.iterator().hasNext();
        }

        private List<Integer> vec = new ArrayList<>();
    }


    /**
     * Two-Pointer Solution
     */
    static class Vector2DV2 {
        public Vector2DV2(int[][] vec) {
            this.vec = vec;
        }

        public int next() {
            hasNext();
            return vec[i][j++];
        }

        public boolean hasNext() {
            while (i < vec.length && j == vec[i].length) {
                i++;
                j = 0;
            }
            return i < vec.length;
        }

        /**
         * The i represents the row
         */
        private int i = 0;
        /**
         * The j represents the column
         */
        private int j = 0;

        private int[][] vec;
    }


    /**
     * Two-Pointer Solution
     * -------------------------
     * class Vector2D:
     *     def __init__(self, vec: List[List[int]]):
     *         self.i = 0
     *         self.j = 0
     *         self.vec = vec
     *
     *     def next(self) -> int:
     *         self.forward()
     *         res = self.vec[self.i][self.j]
     *         self.j += 1
     *         return res
     *
     *     def hasNext(self) -> bool:
     *         self.forward()
     *         return self.i < len(self.vec)
     *
     *     def forward(self):
     *         while self.i < len(self.vec) and self.j >= len(self.vec[self.i]):
     *             self.i += 1
     *             self.j = 0
     */
    static class Vector2DV1 {
        public Vector2DV1(int[][] vec) {
            this.vec = vec;
        }

        public int next() {
            forward();
            return vec[i][j++];
        }

        public boolean hasNext() {
            forward();
            return i < vec.length;
        }

        private void forward() {
            while (i < vec.length && j >= vec[i].length) {
                i++;
                j = 0;
            }
        }

        /**
         * The i represents the row
         */
        private int i = 0;
        /**
         * The j represents the column
         */
        private int j = 0;

        private int[][] vec;
    }

    /**
     * Understanding the following solution
     * Index + Array Solution
     * -----------------------------
     * class Vector2D:
     *   def __init__(self, vec: List[List[int]]):
     *     self.vec = []
     *     self.i = 0
     *
     *     for A in vec:
     *       self.vec += A
     *
     *   def next(self) -> int:
     *     ans = self.vec[self.i]
     *     self.i += 1
     *     return ans
     *
     *   def hasNext(self) -> bool:
     *     return self.i < len(self.vec)
     * -----------------------------
     * class Vector2D {
     *  public:
     *   Vector2D(vector<vector<int>>& vec) {
     *     for (const vector<int>& A : vec)
     *       for (const int a : A)
     *         this->vec.push_back(a);
     *   }
     *
     *   int next() {
     *     return vec[i++];
     *   }
     *
     *   bool hasNext() {
     *     return i < vec.size();
     *   }
     *
     *  private:
     *   vector<int> vec;
     *   int i = 0;
     * };
     */
    static class Vector2D {

        public Vector2D(int[][] vec) {
            for (int[] arr : vec) {
                for (int a : arr) {
                    this.vec.add(a);
                }
            }
        }

        public int next() {
            return vec.get(i++);
        }

        public boolean hasNext() {
            return i < vec.size();
        }

        private int i = 0;
        private List<Integer> vec = new ArrayList<>();
    }
}
