import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Engine {
	private WalkerFactory walkerGen;
	private int walkersNum;
	
	public Engine() {
		//RNG gives a standard grid, it isn't random
		this.walkerGen = new WalkerFactory(RNG.getGrid());
		this.walkersNum = RNG.getWalkerNum();
	}
	
	public void generateWalkers(PrintWriter pw) {
		//pw.println(""+walkersNum);
		for(int i=0;i<walkersNum;i++) {
			System.out.println("Generating Walker "+i);
			Walker walker = walkerGen.generateWalker();
			walkerGen.exportWalker(walker,i,pw);
			pw.flush();
		}
	}
	
	public void createEmptyFile(String filepath) {
		try {
		      File myObj = new File(filepath);
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		    	 FileWriter f = new FileWriter(filepath, false);
		    	 f.close();
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public void createPrintWriter(String filepath) {
		FileWriter fw = null;
		PrintWriter pw = null;
		try{ 
			 fw = new FileWriter(filepath, true);
			 BufferedWriter bw = new BufferedWriter(fw);
			 pw = new PrintWriter(bw);
		   }
		catch (IOException e) {
			    System.out.println("Unable to open printwriter, exiting\n");
			    e.printStackTrace();
			    System.exit(0);
			}
		
		generateWalkers(pw); //generate walkers and print them to pw
		
		pw.close();
		try {
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static void main(String args[]) {
		String filepath = RNG.getOutputFile();
		Engine engine = new Engine();
		engine.createEmptyFile(filepath); //used to overwrite old files (possibly not needed)
		engine.createPrintWriter(filepath);
	}
	
}
