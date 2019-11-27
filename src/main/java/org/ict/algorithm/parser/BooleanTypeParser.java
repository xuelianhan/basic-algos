package org.ict.algorithm.parser;

public class BooleanTypeParser implements TypeParser<Boolean> {

	@Override
	public Boolean parse(String value) {
		return Boolean.valueOf(value);
	}

	@Override
	public Class<Boolean> getParsedType() {
		return Boolean.class;
	}

}
