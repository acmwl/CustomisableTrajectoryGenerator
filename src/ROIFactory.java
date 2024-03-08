
//class responsible for managing the ROIs and start-points needed for each walk
//ROIs can depend on the last point of the previous trajectory, and go on from it
//	if we continue from the last available point, we find a new random point in a (predefined) radius around the last, inside of our grid
//	if we have a set of areas or possible RoIs, we can develop a way of selecting a RoI close to the last
public class ROIFactory {
	private Rectangle grid;
	
	public ROIFactory(Rectangle grid) {
		this.grid = grid;
	}
	
	//find a new starting point, inside the grid, preferably outside the previous rectangle
	//radius must be larger than the rectangle in both dimensions
	public Point getNewRadiusStart(Point lastPoint, Rectangle oldRect) {
		double dx = RNG.getRandomDoubleInRadius();
		double dy = RNG.getRandomDoubleInRadius();
		Point newPoint = new Point(lastPoint.getX()+dx, lastPoint.getY()+dy);
		while(oldRect.contains(newPoint) || !grid.contains(newPoint)) {
			dx = RNG.getRandomDoubleInRadius();
			dy = RNG.getRandomDoubleInRadius();
			newPoint = new Point(lastPoint.getX()+dx, lastPoint.getY()+dy);
		}
		return newPoint;
	}
	
	public Point getNewRadiusStart(Trajectory lastTrajectory) {
		return getNewRadiusStart(lastTrajectory.getLastPoint(), lastTrajectory.getRect());
	}
	
	public Rectangle generateROI(Point center) {
		double sizeX = RNG.getRandomRectangleEdge();
		double sizeY = RNG.getRandomRectangleEdge();
		Rectangle newRect = grid.clamp(new Rectangle(
				center.getX()-sizeX/2,
				center.getY()-sizeY/2,
				center.getX()+sizeX/2,
				center.getY()+sizeY/2));
		return newRect;
	}

}
