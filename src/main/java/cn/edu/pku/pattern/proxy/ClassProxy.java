package cn.edu.pku.pattern.proxy;

/**
 * 
 * @see https://stackoverflow.com/questions/3437897/how-to-get-a-class-instance-of-generics-type-t
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
