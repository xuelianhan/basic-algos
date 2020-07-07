package org.ict.util;

import java.util.Stack;

import org.json.JSONTokener;

public class JsonParseLeafNode {
	
	public static void main(String[] args) {
		testParseLeafNode();
	}

	public static void testParseLeafNode() {
		String s = "{\"A\":[{\"B\":{\"C\":[{\"D\":[{\"F\":[\"G\"]}],\"E\":[{\"H\":[\"I\"]}]}]}}]}";
		JSONTokener jt = new JSONTokener(s);
		Stack<Character> operator = new Stack<>();
		Stack<Character> operand = new Stack<>();
		boolean skip = true;
		while(jt.more()) {
			char c = jt.next();
			if (c == ',' || c == ':' || c == '"') {
				continue;
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
				operand.push(c);
			}
		}
		System.out.println(operand);
	}
}
