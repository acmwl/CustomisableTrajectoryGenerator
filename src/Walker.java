import java.util.ArrayList;

public class Walker {
	private ArrayList<Trajectory> trajectories;
	private int maxWalks;
	
	public Walker(int W) {
		this.maxWalks = W;
		this.trajectories = new ArrayList<Trajectory>();
	}

	
	public Trajectory getTrajectory(int pos) {
		return this.trajectories.get(pos);
	}
	
	public void addTrajectory(Trajectory trajectory) {
		trajectories.add(trajectory);
	}
	
	public Point getLastPoint() {
		return this.trajectories.get(this.trajectories.size()-1).getLastPoint();
	}
	
	public Trajectory getLastTrajectory() {
		return this.trajectories.get(this.trajectories.size()-1);
	}
	
	public ArrayList<Trajectory> getTrajectories(){
		return this.trajectories;
	}
	
	public int getMaxWalks() {
		return maxWalks;
	}
	
}
