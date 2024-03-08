import java.lang.Math;

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
		double direction = randomDirection(Math.PI, trajectory.getLastPoint(), roi);
		double distance = randomDistance();
		while(trajectory.getPoints().size()<maxSteps) {
			distance = randomDistance();
			direction = randomDirection(direction,trajectory.getLastPoint(), roi);
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
	
	public double randomDirection(double previousDirection, Point previousPoint, Rectangle bounds) {
		if(!bounds.contains(previousPoint)) {
			Point destinationPoint = Point.randomPoint(bounds);
			return Point.determineDirection(previousPoint, destinationPoint);
		}
		double directionChange = RNG.getRandomDirectionChange();
		return (previousDirection+directionChange)%(2*Math.PI);
	}
	
	
}
