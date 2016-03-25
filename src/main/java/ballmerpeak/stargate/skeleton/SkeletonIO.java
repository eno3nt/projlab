package ballmerpeak.stargate.skeleton;

import java.util.Scanner;

import ballmerpeak.stargate.Direction;

// TODO: Auto-generated Javadoc
/**
 * The Class SkeletonIO.
 */
public class SkeletonIO {
	
	/** The Constant scanner. */
	static final Scanner scanner = new Scanner(System.in);

	/** The indent level. */
	static int indentLevel = 0;
	
	/**
	 * Indent.
	 */
	static void indent() {
		for (int i = 0; i < indentLevel; i++) {
			System.out.print("   ");
		}
	}
	
	/**
	 * Enter.
	 */
	public static void enter() {
		indentLevel++;
	}
	
	/**
	 * Leave.
	 */
	public static void leave() {
		indentLevel--;
	}
	
	/**
	 * Log.
	 *
	 * @param msg the msg
	 */
	public static void log(String msg) {
		indent();
		System.out.println(msg);
	}
	
	/**
	 * Ask.
	 *
	 * @param q the q
	 * @return true, if successful
	 */
	public static boolean ask(String q) {
		while (true) {
			System.out.format("%s [Y/N]\n", q);
			String n = scanner.next();
			switch (n.toLowerCase().charAt(0)) {
			case 'y':
				return true;
			case 'n':
				return false;
			}
		}
	}
	
	/**
	 * Question.
	 *
	 * @param q the q
	 * @return the string
	 */
	public static String question(String q) {
		System.out.println(q);
		return scanner.next();
	}
}
