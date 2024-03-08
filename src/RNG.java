import java.util.Random;

public class RNG {
	private static final int NumberOfUsers=2;
	
	private static final double Gx0=10, Gy0=10, Gx1=110, Gy1=110; //grid size
	private static final double WM=20, WS=10, Wmin=0, Wmax=30; // number of walks per user --translates to tau
	private static final double RM=15, RS=5, Rmin=10, Rmax=19; //average size of rectangle --translates to epsilon
	private static final double Radius=30;
	// Below are chances used for mostly normal distributions. Chances are doubles between 0 and 1
	private static final double TM = 50, TS = 30, Tmin=5, Tmax = Integer.MAX_VALUE; //number of steps taken in this trajectory
	private static final double Z = 0.3; //chance of not taking the next step
	private static final double SM = 3,SS = 1, Smin=1, Smax=5, Sn = Smin/10; //distance of next step
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
	
	public static double getRandomRectangleEdge() {
		return stdRandom(RM, RS, Rmin, Rmax);
	}
	
	public static Random getRandom() {
		return r;
	}
}
