package org.ict.algorithm.leetcode.math;

import java.awt.*;
/**
 * You are given a function called draw_point(x, y),
 * which can draw a point on the screen, with location x, y
 * Please complete a function called draw_circle(x, y, r),
 * this function should draw a circle on the screen, with x,y be the center, r be the radius.
 * Note that: x, y, r are all positive integers
 * What if this code is running on a very simplified cpu,
 * and you can not use math function like sqrt, sin, cos, etc?
 *
 * @see <a href="https://arcade.makecode.com/graphics-math/bresenham-circle"></a>
 * @see <a href="https://www.geeksforgeeks.org/draw-circle-without-floating-point-arithmetic/"></a>
 * @see <a href="https://codingface.com/print-a-circle-pattern-in-java/"></a>
 * @author sniper
 * @date 24 Jul 2023
 */
public class DrawCircle {

    public static void main(String[] args) {
        DrawCircle instance = new DrawCircle();
        //instance.drawCirclePie(0, 0, 2);
        //instance.drawCircle(4);
        instance.drawCircleLineV1(0, 0, 100);
    }

    public void drawCircleLineV3(int xc, int yc, int r) {
        int x = 0;
        int y = r;
        double d = 1.25 - r;
        /**
         * Eight directions including diagonal-line
         */
        int[][] dirs = new int[][]{{1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
        printCircle(xc, yc, x, y, dirs);
        while (x < y) {
            /**
             * For each pixel we will draw all eight pixels
             */
            x++;
            /**
             * Check for decision parameter and corresponding y, update d, x and y.
             */
            if (d > 0) {
                d = d + 2 * (x - y) + 5;
                y--;
            } else {
                d = d + 2 * x + 3;
            }
            printCircle(xc, yc, x, y, dirs);
        }
    }

    public void drawPoint(int x, int y) {
        // no need to implement
        System.out.print(".");
    }

    /**
     * We have already discussed the Mid-Point circle drawing algorithm in our previous post.
     * In this post we will consider the Bresenham’s circle drawing algorithm.
     *
     * Both of these algorithms uses the key feature of circle that it is highly symmetric.
     * So, for whole 360 degree of circle we will divide it in 8-parts each octant of 45 degree.
     * In order to do that we will use Bresenham’s Circle Algorithm for calculation of the locations
     * of the pixels in the first octant of 45 degrees.
     * It assumes that the circle is centered on the origin. So for every pixel (x, y) it calculates,
     * we draw a pixel in each of the 8 octants of the circle as shown below :
     * <img href="https://makecode.trafficmanager.cn/blob/49f2ae0d24366ba7f84cc53329afd7c3dc422bc0/static/graphics-math/bresenham-circle/decision-points.jpg">
     *
     * circle 1
     *
     * Now, we will see how to calculate the next pixel location from a previously known pixel location (x, y).
     * In Bresenham’s algorithm at any point (x, y) we have two options
     * either to choose the next pixel in the east i.e. (x+1, y) or in the south east i.e. (x+1, y-1).
     *
     *
     * circle 2
     *
     * And this can be decided by using the decision parameter d as:
     *
     *
     * If d > 0, then (x+1, y-1) is to be chosen as the next pixel as it will be closer to the arc.
     * else (x+1, y) is to be chosen as next pixel.
     * Now to draw the circle for a given radius ‘r’ and centre (xc, yc)
     * We will start from (0, r) and move in first quadrant till x=y (i.e. 45 degree).
     * We should start from listed initial condition:
     *
     *
     * d = 3 - (2 * r)
     * x = 0
     * y = r
     * Now for each pixel, we will do the following operations:
     *
     * Set initial values of (xc, yc) and (x, y)
     * Set decision parameter d to d = 3 – (2 * r).
     *
     * call drawCircle(int xc, int yc, int x, int y) function.
     * Repeat steps 5 to 8 until x < = y
     * Increment value of x.
     * If d < 0, set d = d + (4*x) + 6
     * Else, set d = d + 4 * (x – y) + 10 and decrement y by 1.
     * call drawCircle(int xc, int yc, int x, int y) function
     * @param xc
     * @param yc
     * @param r
     */
    public void drawCircleLineV2(int xc, int yc, int r) {
        int x = 0;
        int y = r;
        int d = 3 - 2 * r;
        /**
         * Eight directions including diagonal-line
         */
        int[][] dirs = new int[][]{{1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
        printCircle(xc, yc, x, y, dirs);
        while (y >= x) {
            /**
             * For each pixel we will draw all eight pixels
             */
            x++;
            /**
             * Check for decision parameter and corresponding y, update d, x and y.
             */
            if (d > 0) {
                y--;
                d = d + 4 * (x - y) + 10;
            } else {
                d = d + 4 * x + 6;
            }
            printCircle(xc, yc, x, y, dirs);
        }
    }

    private void printCircle(int xc, int yc, int x, int y, int[][] dirs) {
        for (int i = 0; i < dirs.length; i++) {
            int[] d = dirs[i];
            drawPoint(xc + d[0] * x, yc + d[1] * y);
        }
        for (int i = 0; i < dirs.length; i++) {
            int[] d = dirs[i];
            drawPoint(xc + d[0] * y, yc + d[1] * x);
        }
    }

    /**
     * Bresenham’s circle drawing algorithm
     * @see <a href="https://www.geeksforgeeks.org/bresenhams-circle-drawing-algorithm/#"></a>
     * @see <a href="https://stackoverflow.com/questions/35383299/bresenham-circle-drawing-algorithm-implementation-in-java"></a>
     */
    public void drawCircleLineV3(int radius, int centerX, int centerY, Graphics g) {
        int y = radius;
        int x = 0;

        int delta = 3 - 2 * radius;
        while (y >= x) {
            drawPixelAndReflect(centerX, centerY, x, y, g);
            if (delta < 0) {
                delta = delta + 4 * x + 6;
            } else {
                delta = delta + 4 * (x - y) + 10;
                y--;
            }
            x++;
        }
    }

    private static void drawPixelAndReflect(int centerX, int centerY, int x, int y, Graphics g) {
        g.drawLine(centerX + x, centerY + y, centerX + x, centerY + y);
        g.drawLine(centerX + x, centerY - y, centerX + x, centerY - y);
        g.drawLine(centerX - x, centerY + y, centerX - x, centerY + y);
        g.drawLine(centerX - x, centerY - y, centerX - x, centerY - y);

        g.drawLine(centerX - y, centerY + x, centerX - y, centerY + x);
        g.drawLine(centerX - y, centerY - x, centerX - y, centerY - x);
        g.drawLine(centerX + y, centerY + x, centerX + y, centerY + x);
        g.drawLine(centerX + y, centerY - x, centerX + y, centerY - x);
    }


    /**
     * Thinking Process:
     * if (x, y) == (0, 0), -r <= x <= r, -r <= y <= r
     * if (x, y) == (1, 1), 0 <= x <= 2r, 0 <= y <= r
     * if (x, y) == (2, 1), r <= x <= 3r, 0 <= y <= 2r
     * if (x, y)==(-1, -1),-2r <= x <= 0, -2r <= y <= 0
     * So:
     * x * r - r <= row <= x * r + r
     * y * r - r <= col <= y * r + r
     * if distance from (row, col) to (x, y) between (r-0.5, r+0.5)
     *   print this point(row, col)
     * otherwise
     *  print space
     *
     * @param x
     * @param y
     * @param r
     */
    public void drawCircleLineV1(int x, int y, int r) {
        for (int row = x * r - r; row <= x * r + r; row++) {
            for (int col = y * r - r; col <= y * r + r; col++) {
                int dd = (row - x) * (row - x) + (col - y) * (col - y);
                /**
                 * Because don't allow to use Math.sqrt, so we
                 * use our sqrt instead.
                 */
                //double d = Math.sqrt(dd);
                int d = mySqrtV3(dd);
                if (d > r - 0.5 && d < r + 0.5) {
                    System.out.print(".");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static int mySqrtV3(int a) {
        long x = a;
        while (x * x > a) {
            x = (x + a / x) >> 1;
        }
        return (int) x;
    }

    public void drawCircleLine(int x, int y, int r) {
        double distance = 0.0;
        for (int i = 0; i <= 2 * r; i++) {
            for (int j = 0; j < 2 * r; j++) {
                distance = Math.sqrt((i - r) * (i - r) + (j - r) * (j - r));
                /**
                 * Check whether distance is in the range of (radius - 0.5) and (radius + 0.5)
                 * or not to print *
                 */
                if (distance > r - 0.5 && distance < r + 0.5) {
                    System.out.print(".");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public void drawCirclePie(int x, int y, int r) {
        /**
         * Consider a rectangle of size N*N
         */
        int n = 2 * r + 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                /**
                 * Start from the left most corner point
                 */
                x = i - r;
                y = j - r;
                /**
                 *  If this point is inside the circle, print it
                 */
                if (x * x + y * y <= r * r + 1) {
                    drawPoint(x, y);
                } else {
                    /**
                     * If outside the circle, print space
                     */
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }


    public void drawCirclePie(int r) {
        /**
         * Consider a rectangle of size N*N
         */
        int n = 2 * r + 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                /**
                 * Start from the left most corner point
                 */
                int x = i - r;
                int y = j - r;
                /**
                 *  If this point is inside the circle, print it
                 */
                if (x * x + y * y <= r * r + 1) {
                    drawPoint(x, y);
                } else {
                    /**
                     * If outside the circle, print space
                     */
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
