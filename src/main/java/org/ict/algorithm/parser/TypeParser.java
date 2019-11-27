package org.ict.algorithm.parser;

/**
 * 
 * 
 * @see https://codereview.stackexchange.com/questions/38145/parse-strings-and-convert-the-value-to-java-types
 * @see https://codereview.stackexchange.com/users/27386/rafael-winterhalter
 * @see https://stackoverflow.com/questions/24589495/conversion-from-string-to-generic-type
 * @see https://stackoverflow.com/questions/5498776/convert-a-string-to-generic-type?rq=1
 * @param <T>
 */
public interface TypeParser<T> {
	
	 T parse(String value);
	 
	 /**
	  * This requires the interface implementor to name the type explicitly 
	  * and will get rid of all trouble with the reflective soltion
	  * @return
	  */
	 Class<T> getParsedType();
}
