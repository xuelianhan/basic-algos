package org.ict.algorithm.util;

/**
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public final class Point2D implements Comparable<Point2D> {
   
    public static final Comparator<Point2D> X_ORDER = new XOrder();
    
    public static final Comparator<Point2D> Y_ORDER = new YOrder();
    
    public static final Comparator<Point2D> R_ORDER = new ROrder();_

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

	public int compareTo(Point2D o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
