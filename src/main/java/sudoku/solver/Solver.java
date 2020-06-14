package sudoku.solver;

import sudoku.model.Board;
import sudoku.model.Field;

public class Solver {

	private final Board board;

	private boolean solved = false;

	public Solver(final Board board) {
		this.board = board;
	}

	public void solve() {
		fillUnambiguousFields();
		bruteForce();
	}

	private void fillUnambiguousFields() {
		boolean lastIteration;
		do {
			lastIteration = true;
			for (final Field field : board.getFields()) {
				if (field.getValue() == 0) {
					final int[] allowedValues = field.generateAllowedValues();
					if (allowedValues.length == 1) {
						lastIteration = false;
						field.setValue(allowedValues[0]);
					}
				}
			}
		} while (!lastIteration);
	}

	private void bruteForce() {
		bruteForce(board.getNextInputField(0));
	}

	private void bruteForce(final Field field) {
		if (field == null) {
			solved = true;
		} else {
			final Field nextField = board.getNextInputField(field.getIndex() + 1);
			for (final int value : field.generateAllowedValues()) {
				field.setValue(value);
				bruteForce(nextField);
				if (solved) {
					break;
				}
				field.setValue(0);
			}

		}
	}

}
