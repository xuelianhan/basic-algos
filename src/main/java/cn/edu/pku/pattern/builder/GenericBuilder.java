package cn.edu.pku.pattern.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 
 * @see https://www.sayem.org/generic-builder-pattern/
 * 
 * @param <T>
 */
public class GenericBuilder<T> {

	public static void main(String[] args) {
		Person value = GenericBuilder.of(Person::new)
				.with(Person::setName, "Syed")
				.with(Person::setAge, 50)
				.build();
		
		System.out.println(value.getAge());
		System.out.println(value.getName());
	}

	private final Supplier<T> instantiator;

	private List<Consumer<T>> instanceModifiers = new ArrayList<>();

	public GenericBuilder(Supplier<T> instantiator) {
		this.instantiator = instantiator;
	}

	public static <T> GenericBuilder<T> of(Supplier<T> instantiator) {
		return new GenericBuilder<T>(instantiator);
	}

	public <U> GenericBuilder<T> with(BiConsumer<T, U> consumer, U value) {
		Consumer<T> c = instance -> consumer.accept(instance, value);
		instanceModifiers.add(c);
		return this;
	}

	public T build() {
		T value = instantiator.get();
		instanceModifiers.forEach(modifier -> modifier.accept(value));
		instanceModifiers.clear();
		return value;
	}

	static class Person {

		private String name;
		private int age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}
	}
}
