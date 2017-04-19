package no.uib.info233.v2017.khu010.oblig3;

/**
 * Class representing a stage...
 * @author khuf010 && xeq003.
 * @version 0.0.1 (09.04.2017).
 *
 */
public class Point {
	
	private final float pointA;
	private final float pointB;
	
	public Point(float pointA, float pointB) {
		this.pointA = pointA;
		this.pointB = pointB;
	}
	
	/**
	 * Gets the score for player A
	 * @return points scored by player A in the game
	 */
	public float getPointA() { return pointA; }
	
	/**
	 * Gets the score for player B
	 * @return points scored by player B in the game
	 */
	public float getPointB() { return pointB; }

}
