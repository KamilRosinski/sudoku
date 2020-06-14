package sudoku.input;

public class InputParser {

	public static int[] parseInput(final String input) {
		return input.replaceAll("\\D", "")
				.chars()
				.map(digit -> Integer.parseInt(String.valueOf((char) digit)))
				.toArray();
	}

}
