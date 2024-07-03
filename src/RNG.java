import java.util.Random;

public class RNG {
	private static final int NumberOfUsers=10;
	
	// Layout mode loads a layout-graph from a file. Given file must contain rectangles and neighbors
	private static final boolean LAYOUT_MODE = false;
	//private static final String LAYOUT_FILE = "C:\\Users\\acmwl\\Desktop\\GroceryShop\\groceryLayout.csv";
	private static final String LAYOUT_FILE = "/home/lookos/Dropbox/CustomTrajectoryGenerator/shoppingLayout.csv";
	private static final String OUTPUT_FILE = "/home/lookos/Dropbox/CustomTrajectoryGenerator/printable.csv";
	
	private static final int days = 1;
	private static final boolean restricted = false;
	private static final int subdivision = 8;
	
	// Rectangle Generation (do not affect layout mode)
	private static final double Gx0=15, Gy0=15, Gx1=5000, Gy1=5000; //grid size
	private static final double WM=1, WS=0, Wmin=1, Wmax=1;//Integer.MAX_VALUE;//Integer.MAX_VALUE; // number of walks per user 
	private static final double RM=600, RS=100, Rmin=5, Rmax=5000; //average size of rectangle --translates to epsilon
	private static final double Radius=150; //must be greater than Smax (so as to not get stuck outside grid) and greater than (RM+RS)/2
	private static final boolean CheckOutsideOnly=false; // Generate new rectangle (center) only outside of the last - increases spread
	
	// Trajectory Generation
	private static final double TM = 10000, TS = 2000, Tmin=10, Tmax = Integer.MAX_VALUE; //number of steps taken in this trajectory --translates to tau
	private static final double Z = 0.3; //chance of not taking the next step
	private static final double SM = 5,SS = 5, Smin=1, Smax=50, Sn = Smin/10; //distance of next step
	private static final double DM = 0, DS = Math.PI/2; //direction of next step
	
	private static Random r = new Random();
	public static double stdRandom(double m, double s, double min, double max) {
		double result =  r.nextGaussian()*s + m;
		if(result>max) {
			return max;
		}
		else if(result<min) {
			return min;
		}
		return result;
	}
	
	public static int getDays() {
		return days;
	}
	
	public static int getSubdivision() {
		return subdivision;
	}
	
	public static boolean restricted() {
		return restricted;
	}
	
	public static String getOutputFile() {
		return OUTPUT_FILE;
	}
	
	public static int getWalkerNum() {
		return NumberOfUsers;
	}
	
	public static Rectangle getGrid() {
		return new Rectangle(Gx0,Gy0,Gx1,Gy1);
	}
	
	public static int getRandomStepCount() {
		return (int)stdRandom(TM,TS,Tmin, Tmax);
	}
	
	public static boolean stepDecision() {
		return RNG.getRandom().nextDouble(1)<=Z;
	}
	
	public static double stdRandom(double m, double s) {
		return r.nextGaussian()*s + m;
	}
	
	public static double getMinimalStepDistance() {
		return Sn;
	}
	
	public static double getAverageStepDistance() {
		return SM;
	}
	
	public static double getRandomStepDistance() {
		return stdRandom(SM, SS, Smin, Smax);
	}
	
	public static double getRandomDirectionChange() {
		return stdRandom(DM,DS);
	}
	
	public static int getRandomNumberOfWalks() {
		return (int)stdRandom(WM, WS, Wmin, Wmax);
	}
	
	public static double getRandomDoubleInRadius() {
		return getRandom().nextDouble(-Radius, Radius);
	}
	
	public static double getRadius() {
		return Radius;
	}
	
	public static double getRandomRectangleEdge() {
		return stdRandom(RM, RS, Rmin, Rmax);
	}
	
	public static boolean checkOutsideOnly() {
		return CheckOutsideOnly;
	}
	
	public static Random getRandom() {
		return r;
	}
	
	public static double getRangeX() {
		return Gx1-Gx0;
	}
	
	public static double getRangeY() {
		return Gy1-Gy0;
	}
	
	public static boolean layoutMode() {
		return LAYOUT_MODE;
	}
	
	public static String getLayoutFile(){
		return LAYOUT_FILE;
	}
}
