package ballmerpeak.stargate.skeleton;

public class SkeletonLogger {

	static int indentLevel = 0;
	
	public static void enter() {
		indentLevel++;
	}
	
	public static void leave() {
		indentLevel--;
	}
	
	public static void log(String msg) {
		for (int i = 0; i < indentLevel; i++) {
			System.out.print("   ");
		}
		System.out.println(msg);
	}
}
