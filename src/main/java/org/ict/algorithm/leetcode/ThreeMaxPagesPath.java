package org.ict.algorithm.leetcode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.ict.algorithm.util.MapUtil;

/**
 * U1,/
 * U1,subscribers
 * U2,/
 * U2,subscribers
 * U1,filter
 * U1,export
 * U2,filter
 * U2,export
 * U3,/
 * The most frequent 3 page sequence:
 * U1:/-->subscribers-->filter-->export
 * U2:/-->subscribers-->filter-->export
 * U3:/
 * 
 * results:
 * /-->subscribers-->filter
 * subscribers-->filter-->export
 * 
 * @author hanxuelian
 *
 */
public class ThreeMaxPagesPath {
	
	public static File createRandomAccessLog() {
		File f = new File("/home/hanxuelian/Desktop/test-access.log");
		if (!f.exists()) {
			try {
				f.createNewFile();
				FileWriter fw = new FileWriter(f);
				BufferedWriter bw = new BufferedWriter(fw);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return f;
	}
	
	

	public static Map<String, Integer> findThreeMaxPagesPathV1(String file, String separator) {
		Map<String, Integer> pageVisitCounts = new HashMap<String, Integer>();
		if (file == null || "".equals(file)) {
			return pageVisitCounts;
		}
		try {
			File f = new File(file);
			FileReader fr = new FileReader(f);
			BufferedReader bf = new BufferedReader(fr);
			
			Map<String, List<String>> userUrls = new HashMap<String, List<String>>();
			String currentLine = "";
			while ((currentLine = bf.readLine()) != null) {
				String[] lineArr = currentLine.split(separator);
				if (lineArr == null || lineArr.length != 2) {
					continue;
				}
				
				String user = lineArr[0];
				String page = lineArr[1];
				List<String> urlLinkedList = null;
				if (userUrls.get(user) == null) {
					urlLinkedList = new LinkedList<String>();
				} else {
					urlLinkedList = userUrls.get(user);
					if (urlLinkedList.size() == 2) {
						String pages = urlLinkedList.get(0) + "_" + urlLinkedList.get(1) + "_" + page;
					    Integer count = (pageVisitCounts.get(pages) == null ? 0 : pageVisitCounts.get(pages))  + 1;
						pageVisitCounts.put(pages, count);
					} else if (urlLinkedList.size() > 2) {
						urlLinkedList.remove(0);
					}
				}
				urlLinkedList.add(page);
				userUrls.put(user, urlLinkedList);
			}
		    bf.close();
		    fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pageVisitCounts;
	}
	
	public static void main(String[] args) {
		String file = "/home/hanxuelian/Desktop/test-access.log";
		String separator = ",";
		Map<String, Integer> pageVisitCounts = findThreeMaxPagesPathV1(file, separator);
		System.out.println(pageVisitCounts.size());
		Map<String, Integer>  result = MapUtil.sortByValueDescendOrder(pageVisitCounts);
		System.out.println(result);
	}
}
