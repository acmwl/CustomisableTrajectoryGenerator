import java.util.ArrayList;

public class RectangleCell {
	private ArrayList<Integer> neighborIDs;
	private Rectangle rectangle;
	private int ID;
	
	public RectangleCell(Rectangle rectangle) {
		neighborIDs = new ArrayList<Integer>();
		this.rectangle = rectangle;
	}
	
	public RectangleCell(Rectangle rectangle, ArrayList<Integer> neighbors) {
		this.neighborIDs = neighbors;
		this.rectangle = rectangle;
	}
	
	public void addNeighbor(int neighbor) {
		this.neighborIDs.add(neighbor);
	}
	
	public ArrayList<Integer> getNeighborIDs(){
		return neighborIDs;
	}
	
	public int getRandomNeighborID() {
		return neighborIDs.get(RNG.getRandom().nextInt(neighborIDs.size()));
	}
	
	
	public Rectangle getRectangle() {
		return rectangle;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public int getID() {
		return ID;
	}
	
	public String toString() {
		String retString = "";
		retString+=""+ID+": "+rectangle+" - ";
		for(int j : neighborIDs) {
			retString+=";"+j;
		}
		return retString;
	}

}
