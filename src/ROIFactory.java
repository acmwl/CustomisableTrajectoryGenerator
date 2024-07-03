//class responsible for managing the ROIs and start-points needed for each walk
//ROIs can depend on the last point of the previous trajectory, and go on from it
//	if we continue from the last available point, we find a new random point in a (predefined) radius around the last, inside of our grid
//	if we have a set of areas or possible RoIs, we can develop a way of selecting a RoI close to the last


//use a subdivision of the whole layout as a layout for each walker
//Generate that subdivision by doing a bredth first search on the graph and stopping when we have N areas
//We give this subdivision for generation
//This is done every time a new walker is generated
public class ROIFactory {
	private Rectangle grid;
	private LinkedGrid layout;
	private LinkedGrid subdivision;
	private RectangleCell currentRegion;
	private int previousID;
	
	public ROIFactory(Rectangle grid) {
		this.grid = grid;
		if(RNG.layoutMode()) {
			layout = new LinkedGrid();
			if(RNG.restricted()) {
				subdivision = layout.randomSubdivision();
			} else {
				subdivision = layout;
			}
			
		}
	}
	
	public void resetSubdivision() {
		subdivision = layout.randomSubdivision();
	}
	
	//find a new starting point, inside the grid, preferably outside the previous rectangle
	//radius must be larger than the rectangle in both dimensions
	public Point getNewRadiusStart(Point lastPoint, Rectangle oldRect) {
		double dx = RNG.getRandomDoubleInRadius();
		double dy = RNG.getRandomDoubleInRadius();
		Point newPoint = new Point(lastPoint.getX()+dx, lastPoint.getY()+dy);
		while((RNG.checkOutsideOnly() && oldRect.contains(newPoint)) 
				|| !grid.contains(newPoint)) {
			dx = RNG.getRandomDoubleInRadius();
			dy = RNG.getRandomDoubleInRadius();
			newPoint = new Point(lastPoint.getX()+dx, lastPoint.getY()+dy);
		}
		return newPoint;
	}
	
	public Point getNewRadiusStart(Trajectory lastTrajectory) {
		return getNewRadiusStart(lastTrajectory.getLastPoint(), lastTrajectory.getRect());
	}
	
	public Rectangle generateRandomROI(Point center) {
		double sizeX = RNG.getRandomRectangleEdge();
		double sizeY = RNG.getRandomRectangleEdge();
		Rectangle newRect = grid.clamp(new Rectangle(
				center.getX()-sizeX/2,
				center.getY()-sizeY/2,
				center.getX()+sizeX/2,
				center.getY()+sizeY/2));
		return newRect;
	}
	
	public Rectangle getStartingRegion() {
		//ystem.out.println("Here");
		currentRegion = subdivision.getRandomCell();
		previousID = currentRegion.getID();
		return currentRegion.getRectangle();
	}
	
	public Rectangle nextLayoutROI() {
		int newID = currentRegion.getRandomNeighborID();
		if(newID==previousID) {
			//System.out.println("New Roi"+newID+" "+previousID);
			newID = currentRegion.getRandomNeighborID();
		}
		while(subdivision.getCell(newID)==null) {
			newID = currentRegion.getRandomNeighborID();
		}
		previousID=currentRegion.getID();
		currentRegion = subdivision.getCell(newID);
		return currentRegion.getRectangle();
	}

}
