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
		//lets say starting point is Gx0, Gy0
		Point startingPoint = new Point(grid.getStart());
		walker.addTrajectory(trajectoryGen.generateInitialTrajectory(startingPoint)); 
		while(!walker.isComplete()) {
			Point newStart = roiGen.getNewRadiusStart(walker.getLastTrajectory());
			Rectangle newRoi = roiGen.generateROI(newStart);
			//System.out.println(newRoi);
			Trajectory newTrajectory = trajectoryGen.newTrajectory(newRoi, newStart);
			walker.addTrajectory(newTrajectory);
		}
		return walker;
	}
	

	public void exportWalker(Walker walker, int id, PrintWriter pw) {
		ArrayList<Trajectory> trajectories = walker.getTrajectories();
		for(int i=0; i<trajectories.size();i++) {
			for(int j=0; j<trajectories.get(i).getPoints().size();j++) {
				//pw.println(""+id+",0,"+trajectories.get(i).getPoints().get(j));
				pw.println(""+id+",0,"+trajectories.get(i).getPoints().get(j).toStringNorm());
			}	
		}
		System.out.println("Successfully wrote to the file.");
	}
}
