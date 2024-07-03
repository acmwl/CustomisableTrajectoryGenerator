

// Given a rectangle, a walk is generated starting from its center

public class TrajectoryFactory {
	
	//initial trajectory has only one point, the starting one
	public Trajectory generateInitialTrajectory(Point initial) {
		Trajectory trajectory = new Trajectory();
		trajectory.addPoint(initial);
		trajectory.updateRect();
		return trajectory;
	}
	
	public Trajectory newTrajectory(Rectangle roi, Point startPoint) {
		Trajectory trajectory = new Trajectory();
		int maxSteps = RNG.getRandomStepCount();
		trajectory.addPoint(startPoint);
		double direction = randomDirection(Math.PI, trajectory.getLastPoint(), roi, false);
		double distance = randomDistance();
		while(trajectory.getPoints().size()<maxSteps) {
			//System.out.println(trajectory.getPoints().size()-maxSteps);
			distance = randomDistance();
			direction = randomDirection(direction,trajectory.getLastPoint(), roi, false);
			generateStep(trajectory, direction, distance);
		}
		trajectory.updateRect();
		return trajectory;
	}
	
	public Trajectory generateIntermediateTrajectory(Point startPoint, Point targetPoint) {
		Trajectory trajectory = new Trajectory();
		trajectory.addPoint(startPoint);
		double minX = Math.min(startPoint.getX(), targetPoint.getX());
		double maxX = Math.max(startPoint.getX(), targetPoint.getX());
		double minY = Math.min(startPoint.getY(), targetPoint.getY());
		double maxY = Math.max(startPoint.getY(), targetPoint.getY());
		Rectangle roi = new Rectangle(minX,minY,maxX,maxY);
		double direction;
		double distance;
		while(trajectory.getLastPoint().distanceTo(targetPoint)>RNG.getAverageStepDistance()) {
			//System.out.println(trajectory.getLastPoint().distanceTo(targetPoint));
			distance = randomDistance();
			direction = randomDirection(Point.determineDirection(trajectory.getLastPoint(), targetPoint),
					trajectory.getLastPoint(), roi, true);
			generateStep(trajectory, direction, distance);
		}
		trajectory.updateRect();
		return trajectory;
	}
	
	public void generateStep(Trajectory traj, double direction, double distance) {
		//System.out.println("Direction: "+direction+ " Distance: "+distance);
		Point lastPoint = traj.getLastPoint();
		Point newPoint = lastPoint.traverse(direction, distance);
		//System.out.println("New Point"+newPoint+"\n");
		traj.addPoint(newPoint);
	}
	
	public double randomDistance() {
		double distance;
		if(RNG.stepDecision()) { //Z is the chance of NOT taking a step
			distance = RNG.getMinimalStepDistance();
		} else {
			distance = RNG.getRandomStepDistance();
		}
		return distance;
	}
	
	public double randomDirection(double previousDirection, Point previousPoint, Rectangle bounds, boolean tight) {
		if(!bounds.contains(previousPoint) && !tight) {
			Point destinationPoint = Point.randomPoint(bounds);
			return Point.determineDirection(previousPoint, destinationPoint);
		}
		double directionChange = RNG.getRandomDirectionChange();
		if(tight) {
			directionChange/=1.5;
		}
		return (previousDirection+directionChange)%(2*Math.PI);
	}

		
	
}
