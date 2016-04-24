package ballmerpeak.sgdiff;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SGDiff {

	public static void main(String... args) throws FileNotFoundException, IOException {
		if (args.length != 2) {
			System.err.println("Usage: sgdiff <expected> <actual>");
			System.exit(1);
		}
		
		String expectedPath = args[0];
		String actualPath = args[1];
		int lineNumber = 1;
		
		try (
				BufferedReader expected = new BufferedReader(new FileReader(expectedPath));
				BufferedReader actual = new BufferedReader(new FileReader(actualPath));
				) {
			String actualLine, expectedLine;
			while ((actualLine = actual.readLine()) != null) {
				expectedLine = expected.readLine();
				if (expectedLine == null || !expectedLine.equals(actualLine)) {
					System.err.format("mismatch at line %d\n", lineNumber);
					System.exit(1);
				}			
				lineNumber++;
			}
			if (expected.readLine() != null) {
				System.err.format("mismatch at line %d\n", lineNumber);
				System.exit(1);
			}
		}
	}
}
