package org.ict.algorithm.leetcode.math;



import sun.java2d.SunGraphics2D;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * You are given a function called draw_point(x, y),
 * which can draw a point on the screen, with location x, y
 * Please complete a function called draw_circle(x, y, r),
 * this function should draw a circle on the screen, with x,y be the center, r be the radius.
 * Note that: x, y, r are all positive integers
 * What if this code is running on a very simplified cpu,
 * and you can not use math function like sqrt, sin, cos, etc?
 *
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

    public void drawPoint(int x, int y) {
        // no need to implement
        System.out.print(".");
    }


    public void drawCircleLineV3(int x, int y, int r) {
        //todo
    }

    /**
     * Bresenham’s circle drawing algorithm
     * @see <a href="https://www.geeksforgeeks.org/bresenhams-circle-drawing-algorithm/#"></a>
     * @see <a href="https://stackoverflow.com/questions/35383299/bresenham-circle-drawing-algorithm-implementation-in-java"></a>
     */
    public void drawCircleLineV3(int radius, int centerX, int centerY, Graphics g) {
        int y = radius;
        int x = 0;

        int delta = calculateStartDelta(radius);
        while (y >= x) {
            drawPixelAndReflect(centerX, centerY, x, y, g);
            if (delta < 0) {
                delta = calculateDeltaForHorizontalPixel(delta, x);
            } else {
                delta = calculateDeltaForDiagonalPixel(delta, x, y);
                y--;
            }
            x++;
        }
    }

    private static int calculateStartDelta(int radius) {
        return 3 - 2 * radius;
    }

    private static int calculateDeltaForHorizontalPixel(int oldDelta, int x) {
        return oldDelta + 4 * x + 6;
    }

    private static int calculateDeltaForDiagonalPixel(int oldDelta, int x, int y) {
        return oldDelta + 4 * (x - y) + 10;
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


    public void drawCircleLineV2(int x, int y, int r) {
        for (int row = x * r - r; row <= x * r + r; row++) {
            int min = Integer.MAX_VALUE;
            Map<Integer, int[]> map = new HashMap<>();
            for (int col = y * r - r; col <= y * r + r; col++) {
                int dd = (row - x) * (row - x) + (col - y) * (col - y);
                int dxy = x * x + y * y;
                int abs = Math.abs(dd - dxy);
                if (abs < min) {
                    min = abs;
                }
                map.put(abs, new int[] {row, col});
            }
            int[] point = map.get(min);

        }
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
