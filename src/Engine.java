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
	
	public void run() {
		try {
		      File myObj = new File("C:\\Users\\acmwl\\Desktop\\maou.txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		    	 FileWriter f = new FileWriter("C:\\Users\\acmwl\\Desktop\\maou.txt");
		    	 f.close();
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		FileWriter fw = null;
		PrintWriter pw = null;
		try{ 
			 fw = new FileWriter("C:\\Users\\acmwl\\Desktop\\maou.txt", true);
			 BufferedWriter bw = new BufferedWriter(fw);
			 pw = new PrintWriter(bw);
		   }
		catch (IOException e) {
			    System.out.println("Unable to open printwriter, exiting\n");
			    e.printStackTrace();
			    System.exit(0);
			}
		//pw.println(""+walkersNum);
		for(int i=0;i<walkersNum;i++) {
			Walker walker = walkerGen.generateWalker();
			walkerGen.exportWalker(walker,i,pw);
		}
		pw.close();
		try {
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static void main(String args[]) {
		Engine engine = new Engine();
		engine.run();
	}
	
}
