import java.io.PrintWriter;
import java.util.ArrayList;

public class WalkerFactory {
	private TrajectoryFactory trajectoryGen;
	private ROIFactory roiGen;
	private Rectangle grid;
	
	public WalkerFactory(Rectangle grid) {
		this.grid = grid;
		this.trajectoryGen = new TrajectoryFactory();
		this.roiGen = new ROIFactory(grid);
	}
	
	public Walker generateWalker() {
		int W = RNG.getRandomNumberOfWalks();
		Walker walker = new Walker(W);
		if(RNG.restricted()) {
			roiGen.resetSubdivision(); 
		}
		for(int i=0;i<RNG.getDays();i++) {
			//System.out.println("Generating walk "+ i);
			addDay(walker);
		}
		return walker;
	}
	
	public void addDay(Walker walker) {
		Point startingPoint;
		if(RNG.layoutMode()) {
			Rectangle startingRegion = roiGen.getStartingRegion();
			startingPoint = startingRegion.getCenter();
		} else {
			startingPoint = new Point(grid.getRandomStart());
		}
		walker.addTrajectory(trajectoryGen.generateInitialTrajectory(startingPoint)); 
		for(int i=0;i<walker.getMaxWalks();i++) { 
			Rectangle newRoi;
			Point newStart;
			Point lastPoint = walker.getLastTrajectory().getLastPoint();
			if(RNG.layoutMode()) {
				newRoi = roiGen.nextLayoutROI();
				newStart = newRoi.getNearbyPoint(lastPoint,newRoi); 
			} else {
				newStart = roiGen.getNewRadiusStart(walker.getLastTrajectory());
				newRoi = roiGen.generateRandomROI(newStart);
			}
			if(lastPoint.distanceTo(newStart)>RNG.getAverageStepDistance()) {
				Trajectory intTrajectory = trajectoryGen.generateIntermediateTrajectory(lastPoint,newStart);
				walker.addTrajectory(intTrajectory);
			}
			Trajectory newTrajectory = trajectoryGen.newTrajectory(newRoi, newStart);
			walker.addTrajectory(newTrajectory);
		}
	}
	

	public void exportWalker(Walker walker, int id, PrintWriter pw) {
		ArrayList<Trajectory> trajectories = walker.getTrajectories();
		for(int i=0; i<trajectories.size();i++) {
			for(int j=0; j<trajectories.get(i).getPoints().size();j++) {
				//pw.println(""+id+",0,"+trajectories.get(i).getPoints().get(j));
				pw.println(""+id+",0,"+trajectories.get(i).getPoints().get(j).toStringNorm());
			}	
		}
		//System.out.println("Successfully wrote to the file.");
	}
}
