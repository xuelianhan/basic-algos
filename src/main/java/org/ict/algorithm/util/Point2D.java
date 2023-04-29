package org.ict.algorithm.util;

import java.util.Comparator;

/**
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public final class Point2D implements Comparable<Point2D> {
   
	public static final Comparator<Point2D> X_ORDER = new XOrder();
	    
    public static final Comparator<Point2D> Y_ORDER = new YOrder();
    
    public static final Comparator<Point2D> R_ORDER = new ROrder();

    private final double x;

    private final double y;
    

    public Point2D(double x, double y) {
        if (Double.isInfinite(x) || Double.isInfinite(y)) {
            throw new IllegalArgumentException("Coordinates must be finite!");
        }
        if (Double.isNaN(x) || Double.isNaN(y)) {
            throw new IllegalArgumentException("Coordinates cannot be NaN!");
        }
        if (x == 0.0) {
            this.x = 0.0;
        } else {
            this.x = x;
        }
        if (y == 0.0) {
            this.y = 0.0;
        } else {
            this.y = y;
        }
    }

    public double x() {
        return this.x;
    }

    public double y() {
        return this.y;
    }

    public double r() {
        return Math.sqrt(x*x + y*y);
    }
    
    
    
    /**
     * https://en.wikipedia.org/wiki/Inverse_trigonometric_functionse
     * http://mathworld.wolfram.com/InverseTangent.html
     * @return
     */
    public double theta() {
    	return Math.atan2(y, x);
    }
    
    private double angleTo(Point2D that) {
    	double dx = that.x - this.x;
    	double dy = that.y - this.y;
    	return Math.atan2(dy, dx);
    }

	public int compareTo(Point2D o) {
		// TODO Auto-generated method stub
		return 0;
	}

    private static class XOrder implements Comparator<Point2D> {
      public int compare(Point2D p, Point2D q) {
          if (p.x < q.x){
            return -1;
          } else if (p.x > q.x) {
            return 1;
          } else {
            return 0;
          }
      }
    }

    private static class YOrder implements Comparator<Point2D> {
      public int compare(Point2D p, Point2D q) {
          if (p.y < q.y){
            return -1;
          } else if (p.y > q.y) {
            return 1;
          } else {
            return 0;
          }
      }
    }

    private static class ROrder implements Comparator<Point2D> {
      public int compare(Point2D p, Point2D q) {
          int delta = (p.x*p.x + p.y*p.y) - (q.x*q.x + q.y*q.y);
          if (delta < 0){
            return -1;
          } else if (delta > 0) {
            return 1;
          } else {
            return 0;
          }
      }
    }

}
