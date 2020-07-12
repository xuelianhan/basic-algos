package org.ict.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONTokener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;

public class JsonParseLeafNode {

	public static void main(String[] args) {
		String s = "{\"A\":[{\"B\":{\"C\":[{\"D\":[{\"F\":[\"GRE\"]}],\"E\":[{\"H\":[\"IVR\"]}]}]}}],\"J\":[\"AMZ\"],\"Hulu\":[],\"A10001\":{\"Apple\":[],\"A15094\":[\"Google\",\"Facebook\"]}}";
		// String s =
		// "{\"A10001\":{\"A15100\":[],\"A15094\":[\"A131178\",\"A131473\"]}}";
		List<String> operand = testParseLeafNode(s);
		System.out.println(operand);
		List<String> result = testParseLeafNodeV3(s);
		System.out.println(result);
	}

	public static List<String> testParseLeafNodeV3(String s) {
		if (StringUtils.isBlank(s)) {
			return new ArrayList<>();
		}
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = null;
		try {
			rootNode = objectMapper.readTree(s);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		if (rootNode == null) {
			return new ArrayList<>();
		}
		List<String> res = new ArrayList<>();
		traverseV3(rootNode, 1, res);
		return res;
	}

	private static void traverseV3(JsonNode node, int level, List<String> res) {
		if (node.getNodeType() == JsonNodeType.ARRAY) {
			traverseArrayV3(node, level, res);
		} else if (node.getNodeType() == JsonNodeType.OBJECT) {
			traverseObjectV3(node, level, res);
		} else {
			throw new RuntimeException("Not yet implemented");
		}
	}
	
	private static void traverseObjectV3(JsonNode node, int level, List<String> res) {
		node.fieldNames().forEachRemaining((String fieldName) -> {
			JsonNode childNode = node.get(fieldName);
			if (childNode.getNodeType() == JsonNodeType.ARRAY && childNode.get(0) == null) {
				System.out.println("node filedName:" + fieldName + ", child node is:" + childNode.get(0));
				res.add(fieldName);
 			}
			printNodeV3(childNode, fieldName, level, res);
			// for nested object or arrays
			if (traversableV3(childNode)) {
				traverseV3(childNode, level + 1, res);
			}
		});
	}

	private static void traverseArrayV3(JsonNode node, int level, List<String> res) {
		for (JsonNode jsonArrayNode : node) {
			printNodeV3(jsonArrayNode, "arrayElement", level, res);
			if (traversableV3(jsonArrayNode)) {
				traverseV3(jsonArrayNode, level + 1, res);
			}
		}
	}

	private static boolean traversableV3(JsonNode node) {
		return node.getNodeType() == JsonNodeType.OBJECT || node.getNodeType() == JsonNodeType.ARRAY;
	}

	private static void printNodeV3(JsonNode node, String keyName, int level, List<String> res) {
		if (traversable(node)) {
			System.out.printf("%" + (level * 4 - 3) + "s|-- %s=%s type=%s%n", "", keyName, node.toString(), node.getNodeType());
		} else {
			Object value = null;
			if (node.isTextual()) {
				value = node.textValue();
			} else if (node.isNumber()) {
				value = node.numberValue();
			} // todo add more types
			res.add(value.toString());
			System.out.printf("%" + (level * 4 - 3) + "s|-- %s=%s type=%s%n", "", keyName, value, node.getNodeType());
		}
	}

	/**
	 * @see https://www.logicbig.com/tutorials/misc/jackson/tree-model.html
	 * @param s
	 */
	public static void testParseLeafNodeV2(String s) {
		if (StringUtils.isBlank(s)) {
			// return new ArrayList<>();
			return;
		}
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = null;
		try {
			rootNode = objectMapper.readTree(s);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		if (rootNode == null) {
			// return new ArrayList<>();
			return;
		}
		traverse(rootNode, 1);
	}

	private static void traverse(JsonNode node, int level) {
		if (node.getNodeType() == JsonNodeType.ARRAY) {
			traverseArray(node, level);
		} else if (node.getNodeType() == JsonNodeType.OBJECT) {
			traverseObject(node, level);
		} else {
			throw new RuntimeException("Not yet implemented");
		}
	}

	private static void traverseObject(JsonNode node, int level) {
		node.fieldNames().forEachRemaining((String fieldName) -> {
			JsonNode childNode = node.get(fieldName);
			printNode(childNode, fieldName, level);
			// for nested object or arrays
			if (traversable(childNode)) {
				traverse(childNode, level + 1);
			}
		});
	}

	private static void traverseArray(JsonNode node, int level) {
		for (JsonNode jsonArrayNode : node) {
			printNode(jsonArrayNode, "arrayElement", level);
			if (traversable(jsonArrayNode)) {
				traverse(jsonArrayNode, level + 1);
			}
		}
	}

	private static boolean traversable(JsonNode node) {
		return node.getNodeType() == JsonNodeType.OBJECT || node.getNodeType() == JsonNodeType.ARRAY;
	}

	private static void printNode(JsonNode node, String keyName, int level) {
		if (traversable(node)) {
			System.out.printf("%" + (level * 4 - 3) + "s|-- %s=%s type=%s%n", "", keyName, node.toString(),
					node.getNodeType());

		} else {
			Object value = null;
			if (node.isTextual()) {
				value = node.textValue();
			} else if (node.isNumber()) {
				value = node.numberValue();
			} // todo add more types
			System.out.printf("%" + (level * 4 - 3) + "s|-- %s=%s type=%s%n", "", keyName, value, node.getNodeType());
		}
	}

	public static List<String> testParseLeafNode(String s) {
		if (StringUtils.isBlank(s)) {
			return new ArrayList<>();
		}
		JSONTokener jt = new JSONTokener(s);
		Stack<Character> operator = new Stack<>();
		StringBuilder sb = new StringBuilder();
		// Stack<Character> operand = new Stack<>();
		boolean skip = true;
		while (jt.more()) {
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
				// operand.push(c);
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
