
public class Rectangle {
	private Point start;
	private Point end;
	
	public Rectangle(double xStart, double yStart, double xEnd, double yEnd) {
		Point start = new Point(xStart, yStart);
		Point end = new Point(xEnd, yEnd);
		this.start = start;
		this.end = end;
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
			//System.out.println("Im out");
			return false;
		}
		return true;
	}
	
	public Point getCenter() {
		return new Point((this.end.getX()-this.start.getX())/2,
				(this.end.getY()-this.start.getY())/2);
	}
	
	public Point getStart() {
		return start;
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
