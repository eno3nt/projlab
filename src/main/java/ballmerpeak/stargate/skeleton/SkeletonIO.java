package ballmerpeak.stargate.skeleton;

import java.util.Scanner;

import ballmerpeak.stargate.Direction;

public class SkeletonIO {
	
	static final Scanner scanner = new Scanner(System.in);

	static int indentLevel = 0;
	
	static void indent() {
		for (int i = 0; i < indentLevel; i++) {
			System.out.print("   ");
		}
	}
	
	public static void enter() {
		indentLevel++;
	}
	
	public static void leave() {
		indentLevel--;
	}
	
	public static void log(String msg) {
		indent();
		System.out.println(msg);
	}
	
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
	
	public static String question(String q) {
		System.out.println(q);
		return scanner.next();
	}
}
