package org.ict.algorithm.util;

import java.util.Locale;
import java.util.Scanner;
import org.ict.algorithm.util.CharSetNameImpl;
import java.io.BufferedInputStream;


public final class StandardIn implements CharSetNameImpl {
	private StandardIn(){}
	
	private static Scanner scanner;

	private static final Locale usLocale = new Locale("en", "US");

	static {
		scanner = new Scanner(new BufferedInputStream(System.in),CharSetNameImpl.UTF_8);
		scanner.useLocale(usLocale);
	}	

}	
