package sudoku.model;

import java.util.ArrayList;
import java.util.List;

public class Field {

	private final int index;
	private int value;
	private final List<Field> column = new ArrayList<>();
	private final List<Field> row = new ArrayList<>();
	private final List<Field> grid = new ArrayList<>();

	public Field(final int index) {
		this.index = index;
	}

	public Field(final int index, final int value) {
		this.index = index;
		this.value = value;
	}

	public int getIndex() {
		return index;
	}

	public int getValue() {
		return value;
	}

	public void setValue(final int value) {
		this.value = value;
	}

	public List<Field> getColumn() {
		return column;
	}

	public List<Field> getRow() {
		return row;
	}

	public List<Field> getGrid() {
		return grid;
	}

	public int[] generateAllowedValues() {

		final List<Integer> result = new ArrayList<>();
		for (int i = 0; i < 9; ++i) {
			final int value = i + 1;
			if (column.stream().noneMatch(field -> field.value == value)
					&& row.stream().noneMatch(field -> field.value == value)
					&& grid.stream().noneMatch(field -> field.value == value)) {
				result.add(Integer.valueOf(value));
			}
		}

		return result.stream().mapToInt(Integer::intValue).toArray();
	}

}
