package cn.edu.pku.pattern.parser;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;

public class JsonParseArrayElement {
	
	public static void main(String[] args) {
		String s = "{\"A\":[{\"B\":{\"C\":[{\"D\":[{\"F\":[\"GRE\"]}],\"E\":[{\"H\":[\"IVR\"]}]}]}}],\"J\":[\"AMZ\"],\"Hulu\":[],\"A10001\":{\"Apple\":[],\"A15094\":[\"Google\",\"Facebook\"]}}";
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
		if (traversableV3(node)) {
			System.out.printf("%" + (level * 4 - 3) + "s|-- %s=%s type=%s%n", "", keyName, node.toString(), node.getNodeType());
		} else {
			Object value = null;
			if (node.isTextual()) {
				value = node.textValue();
			} else if (node.isNumber()) {
				value = node.numberValue();
			} 
			// need to add more types here
			res.add(value.toString());
			System.out.printf("%" + (level * 4 - 3) + "s|-- %s=%s type=%s%n", "", keyName, value, node.getNodeType());
		}
	}

}
