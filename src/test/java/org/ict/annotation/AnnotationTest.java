package org.ict.annotation;


@TestAnnotation(count = 2)
public class AnnotationTest {
	
	public static void main(String[] args) {
		TestAnnotation annotation = AnnotationTest.class.getAnnotation(TestAnnotation.class);
		System.out.println(annotation.count());
	}
}
