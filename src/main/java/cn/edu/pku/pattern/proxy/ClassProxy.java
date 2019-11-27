package cn.edu.pku.pattern.proxy;

/**
 * 
 * @see https://stackoverflow.com/questions/3437897/how-to-get-a-class-instance-of-generics-type-t
 * @see https://www.baeldung.com/java-type-casting
 * @see http://www.mathcs.emory.edu/~cheung/Courses/171/Syllabus/2-OOP/convert.html
 *
 */
public class ClassProxy<T> {

	private final Class<T> type;
	
    public ClassProxy(Class<T> type) {
      this.type = type;
    }

    public Class<T> getType() {
      return type;
    }

	public T newInstance() {
		try {
			return type.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
