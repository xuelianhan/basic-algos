package org.ict.algorithm.parser;

public class TrimmingInputPreprocessor implements InputPreprocessor {

	@Override
	public String prepare(String input, Class<?> targetType) {
		return input == null ? null : input.trim();
	}

}
