import java.lang.Math;

public class Rectangle {
	private Point start;
	private Point end;
	
	public Rectangle(double xStart, double yStart, double xEnd, double yEnd) {
		Point start = new Point(xStart, yStart);
		Point end = new Point(xEnd, yEnd);
		this.start = start;
		this.end = end;
	}
	
	public Rectangle(Point start, Point end) {
		this.start=start;
		this.end=end;
	}
	
	public Rectangle(Rectangle other) {
		Point otherStart = other.getStart();
		Point otherEnd = other.getEnd();
		Point start = new Point(otherStart);
		Point end = new Point(otherEnd);
		this.start = start;
		this.end = end;
	}
	
	//clamp given rectangle inside this one. Return as a new rectangle
	public Rectangle clamp(Rectangle other) {
		Rectangle returnRect = new Rectangle(other);
		if(returnRect.start.getX()<start.getX()) {
			returnRect.start.setX(start.getX());
		}
		if(returnRect.start.getY()<start.getY()) {
			returnRect.start.setY(start.getY());
		}
		if(returnRect.end.getX()>end.getX()) {
			returnRect.end.setX(end.getX());
		}
		if(returnRect.end.getY()>end.getY()) {
			returnRect.end.setY(end.getY());
		}
		return returnRect;
	}
	
	public boolean contains(Point point) {
		double pointX = point.getX();
		double pointY = point.getY();
		if(pointX>this.end.getX() || pointX<this.start.getX() ||
		   pointY>this.end.getY() || pointY<this.start.getY()) {
			return false;
		}
		return true;
	}
	
	public Point getCenter() {
		return new Point((this.end.getX()-this.start.getX())/2 + this.start.getX(),
				(this.end.getY()-this.start.getY())/2 + this.start.getY());
	}
	
	public Point getStart() {
		return start;
	}
	
	public Point getRandomStart() {
		return Point.randomPoint(this);
	}
	
	public Point getNearbyPoint(Point lastPoint, Rectangle newRect) { 
		double x,y;
		if(lastPoint.getX()<=newRect.end.getX() && lastPoint.getX()>=newRect.start.getX()) {
			x = lastPoint.getX();
	    }else if(Math.abs(lastPoint.getX()-newRect.start.getX())<
			Math.abs(lastPoint.getX()-newRect.end.getX())) {
			x = newRect.start.getX();
		} else {
			x = newRect.end.getX();
		}
		if(lastPoint.getY()<=newRect.end.getY() && lastPoint.getY()>=newRect.start.getY()) {
			y = lastPoint.getY();
	    }else if(Math.abs(lastPoint.getY()-newRect.start.getY())<
				Math.abs(lastPoint.getY()-newRect.end.getY())) {
			y = newRect.start.getY();
		} else {
			y = newRect.end.getY();
		}
		return new Point(x,y);
	}
	
	public Point getEnd() {
		return end;
	}
	
	public void setStart(Point start) {
		this.start = start;
	}
	
	public void setEnd(Point end) {
		this.end = end;
	}
	
	public String toString() {
		return "Start: "+start+" End: "+end;
	}
}
