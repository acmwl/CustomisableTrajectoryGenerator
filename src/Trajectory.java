
import java.util.ArrayList;


public class Trajectory {
	private ArrayList<Point> points;
	private Rectangle rect; //rectangle of trajectory, represented in xstart ystart, xend yend
	
	public Trajectory() {
		this.points = new ArrayList<Point>();
	}
	
	public Trajectory(ArrayList<Point> points) {
		this.points = new ArrayList<Point>(points);
		this.updateRect();
	}
	
	public Trajectory(Trajectory other) {
		this.points = new ArrayList<Point>(other.getPoints());
		Rectangle newRect = new Rectangle(other.rect);
		this.rect = newRect;
	}
	
	public void addPoint(double x, double y) {
		this.points.add(new Point(x,y));
	}
	
	public void addPoint(Point p) {
		this.points.add(p);
	}
	
	public Trajectory combine(Trajectory other){
		ArrayList<Point> newPoints = new ArrayList<Point>(points);
		newPoints.addAll(other.getPoints());
		return new Trajectory(newPoints);
	}
	
	public void updateRect() {
		double minX=points.get(0).getX(), minY=points.get(0).getY();
		double maxX=points.get(0).getX(), maxY=points.get(0).getY();
		
		for(Point currPoint: points) {
			if(minX>currPoint.getX()){
				minX = currPoint.getX();
			}
			if(minY>currPoint.getY()){
				minY = currPoint.getY();
			}
			if(maxX<currPoint.getX()){
				maxX = currPoint.getX();
			}
			if(maxY<currPoint.getY()){
				maxY = currPoint.getY();
			}
		}
		Rectangle rect = new Rectangle(minX, minY, maxX, maxY);
		this.rect=rect;
	}
	
	public ArrayList<Point> getPoints(){
		return points;
	}
	
	public Point getLastPoint() {
		return points.get(points.size()-1);
	}
	
	public Rectangle getRect() {
		return this.rect;
	}
	
	public String toString() {
		String returnString = "";
		for(int i=0; i<points.size()-1;i++) {
			returnString+= points.get(i)+"\n";
		}
		return returnString+points.get(points.size()-1);
	}
}
