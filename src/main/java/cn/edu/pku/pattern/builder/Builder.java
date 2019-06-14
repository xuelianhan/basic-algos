package cn.edu.pku.pattern.builder;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

/**
 * @see https://howtocodetutorial.wordpress.com/generic-builder-pattern-in-java-8/
 * @see https://stackoverflow.com/questions/3204623/java-generics-builder-pattern
 * @see https://www.sayem.org/generic-builder-pattern/
 * @author
 *
 * @param <T>
 */
public class Builder<T> {

	private T instance;

	private boolean ifCon = true;

	public Builder(Class<T> clazz) {
		try {
			instance = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public Builder<T> with(Consumer<T> setter) {
		if (ifCon) {
			setter.accept(instance);
		}
		return this;
	}

	public T get() {
		return instance;
	}

	public static <T> Builder<T> build(Class<T> clazz) {
		return new Builder<>(clazz);
	}

	public Builder<T> If(BooleanSupplier condition) {
		this.ifCon = condition.getAsBoolean();
		return this;
	}

	public Builder<T> endIf() {
		this.ifCon = true;
		return this;
	}

	public static class Sample {
		int id;
		String name;
		List<Integer> list;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List getList() {
			return list;
		}

		public void setList(List list) {
			this.list = list;
		}

		@Override
		public String toString() {
			return String.format("id:%d, name:%s, list:%s", id, name, list);
		}
	}

	public static void main(String[] args) {
		List list = Arrays.asList(1, 2, 3, 4, 5);

		System.out.println(Builder.build(Sample.class)
				.with(s -> s.setId(1))
				.with(s -> s.setName("Sample object"))
				.with(s -> s.setList(list))
				.get());

		// Java Properties
		System.out.println(Builder.build(Properties.class)
				.with(p -> p.put("one", 1))
				.with(p -> p.put("two", 2))
				.get()

		);
		System.out.println(Builder.build(Properties.class)
				.with(p -> p.put("one", 1))
				.If(() -> false) // any expression
				.with(p -> p.put("two", 2))
				.with(p -> p.put("three", 3))
				.endIf()
				.with(p -> p.put("four", 4))
				.get()

		); // output=> {one=1, four=4}
	}

}
