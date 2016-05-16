package org.ict.algorithm.util;

import java.util.Comparator;

/**
 * http://algs4.cs.princeton.edu/12oop/Point2D.java.html
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
     * https://en.wikipedia.org/wiki/Inverse_trigonometric_functions
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
    
    /**
     * Shoelace formula
     * Return twice the signed area of the triangle a-b-c
     * the area size is Math.abs(a.x * b.y + b.x * c.y + c.x * a.y - b.x * a.y - c.x * b.y - a.x * c.y)
     * http://math.stackexchange.com/questions/516219/finding-out-the-area-of-a-triangle-if-the-coordinates-of-the-three-vertices-are
     */
    public static double area2(Point2D a, Point2D b, Point2D c) {
        return a.x * b.y + b.x * c.y + c.x * a.y - b.x * a.y - c.x * b.y - a.x * c.y;
        //equals to Vector.ab and Vector.ac 's cross-product
        //Vab = (b.x - a.x, b.y - a.y),Vac = (c.x - a.x, c.y - a.y)
        //Vab, Vac cross-product is Vab cross-multi Vac:
        //(b.x - a.x)*(c.y - a.y) - (b.y - a.y)*(c.x - a.x)
        //so the above formula can write as this:
        //return (b.x - a.x)*(c.y - a.y) - (b.y - a.y)*(c.x - a.x);
    }

    public static int ccw(Point2D a, Point2D b, Point2D c) {
        double area2 = area2(a, b, c);
        if (area2 < 0)  { 
            return -1;
        } else if (area2 > 0) { 
            return 1;
        } else {
            return 0;
        }
    }

    public double distanceTo(Point2D o) {
        double dx = this.x - o.x;
        double dy = this.y - o.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double distanceSquaredTo(Point2D o) {
        double dx = this.x - o.x;
        double dy = this.y - o.y;
        return dx * dx + dy * dy;
    }

	public int compareTo(Point2D o) {
        if (this.y < o.y) return -1;
        if (this.y > o.y) return 1;
        if (this.x < o.x) return -1;
        if (this.x > o.x) return 1;
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
            double delta = (p.x*p.x + p.y*p.y) - (q.x*q.x + q.y*q.y);
            if (delta < 0){
                return -1;
            } else if (delta > 0) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    private class Atan2Order implements Comparator<Point2D> {
        public int compare(Point2D q1, Point2D q2) {
            double angle1 = angleTo(q1);
            double angle2 = angleTo(q2);
            if (angle1 < angle2) {
                return -1;
            } else if (angle1 > angle2) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    private class PolarOrder implements Comparator<Point2D> {
        public int compare(Point2D q1, Point2D q2) {
            double dx1 = q1.x - x;
            double dx2 = q2.x - x;
            double dy1 = q1.y - y;
            double dy2 = q2.y - y;
            if (dy1 >= 0 && dy2 < 0) {
                //q1 above, q2 below
                return -1;
            } else if (dy2 >= 0 && dy1 < 0) {
                //q1 below, q2 above
                return 1;
            } else if (dy1 == 0 && dy2 == 0) {//horizontal
                if (dx1 >= 0 && dx2 < 0) {
                    //q1 right, q2 left 
                    return -1;
                } else if (dx2 >=0 && dx1 < 0) {
                    //q2 right, q1 left
                    return 1;
                } else {
                    return 0;
                }
            } else { //both above or below 
                //why add negative symbol?
                return -ccw(Point2D.this, q1, q2); 
            }
        }
    }

    private class DistanceToOrder implements Comparator<Point2D> {
        public int compare(Point2D q1, Point2D q2) {
            double dist1 = distanceSquaredTo(q1);
            double dist2 = distanceSquaredTo(q2);
            if (dist1 < dist2) {
                return -1;
            } else if (dist1 > dist2) {
                return 1; 
            } else {
                return 0;
            }
        }
    }
    
    @Override
    public boolean equals(Object that) {
        if (that == null) return false;
        if (that == this) return true;
        if (that.getClass() != this.getClass()) return false;
        Point2D obj = (Point2D)that;
        return (this.x == obj.x) && (this.y == obj.y);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    
    public int hashCode() {
        int hx = ((Double)x).hashCode();
        int hy = ((Double)y).hashCode();
        return hx * 31 + hy;
    }


    
}
