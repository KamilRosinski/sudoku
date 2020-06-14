package sudoku.input;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class InputParserTest {

	@ParameterizedTest
	@MethodSource("inputProvider")
	void shouldParseInput(final String input, final int[] parsed) {

		// when
		final int[] result = InputParser.parseInput(input);

		// then
		Assertions.assertThat(result).isEqualTo(parsed);
	}

	private static Stream<Arguments> inputProvider() {
		return Stream.of(
				Arguments.of("", new int[]{}),
				Arguments.of("123", new int[]{1, 2, 3}),
				Arguments.of("1 2  3", new int[]{1, 2, 3}),
				Arguments.of("1,2 | 3 .4", new int[]{1, 2, 3, 4})
		);
	}

}
