import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Map;
import java.util.Hashtable;

public class LinkedGrid {
	private Map<Integer, RectangleCell> cells;
	
	public LinkedGrid() {
		this.cells = new Hashtable<Integer, RectangleCell>();
		readFile(RNG.getLayoutFile());
	}
	
	public LinkedGrid(Map<Integer, RectangleCell> cells) {
		this.cells = cells;
	}
	
	public LinkedGrid randomSubdivision() { //TODO
		// starting from a random rectangle, traverse the grid going breadth first
		// when reaching the desired number of cells (subdivision of total cells) we return the new grid
		Map<Integer, RectangleCell> subdivision = new Hashtable<Integer, RectangleCell>();
		RectangleCell start = getRandomCell();
		subdivision.put(start.getID(), start);
		traverse(subdivision, start);
		//print subdivision
		/*
		for(Integer i : subdivision.keySet()) {
			System.out.println(subdivision.get(i));
		}*/
		//System.out.println(subdivision.size());
		return new LinkedGrid(subdivision);
	}
	
	private void traverse(Map<Integer, RectangleCell> subdivision, RectangleCell cell) {
		if(subdivision.size()>cells.size()/RNG.getSubdivision()) {
			return;
		}
		if(subdivision.get(cell.getID()) == null) {
			subdivision.put(cell.getID(), cell);
		}
		for(int neighborid : cell.getNeighborIDs()) {
			if(subdivision.get(neighborid) != null) {
				continue;
			}
			traverse(subdivision, cells.get(neighborid));
		}
	}
	
	public void addCell(RectangleCell newCell) {
		cells.put(newCell.getID(),newCell);
	}
	
	public RectangleCell getRandomCell() {
		int randomIndex = RNG.getRandom().nextInt(cells.size());
		//System.out.println(cells.size());
		//System.out.println(randomIndex);
		int i = 0;
		for(Integer obj : cells.keySet())
		{
		    if (i == randomIndex)
		        return cells.get(obj);
		    i++;
		}
		return null;
	}
	
	public RectangleCell getCell(int id) {
		return cells.get(id);
	}

	
	public void readFile(String inputfile) {
		try {
		      File myObj = new File(inputfile);
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        if(!data.isBlank()) {
		        	parseLine(data);
		        }
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		/*
		for(int i=0;i<cells.size();i++) {
			System.out.println(cells.get(i));
		}
		*/
	}
	
	public void parseLine(String line) {
		String delimiter = ",";
		String[] elements = line.split(delimiter);
		Rectangle rectangle = new Rectangle(Integer.parseInt(elements[1]),Integer.parseInt(elements[2]),
				Integer.parseInt(elements[3]),Integer.parseInt(elements[4]));
		RectangleCell cell = new RectangleCell(rectangle);
		cell.setID(Integer.parseInt(elements[0]));
		for(int i=5;i<elements.length; i++) {
			cell.addNeighbor(Integer.parseInt(elements[i]));
		}
		this.addCell(cell);
	}
	
	public String toString() {
		String retString = "";
		for(int i=0;i<cells.size();i++) {
			retString+=cells.get(i)+"\n";
		}
		return retString;
	}
	
}
