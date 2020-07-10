package org.ict.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONTokener;

public class JsonParseLeafNode {
	
	public static void main(String[] args) {
		String s = "{\"A\":[{\"B\":{\"C\":[{\"D\":[{\"F\":[\"GRE\"]}],\"E\":[{\"H\":[\"IVR\"]}]}]}}],\"J\":[\"AMZ\"],\"Hulu\":[],\"A10001\":{\"Apple\":[],\"A15094\":[\"Google\",\"Facebook\"]}}";
		//String s = "{\"A10001\":{\"A15100\":[],\"A15094\":[\"A131178\",\"A131473\"]}}";
		List<String> operand = testParseLeafNode(s);
		System.out.println(operand);
	}
	
	public static List<String> testParseLeafNodeV2(String s) {
		if (StringUtils.isBlank(s)) {
			return new ArrayList<>();
		}
		JSONTokener jt = new JSONTokener(s);
		Stack<Character> operator = new Stack<>();
		StringBuilder sb = new StringBuilder();
		Stack<Character> operand = new Stack<>();
		boolean skip = true;
		while(jt.more()) {
			char c = jt.next();
			if (c == ':') {
				continue;
			}
			if (c == '"') {
				continue;
			}
			if (c == ',') {
				sb.append(c);
			}
			if (c == '[') {
				operator.push(c);
				skip = false;
				continue;
			} else if (c == ']') {
				operator.pop();
				skip = true;
				continue;
			} else if (c == '{') {
				operator.push(c);
				skip = true;
			} else if (c == '}') {
				operator.pop();
				skip = true;
			}
			if (!operator.isEmpty() && !skip) {
				//operand.push(c);
				sb.append(c);
			}
		}
		
		System.out.println(sb.toString());
		String result = sb.toString();
		String[] a = result.split(",");
		System.out.println(Arrays.toString(a));
		return Arrays.asList(a);
	}
	

	public static List<String> testParseLeafNode(String s) {
		if (StringUtils.isBlank(s)) {
			return new ArrayList<>();
		}
		JSONTokener jt = new JSONTokener(s);
		Stack<Character> operator = new Stack<>();
		StringBuilder sb = new StringBuilder();
		//Stack<Character> operand = new Stack<>();
		boolean skip = true;
		while(jt.more()) {
			char c = jt.next();
			if (c == ':') {
				continue;
			}
			if (c == '"') {
				continue;
			}
			if (c == ',') {
				sb.append(c);
			}
			if (c == '[') {
				operator.push(c);
				skip = false;
				continue;
			} else if (c == ']') {
				operator.pop();
				skip = true;
				continue;
			} else if (c == '{') {
				operator.push(c);
				skip = true;
			} else if (c == '}') {
				operator.pop();
				skip = true;
			}
			if (!operator.isEmpty() && !skip) {
				//operand.push(c);
				sb.append(c);
			}
		}
		
		System.out.println(sb.toString());
		String result = sb.toString();
		String[] a = result.split(",");
		System.out.println(Arrays.toString(a));
		return Arrays.asList(a);
	}
}
