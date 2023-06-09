package com.sss.resources.designcomposition.fastgeometry;

/**
 * PARTICIPANTS: DO NOT MODIFY.
 *
 * This class represents a third-party dependency. As such, Amazon would have no control
 * over it.
 *
 * This class is provided as an example; it does not actually perform fast geometrical
 * calculations without security issues. It is a stand-in representing code that we do
 * not control, but that we want to enhance and/or modify.
 *
 * Again, DO NOT MODIFY THIS CODE.
 */
public class MITPoint2D {
    // DO NOT MODIFY
    protected double x;
    protected double y;
    // DO NOT MODIFY

    /**
     * Constructor taking an initial position for the point.
     *
     * DO NOT MODIFY!
     * @param x The x position of the point.
     * @param y The y position of the point.
     */
    public MITPoint2D(double x, double y) {
        // DO NOT MODIFY
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the current X location of the point.
     * DO NOT MODIFY!
     * @return the current X location of the point.
     */
    public double getX() {
        // DO NOT MODIFY
        return this.x;
    }

    /**
     * Returns the current Y location of the point.
     * DO NOT MODIFY!
     * @return the current Y location of the point.
     */
    public double getY() {
        // DO NOT MODIFY
        return this.y;
    }

    /**
     * Updates the point's location to the provided location.
     * DO NOT MODIFY!
     * @param x The new X location for the point.
     * @param y The new Y location for the point.
     */
    public void setLocation(double x, double y) {
        // DO NOT MODIFY
        this.x = x;
        this.y = y;
    }

    /**
     * Updates the point's location by adding the amounts provided.
     * DO NOT MODIFY!
     * @param x The X distance and direction to move the point.
     * @param y The Y distance and direction to move the point.
     */
    public void transform(double x, double y) {
        // DO NOT MODIFY
        this.x += x;
        this.y += y;
    }

    /**
     * Finds the distance in the XY plane between this point and the given point.
     *
     * In theory, this would use some kind of fast algorithm, avoiding
     * the Math.sqrt() altogether. I'm don't have that magic, so it actually just
     * uses the standard Pythagorean theorem.
     *
     * DO NOT MODIFY!
     * @param p The point to calculate distance to.
     * @return the distance in the XY plane between this point and the given point.
     */
    public double distanceTo(MITPoint2D p) {
        // Imagine magic happening here
        // DO NOT MODIFY
        double deltaX = p.getX() - this.x;
        double deltaY = p.getY() - this.y;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
