package sudoku.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

	private final List<Field> fields = new ArrayList<>();

	public Board(final int... values) {

		if (values.length != 81) {
			throw new IllegalArgumentException("Input vector must contain 81 elements.");
		}

		for (int i = 0; i < 81; ++i) {
			fields.add(values[i] != 0 ? new Field(i, values[i]) : new Field(i));
		}

		for (int row = 0; row < 9; ++row) {
			for (int column = 0; column < 9; ++column) {
				final Field field = getField(row, column);
				field.getColumn().addAll(getFieldsInColumn(column).stream()
						.filter(f -> f != field)
						.collect(Collectors.toList()));
				field.getRow().addAll(getFieldsInRow(row).stream()
						.filter(f -> f != field)
						.collect(Collectors.toList()));
				field.getGrid().addAll(getFieldsInGrid(row / 3, column / 3).stream()
						.filter(f -> f != field)
						.collect(Collectors.toList()));
			}
		}

	}

	public List<Field> getFields() {
		return fields;
	}

	private Field getField(final int row, final int column) {
		return fields.get(9 * row + column);
	}

	private List<Field> getFieldsInRow(final int row) {

		final List<Field> result = new ArrayList<>();
		for (int column = 0; column < 9; ++column) {
			result.add(getField(row, column));
		}

		return result;
	}

	private List<Field> getFieldsInColumn(final int column) {

		final List<Field> result = new ArrayList<>();
		for (int row = 0; row < 9; ++row) {
			result.add(getField(row, column));
		}

		return result;
	}

	private List<Field> getFieldsInGrid(final int gridRow, final int gridColumn) {

		final List<Field> result = new ArrayList<>();
		for (int row = 0; row < 3; ++row) {
			for (int column = 0; column < 3; ++column) {
				result.add(getField(3 * gridRow + row, 3 * gridColumn + column));
			}
		}

		return result;
	}

	public Field getNextInputField(final int startIndex) {
		return fields.subList(startIndex, fields.size()).stream()
				.filter(field -> field.getValue() == 0 /*field.getType() == FieldType.PLAYER_INPUT*/)
				.findFirst()
				.orElse(null);
	}

	@Override
	public String toString() {
		return fields.stream()
				.map(field -> Integer.valueOf(field.getValue()).toString())
				.collect(Collectors.joining(", "));
	}

}
