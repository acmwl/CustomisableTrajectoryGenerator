
public class Point {
	private double x;
	private double y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Point() {
		this.x = 0.0;
		this.y = 0.0;
	}
	
	public Point(Point other) {
		this.x = other.x;
		this.y = other.y;
	}
	
	public static Point randomPoint(Rectangle bounds) {
		Point startBounds = bounds.getStart();
		Point endBounds = bounds.getEnd();
		double randomX = startBounds.getX() + 
				(endBounds.getX() - startBounds.getX()) * RNG.getRandom().nextDouble();
		double randomY = startBounds.getY() + 
				(endBounds.getY() - startBounds.getY()) * RNG.getRandom().nextDouble();
		return new Point(randomX, randomY);
	}
	
	public static double determineDirection(Point source, Point destination) {
		double dir =  Math.atan2(destination.getY()-source.getY(), destination.getX()-source.getX());
		//System.out.println("Direction from "+source+" to "+destination+" is "+dir);
		return dir;
	}
	
	public Point traverse(double direction, double distance) {
		double x = Math.cos(direction)*distance;
		double y = Math.sin(direction)*distance;
		return new Point(this.x+x,this.y+y);
	}
	
	public double distanceTo(Point other) {
		return Math.sqrt(Math.pow(x-other.x,2)+Math.pow(y-other.y,2));
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public String toStringNorm(){
		return ""+x/RNG.getRangeX()+","+y/RNG.getRangeY();
	}
	public String toString() {
		return ""+x+","+y;
	}
}
